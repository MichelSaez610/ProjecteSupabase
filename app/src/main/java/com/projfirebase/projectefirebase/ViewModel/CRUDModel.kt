package com.projfirebase.projectefirebase.ViewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.projfirebase.projectefirebase.Model.Items
import io.github.jan.supabase.auth.Auth
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.postgrest.Postgrest
import io.github.jan.supabase.postgrest.from
import io.github.jan.supabase.postgrest.query.Count

class CRUDModel: ViewModel() {

    val supabase = createSupabaseClient(
        supabaseUrl = "https://ahmxhphcdywcrxyehrea.supabase.co",
        supabaseKey = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6ImFobXhocGhjZHl3Y3J4eWVocmVhIiwicm9sZSI6ImFub24iLCJpYXQiOjE3Mzk3ODkyNjUsImV4cCI6MjA1NTM2NTI2NX0.js8rw7i2Js05OmawwiUEyU9RmwVJNgAZ3Qr3lUnksE8"
    ) {
        install(Auth)
        install(Postgrest)
    }

    suspend fun llistarItems(): List<Items> {

        val items = supabase.from("items").select().decodeList<Items>()

        Log.i("TAULA: ", items.toString())

        return items
    }

}