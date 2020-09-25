package com.example.formaciones

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class FenomenoAdapter(private val animals: List<Fenomeno>) : RecyclerView.Adapter<FenomenoAdapter.FenomenoHolder>() {

    class FenomenoHolder(private val view: View) : RecyclerView.ViewHolder(view){
        fun onBind(fenomeno: Fenomeno){
            val image = view.findViewById<ImageView>(R.id.image)
            val title = view.findViewById<TextView>(R.id.title)
            val content = view.findViewById<TextView>(R.id.content)

            image.setImageResource(fenomeno.image)
            title.text = fenomeno.title
            content.text = fenomeno.content

        }

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FenomenoHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.card_fenomenos,parent,false)
        return FenomenoHolder(view)
    }

    override fun onBindViewHolder(fenomenoHolder: FenomenoHolder, position: Int) {
        val fenomeno = animals[position]
        fenomenoHolder.onBind(fenomeno)
    }

    override fun getItemCount(): Int {
        return animals.size
    }

}