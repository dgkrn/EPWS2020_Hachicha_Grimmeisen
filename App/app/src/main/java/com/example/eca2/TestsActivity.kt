package com.example.eca2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class TestsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tests)

        // Rename Action Bar
        val actionBar = supportActionBar
        actionBar!!.title = "Tests"
        // Add Nav Back Button
        actionBar.setDisplayHomeAsUpEnabled(true)

    }
}