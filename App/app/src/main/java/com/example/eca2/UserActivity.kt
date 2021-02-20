package com.example.eca2

import android.content.DialogInterface
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_contacts.*
import kotlinx.android.synthetic.main.activity_user.*
import java.util.*

class UserActivity : AppCompatActivity() {
    var userRiskIndex : Float = 1.0f
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
        val userCity = if (sharedPref.contains("userCity"))
            sharedPref.getString("userCity", "No Location" ) else "No Location"



        etUserName.setText(userName)
        tvUserLocation.setText(userCity)

        // Alert Dialog
        if (!sharedPref.contains("userName")){
            etUserName.text == "No Name"
            val intent = Intent(this, GetDataActivity :: class.java)
            val builder = AlertDialog.Builder(this)
            builder.setTitle("User Information Needed")
            builder.setMessage("Welcome to Your Profile!\nFirst you need to setup some Data.\nPlease go to Settings -> User Data")
            builder.setPositiveButton("Bring me there", { dialogInterface: DialogInterface, i: Int -> startActivity(intent)})
            builder.setNegativeButton("Decline") { _: DialogInterface, i: Int ->  }
            builder.show()
        }





        cvUserRiskIndex.setOnClickListener {
            userRiskIndex = Risiko().berechneRisiko(userRoomMates, userCoWorkers, userWorks)
            userRiskIndex = 5.0f

            tvUserRiskIndex.text = "Your Risk Index: $userRiskIndex"

            val ampel = userRiskIndex.toInt()
            when(ampel){
                1,2,3 -> cvUserRiskAmpel.setCardBackgroundColor(Color.GREEN)
                4,5,6 -> cvUserRiskAmpel.setCardBackgroundColor(Color.YELLOW)
                7,8,9,10 -> cvUserRiskAmpel.setCardBackgroundColor(Color.RED)
                else -> cvUserRiskAmpel.setCardBackgroundColor(Color.WHITE)
            }
        }

        userRiskIndex = Risiko().berechneRisiko(userRoomMates, userCoWorkers, userWorks)
        update()



        tvUserRiskIndex.text = "Your Risk Index: $userRiskIndex"

        // userRiskIndex = 5f

        val ampel = userRiskIndex.toInt()
        when(ampel){
            1,2,3 -> cvUserRiskAmpel.setCardBackgroundColor(Color.GREEN)
            4,5,6 -> cvUserRiskAmpel.setCardBackgroundColor(Color.YELLOW)
            7,8,9,10 -> cvUserRiskAmpel.setCardBackgroundColor(Color.RED)
            else -> cvUserRiskAmpel.setCardBackgroundColor(Color.WHITE)
        }

    }

    fun update () {

        val sharedPref = getSharedPreferences("userData", MODE_PRIVATE)
        val editor = sharedPref.edit()

        var isPositive : Boolean = if (sharedPref.contains("isPositive")) {
            sharedPref.getBoolean("isPositive", false)
        } else false

        var isNegative : Boolean = if (sharedPref.contains("isNegative")) {
            sharedPref.getBoolean("isPositive", false)
        } else false

        if (isPositive){
            userRiskIndex = 10.0f
        }

        if (isNegative){
            userRiskIndex = 1.0f
        }

    }



}




