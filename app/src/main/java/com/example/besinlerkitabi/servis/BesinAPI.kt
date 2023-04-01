package com.example.besinlerkitabi.servis

import com.example.besinlerkitabi.model.Besin
import io.reactivex.Single
import retrofit2.Call
import retrofit2.http.GET

interface BesinAPI {
    //GET,POST BİRŞEYLERİ CEKECEKSEK GET YOLLAYACAKSAK POSTU KULLANIRIZ

    //https://raw.githubusercontent.com/atilsamancioglu/BTK20-JSONVeriSeti/master/besinler.json

    //BASE_URL->https://raw.githubusercontent.com/
    //atilsamancioglu/BTK20-JSONVeriSeti/master/besinler.json


    @GET("atilsamancioglu/BTK20-JSONVeriSeti/master/besinler.json")
   // fun getBesin() : Call<List<Besin>>  // bu şekilde yazsak yeterli fakat rxjava ile çalışacağımız için farklı şekkilde yapıcagız
    //rxjavanın farklı secenekleri mevcut: flowable,observable,single,maybe gibi
    //single veriyi birkez alır ve durur
    fun getBesin(): Single<List<Besin>>

}