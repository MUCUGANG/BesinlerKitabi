package com.example.besinlerkitabi.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.besinlerkitabi.databinding.FragmentBesinDetayiBinding
import com.example.besinlerkitabi.model.Besin
import com.example.besinlerkitabi.util.gorselIndir
import com.example.besinlerkitabi.util.placeHolderYap
import com.example.besinlerkitabi.viewmodel.BesinDetayiViewModel
import com.example.besinlerkitabi.viewmodel.BesinListesiViewModel


class BesinDetayiFragment : Fragment() {
    private val viewModel by viewModels<BesinDetayiViewModel>()
    private var besinId = 0
    private lateinit var binding:FragmentBesinDetayiBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(

        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentBesinDetayiBinding.inflate(layoutInflater)
        return binding.root
        //return inflater.inflate(R.layout.fragment_besin_detayi, container, false)



    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let {
            besinId = BesinDetayiFragmentArgs.fromBundle(it).besinId
            println(besinId)
        }
        viewModel.roomVerisiniAl(besinId)
        observeLiveData()

    }




    fun observeLiveData(){
        viewModel.besinLiveData.observe(viewLifecycleOwner, Observer {besin ->
        besin?.let {
            binding.besinIsim.text  = it.besinIsim
            binding.besinKalori.text = it.besinKalori
            binding.besinProtein.text = it.besinProtein
            binding.besinYag.text = it.besinYag
            binding.besinKarbonhidrat.text = it.besinKarbonhidrat
            context?.let {
                binding.besinImage.gorselIndir(besin.besinGorsel, placeHolderYap(it))
            }

        }

        })
    }
}