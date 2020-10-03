package com.example.formularios

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AlertDialog
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.text.DateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var chkJazz : CheckBox
    private lateinit var chkSalsa : CheckBox
    private lateinit var chkCountry : CheckBox
    private lateinit var textGeneros : TextView

    private lateinit var radio1: RadioButton
    private lateinit var radio2: RadioButton
    private lateinit var radio3: RadioButton
    private lateinit var textRadio : TextView


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

        radio1 = findViewById(R.id.radio1)
        radio2 = findViewById(R.id.radio2)
        radio3 = findViewById(R.id.radio3)
        textRadio = findViewById(R.id.textRadio)

        chkJazz.setOnCheckedChangeListener(changeChecked)
        chkSalsa.setOnCheckedChangeListener(changeChecked)
        chkCountry.setOnCheckedChangeListener(changeChecked)

        radio1.setOnCheckedChangeListener(radioChecked)
        radio2.setOnCheckedChangeListener(radioChecked)
        radio3.setOnCheckedChangeListener(radioChecked)

        val buttonToast = findViewById<Button>(R.id.button_toast)
        buttonToast.setOnClickListener(clickToast)

        val fabActionDialog = findViewById<FloatingActionButton>(R.id.fabActionDialog)
        fabActionDialog.setOnClickListener(fabClick)

        val fabCalendario = findViewById<FloatingActionButton>(R.id.fabCalendario)
        fabCalendario.setOnClickListener(fabCalendar)

    }

    private val clickToast = View.OnClickListener { view->

        Toast.makeText(view.context,"Toast de ejemplo",Toast.LENGTH_LONG).show()

    }

    private val fabClick = View.OnClickListener { fab ->
        val alertDialog = AlertDialog.Builder(fab.context)
            .setTitle("Alert Dialog de ejemplo")
            .setMessage("Este es un ejemplo de alerta")
            .setPositiveButton("Ok",null)
            .setNegativeButton("No",null)
            .create()
        alertDialog.show()
    }

    private val fabCalendar = View.OnClickListener { fab ->
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(fab.context,{datePicker, y, m, dm ->
            val dateFormat = DateFormat.getDateInstance(DateFormat.FULL)
            val cal = Calendar.getInstance()
            cal.set(y, m, dm)
            val date = cal.time
            val dateString = dateFormat.format(date)

            Toast.makeText(datePicker.context, dateString, Toast.LENGTH_LONG).show()

        },year,month,dayOfMonth)
        datePickerDialog.datePicker.minDate=calendar.timeInMillis
        calendar.set(Calendar.MONTH,calendar.get(Calendar.MONTH)+3)
        datePickerDialog.datePicker.maxDate = calendar.timeInMillis
        datePickerDialog.show()
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

    private val radioChecked = CompoundButton.OnCheckedChangeListener { button, checked ->

        var texto = ""

        if (radio1.isChecked)
            texto = "El radio 1 está activo"

        if (radio2.isChecked)
            texto = "El radio 2 está activo"

        if (radio3.isChecked)
            texto = "El radio 3 está activo"

        textRadio.text = texto

    }
}