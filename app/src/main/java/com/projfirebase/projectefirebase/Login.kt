package com.projfirebase.projectefirebase

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.projfirebase.projectefirebase.ViewModel.LoginSignInModel
import com.projfirebase.projectefirebase.databinding.FragmentLoginBinding
import kotlinx.coroutines.runBlocking

class Login : Fragment() {

    private lateinit var loginSignInModel: LoginSignInModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = FragmentLoginBinding.inflate(inflater, container, false)

        loginSignInModel = LoginSignInModel()

        binding.buttonSignIn.setOnClickListener{
            findNavController().navigate(R.id.action_login_to_signIn)
        }

        binding.buttonLogIn.setOnClickListener{
            val email: String = binding.emailEditText.text.toString()
            val password: String = binding.passwordEditText.text.toString()

            runBlocking {
                loginSignInModel.login(email,password)
            }

            findNavController().navigate(R.id.action_login_to_llistatItems)
        }

        return binding.root
    }

}