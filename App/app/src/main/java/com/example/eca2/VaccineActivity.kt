package com.example.eca2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import kotlinx.android.synthetic.main.activity_vaccine.*

class VaccineActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vaccine)


        // Rename Action Bar
        val actionBar = supportActionBar
        actionBar!!.title = "Vaccinations"
        // Add Nav Back Button
        actionBar.setDisplayHomeAsUpEnabled(true)

        val sharedPref = getSharedPreferences("userData", MODE_PRIVATE)
        val editor = sharedPref.edit()

        val alter = if (sharedPref.contains("userAge"))
            sharedPref.getInt("userAge", 0) else 0
        val profession = if (sharedPref.contains("profession"))
            sharedPref.getString("profession", "empty") else "empty"

        var impfGruppe = 6

        btnBrechnen.setOnClickListener {

            if (profession == "Pflegepersonal" || profession == "Personal auf Intensivstationen" || profession == "Rettungsdienst"){
                impfGruppe = 1
            } else if (profession == "Polizei/Feuerwehr") {
                impfGruppe = 2
            } else {
                when (alter) {
                    in 0..59 -> impfGruppe = 6
                    in 60..64 -> impfGruppe = 5
                    in 65..69 -> impfGruppe = 4
                    in 70..74 -> impfGruppe = 3
                    in 75..79 -> impfGruppe = 2
                    in 80..120 -> impfGruppe =1
                    else -> impfGruppe = 6
                }
            }


            tvImpfgruppe.text = "Sie entsprechen Impfgruppe: $impfGruppe"
        }

    }
}