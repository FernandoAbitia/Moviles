package com.example.mascotas

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view)
        val animalAdapter = AnimalAdapter(createAnimals())

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter=animalAdapter

        animalAdapter.notifyDataSetChanged()
    }

    fun createAnimals(): List<Animal>{
        val animals = mutableListOf<Animal>()

        animals.add(
                Animal(
                    R.drawable.ic_pajaro,
                    "Pajaro",
                    "Ave común y abundante en cualquier parte del mundo",
                )
        )

        animals.add(
            Animal(
                R.drawable.ic_tiburon,
                "Tiburon",
                "Animal marino de dieta carnívora",
            )
        )

        animals.add(
            Animal(
                R.drawable.ic_lobo,
                "Lobo",
                "Depredador terrestre",
            )
        )

        return animals
    }
}