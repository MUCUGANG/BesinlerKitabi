package com.example.besinlerkitabi.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.besinlerkitabi.model.Besin
import com.example.besinlerkitabi.servis.BesinDataBase
import kotlinx.coroutines.launch

class BesinDetayiViewModel(application: Application) : BaseViewModel(application) {
    val besinLiveData = MutableLiveData<Besin>()

    fun roomVerisiniAl(uuid : Int){  //aldığı id ile roomda veriyi çekiyor
        launch {
            val dao = BesinDataBase(getApplication()).besinDao()
            val besin = dao.getBesin(uuid)
            besinLiveData.value = besin
        }


    }

}