package com.fgonzalezh.myfirstroomdb.views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.fgonzalezh.myfirstroomdb.R
import com.fgonzalezh.myfirstroomdb.adapters.ContentAdapter
import com.fgonzalezh.myfirstroomdb.viewmodels.MainActivityViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fabAdd = findViewById<FloatingActionButton>(R.id.fab_add_contact)
        fabAdd.setOnClickListener {
            val intent = Intent(this, ActivityAddContact::class.java)
            startActivity(intent)
        }

        val recyclerView = findViewById<RecyclerView>(R.id.recycler_contacts)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val viewModel : MainActivityViewModel by viewModels()
        viewModel.getContents().observe(this, { contents ->
            val contentAdapter = ContentAdapter(contents)

            recyclerView.adapter = contentAdapter

            contentAdapter.notifyDataSetChanged()
        })
    }
}