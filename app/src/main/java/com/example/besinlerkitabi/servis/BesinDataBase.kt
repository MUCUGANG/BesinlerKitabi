package com.example.besinlerkitabi.servis

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.besinlerkitabi.model.Besin
//birden fazla entities olabileceği için array içerisinde vermemizi istiyor
@Database(entities = arrayOf(Besin::class), version = 1)//veri tabını ile bir değişikliğe gidersek
// bu versiyonu degistirmemizgerekebilir.
abstract class BesinDataBase : RoomDatabase() {
    abstract fun besinDao() : BesinDAO

    //Singleton ile çalışmak istediğimiz için bu kodları ekliyoruz
    companion object {
        //threadler veyahut coroutineslar ile çalışmıyorken eklemeye gerek yok
        //farklı threadleri görünür yaptığımız için bunu kayarız
      @Volatile  private var instance : BesinDataBase? = null
        private val lock = Any()//sadece any sınıfndan bir kilit oluşturuyoruz
        //daha önceden bu invoke fonsiyonu çağrıldı mı onu kontrol ediyoruz
        // eğer çağırıldıysa zaten oluşturduğumuz instanceyi döndür diyoruz
        //çağırlmadıysa yenisin oluştur diyoruz
        operator fun invoke(context: Context) = instance ?: synchronized(lock){//bizden bir kilit istiyor
            instance  ?: databaseOlustur(context).also {
                instance = it
            }
        }

        private fun databaseOlustur(context: Context) = Room.databaseBuilder(
            context.applicationContext, BesinDataBase::class.java, "besindatabase").build()

    }
}