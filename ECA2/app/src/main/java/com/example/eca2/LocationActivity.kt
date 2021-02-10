package com.example.eca2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class LocationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_location)


        // Rename Action Bar
        val actionBar = supportActionBar
        actionBar!!.title = "Location"
        // Add Nav Back Button
        actionBar.setDisplayHomeAsUpEnabled(true)

    }
}