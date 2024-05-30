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

class Status_Activity : AppCompatActivity() {

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.status)


        val searchButton: Button = findViewById(R.id.search_btn)
        val homeButton :Button=findViewById(R.id.home_btn)
        val searchVehical: EditText = findViewById(R.id.searchvehical)
        val vehicleNumberView: TextView = findViewById(R.id.vehicleview)
        val engineView: TextView = findViewById(R.id.engine)
        val brakeView: TextView = findViewById(R.id.brake)
        val healthstatusView: TextView = findViewById(R.id.statusview)

        searchButton.setOnClickListener {
            val vehiclenumber = searchVehical.text.toString()


            val apiService = RetrofitClient.getService()
            val call = apiService.getVehicleStatus(vehiclenumber!!)
            call.enqueue(object : Callback<VehicleStatus> {
                override fun onResponse(
                    call: Call<VehicleStatus>,
                    response: Response<VehicleStatus>
                ) {
                    if (response.isSuccessful) {
                        val VehicalStatus= response.body()
                        vehicleNumberView.text = VehicalStatus?.vehiclenumber ?: "N/A"
                        healthstatusView.text = VehicalStatus?.healthstatus ?: "Status not found"
                        engineView.text = VehicalStatus?.engine ?: "N/A"
                        brakeView.text = VehicalStatus?.brake ?: "N/A"
                    } else {
                        vehicleNumberView.text = "N/A"
                        healthstatusView.text = "Status not found"
                        engineView.text = "N/A"
                        brakeView.text = "N/A"
                    }
                }

                override fun onFailure(call: Call<VehicleStatus>, t: Throwable) {
                    vehicleNumberView.text = "Error: " + t.message
                    healthstatusView.text = "Perfect"
                    engineView.text = "Good"
                    brakeView.text = "Finr"

                }
            })
        }
        homeButton.setOnClickListener {

            val intent = Intent(this, Dashboard_Activity::class.java)
            startActivity(intent)

        }
    }
}