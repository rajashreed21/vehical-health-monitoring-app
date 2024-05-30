package com.example.vehicalhealthmonitoringsys

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import android.content.Intent
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class Dashboard_Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dashboard)

        val regview = findViewById<ImageView>(R.id.register)
        val statusview = findViewById<ImageView>(R.id.statusimage)

        regview.setOnClickListener {

            val intent = Intent(this, Register_Activity::class.java)
            startActivity(intent)
        }
        statusview.setOnClickListener {

            val intent = Intent(this, Status_Activity::class.java)
            startActivity(intent)

        }
    }
}


