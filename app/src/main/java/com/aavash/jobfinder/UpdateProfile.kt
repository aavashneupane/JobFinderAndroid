package com.aavash.jobfinder

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class UpdateProfile : AppCompatActivity() {

    private lateinit var tvUpdateFirstName:TextView
    private lateinit var tvUpdateLastName:TextView
    private lateinit var tvUpdatePhone:TextView
    private lateinit var tvUpdateEmail:TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_profile)




    }
}