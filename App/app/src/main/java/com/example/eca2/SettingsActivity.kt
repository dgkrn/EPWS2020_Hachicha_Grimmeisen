package com.example.eca2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class SettingsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        // Rename Action Bar
        val actionBar = supportActionBar
        actionBar!!.title = "Settings"
        // Add Nav Back Button
        actionBar.setDisplayHomeAsUpEnabled(true)

    }
}