package com.example.besinlerkitabi.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.besinlerkitabi.databinding.BesinRecyclerRowBinding
import com.example.besinlerkitabi.model.Besin

class BesinRecyclerAdapter (val besinListesi : ArrayList<Besin>):RecyclerView.Adapter<BesinRecyclerAdapter.BesinViewHolder>() {
    class BesinViewHolder(val itemBinding : BesinRecyclerRowBinding) : RecyclerView.ViewHolder(itemBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BesinViewHolder {
        //besin reycler row u burada inflater kullanarak bağlıyoruz
        val inflater = LayoutInflater.from(parent.context)
        val itemBinding  = BesinRecyclerRowBinding.inflate(inflater,parent,false)

        return BesinViewHolder(itemBinding)
    }

    override fun getItemCount(): Int {//recycler viewde kaçtane row olacagını belirliyoruz
        return besinListesi.size
    }

    override fun onBindViewHolder(holder: BesinViewHolder, position: Int) {
        holder.itemBinding.isim.text = besinListesi.get(position).besinIsim
        holder.itemBinding.kalori.text = besinListesi.get(position).besinKalori
        //görsel kısmı eklenecek
    }
    fun besinListesiniGuncelle(yeniBesinListesi: List<Besin>){//bu fonsiyonla listeye yeni birşey eklenirse yenilenecek
        besinListesi.clear()
        besinListesi.addAll(yeniBesinListesi)
        notifyDataSetChanged()//adapterin içinde olduğumuz için adapter.notifyDataSetChanged dememize gerek yok


    }
}