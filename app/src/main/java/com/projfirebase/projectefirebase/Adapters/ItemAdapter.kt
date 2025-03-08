package com.projfirebase.projectefirebase.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.projfirebase.projectefirebase.Model.Items
import com.projfirebase.projectefirebase.R

class ItemAdapter(
    private val itemList: List<Items>,
    private val onItemClick: (Items) -> Unit
) : RecyclerView.Adapter<ItemAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.items_cardview, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = itemList[position]
        holder.textViewName.text = item.name
        holder.textViewCraftable.text = item.craftable.toString()
        holder.textViewStackable.text = item.stackable.toString()
        holder.textViewStackLimit.text = item.stack_limit.toString()

        holder.itemView.setOnClickListener {
            onItemClick(item)
        }
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textViewName: TextView = itemView.findViewById(R.id.textViewName)
        val textViewCraftable: TextView = itemView.findViewById(R.id.textViewCraftable)
        val textViewStackable: TextView = itemView.findViewById(R.id.textViewStackable)
        val textViewStackLimit: TextView = itemView.findViewById(R.id.textViewStackLimit)
    }
}
