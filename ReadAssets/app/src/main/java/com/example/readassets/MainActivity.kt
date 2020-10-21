package com.example.readassets

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.layout_content.*
import java.io.BufferedReader

class MainActivity : AppCompatActivity() {
        private lateinit var textViewLastContent: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textViewLastContent = findViewById(R.id.textview_lasttext)

        val contents = readContents()

        val contentAdapter = ContentAdapter(clickListener)

        val recyclerAssets = findViewById<RecyclerView>(R.id.recycler_assets)
        recyclerAssets.adapter = contentAdapter
        recyclerAssets.layoutManager = LinearLayoutManager(this)
        contents?.apply {
            contentAdapter.setData(this)
            val sharedPreferences = getSharedPreferences(DEFAULT_SHARED_PREFERENCES,Activity.MODE_PRIVATE)
            val text = sharedPreferences.getString(LAST_TEXT, contents[0])
            textViewLastContent.text = text
        }

    }
    override fun onPause(){
        super.onPause()
        val sharedPreferences = getSharedPreferences("default", Activity.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString(LAST_TEXT, textViewLastContent.text.toString())
            .apply()
    }

    private  val clickListener = View.OnClickListener { view ->
        val textView = view as TextView
        textViewLastContent.text = textView.text
    }

    private fun readContents() = assets.list("contents")?.map{file ->
        assets.open("contents/$file").bufferedReader().use(BufferedReader::readText)
    }?.toList()
}