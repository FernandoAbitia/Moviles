package com.example.formaciones

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view)
        val fenomenoAdapter = FenomenoAdapter(createFenomenos())

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter=fenomenoAdapter

        fenomenoAdapter.notifyDataSetChanged()
    }

    fun createFenomenos(): List<Fenomeno>{
        val fenomenos = mutableListOf<Fenomeno>()

        fenomenos.add(
            Fenomeno(
                R.drawable.ic_aurora,
                "Aurora Boreal",
                "Fenómeno atmosférico que consiste en la aparición en el cielo de manchas y columnas luminosas de varias tonalidades y que es producido por la radiación solar.",
            )
        )

        fenomenos.add(
            Fenomeno(
                R.drawable.ic_hotspring,
                "Aguas Termales",
                "Aguas con una elevada cantidad de minerales que brotan del suelo de manera natural y a una temperatura que supera en 5°C.",
            )
        )

        fenomenos.add(
            Fenomeno(
                R.drawable.ic_rio,
                "Rio",
                "Corriente natural de agua que fluye permanentemente y va a desembocar en otra, en un lago o en el mar.",
            )
        )

        return fenomenos
    }
}