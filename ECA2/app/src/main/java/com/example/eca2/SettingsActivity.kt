package com.example.eca2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_settings.*

class SettingsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        // Rename Action Bar
        val actionBar = supportActionBar
        actionBar!!.title = "Settings"
        // Add Nav Back Button
        actionBar.setDisplayHomeAsUpEnabled(true)

        // Add Nav Back Button
        actionBar.setDisplayHomeAsUpEnabled(true)


        cvUserData.setOnClickListener {
            val intent = Intent(this, GetDataActivity::class.java)
            startActivity(intent)
        }

    }
}