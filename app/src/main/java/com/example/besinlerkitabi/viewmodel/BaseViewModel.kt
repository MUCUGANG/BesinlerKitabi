package com.example.besinlerkitabi.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlin.coroutines.CoroutineContext

//couroutine fonksiyonlarını yazacagımız, couroutines temellerini atacağımız
// ve sonradan bunu diğer viewv modelleri uygulacağız
//butun uygulamaya uyarlıyacağımız için AndroidViewModeli kullanırız
open class BaseViewModel(application: Application) : AndroidViewModel(application),CoroutineScope  {


    private val job = Job()
    override val coroutineContext: CoroutineContext
    get() = job + Dispatchers.Main //arka planda ne yapılıyorsa yapılıp main e dönülecek ve mainde işleler devam edecek

    override fun onCleared() {
        super.onCleared()
        job.cancel()
    //view model ile ilgili işlemler bitince onunda iptal edilemsi gerektigini soyluyoruz
    }


}