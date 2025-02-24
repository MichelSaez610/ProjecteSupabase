package com.projfirebase.projectefirebase.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.projfirebase.projectefirebase.Adapters.ItemAdapter
import com.projfirebase.projectefirebase.ViewModel.CRUDModel
import com.projfirebase.projectefirebase.databinding.FragmentLlistatItemsBinding
import kotlinx.coroutines.runBlocking

class LlistatItems : Fragment() {

    private lateinit var crudModel: CRUDModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = FragmentLlistatItemsBinding.inflate(inflater, container, false)
        crudModel = CRUDModel()

        binding.RecyclerViewItems.layoutManager = LinearLayoutManager(requireContext())

        binding.RecyclerViewItems.adapter = ItemAdapter(runBlocking { crudModel.llistarItems() })


        return binding.root
    }

}