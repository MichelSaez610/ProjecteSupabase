package com.projfirebase.projectefirebase.ViewModel

import androidx.lifecycle.ViewModel
import io.github.jan.supabase.auth.Auth
import io.github.jan.supabase.auth.auth
import io.github.jan.supabase.auth.providers.builtin.Email
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.postgrest.Postgrest

class LoginSignInModel : ViewModel() {

    val supabase = createSupabaseClient(
        supabaseUrl = "https://ahmxhphcdywcrxyehrea.supabase.co",
        supabaseKey = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6ImFobXhocGhjZHl3Y3J4eWVocmVhIiwicm9sZSI6ImFub24iLCJpYXQiOjE3Mzk3ODkyNjUsImV4cCI6MjA1NTM2NTI2NX0.js8rw7i2Js05OmawwiUEyU9RmwVJNgAZ3Qr3lUnksE8"
    ) {
        install(Auth)
        install(Postgrest)
    }

    suspend fun register(userMail: String, userPassword: String) {
        supabase.auth.signUpWith(Email) {
            email = userMail
            password = userPassword
        }
    }

    suspend fun login(userMail: String, userPassword: String) {
        supabase.auth.signInWith(Email) {
            email = userMail
            password = userPassword
        }
    }

}