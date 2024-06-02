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
        val searchVehicle: EditText = findViewById(R.id.searchvehicle)
        val engineView: TextView = findViewById(R.id.engine)
        val brakeView: TextView = findViewById(R.id.brake)
        val healthstatusView: TextView = findViewById(R.id.statusview)

        searchButton.setOnClickListener {
            val vehiclenumber = searchVehicle.text.toString()


            val apiService = RetrofitClient.getService()
            val call = apiService.getVehicleStatus(vehiclenumber)
            call.enqueue(object : Callback<List<VehicleStatus>> {
                override fun onResponse(call: Call<List<VehicleStatus>>, response: Response<List<VehicleStatus>>) {
                    if (response.isSuccessful) {
                        val Vehiclelist= response.body()
                        if (Vehiclelist != null && Vehiclelist.isNotEmpty()) {
                            val Vehicles = Vehiclelist[0]

                            engineView.text = Vehicles?.engine ?: "N/A"
                            brakeView.text = Vehicles?.brake ?: "N/A"
                            healthstatusView.text = Vehicles?.healthstatus ?: "Status not found"
                        } else {
                            Toast.makeText(this@Status_Activity, "Vehicle not found", Toast.LENGTH_SHORT).show()
                        }
                    } else {
                        Toast.makeText(this@Status_Activity, "Error: ${response.errorBody()?.string()}", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<List<VehicleStatus>>, t: Throwable) {
                    engineView.text = "Error"
                    brakeView.text = "Error"
                    healthstatusView.text = "Error"

                }
            })
        }
        homeButton.setOnClickListener {

            val intent = Intent(this, Dashboard_Activity::class.java)
            startActivity(intent)

        }
    }
}