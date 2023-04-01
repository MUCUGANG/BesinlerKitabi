package com.example.besinlerkitabi.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.besinlerkitabi.adapter.BesinRecyclerAdapter
import com.example.besinlerkitabi.databinding.FragmentBesinListesiBinding
import com.example.besinlerkitabi.viewmodel.BesinListesiViewModel


class BesinListesiFragment : Fragment() {

    private val recyclerBesinAdapter = BesinRecyclerAdapter(arrayListOf())
    private val viewModel by viewModels<BesinListesiViewModel>()//view modeli kurduktan sonra buuradan init ediyoruz.

    fun abc(){

    }

    private lateinit var binding : FragmentBesinListesiBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBesinListesiBinding.inflate(layoutInflater)
        return binding.root
    //return inflater.inflate(R.layout.fragment_besin_listesi, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

       viewModel.refreshData()
        //binding.besinListRecycler.layoutManager = LinearLayoutManager(context)//exmlden yaptık
        binding.besinListRecycler.adapter = recyclerBesinAdapter
        observeLiveData()

    }

    fun observeLiveData(){
        viewModel.besinler.observe(viewLifecycleOwner, Observer {besinler ->
            besinler?.let {
                binding.besinListRecycler.visibility = View.VISIBLE
                recyclerBesinAdapter.besinListesiniGuncelle(besinler)
            }
        })

        viewModel.besinHataMesajı.observe(viewLifecycleOwner, Observer {hata->
            hata?.let {
                if (it){
                    binding.besinHataMesaji.visibility = View.VISIBLE
                    binding.besinListRecycler.visibility = View.GONE
                }else{
                    binding.besinHataMesaji.visibility = View.GONE
                }
            }
        })
        viewModel.besinYukleniyor.observe(viewLifecycleOwner, Observer { yukleniyor->
            yukleniyor?.let {
                if (it){
                    binding.besinListRecycler.visibility = View.GONE
                    binding.besinHataMesaji.visibility = View.GONE
                    binding.besinYukleniyor.visibility = View.VISIBLE
                }else{
                    binding.besinYukleniyor.visibility = View.GONE
                }
            }

        })

    }

}