package com.example.besinlerkitabi.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.besinlerkitabi.databinding.BesinRecyclerRowBinding
import com.example.besinlerkitabi.model.Besin
import com.example.besinlerkitabi.util.gorselIndir
import com.example.besinlerkitabi.util.placeHolderYap
import com.example.besinlerkitabi.view.BesinListesiFragmentDirections

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

        holder.itemView.setOnClickListener { //ilgili görsele tıklandıgında ne olsun demek
            val action = BesinListesiFragmentDirections.actionBesinListesiFragmentToBesinDetayiFragment(besinListesi.get(position).uuid)
            Navigation.findNavController(it).navigate(action)
        }
        holder.itemBinding.imageView.gorselIndir(besinListesi.get(position).besinGorsel,
            placeHolderYap(holder.itemView.context)
        )

    }
    fun besinListesiniGuncelle(yeniBesinListesi: List<Besin>){//bu fonsiyonla listeye yeni birşey eklenirse yenilenecek
        besinListesi.clear()
        besinListesi.addAll(yeniBesinListesi)
        notifyDataSetChanged()//adapterin içinde olduğumuz için adapter.notifyDataSetChanged dememize gerek yok


    }
}