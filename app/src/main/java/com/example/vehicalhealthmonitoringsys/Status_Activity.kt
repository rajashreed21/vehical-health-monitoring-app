package com.example.vehicalhealthmonitoringsys

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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.status)

        val vehiclenumtext = findViewById<TextView>(R.id.vehicleno1)
        val enginetext = findViewById<TextView>(R.id.engine)
        val braketext = findViewById<TextView>(R.id.brake)
        val healthtext = findViewById<TextView>(R.id.healthstatus)
        val vehiclenumber = intent.getStringExtra("vehiclenumber")

        if (vehiclenumber != null) {
            fetchVehicleStatus(vehiclenumber, vehiclenumtext,enginetext,braketext,healthtext)
        } else {
            Toast.makeText(this, "Vehicle number is missing", Toast.LENGTH_SHORT).show()
        }
    }

    private fun fetchVehicleStatus(vehiclenumber: String, vehiclenumtext: TextView,enginetext: TextView,braketext: TextView,healthtext:TextView) {
        val apiService = RetrofitClient.getService()
        val call = apiService.getVehicleStatus(vehiclenumber)
        call.enqueue(object : Callback<VehicleStatus> {
            override fun onResponse(call: Call<VehicleStatus>, response: Response<VehicleStatus>) {
                if (response.isSuccessful) {
                    val status = response.body()
                    if (status != null) {
                        vehiclenumtext.text="VehicalNumber:${status.vehiclenumber}"
                        enginetext.text = "Engine: ${status.engine}"
                        braketext.text=" Brake: ${status.brake}"
                        healthtext.text=" Health: ${status.healthstatus}"
                    } else {
                        Toast.makeText(this@Status_Activity, "No status found", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Toast.makeText(this@Status_Activity, "Failed to retrieve status", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<VehicleStatus>, t: Throwable) {
                Toast.makeText(this@Status_Activity, "Failed", Toast.LENGTH_SHORT).show()
            }
        })
    }
}