package com.example.eca2

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager

class ContactsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contacts)

        // Rename Action Bar
        val actionBar = supportActionBar
        actionBar!!.title = "Contacts"
        // Add Nav Back Button
        actionBar.setDisplayHomeAsUpEnabled(true)





    }
}