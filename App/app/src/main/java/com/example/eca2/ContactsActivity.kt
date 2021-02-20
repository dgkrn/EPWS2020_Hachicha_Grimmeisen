package com.example.eca2

import android.R.attr
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.zxing.integration.android.IntentIntegrator
import kotlinx.android.synthetic.main.activity_contacts.*


class ContactsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contacts)

        // Rename Action Bar
        val actionBar = supportActionBar
        actionBar!!.title = "Contacts"
        // Add Nav Back Button
        actionBar.setDisplayHomeAsUpEnabled(true)


        val sharedPref = getSharedPreferences("userData", MODE_PRIVATE)
        val editor = sharedPref.edit()

        btnLoad.setOnClickListener {
            val name = sharedPref.getString("userName", "Default").toString()
            etContactName.setText(name)
        }

        btnCreateID.setOnClickListener {
            val randomID = (100..999).random().toString()
            etID.setText(randomID)
        }


        // Add New Contacts
        var contactList = mutableListOf(
            Contacts("David", 101)
        )

        val adapter = ContacAdapter(contactList)
        rvContacts.adapter = adapter
        rvContacts.layoutManager = LinearLayoutManager(this)

        btnSave.setOnClickListener {
            val name = etContactName.text.toString()
            val id = etID.text.toString().toInt()
            val contact = Contacts(name, id)
            contactList.add(contact)
            // adapter.notifyDataSetChanged() // Updates the whole Recylcer view / Less efficient
            adapter.notifyItemInserted(contactList.size - 1)
        }


        // Barcdoe Scanner
        btnScanNow.setOnClickListener {
            val scanner = IntentIntegrator(this)
            // Only Scan Barcode
            scanner.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE)
            // Disable Beep Sounds upon Success
            // scanner.setBarcodeImageEnabled(false)
            scanner.initiateScan()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode == Activity.RESULT_OK) {
            val result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data)
            if (result != null) {
                if (result.contents == null) {
                    Toast.makeText(this, "Cancelled", Toast.LENGTH_LONG).show()
                } else {
                    Toast.makeText(this, "Scanned: " + result.contents, Toast.LENGTH_LONG).show()
                }
            } else {
                super.onActivityResult(requestCode, resultCode, data)
            }

        }
    }


}