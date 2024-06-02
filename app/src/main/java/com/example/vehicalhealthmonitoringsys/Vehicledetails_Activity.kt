package com.example.vehicalhealthmonitoringsys

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Vehicledetails_Activity : AppCompatActivity() {

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.vehicledetails)


        val searchbutton: Button = findViewById(R.id.searchvehical_btn)
        val homebutton :Button=findViewById(R.id.home1_btn)
        val searchvehiclenumber: EditText = findViewById(R.id.searchvehicleno)
        val nametextview: TextView = findViewById(R.id.nameview)
        val vehiclenumbertextview: TextView = findViewById(R.id.vehiclenoview)
        val chassistextview: TextView = findViewById(R.id.chassisview)
        val insurancetextview: TextView = findViewById(R.id.insuranceview)
        val licensetextview: TextView = findViewById(R.id.licenseview)

        searchbutton.setOnClickListener {
            val vehiclenumber = searchvehiclenumber.text.toString()


            val apiService = RetrofitClient.getService()
            val call = apiService.getVehicleDetails(vehiclenumber)
            call.enqueue(object : Callback<List<Vehicle>> {
                override fun onResponse(
                    call: Call<List<Vehicle>>,
                    response: Response<List<Vehicle>>
                ) {
                    if (response.isSuccessful) {
                        val VehicleList = response.body()
                        if (VehicleList != null && VehicleList.isNotEmpty()) {
                            val vehicle = VehicleList[0]
                            nametextview.text = vehicle.holdername ?: "N/A"
                            vehiclenumbertextview.text = vehicle.vehiclenumber ?: "Status not found"
                            chassistextview.text = vehicle.chassisnumber ?: "N/A"
                            insurancetextview.text = vehicle.insurancenumber ?: "N/A"
                            licensetextview.text = vehicle.licensenumber ?: "N/A"
                        } else {
                            Toast.makeText(this@Vehicledetails_Activity, "Vehicle not found", Toast.LENGTH_SHORT).show()
                        }
                    } else {
                        Toast.makeText(this@Vehicledetails_Activity, "Error: ${response.errorBody()?.string()}", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<List<Vehicle>>, t: Throwable) {
                    nametextview.text ="Error"
                    vehiclenumbertextview.text = "Error: " + t.message
                    chassistextview.text = "Error"
                    insurancetextview.text = "Error"
                    licensetextview.text = "Error"

                }
            })
        }
        homebutton.setOnClickListener {

            val intent = Intent(this, Dashboard_Activity::class.java)
            startActivity(intent)

        }
    }
}

