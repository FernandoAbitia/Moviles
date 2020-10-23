package com.fgonzalezh.myfirstroomdb.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.fgonzalez.myfirstroomdb.interfaces.BottomDialogListener
import com.fgonzalezh.myfirstroomdb.R
import com.fgonzalezh.myfirstroomdb.models.entities.Content
import com.fgonzalezh.myfirstroomdb.views.MainActivity

class ContentAdapter(private val contents: List<Content>, val bottomDialogListener: BottomDialogListener) : RecyclerView.Adapter<ContentAdapter.ContentAdapterViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContentAdapterViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.contact, parent, false)

        return ContentAdapterViewHolder(view)
    }

    override fun onBindViewHolder(holder: ContentAdapterViewHolder, position: Int) {
        val content = contents[position]
        holder.onBind(content)
    }

    override fun getItemCount(): Int {
        return contents.size
    }

    inner class ContentAdapterViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        fun onBind(content: Content) {
            val textViewName = view.findViewById<TextView>(R.id.textview_name)
            val textViewLastName = view.findViewById<TextView>(R.id.textview_lastname)
            val textViewAge = view.findViewById<TextView>(R.id.textview_age)

            textViewName.text = content.name
            textViewLastName.text = content.lastName
            textViewAge.text = content.age.toString()

            view.setOnClickListener{
                bottomDialogListener.onClick(content.id!!)
            }
        }
    }
}