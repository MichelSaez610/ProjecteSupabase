package com.projfirebase.projectefirebase.ViewModel

import android.content.ClipData.Item
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.projfirebase.projectefirebase.Model.Items
import io.github.jan.supabase.auth.Auth
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.postgrest.Postgrest
import io.github.jan.supabase.postgrest.from
import io.github.jan.supabase.postgrest.query.Columns
import io.github.jan.supabase.postgrest.query.Count
import io.github.jan.supabase.postgrest.query.Order

class SharedViewModel: ViewModel() {

    val supabase = createSupabaseClient(
        supabaseUrl = "https://ahmxhphcdywcrxyehrea.supabase.co",
        supabaseKey = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6ImFobXhocGhjZHl3Y3J4eWVocmVhIiwicm9sZSI6ImFub24iLCJpYXQiOjE3Mzk3ODkyNjUsImV4cCI6MjA1NTM2NTI2NX0.js8rw7i2Js05OmawwiUEyU9RmwVJNgAZ3Qr3lUnksE8"
    ) {
        install(Auth)
        install(Postgrest)
    }

    // LiveData to hold the list of items
    private val _items = MutableLiveData<List<Items>>()
    val items: LiveData<List<Items>> get() = _items

    // LiveData to hold a single item
    private val _item = MutableLiveData<Items?>()
    val item: LiveData<Items?> get() = _item

    // Fetch all items
    suspend fun llistarItems() {

        val result = supabase.from("items").select().decodeList<Items>()

        Log.i("TAULA: ", items.toString())

        _items.value = result
    }

    // Fetch a single item by ID
    suspend fun getItemById(itemId: Int) {
        val result = supabase.from("items")
            .select(columns = Columns.list("id", "name", "craftable", "stackable", "stack_limit")) {
                filter {
                    eq("id", itemId)
                }
            }.decodeList<Items>()

        Log.i("ITEM: ", result.toString())

        _item.value = result.firstOrNull()
    }

    suspend fun insertItem(itemName: String, itemCraftable: Boolean, ItemStackable: Boolean, itemStackLimit: Int) {
        val lastID = supabase.from("items").select(columns = Columns.list("id", "name", "craftable", "stackable", "stack_limit")) {
            order("id", order = Order.DESCENDING)
            limit(1)
        }.decodeSingle<Items>()

        val newItem = Items(
            id = lastID.id.toString().toInt() + 1,
            name = itemName,
            craftable = itemCraftable,
            stackable = ItemStackable,
            stack_limit = itemStackLimit
        )

        supabase.from("items").insert(newItem)
    }

    suspend fun updateItem(itemId: Int,ItemName: String, StackLimit: Int) {
        supabase.from("items").update({
            set("name", ItemName)
            set("stack_limit", StackLimit)
        }) {
            filter {
                eq("id", itemId)
            }
        }
        val updatedItems = supabase.from("items").select().decodeList<Items>()
        Log.i("Updated Items", updatedItems.toString())
        _items.value = updatedItems
    }

    suspend fun deleteItem(itemId: Int) {
        supabase.from("items").delete{
            filter {
                eq("id", itemId)
            }
        }
    }
}