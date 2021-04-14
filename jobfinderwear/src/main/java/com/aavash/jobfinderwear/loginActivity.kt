package com.aavash.jobfinderwear

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.Toast
import com.aavash.jobfinderwear.api.ServiceBuilder
import com.aavash.jobfinderwear.entity.User
import com.aavash.jobfinderwear.repository.UserRepository
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class loginActivity : AppCompatActivity() {
    private lateinit var atvEmailLog: AutoCompleteTextView
    private lateinit var atvPasswordLog: AutoCompleteTextView
    private lateinit var btnSignIn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        atvEmailLog=findViewById(R.id.atvEmailLog)
        atvPasswordLog=findViewById(R.id.atvPasswordLog)

        btnSignIn=findViewById(R.id.btnSignIn)

        btnSignIn.setOnClickListener{

            login()


        }



    }
    private fun login() {
        val email = atvEmailLog.text.toString()
        val password = atvPasswordLog.text.toString()
        var user: User?=null
        CoroutineScope(Dispatchers.IO).launch {
            try {

                val repository = UserRepository()
                val response = repository.checkUser(email,password)
                if (response.success==true) {
                    Log.i("em",email)
                    Log.i("pw",password)


                    ServiceBuilder.token = "Bearer " + response.token

                    //to save user details

                    startActivity(
                        Intent(
                            this@loginActivity,
                            MainActivity::class.java
                        )
                    )
                    finish()



                } else {
                    withContext(Dispatchers.Main) {
                        Toast.makeText(
                            this@loginActivity,
                            "Invalid credentials", Toast.LENGTH_SHORT
                        ).show()
                    }
                }

            } catch (ex: Exception) {
                withContext(Dispatchers.Main) {
                    Toast.makeText(
                        this@loginActivity,
                        "Login error", Toast.LENGTH_SHORT
                    ).show()

                }
            }
        }
    }
}