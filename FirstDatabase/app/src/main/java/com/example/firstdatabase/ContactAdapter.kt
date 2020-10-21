package com.example.firstdatabase

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ContactAdapter(private val contacts: List<ContactData>): RecyclerView.Adapter<ContactAdapter.ContactViewHolder>() {

    class ContactViewHolder(private val view: View) : RecyclerView.ViewHolder(view){

        fun onBind(contact: ContactData){
            val textviewName = view.findViewById<TextView>(R.id.textview_name)
            val textviewLastName = view.findViewById<TextView>(R.id.textview_lastName)
            val textviewAge = view.findViewById<TextView>(R.id.textview_Age)

            textviewName.text = contact.name
            textviewLastName.text = contact.lastName
            textviewAge.text = contact.age.toString()
        }

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.contact,parent,false)
        return ContactViewHolder(view)
    }

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        val contact = contacts[position]
        holder.onBind(contact)
    }

    override fun getItemCount(): Int {
        return contacts.size
    }

}