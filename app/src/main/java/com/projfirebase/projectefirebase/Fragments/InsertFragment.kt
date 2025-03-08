package com.projfirebase.projectefirebase.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.projfirebase.projectefirebase.R
import com.projfirebase.projectefirebase.ViewModel.SharedViewModel
import com.projfirebase.projectefirebase.databinding.FragmentInsertBinding
import kotlinx.coroutines.runBlocking

class InsertFragment : Fragment() {

    private lateinit var sharedViewModel: SharedViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = FragmentInsertBinding.inflate(inflater,container,false)

        sharedViewModel = ViewModelProvider(requireActivity()).get(SharedViewModel::class.java)

        binding.InsertButton.setOnClickListener{
            val craftable = binding.CraftableCheck.isChecked
            val stackable = binding.StackableCheck.isChecked
            val itemName = binding.insertName.text.toString()
            val stackLimit = binding.InsertStackLimit.text.toString().toInt()

            runBlocking {
                sharedViewModel.insertItem(itemName, craftable, stackable, stackLimit)
                Toast.makeText(context, "Item Inserted" + stackable, Toast.LENGTH_SHORT).show()
                findNavController().navigate(R.id.action_insertFragment_to_llistatItems)
            }
        }

        return binding.root
    }

}