package com.example.eca2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class VaccineActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vaccine)


        // Rename Action Bar
        val actionBar = supportActionBar
        actionBar!!.title = "Vaccinations"
        // Add Nav Back Button
        actionBar.setDisplayHomeAsUpEnabled(true)

    }
}