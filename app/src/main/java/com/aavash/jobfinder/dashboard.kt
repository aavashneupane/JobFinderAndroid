package com.aavash.jobfinder

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity


class dashboard : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        val btnViewJob=findViewById<Button>(R.id.btnViewJob)
        val btnAddJob=findViewById<Button>(R.id.btnAddJob)

        btnViewJob.setOnClickListener {
            val intent=Intent(this,afterLogin::class.java)
            startActivity(intent)
        }

        btnAddJob.setOnClickListener {
            val intent=Intent(this,addJob::class.java)
            startActivity(intent)
        }


    }
}