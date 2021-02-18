package com.example.eca2

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_contacts.*
import kotlinx.android.synthetic.main.activity_user.*
import java.util.*

class UserActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user)

        // Status bar color
        // window.statusBarColor = ContextCompat.getColor(this, R.color.orangelight2)

        // Rename Action Bar
        val actionBar = supportActionBar
        actionBar!!.title = "Me"
        // Add Nav Back Button
        actionBar.setDisplayHomeAsUpEnabled(true)



        // Estimate Risk Index
        val sharedPref = getSharedPreferences("userData", MODE_PRIVATE)
        val editor = sharedPref.edit()

        val userName = if (sharedPref.contains("userName"))
            sharedPref.getString("userName", "No Name" ) else "No Name"
        val userRoomMates = if (sharedPref.contains("userRoomMates"))
            sharedPref.getInt("userRoomMates", 0 ) else 0
        val userWorks = if (sharedPref.contains("userWorks"))
            sharedPref.getBoolean("userWorks", false) else false
        val userCoWorkers = if (sharedPref.contains("userCoWorkers"))
            sharedPref.getInt("userCoWorkers", 0 ) else 0


        etUserName.setText(userName)

        var userRiskIndex = Risiko().berechneRisiko(userRoomMates, userCoWorkers, userWorks)
        tvUserRiskIndex.text = "Your Risk Index: $userRiskIndex"

        userRiskIndex = 5f

        val ampel = userRiskIndex.toInt()
        when(ampel){
            1,2,3 -> cvUserRiskAmpel.setCardBackgroundColor(Color.GREEN)
            4,5,6 -> cvUserRiskAmpel.setCardBackgroundColor(Color.YELLOW)
            7,8,9 -> cvUserRiskAmpel.setCardBackgroundColor(Color.RED)
            else -> cvUserRiskAmpel.setCardBackgroundColor(Color.WHITE)
        }




    }



}




