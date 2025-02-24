package com.projfirebase.projectefirebase.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.projfirebase.projectefirebase.Model.Items
import com.projfirebase.projectefirebase.R

class ItemAdapter(private val Ilist: List<Items>):
    RecyclerView.Adapter<ItemAdapter.ViewHolder>() {

    // create new views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // inflates the card_view_design view
        // that is used to hold list item
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.items_cardview, parent, false)

        return ViewHolder(view)
    }

    // binds the list items to a view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val item = Ilist[position]

        holder.textViewNameLabel.text = item.name
        holder.textViewCraftable.text = item.craftable.toString()
        holder.textViewStackable.text = item.stackable.toString()
        holder.textViewStackLimit.text = item.stack_limit.toString()


        holder.itemView.setOnClickListener{ view ->
            view.findNavController().navigate(R.id.action_llistatItems_to_updateFragment)
        }
    }

    // return the number of the items in the list
    override fun getItemCount(): Int {
        return Ilist.size
    }

    // Holds the views for adding it to image and text
    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val textViewNameLabel: TextView = itemView.findViewById(R.id.textViewNameLabel)
        val textViewCraftable: TextView = itemView.findViewById(R.id.textViewCraftable)
        val textViewStackable: TextView = itemView.findViewById(R.id.textViewStackable)
        val textViewStackLimit: TextView = itemView.findViewById(R.id.textViewStackLimit)
    }
}