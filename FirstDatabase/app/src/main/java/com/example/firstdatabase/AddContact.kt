package com.example.firstdatabase

import android.content.ContentValues
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.button.MaterialButton
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputEditText

class AddContact : AppCompatActivity() {

    private lateinit var contactDbHelper: ContactDBHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_contact)

        contactDbHelper = ContactDBHelper(this)

        val buttonSave = findViewById<MaterialButton>(R.id.button_save)
        val editTextName = findViewById<TextInputEditText>(R.id.textInput_nombre)
        val editTextLastName = findViewById<TextInputEditText>(R.id.textInput_Apellido)
        val editTextAge = findViewById<TextInputEditText>(R.id.textInput_Edad)

        buttonSave.setOnClickListener{ view ->
            val db = contactDbHelper.writableDatabase
            db.use {
                val contentValues = ContentValues()
                contentValues.put(COLUMN_NAME, editTextName.text.toString())
                contentValues.put(COLUMN_LASTNAME,editTextLastName.text.toString())
                contentValues.put(COLUMN_AGE,editTextAge.text.toString().toInt())

                db.insert(TABLE_NAME_CONTACT, null, contentValues)

                val snackbar = Snackbar.make(view.rootView,"Contacto guardado",Snackbar.LENGTH_SHORT).show()
            }
        }
    }
}