package com.example.proyectocardviewrecycler

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    private lateinit var textNombre : EditText
    private lateinit var textEdad : EditText
    private lateinit var textTel : EditText
    private lateinit var textMail : EditText
    private lateinit var switchFrecuente : Switch
    private lateinit var radioGenero : RadioButton
    private lateinit var checkFinlandia: CheckBox
    private lateinit var checkChina: CheckBox
    private lateinit var checkNoruega: CheckBox
    private lateinit var checkUSA: CheckBox
    private lateinit var checkLondres: CheckBox
    private lateinit var checkMexico: CheckBox
    private lateinit var fabReporte : FloatingActionButton


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textNombre = findViewById(R.id.editTextNombre)
        textEdad = findViewById(R.id.editTextEdad)
        textTel = findViewById(R.id.editTextPhone)
        textMail = findViewById(R.id.editTextMail)
        switchFrecuente = findViewById(R.id.switchFrecuente)
        checkFinlandia = findViewById(R.id.checkFinlandia)
        checkChina = findViewById(R.id.checkChina)
        checkNoruega = findViewById(R.id.checkNoruega)
        checkUSA = findViewById(R.id.checkUSA)
        checkLondres = findViewById(R.id.checkLondres)
        checkMexico = findViewById(R.id.checkMexico)
        fabReporte = findViewById(R.id.fabReporte)
        radioGenero = findViewById(R.id.rbHombre)

        checkFinlandia.setOnCheckedChangeListener(checkBoxCheck)
        checkChina.setOnCheckedChangeListener(checkBoxCheck)
        checkNoruega.setOnCheckedChangeListener(checkBoxCheck)
        checkUSA.setOnCheckedChangeListener(checkBoxCheck)
        checkLondres.setOnCheckedChangeListener(checkBoxCheck)
        checkMexico.setOnCheckedChangeListener(checkBoxCheck)
        radioGenero.setOnCheckedChangeListener(radioCheck)
        fabReporte.setOnClickListener(fabClick)

    }

    private val checkBoxCheck = CompoundButton.OnCheckedChangeListener { button, checked ->
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerViewDestinos)
        
        val destinoAdapter = DestinoAdapter(createDestinos())

        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        recyclerView.adapter=destinoAdapter

        destinoAdapter.notifyDataSetChanged()
    }

    private val radioCheck = CompoundButton.OnCheckedChangeListener { radio, checked ->

        if (findViewById<RadioButton>(R.id.rbHombre).isChecked)
            radioGenero = findViewById(R.id.rbHombre)
        else
            radioGenero = findViewById(R.id.rbMujer)
    }

    private val fabClick = View.OnClickListener { fab ->
        val alertDialog = AlertDialog.Builder(fab.context)
            .setTitle("Reporte del usuario")
            .setMessage(concatenaMensaje())
            .setPositiveButton("Ok",null)
            .create()
        alertDialog.show()
    }
    fun concatenaMensaje(): String{
        var string = "\n"
        string+= " NOMBRE: "+textNombre.text+"\n"
        string+= " EDAD: "+textEdad.text+"\n"
        string+= " GÉNERO: "+radioGenero.text+"\n"
        string+= " TELÉFONO: "+textTel.text+"\n"
        if (switchFrecuente.isChecked)
            string+= " VIAJERO FRECUENTE: Si"+"\n"
        else
            string+= " VIAJERO FRECUENTE: No"+"\n"
        string+="  DESTINOS A VISITAR:"+"\n"
        if (checkFinlandia.isChecked)
            string+="       Finlandia -> Aurora Polar"+"\n"
        if (checkChina.isChecked)
            string+="       China -> Muralla China"+"\n"
        if (checkNoruega.isChecked)
            string+="       Noruega -> Flam"+"\n"
        if (checkUSA.isChecked)
            string+="       USA -> Isla Maui"+"\n"
        if (checkLondres.isChecked)
            string+="       Londres -> Palacio Real"+"\n"
        if (checkMexico.isChecked)
            string+="       México -> Aguas Termales"+"\n"
        return string

    }

    fun createDestinos(): List<Destino>{
        val destinos = mutableListOf<Destino>()

        if (checkFinlandia.isChecked){
            destinos.add(
                Destino(
                    R.drawable.ic_aurora,
                    "Aurora Polar"
                )
            )
        }
        if (checkNoruega.isChecked){
            destinos.add(
                Destino(
                    R.drawable.ic_flam,
                    "Flam"
                )
            )
        }
        if (checkUSA.isChecked){
            destinos.add(
                Destino(
                    R.drawable.ic_hawaii,
                    "Isla Maui"
                )
            )
        }
        if (checkChina.isChecked){
            destinos.add(
                Destino(
                    R.drawable.ic_muralla,
                    "Muralla China"
                )
            )
        }
        if (checkLondres.isChecked){
            destinos.add(
                Destino(
                    R.drawable.ic_torres,
                    "Palacio Real"
                )
            )
        }
        if (checkMexico.isChecked){
            destinos.add(
                Destino(
                    R.drawable.ic_aguas,
                    "Aguas Termales"
                )
            )
        }
        return destinos
    }
}