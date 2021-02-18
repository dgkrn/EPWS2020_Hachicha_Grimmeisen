package com.example.eca2

import android.content.Context
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_contacts.*

class ContactsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contacts)

        // Rename Action Bar
        val actionBar = supportActionBar
        actionBar!!.title = "Contacts"
        // Add Nav Back Button
        actionBar.setDisplayHomeAsUpEnabled(true)


        val sharedPref = getSharedPreferences("userData", MODE_PRIVATE)
        val editor = sharedPref.edit()

        btnLoad.setOnClickListener {
            val name = sharedPref.getString("userName", "Default").toString()
            etContactName.setText(name)
        }


    }
}