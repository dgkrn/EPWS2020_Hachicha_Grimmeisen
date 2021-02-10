package com.example.eca2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class UserActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user)

        // Rename Action Bar
        val actionBar = supportActionBar
        actionBar!!.title = "Me"
        // Add Nav Back Button
        actionBar.setDisplayHomeAsUpEnabled(true)
    }
}