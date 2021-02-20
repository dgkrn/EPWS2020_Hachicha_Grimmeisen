package com.example.eca2

import android.content.DialogInterface
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_tests.*
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

class TestsActivity : AppCompatActivity() {

    private  var isPositive : Boolean = false

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tests)

        // Rename Action Bar
        val actionBar = supportActionBar
        actionBar!!.title = "Tests"
        // Add Nav Back Button
        actionBar.setDisplayHomeAsUpEnabled(true)


        btnPositive.setOnClickListener {
            val current = LocalDateTime.now()

            val formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy")
            val formatted = current.format(formatter).toString()
            tvTest1.text = "$formatted: POSITIVE"
            isPositive = true


            // Save Positive Result in Shared Preferences
            val sharedPref = getSharedPreferences("userData", MODE_PRIVATE)
            val editor = sharedPref.edit()
            editor.apply {
                // put
                putBoolean("isPositive", true)
                apply()
            }

            if (isPositive) {
                // Alert Dialog
                val builder = AlertDialog.Builder(this)
                builder.setTitle("Take Action!")
                builder.setMessage("Your Risk Index has changed\nPlease Review and self-quarantine!")
                val intent = Intent(this, UserActivity :: class.java)
                builder.setPositiveButton("Review", { dialogInterface: DialogInterface, i: Int -> startActivity(intent)})
                builder.setNegativeButton("No Thanks") { _: DialogInterface, i: Int -> finish() }
                builder.show()

                isPositive = true
            }
            // UserActivity().finish()

        }

        btnNegative.setOnClickListener {
            val current = LocalDateTime.now()

            val formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy")
            val formatted = current.format(formatter).toString()
            tvTest1.text = "$formatted: NEGATIVE"

            val sharedPref = getSharedPreferences("userData", MODE_PRIVATE)
            val editor = sharedPref.edit()
            editor.apply {
                putBoolean("isNegative", true)
                apply()
            }
        }

    }

    fun isPositive () : Boolean {
        return isPositive
    }
}

