package com.example.caching_data_api.overview

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.caching_data_api.databinding.FragmentOverviewBinding

class OverviewFragment : Fragment() {

    private lateinit var viewModel : OverviewViewModel
    private lateinit var binding : FragmentOverviewBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        viewModel = ViewModelProvider(this).get(OverviewViewModel::class.java)
        binding = FragmentOverviewBinding.inflate(inflater)

        binding.setLifecycleOwner(this)
        binding.viewModel = viewModel

        var viewAdapter = ItemAdapter()
        binding.recyclerView.adapter = viewAdapter

        viewModel.items.observe(this, Observer { list->
            viewAdapter.submitList(list)
        })

        return binding.root

    }

}