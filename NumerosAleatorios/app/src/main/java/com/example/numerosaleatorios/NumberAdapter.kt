package com.example.numerosaleatorios

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class NumberAdapter(val numbers: List<Int>) : RecyclerView.Adapter<NumberAdapter.NumberHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NumberHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.number_text,parent,false)

        return NumberHolder(view)
    }

    override fun onBindViewHolder(holder: NumberHolder, position: Int) {
        val number = numbers[position]
        holder.onBind(number)
    }

    override fun getItemCount(): Int {
        return numbers.size
    }
    class NumberHolder (val view: View) : RecyclerView.ViewHolder(view){
        fun onBind(number: Int){
            val textView = view as TextView
            textView.text = number.toString()
        }
    }
}