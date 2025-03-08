package com.projfirebase.projectefirebase.Fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.projfirebase.projectefirebase.R
import com.projfirebase.projectefirebase.ViewModel.SharedViewModel
import com.projfirebase.projectefirebase.databinding.FragmentManageitemBinding
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class ManageItemFragment : Fragment() {

    private lateinit var sharedViewModel: SharedViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = FragmentManageitemBinding.inflate(inflater, container, false)

        sharedViewModel = ViewModelProvider(requireActivity()).get(SharedViewModel::class.java)

        val itemID = arguments?.getInt("ItemID")
        Log.i("Bundle RESULT", itemID.toString())

        if (itemID != null) {
            lifecycleScope.launch {
                sharedViewModel.getItemById(itemID)
            }

            // Observe the item LiveData
            sharedViewModel.item.observe(viewLifecycleOwner) { item ->
                if (item != null) {
                    binding.editName.setText(item.name)
                    binding.editStackLimit.setText(item.stack_limit.toString())
                } else {
                    Toast.makeText(requireContext(), "Item not found", Toast.LENGTH_SHORT).show()
                }
            }
        }

        binding.updateButton.setOnClickListener{
            val name = binding.editName.text.toString()
            val stackLimit = binding.editStackLimit.text.toString().toInt()
            Log.i("NAME", name)
            Log.i("Stacklimeit", stackLimit.toString())
            if (itemID != null) {
                runBlocking {
                    sharedViewModel.updateItem(itemID, name, stackLimit)
                }
            }
            findNavController().navigate(R.id.action_updateFragment_to_llistatItems)
        }

        binding.deleteButton.setOnClickListener{
            if (itemID != null) {
                runBlocking {
                    sharedViewModel.deleteItem(itemID)
                }
            }
            findNavController().navigate(R.id.action_updateFragment_to_llistatItems)
        }



        return binding.root
    }

}