package com.aavash.jobfinderwear

import android.content.Intent
import android.os.Bundle
import android.support.wearable.activity.WearableActivity
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.aavash.jobfinderwear.api.ServiceBuilder
import com.aavash.jobfinderwear.repository.UserRepository
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : WearableActivity() {

    private lateinit var text:TextView
    private lateinit var btnLogout: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        text=findViewById(R.id.text)
        btnLogout=findViewById(R.id.btnLogout)

        // Enables Always-on
        setAmbientEnabled()


        CoroutineScope(Dispatchers.Main).launch {


            val repository = UserRepository()
            val response = repository.getUser()


            if (response.success==true) {
                Toast.makeText(
                    this@MainActivity,
                    response.data!!.email, Toast.LENGTH_SHORT
                ).show()

                val a=(response.data!!.firstname)

                val b=(response.data!!.lastname)
                val name=a+" "+b


                text.setText("Hi "+name)



            } else {
                withContext(Dispatchers.Main) {


//                    Toast.makeText(
//                        this@MainActivity,
//                        "Error", Toast.LENGTH_SHORT
//                    ).show()
                }
            }


        }
        btnLogout.setOnClickListener {
            logout()
            startActivity(
                Intent(
                    this@MainActivity,
                    loginActivity::class.java
                )
            )
        }




    }
    fun logout(){


        this?.deleteSharedPreferences("MyPref");

    }
}