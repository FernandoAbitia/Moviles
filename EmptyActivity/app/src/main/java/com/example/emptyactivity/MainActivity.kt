package com.example.emptyactivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    private val TAG = "MainActivity"
    private lateinit var logView: TextView
    //var si cambiará de valor

    private fun addLog(message: String){
        val text = logView.text

        logView.text = text.toString() + message +"\n"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        logView = findViewById(R.id.logView)

        Log.d(TAG,"Se llamó a OnCreate")

        addLog("Se llamó a onCreateeee")
    }

    override fun onStart(){
        super.onStart()
        Log.d(TAG,"Se llamó a OnStart")
        addLog("Se llamó a onStarttt")
    }

    override fun onResume(){
        super.onResume()
        Log.d(TAG,"Se llamó a OnResume")
        addLog("Se llamó a onResumeee")
    }

    override fun onPause(){
        super.onPause()
        Log.d(TAG,"Se llamó a OnPause")
        addLog("Se llamó a onPauseee")
    }

    override fun onStop(){
        super.onStop()
        Log.d(TAG,"Se llamó a OnStop")
        addLog("Se llamó a onStoppp")

    }

    override fun onDestroy(){
        super.onDestroy()
        Log.d(TAG,"Se llamó a OnDestroy")

    }

}