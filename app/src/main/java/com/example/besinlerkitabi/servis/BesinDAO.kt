package com.example.besinlerkitabi.servis

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.besinlerkitabi.model.Besin
@Dao//bunun bir dao olduğunu buraya gelip yazmamız lazım
interface BesinDAO {

    //Data Access Object


    @Insert
    suspend fun  insertAll(vararg besin: Besin) : List<Long>
    //Insert -> Room, insert into
    //suspend -> coroutine scope // bu fonksşyon içerisinde durdurulup başlatılabiliyor
    //vararg -> birden fazla ve istediğimiz sayıda besin objesini vermemize olanak tanıyor
    // List<Long> -> long döndürmesinin sebebi modelde ki besin içerisine yazdığımız idler

    //veri çekme
    @Query("SELECT * FROM besin")
    suspend fun getAllBesin() : List<Besin>//besin içerisindeki tüm besinleri çeker

    //id ile tek bir veri çekme
    //SELECT * FROM besin WHERE uuid=:besinId
    @Query("SELECT * FROM besin WHERE uuid =:besinId")//bir ıd istiyoruz ve tek ıd ile işlemi tamamlıyoruz
    suspend fun getBesin(besinId : Int) : Besin


   // @Query("SELECT * FROM recipe WHERE id=:recipeId")
   // suspend fun isRecipeSaved(recipeId: Int): RecipeUI

    //veri silme
    @Query("DELETE FROM besin")//besinin içindeki tüm herşeyi siler
    suspend fun deleteAllBesin()

    //tek bir id ile veri silme
   // @Query("SELECT FROM besin uuid = : besinId")
    //suspend fun deleteBesin(besinId: Int) : Besin

}