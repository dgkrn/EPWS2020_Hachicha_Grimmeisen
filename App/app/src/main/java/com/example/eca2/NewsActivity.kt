package com.example.eca2

import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.webkit.WebViewClient
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_news.*

class NewsActivity : AppCompatActivity() {


    val INTERNET_RO = 101

    @RequiresApi(Build.VERSION_CODES.O)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news)

        // Rename Action Bar
        val actionBar = supportActionBar
        actionBar!!.title = "News"
        // Add Nav Back Button
        actionBar.setDisplayHomeAsUpEnabled(true)

        // WebView Setup
        webViewSetup()
        btnInternPermission.setOnClickListener{
            checkForPermissions(android.Manifest.permission.INTERNET,"Internet", INTERNET_RO)
        }

    }


    // Implement WebView
    @SuppressLint("SetJavaScriptEnabled")
    @RequiresApi(Build.VERSION_CODES.O)
    private fun webViewSetup() {
        wvNews.webViewClient = WebViewClient()
        wvNews.apply {
            loadUrl("https://www.n-tv.de/infografik/Alle-Daten-alle-Fakten-kompakt-Die-wichtigsten-Corona-Zahlen-im-Uberblick-article22079337.html")
            settings.javaScriptEnabled = true
            settings.safeBrowsingEnabled = true
        }
    }

    // Add App Bar Menu
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.app_bar_menu, menu)
        return true
    }

    // Request Runtime Internet permission
    @RequiresApi(Build.VERSION_CODES.M)
    private fun checkForPermissions(permission: String, name: String, requestCode: Int) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            when {
                ContextCompat.checkSelfPermission(
                    applicationContext,
                    permission
                ) == PackageManager.PERMISSION_GRANTED -> {
                    Toast.makeText(
                        applicationContext,
                        "$name permission granted",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                shouldShowRequestPermissionRationale(permission) -> showDialog(
                    permission,
                    name,
                    requestCode
                )

                else -> ActivityCompat.requestPermissions(this, arrayOf(permission), requestCode)
            }
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        fun innerCheck(name: String) {
            if (grantResults.isEmpty() || grantResults[0] != PackageManager.PERMISSION_GRANTED){
                Toast.makeText(applicationContext,"$name permission refused", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(applicationContext,"$name permission granted", Toast.LENGTH_SHORT).show()
            }
        }
        when(requestCode){
            INTERNET_RO -> innerCheck("location")
        }
    }

    private fun showDialog(permission: String, name: String, requestCode: Int) {
        val builder = AlertDialog.Builder(this)

        builder.apply {
            setMessage("Permission to acces you $name is required to use this app")
            setTitle("Permission required")
            setPositiveButton("Yes") { dialog, which ->
                ActivityCompat.requestPermissions(this@NewsActivity, arrayOf(permission),requestCode)
            }
        }
        val dialog = builder.create()
        dialog.show()
    }
}