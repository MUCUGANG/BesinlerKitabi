package com.example.besinlerkitabi.util

import android.content.Context
import android.widget.ImageView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.besinlerkitabi.R

/*fun String.benimEklentim(parametre : String){
    println(parametre)
}
*/
fun ImageView.gorselIndir(url : String?,placeholder : CircularProgressDrawable){
//resim yuklenme esnasında progress bar gibi yükleniyor görseli oluşturur
    val options = RequestOptions().placeholder(placeholder).error(R.mipmap.ic_launcher_round)//ic louncher hazır yuvarlak koyar çalışmazsa diye
    Glide.with(context).setDefaultRequestOptions(options).load(url).into(this)
}

fun placeHolderYap(context : Context) : CircularProgressDrawable{
    return CircularProgressDrawable(context).apply {
        strokeWidth = 10f // ne kadar geniş olacağını belirler
        centerRadius = 40f //çapın ne kadar olacıgını belirler
        start()
    }
}