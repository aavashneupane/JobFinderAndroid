package com.aavash.jobfinder

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity(),View.OnClickListener{

    private lateinit var atvEmailLog:Button
    private lateinit var atvPasswordLog:Button
    private lateinit var btnSignIn:Button
    private lateinit var btnSignUp:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)


//        atvEmailLog=findViewById(R.id.atvEmailLog)
  //      atvPasswordLog=findViewById(R.id.atvPasswordLog)

        btnSignIn=findViewById(R.id.btnSignIn)
        btnSignUp=findViewById(R.id.btnSignUp)

        btnSignIn.setOnClickListener(this)
        btnSignUp.setOnClickListener(this)

    }

    override fun onClick(v: View?) {

        when(v?.id){
            R.id.btnSignIn->{
                val intent=Intent(this,homeScreen::class.java)
                startActivity(intent)
            }
            R.id.btnSignUp->{
                val intent=Intent(this,registration::class.java)
                startActivity(intent)
            }

        }

    }
}