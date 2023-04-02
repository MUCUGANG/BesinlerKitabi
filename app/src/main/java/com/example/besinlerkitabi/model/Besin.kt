package com.example.besinlerkitabi.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

// room a tanıtmak için entity columnInfo ve primary key koymamız lazım
@Entity//boyle yaparak sınıfı Entity olarak işaretliyoruz
// ve sqlite a bir tablo olarak kaydedilmek için hazyır hale geliyor.
data class Besin(
    @ColumnInfo(name = "isim")
    @SerializedName("isim")
    val besinIsim: String?,
    @ColumnInfo(name = "kalori")
    @SerializedName("kalori")
    val besinKalori : String?,
    @ColumnInfo(name = "karbonhidrat")
    @SerializedName("karbonhidrat")
    val besinKarbonhidrat : String?,
    @ColumnInfo(name = "yag")
    @SerializedName("yag")
    val besinYag : String?,
    @ColumnInfo(name = "protein")
    @SerializedName("protein")
    val besinProtein : String,
    @ColumnInfo(name = "gorsel")
    @SerializedName("gorsel")
    val besinGorsel : String?
) {
    @PrimaryKey(autoGenerate = true) //otamatik olarak ıd oluştur dedik ve değişkene atıyoruz sonra
    var uuid : Int = 0
}