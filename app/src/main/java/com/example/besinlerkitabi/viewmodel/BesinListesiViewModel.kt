package com.example.besinlerkitabi.viewmodel

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.besinlerkitabi.model.Besin
import com.example.besinlerkitabi.servis.BesinAPIServis
import com.example.besinlerkitabi.servis.BesinDataBase

import com.example.besinlerkitabi.util.OzelSharedPreferences
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.launch
import kotlinx.coroutines.newSingleThreadContext

class BesinListesiViewModel(application: Application) : BaseViewModel(application) {
    val besinler = MutableLiveData<List<Besin>>()
    val besinHataMesajı = MutableLiveData<Boolean>()//hata varsa hata mesajı gösterecek yoksa göstermeyecek
    val besinYukleniyor = MutableLiveData<Boolean>()//progress barı yukleniyorsa gösterecek yoksa göstermeyecek
    private var guncellemeZamani = 0.2 * 60 * 1000 * 1000 * 1000L //dakikanın nano tıme a çevrilmiş hali

    private val besinApiServis = BesinAPIServis()
    private val disposable = CompositeDisposable()//kullan at manasına gelir
    private val ozelSharedPreferences = OzelSharedPreferences(getApplication())

    fun refreshData(){
        val kaydedilmeZamani = ozelSharedPreferences.zamaniAl()
        if(kaydedilmeZamani != null && kaydedilmeZamani != 0L && System.nanoTime() - kaydedilmeZamani < guncellemeZamani) {//system.nanotime güncel zamanı verir
            //sqliteden çek
            verileriSQLitetanAl()
        }else{
            verileriInternettenAl()
        }

    /*
VERİLERİ İNTERNETTEN ÇEKECEĞİMİZ İÇİNBU ORNEK OLARAK YAPTIĞIMIZ VERİLERE İHTİYACIMIZ YOK
        val muz = Besin("Muz","100","10","1","5","www.test.com")
        val cilek = Besin("Çilek","200","20","10","4","www.test.com")
        val elma = Besin("Elma","300","30","15","3","www.test.com")

        val besinlistesi = arrayListOf<Besin>(muz,cilek,elma)

        besinler.value  = besinlistesi
        besinHataMesajı.value = false
        besinYukleniyor.value = false
        */

    }
    private  fun verileriSQLitetanAl(){
        launch {
          val besinListesi = BesinDataBase(getApplication()).besinDao().getAllBesin()
            besinleriGoster(besinListesi)
            Toast.makeText(getApplication(),"Besinleri Room dan aldık",Toast.LENGTH_LONG).show()
        }

    }
    private fun verileriInternettenAl(){
        besinYukleniyor.value = true //progress barı gözüksün diye true

        //IO veri alış verişinde kullandığımız threadlerdir,DEFAULT threadeler var

        disposable.add(
            besinApiServis.getData()


                .subscribeOn(Schedulers.newThread())//arka planda yapacagımızı soylememiz lazım
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<List<Besin>>(){
                    //absract class olduğu için object olarak ekledik
                    override fun onSuccess(t: List<Besin>) {
                        //Başarılı olursa
                        sqliteSakla(t)
                        Toast.makeText(getApplication(),"Besinleri internetten den aldık",Toast.LENGTH_LONG).show()
                    }

                    override fun onError(e: Throwable) {
                        //Hata alırsak
                        besinHataMesajı.value = true
                        besinYukleniyor.value = false
                        e.printStackTrace()//hatayı logcatte görmemize olanak tanır
                    }

                })

        )
    }

private fun besinleriGoster(besinlerListesi : List<Besin>){
    besinler.value = besinlerListesi
    besinHataMesajı.value = false     // VERİ ÇEKME İŞLEMLERİNİ BURADA YAPTIK
    besinYukleniyor.value = false

}
    private fun sqliteSakla(besinListesi: List<Besin>){
        launch {
            val dao = BesinDataBase(getApplication()).besinDao()
            dao.deleteAllBesin()
            val uuidListesi = dao.insertAll(*besinListesi.toTypedArray())
        //*ben tek  istedim sen array verdin diye hata veriyordu ve bizde başına yıldız ekledik
            var i = 0
            while (i < besinListesi.size){
                besinListesi[i].uuid = uuidListesi[i].toInt()//modelin içerisinde idlere ulaşabilmek için yaptık
                i = i + 1
                besinleriGoster(besinListesi)
            }
        }
        ozelSharedPreferences.zamaniKaydet(System.nanoTime())
    }

}