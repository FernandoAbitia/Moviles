package com.fgonzalezh.myfirstroomdb.views

import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.fgonzalezh.myfirstroomdb.R
import com.fgonzalezh.myfirstroomdb.adapters.FavoritesAdapter
import com.fgonzalezh.myfirstroomdb.models.entities.Content
import com.fgonzalezh.myfirstroomdb.viewmodels.ActivityAddContactViewModel
import com.google.android.material.button.MaterialButton

class ActivityAddContact : AppCompatActivity() {

    private val activityAddContactViewModel: ActivityAddContactViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_contact)

        val recyclerViewFavorites = findViewById<RecyclerView>(R.id.recyclerView_favorites)
        recyclerViewFavorites.layoutManager = LinearLayoutManager(this)

        activityAddContactViewModel.getAllContent().observe(this, {contents ->
            val favoritesAdapter = FavoritesAdapter(contents)
            recyclerViewFavorites.adapter = favoritesAdapter

            favoritesAdapter.notifyDataSetChanged()
        })

        val  buttonSave = findViewById<MaterialButton>(R.id.button_save)
        buttonSave.setOnClickListener{
            val name = findViewById<TextView>(R.id.textinput_name).text.toString()
            val lastName = findViewById<TextView>(R.id.textinput_lastname).text.toString()
            val age = findViewById<TextView>(R.id.textinput_age).text.toString().toInt()

            val content = Content(
                name = name,
                lastName = lastName,
                age = age
            )

            activityAddContactViewModel.insertContent(content).observe(
                this, {succesful ->
                    if (succesful){
                    Toast.makeText(this,"Guardado exitoso", Toast.LENGTH_LONG).show()
                    finish()
                    }else{
                        Toast.makeText(this,"No se pudo guardar", Toast.LENGTH_LONG).show()
                    }
                }
            )
        }
    }

}