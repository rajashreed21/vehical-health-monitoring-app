package com.example.vehicalhealthmonitoringsys

import android.os.Bundle
import android.content.Intent
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class Dashboard_Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dashboard)

        val regview = findViewById<ImageView>(R.id.register)
        val statusview = findViewById<ImageView>(R.id.statusimage)
        val carview = findViewById<ImageView>(R.id.carimage)

        regview.setOnClickListener {

            val intent = Intent(this, Register_Activity::class.java)
            startActivity(intent)
        }
        statusview.setOnClickListener {

            val intent = Intent(this, Status_Activity::class.java)
            startActivity(intent)

        }
        carview.setOnClickListener {

            val intent = Intent(this, Vehicledetails_Activity::class.java)
            startActivity(intent)
            finish()
        }
    }
}


