package com.example.firstdatabase

import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    private lateinit var  db: SQLiteDatabase
    private lateinit var  recyclerContacts : RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fabAddContact = findViewById<FloatingActionButton>(R.id.fab_add_contact)
        recyclerContacts = findViewById(R.id.recycler_contacts)

        fabAddContact.setOnClickListener{
            val intent = Intent(this, AddContact::class.java )
            startActivity(intent)
        }

        val contactDBHelper = ContactDBHelper(this)
        db = contactDBHelper.readableDatabase

    }

    override fun onResume(){

        val cursor = db.query(TABLE_NAME_CONTACT,null,null,null,null,null,null)
        val contacts = mutableListOf<ContactData>()

        super.onResume()
        cursor.use{


            while (cursor.moveToNext()){
                val name = cursor.getString(cursor.getColumnIndex(COLUMN_NAME))
                val lastName = cursor.getString(cursor.getColumnIndex(COLUMN_LASTNAME))
                val age = cursor.getInt(cursor.getColumnIndex(COLUMN_AGE))

                contacts.add(
                    ContactData(
                        name,
                        lastName,
                        age
                    )

                )
            }

            val contactAdapter = ContactAdapter(contacts)

            recyclerContacts.layoutManager = LinearLayoutManager(this)
            recyclerContacts.adapter = contactAdapter
        }
    }
}