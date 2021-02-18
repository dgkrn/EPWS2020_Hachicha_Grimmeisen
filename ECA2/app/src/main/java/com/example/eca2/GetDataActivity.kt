package com.example.eca2



import android.annotation.SuppressLint
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Geocoder
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Looper
import android.provider.Settings
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat.getSystemService
import androidx.core.location.LocationManagerCompat.isLocationEnabled
import com.google.android.gms.location.*
import kotlinx.android.synthetic.main.activity_contacts.*
import kotlinx.android.synthetic.main.activity_get_data.*
import kotlinx.android.synthetic.main.activity_splash_screen.*
import java.util.*
import kotlinx.android.synthetic.main.activity_main.*


/*


Um Daten zu speicher, auf die später, auch nach ausschalten des Gerätes zurückgegriffen werden kann, müssen diese im
Speicher des Geräts gespeichert werden.

Dazu bieten sich unter anderem 3 Möglichkeiten:

1. Speichern von einzelnen Key-Value Pairs in einer Datei --> SharedPreferences
2. Speicherung von langen Texten in einer Textdatei
3. Strukturierte Speicherung in SQLite-Datenbank


 */

class GetDataActivity : AppCompatActivity() {

    //Declaring the needed Variables
    var locationLat : Double = 0.0
    var locationLong : Double = 0.0
    lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    lateinit var locationRequest: LocationRequest
    val PERMISSION_ID = 1010

    @SuppressLint("MissingPermission")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_get_data)

        // Rename Action Bar
        val actionBar = supportActionBar
        actionBar!!.title = "User Data"
        // Add Nav Back Button
        actionBar.setDisplayHomeAsUpEnabled(true)

        // Add Nav Back Button
        actionBar.setDisplayHomeAsUpEnabled(true)

        // Alert Dialog
        val builder = AlertDialog.Builder(this)
        builder.setTitle("User Information Needed")
        builder.setMessage("In order for this App to work accurately, we need some information from you. Please fill out the following forms")
        builder.setPositiveButton("Ok", { dialogInterface: DialogInterface, i: Int -> })
        builder.setNegativeButton("Decline") { _: DialogInterface, i: Int -> finish() }
        builder.show()


        // Save User Data in Shared preferences
        val sharedPref = getSharedPreferences("userData", MODE_PRIVATE)
        val editor = sharedPref.edit()


        // Write Data to SharedPreferences
        btnSaveData.setOnClickListener {
            val userName = etUserName1.text.toString()
            val userWorks = cbWork.isChecked
            val userLivesAlone = cbLiveAlone.isChecked
            val hasKids = cbKids.isChecked
            val userCoWorkers : Int = etUserWorkCount.text.toString().toInt()
            val userRoomMates = etUserRoomMates.text.toString().toInt()
            val userAge = etUserAge.text.toString().toInt()
            val profession : String = spinProfession.selectedItem.toString()




            editor.apply {
                putString("userName", userName)
                putBoolean("userWorks", userWorks)
                putBoolean("userLivesAlone", userLivesAlone)
                putBoolean("hasKids", hasKids)
                putInt("userCoWorkers", userCoWorkers)
                putInt("userRoomMates", userRoomMates)
                putInt("userAge", userAge)
                putString("profession", profession)
                apply()

                // val name2 = if (sharedPref.contains("userName"))
                // sharedPref.getString("userName", "Default Name") else -1
            }
        }


        // Read Data from SharedPreferences
        btnLoadData.setOnClickListener {

            val name = if (sharedPref.contains("userName"))
                sharedPref.getString("userName", "Default Name") else "empty"
            val userWorks = if (sharedPref.contains("userWorks"))
                sharedPref.getBoolean("userWorks", false) else false
            val userLivesAlone = if (sharedPref.contains("userLivesAlone"))
                sharedPref.getBoolean("userLivesAlone", false) else false
            val hasKids = if (sharedPref.contains("hasKids"))
                sharedPref.getBoolean("hasKids", false ) else false
            val userCoWorkers = if (sharedPref.contains("userCoWorkers"))
                sharedPref.getInt("userCoWorkers", 0 ) else 0
            val userRoomMates = if (sharedPref.contains("userRoomMates"))
                sharedPref.getInt("userRoomMates", 0 ) else 0
            val userAge = if (sharedPref.contains("userAge"))
                sharedPref.getInt("userAge", 18 ) else 18
            val spinProfession = if (sharedPref.contains("profession"))
                sharedPref.getString("profession", "empty" ) else "empty"
            val userCity = if (sharedPref.contains("userCity"))
                sharedPref.getString("userCity", "empty" ) else "empty"

            /*


            val name = if (sharedPref.contains("userName"))
                sharedPref.getString("userName", "Default Name") else "empty"
            val userWorks = if (sharedPref.contains("userWorks"))
                sharedPref.getBoolean("userWorks", false) else false
            val userLivesAlone = if (sharedPref.contains("userLivesAlone"))
                sharedPref.getBoolean("userLivesAlone", false) else false
            val hasKids = if (sharedPref.contains("hasKids"))
                sharedPref.getBoolean("hasKids", false ) else false
            val userCoWorkers = if (sharedPref.contains("userCoWorkers"))
                sharedPref.getInt("userCoWorkers", 0 ) else 0
            val userRoomMates = if (sharedPref.contains("userRoomMates"))
                sharedPref.getInt("userRoomMates", 0 ) else 0
            val userAge = if (sharedPref.contains("userAge"))
                sharedPref.getInt("userAge", 18 ) else 18


             */


            // val name = sharedPref.getString("userName", "Default Name")
            // val userWorks = sharedPref.getBoolean("userWorks", false)
            // val userLivesAlone = sharedPref.getBoolean("userLivesAlone", false)
            // val hasKids = sharedPref.getBoolean("hasKids", false)
            // val userCoWorkers = sharedPref.getInt("userCoWorkers", 0)
            // val userRoomMates = sharedPref.getInt("userRoomMates", 0)
            // val userAge = sharedPref.getInt("userAge", 18)
            // val profession = sharedPref.getString("profession", "none")


            etUserName1.setText(name)
            cbWork.isChecked = userWorks
            cbLiveAlone.isChecked = userLivesAlone
            cbKids.isChecked = hasKids
            etUserWorkCount.setText(userCoWorkers.toString())
            etUserRoomMates.setText(userRoomMates.toString())
            etUserAge.setText(userAge.toString())
            tvSpinner.setText(spinProfession)
            tvUserCity.setText(userCity)
        }




        // Get User Location
        // initiate the  fusedLocationProviderClient     Variable inside our onCreate method
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)
        btnLocation.setOnClickListener {
            Log.d("Debug:",CheckPermission().toString())
            Log.d("Debug:",isLocationEnabled().toString())
            RequestPermission()
            /* fusedLocationProviderClient.lastLocation.addOnSuccessListener{location: Location? ->
                 textView.text = location?.latitude.toString() + "," + location?.longitude.toString()
             }*/
            getLastLocation()

        }



    }


    fun CheckPermission():Boolean{
        //this function will return a boolean
        //true: if we have permission
        //false if not
        if(
            ActivityCompat.checkSelfPermission(this,android.Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED ||
            ActivityCompat.checkSelfPermission(this,android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
        ){
            return true
        }

        return false

    }

    fun RequestPermission(){
        //this function will allows us to tell the user to requesut the necessary permsiion if they are not garented
        ActivityCompat.requestPermissions(
            this,
            arrayOf(android.Manifest.permission.ACCESS_COARSE_LOCATION,android.Manifest.permission.ACCESS_FINE_LOCATION),
            PERMISSION_ID
        )
    }


    fun isLocationEnabled():Boolean{
        //this function will return to us the state of the location service
        //if the gps or the network provider is enabled then it will return true otherwise it will return false
        var locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) || locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)
    }


    @SuppressLint("MissingPermission")
    fun getLastLocation(){
        if(CheckPermission()){
            if(isLocationEnabled()){
                fusedLocationProviderClient.lastLocation.addOnCompleteListener {task->
                    var location:Location? = task.result
                    if(location == null){
                        NewLocationData()
                    }else{
                        Log.d("Debug:" ,"Your Location:"+ location.longitude)
                        tvUserLocation.text = "You Current Location is : Long: "+ location.longitude + " , Lat: " + location.latitude + "\n" + getCityName(location.latitude,location.longitude)
                    }
                }
            }else{
                Toast.makeText(this,"Please Turn on Your device Location",Toast.LENGTH_SHORT).show()
            }
        }else{
            RequestPermission()
        }
    }


    @SuppressLint("MissingPermission")
    fun NewLocationData(){
        var locationRequest =  LocationRequest()
        locationRequest.priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        locationRequest.interval = 0
        locationRequest.fastestInterval = 0
        locationRequest.numUpdates = 1
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)
        fusedLocationProviderClient!!.requestLocationUpdates(
            locationRequest,locationCallback,Looper.myLooper()
        )
    }


    private val locationCallback = object : LocationCallback(){
        override fun onLocationResult(locationResult: LocationResult) {
            var lastLocation: Location = locationResult.lastLocation
            Log.d("Debug:","your last last location: "+ lastLocation.longitude.toString())
            textView.text = "You Last Location is : Long: "+ lastLocation.longitude + " , Lat: " + lastLocation.latitude + "\n" + getCityName(lastLocation.latitude,lastLocation.longitude)
            locationLong = lastLocation.longitude
            locationLat = lastLocation.latitude
            tvUserLocation2.text = "Lat: $locationLat Long: $locationLong"
        }
    }


    private fun getCityName(lat: Double,long: Double):String{
        var cityName:String = ""
        var countryName = ""
        var geoCoder = Geocoder(this, Locale.getDefault())
        var Adress = geoCoder.getFromLocation(lat,long,3)

        cityName = Adress.get(0).locality
        countryName = Adress.get(0).countryName
        Log.d("Debug:","Your City: " + cityName + " ; your Country " + countryName)


        tvUserCity.text = cityName

        val sharedPref = getSharedPreferences("userData", MODE_PRIVATE)
        val editor = sharedPref.edit()
        editor.apply {
            putString("userCity", cityName)
        }


        return cityName
    }


}

/*

spinProfession.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(adapterView: AdapterView<*>?, view: View?, position: Int, id: Long) {
                    Toast.makeText(this@GetDataActivity, "You selected ${adapterView?.getItemAtPosition(position).toString()}",
                        Toast.LENGTH_SHORT).show()
                }

                override fun onNothingSelected(p0: AdapterView<*>?) {
                }
            }



// Risk Index
        var userName = ""
        var userWorks = false
        var userWorkerCount = 0


        var listUserRikData = mutableListOf<RiskData>()



        btnLoadData.setOnClickListener {
            listUserRikData.add(0, RiskData(etUserName1.text.toString(), cbWork.isChecked, etUserWorkCount.text.toString().toInt()))
            etUserRiskIndex.text = Risiko().berechneRisiko(3, listUserRikData[0].userWorks,listUserRikData[0].userWorkCount).toString()
            //Log.d("listeMitRisiko",Risiko().listeMitRisiko[0].toString())
        }


 */