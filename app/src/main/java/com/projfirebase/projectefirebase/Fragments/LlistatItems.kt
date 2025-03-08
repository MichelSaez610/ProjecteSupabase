package com.projfirebase.projectefirebase.Fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.projfirebase.projectefirebase.Adapters.ItemAdapter
import com.projfirebase.projectefirebase.R
import com.projfirebase.projectefirebase.ViewModel.SharedViewModel
import com.projfirebase.projectefirebase.databinding.FragmentLlistatItemsBinding
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class LlistatItems : Fragment() {

    private lateinit var sharedViewModel: SharedViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        sharedViewModel = ViewModelProvider(requireActivity()).get(SharedViewModel::class.java)

        // Fetch all items
        lifecycleScope.launch {
            sharedViewModel.llistarItems()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = FragmentLlistatItemsBinding.inflate(inflater, container, false)

        sharedViewModel = ViewModelProvider(requireActivity()).get(SharedViewModel::class.java)

        // Fetch all items
        lifecycleScope.launch {
            sharedViewModel.llistarItems()
        }

        // Observe the items LiveData
        sharedViewModel.items.observe(viewLifecycleOwner) { items ->
            binding.RecyclerViewItems.layoutManager = LinearLayoutManager(requireContext())
            binding.RecyclerViewItems.adapter = ItemAdapter(items) { item ->
                val bundle = bundleOf("ItemID" to item.id)
                findNavController().navigate(R.id.action_llistatItems_to_manageItemFragment, bundle)
            }
        }

        binding.buttonInsert.setOnClickListener{
            findNavController().navigate(R.id.action_llistatItems_to_insertFragment)
        }

        return binding.root
    }

}