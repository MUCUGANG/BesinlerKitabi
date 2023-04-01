package com.example.besinlerkitabi.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.besinlerkitabi.model.Besin
import com.example.besinlerkitabi.servis.BesinAPIServis
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.newSingleThreadContext

class BesinListesiViewModel : ViewModel() {
    val besinler = MutableLiveData<List<Besin>>()
    val besinHataMesajı = MutableLiveData<Boolean>()//hata varsa hata mesajı gösterecek yoksa göstermeyecek
    val besinYukleniyor = MutableLiveData<Boolean>()//progress barı yukleniyorsa gösterecek yoksa göstermeyecek

    private val besinApiServis = BesinAPIServis()
    private val disposable = CompositeDisposable()//kullan at manasına gelir

    fun refreshData(){
        verileriInternettenAl()
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
                        besinler.value = t
                        besinHataMesajı.value = false     // VERİ ÇEKME İŞLEMLERİNİ BURADA YAPTIK
                        besinYukleniyor.value = false
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



}