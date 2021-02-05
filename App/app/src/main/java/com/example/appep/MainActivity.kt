package com.example.appep

import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.widget.SimpleCursorAdapter
import android.widget.Toast
import androidx.core.app.ActivityCompat
import java.util.jar.Manifest

class MainActivity : AppCompatActivity() {

    // Test

    //QR-Code Scanner






    //End QR-Code Scanner

    var cols = listOf<String>(
            ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,
            ContactsContract.CommonDataKinds.Phone.NUMBER,
            ContactsContract.CommonDataKinds.Phone._ID
    ).toTypedArray()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Kontakte mit Telefonbuch Synchronisieren
        // Fragt User nach Berechtigung auf Kontakte zuzugreifen
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.READ_CONTACTS)
                != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,Array(1){android.Manifest.permission.READ_CONTACTS}, 111)
        }
        else
            readContact()
    }

    // Wenn der User Berechtigung erteilt hat
    // Mit Request Code 11 -> Call funtion readContcact
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 111 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
            readContact()
    }

    private fun readContact() {

        var from = listOf<String>(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,
            ContactsContract.CommonDataKinds.Phone.NUMBER).toTypedArray()

        var to = intArrayOf(android.R.id.text1, android.R.id.text2)

        var rs = contentResolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                                        cols, null,null,
                                        ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME)

        var adapter = SimpleCursorAdapter(this, android.R.layout.simple_list_item_2,
                                          rs,from, to,0)

        // listview1.adapter = adapter
        // serchview.setOnQueryTextListener (object: SearchView.OnQueryTextListener)

    }


}

