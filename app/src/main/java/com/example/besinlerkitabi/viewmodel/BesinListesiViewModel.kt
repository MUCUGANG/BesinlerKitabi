package com.example.besinlerkitabi.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.besinlerkitabi.model.Besin

class BesinListesiViewModel : ViewModel() {
    val besinler = MutableLiveData<List<Besin>>()
    val besinHataMesajı = MutableLiveData<Boolean>()//hata varsa hata mesajı gösterecek yoksa göstermeyecek
    val besinYukleniyor = MutableLiveData<Boolean>()//progress barı yukleniyorsa gösterecek yoksa göstermeyecek


    fun refreshData(){
        val muz = Besin("Muz","100","10","1","www.test.com")
        val cilek = Besin("Çilek","200","20","10","www.test.com")
        val elma = Besin("Elma","300","30","15","www.test.com")

        val besinlistesi = arrayListOf<Besin>(muz,cilek,elma)

        besinler.value  = besinlistesi
        besinHataMesajı.value = false
        besinYukleniyor.value = false
        //

    }

}