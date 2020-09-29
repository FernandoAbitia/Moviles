package com.example.formularios

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.CheckBox
import android.widget.CompoundButton
import android.widget.Switch
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    private lateinit var chkJazz : CheckBox
    private lateinit var chkSalsa : CheckBox
    private lateinit var chkCountry : CheckBox
    private lateinit var textGeneros : TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val switchHabilitar = findViewById<Switch>(R.id.switch_habilitar)
        val textHabilitar = findViewById<TextView>(R.id.text_habilitar)
        switchHabilitar.setOnCheckedChangeListener{ button, checked ->
            if (checked)
                textHabilitar.text = "Habilitado"
            else
                textHabilitar.text = "Deshabilitado"

        }

        chkJazz = findViewById(R.id.chkJazz)
        chkSalsa = findViewById(R.id.chkSalsa)
        chkCountry = findViewById(R.id.chkCountry)
        textGeneros = findViewById(R.id.text_generos)

        chkJazz.setOnCheckedChangeListener(changeChecked)
        chkSalsa.setOnCheckedChangeListener(changeChecked)
        chkCountry.setOnCheckedChangeListener(changeChecked)

    }

    private val changeChecked = CompoundButton.OnCheckedChangeListener { button, checked ->

        var texto = ""

        if (chkJazz.isChecked)
            texto+="Jazz\n"

        if (chkSalsa.isChecked)
            texto+="Salsa\n"

        if (chkCountry.isChecked)
            texto+="Country\n"

        textGeneros.text = texto
    }
}