package com.example.besinlerkitabi.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.besinlerkitabi.databinding.FragmentBesinDetayiBinding
import com.example.besinlerkitabi.model.Besin


class BesinDetayiFragment : Fragment() {
    private var besinId = 0
    private lateinit var binding:FragmentBesinDetayiBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(

        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
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


    }
}