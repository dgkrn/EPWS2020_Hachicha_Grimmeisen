package com.example.eca2

import android.accounts.Account
import android.accounts.AccountManager
import android.content.Intent
import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        // Hide ActionBar
        supportActionBar?.hide()

        // Change StatusBar color
        //window.statusBarColor = ContextCompat.getColor(this, R.color.colorName)

        // Make StatusBar transparent
        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )

        // Change this
        val numberOfItems = 6
        tvDashboardItems.text = tvDashboardItems.text.toString() + ": " +numberOfItems.toString()
        val userName = "David"
        tvDashboard.text = "${userName}'s Dashboard"


        //Activities ClickListeners
        cvContacts.setOnClickListener{
            val intent = Intent(this,ContactsActivity::class.java)
            startActivity(intent)
        }
        cvLocations.setOnClickListener{
            val intent = Intent(this,LocationActivity::class.java)
            startActivity(intent)
        }
        cvNews.setOnClickListener{
            val intent = Intent(this,NewsActivity::class.java)
            startActivity(intent)
        }
        cvTest.setOnClickListener{
            val intent = Intent(this,TestsActivity::class.java)
            startActivity(intent)
        }
        cvVaccine.setOnClickListener{
            val intent = Intent(this,VaccineActivity::class.java)
            startActivity(intent)
        }
        cvSettings.setOnClickListener{
            val intent = Intent(this,SettingsActivity::class.java)
            startActivity(intent)
        }
        ivUser.setOnClickListener{
            val intent = Intent(this,UserActivity::class.java)
            startActivity(intent)
        }







    }
}