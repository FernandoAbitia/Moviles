package com.example.proyectocardviewrecycler

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class DestinoAdapter(private val destinos: List<Destino>) : RecyclerView.Adapter<DestinoAdapter.DestinoHolder>() {

    class DestinoHolder(private val view: View) : RecyclerView.ViewHolder(view){
        fun onBind(destino: Destino){
            val image = view.findViewById<ImageView>(R.id.image)
            val title = view.findViewById<TextView>(R.id.title)

            image.setImageResource(destino.image)
            title.text = destino.title

        }

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DestinoHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.card_destinos,parent,false)
        return DestinoHolder(view)
    }

    override fun onBindViewHolder(destinoHolder: DestinoHolder, position: Int) {
        val destino = destinos[position]
        destinoHolder.onBind(destino)
    }

    override fun getItemCount(): Int {
        return destinos.size
    }

}