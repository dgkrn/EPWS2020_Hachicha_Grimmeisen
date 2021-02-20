package com.example.eca2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.contact_item.view.*

data class Contacts (val name : String, val id : Int)

class ContacAdapter( var contacts : List<Contacts> ) : RecyclerView.Adapter<ContacAdapter.ContactViewHolder>() {

    inner class ContactViewHolder (itemView : View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.contact_item, parent,false)
        return ContactViewHolder(view)
    }

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        holder.itemView.apply {
            tvTitle.text = contacts[position].name
            tvContactID.text = contacts[position].id.toString()
        }
    }

    override fun getItemCount(): Int {
        return contacts.size
    }
}