package com.projfirebase.projectefirebase.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.projfirebase.projectefirebase.R
import com.projfirebase.projectefirebase.ViewModel.LoginSignInModel
import com.projfirebase.projectefirebase.databinding.FragmentSignInBinding
import kotlinx.coroutines.runBlocking

class SignIn : Fragment() {

    private lateinit var loginSignInModel: LoginSignInModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = FragmentSignInBinding.inflate(inflater, container, false)

        loginSignInModel = LoginSignInModel()

        binding.button.setOnClickListener{
            val email: String = binding.editTextText2.text.toString()
            val password: String = binding.editTextTextPassword.text.toString()

            runBlocking {
                loginSignInModel.register(email, password)
            }

            findNavController().navigate(R.id.action_signIn_to_login)
        }

        return binding.root
    }

}