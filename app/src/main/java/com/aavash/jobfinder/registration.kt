package com.aavash.jobfinder

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button

class registration : AppCompatActivity(), View.OnClickListener {

    private lateinit var btnSignUp:Button
    private lateinit var btnSignIn:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)

        btnSignIn=findViewById(R.id.btnSignIn)
        btnSignUp=findViewById(R.id.btnSignUp)


    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.btnSignIn->{
                val intent= Intent(this,LoginActivity::class.java)
                startActivity(intent)
            }
            R.id.btnSignUp->{
                val intent= Intent(this,LoginActivity::class.java)
                startActivity(intent)
            }

        }
    }
}