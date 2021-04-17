package com.aavash.jobfinderwear

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.StrictMode
import android.util.Log
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.aavash.jobfinderwear.api.ServiceBuilder
import com.aavash.jobfinderwear.entity.User
import com.aavash.jobfinderwear.repository.UserRepository
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
//        if (Build.VERSION.SDK_INT > 9) {
//            val policy =
//                StrictMode.ThreadPolicy.Builder().permitAll().build()
//            StrictMode.setThreadPolicy(policy)
//        }




        atvEmailLog=findViewById(R.id.atvEmailLog)
        atvPasswordLog=findViewById(R.id.atvPasswordLog)

        btnSignIn=findViewById(R.id.btnSignIn)

        getShared()
        login()

        btnSignIn.setOnClickListener{
            if (isValid()) {
                login()

            }
        }



    }
    private fun login() {
        val email = atvEmailLog.text.toString()
        val password = atvPasswordLog.text.toString()
        var user: User?=null
        Log.i("em",email)
        Log.i("pw",password)
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
                    saveSharedPref()


                } else {
                    withContext(Dispatchers.Main) {
//                        Toast.makeText(
//                            this@loginActivity,
//                            "Invalid credentials", Toast.LENGTH_SHORT
//                        ).show()
                    }
                }

            } catch (ex: Exception) {
                withContext(Dispatchers.Main) {
//                    Toast.makeText(
//                        this@loginActivity,
//                        "", Toast.LENGTH_SHORT
//                    ).show()

                }
            }
        }
    }
    private fun isValid(): Boolean {
        when {

            atvPasswordLog.text.isEmpty() ->{
                atvPasswordLog.error = "Field must not be empty"
                return false
            }

            atvEmailLog.text.isEmpty() -> {
                atvEmailLog.error = "Field must not be empty"
                return false
            }
        }
        return true


    }
    private fun saveSharedPref(){

        val email=atvEmailLog.text.toString()
        val password = atvPasswordLog.text.toString()
        val sharedPref = getSharedPreferences("MyPref", MODE_PRIVATE)

        val editor =sharedPref.edit()
        editor.putString("email",email)
        editor.putString("password",password)
        editor.apply()
//        Toast.makeText(
//                this@LoginActivity,
//                "Username and password saved",
//                Toast.LENGTH_SHORT
//        ).show()



    }
    fun getShared(){
        val sharedPref = getSharedPreferences("MyPref", MODE_PRIVATE)
        val email = sharedPref.getString("email", "")
        val password = sharedPref.getString("password", "")
        atvEmailLog.setText(email)
        atvPasswordLog.setText(password)
    }


}