package com.aavash.jobfinder

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.LinearLayout
import android.widget.Toast
import com.aavash.jobfinder.api.ServiceBuilder
import com.aavash.jobfinder.api.ServiceBuilder.token

import com.aavash.jobfinder.entity.User
import com.aavash.jobfinder.userRepository.UserRepository
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LoginActivity : AppCompatActivity(),View.OnClickListener{

    private lateinit var atvEmailLog:AutoCompleteTextView
    private lateinit var atvPasswordLog:AutoCompleteTextView
    private lateinit var btnSignIn:Button
    private lateinit var linearLayout: LinearLayout
    private lateinit var btnSignUp:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)



        atvEmailLog=findViewById(R.id.atvEmailLog)
        atvPasswordLog=findViewById(R.id.atvPasswordLog)

        getSharedPref()

        btnSignIn=findViewById(R.id.btnSignIn)
        btnSignUp=findViewById(R.id.btnSignUp)

        btnSignIn.setOnClickListener{

            login()


        }

        btnSignUp.setOnClickListener {

            val intent=Intent(this,registration::class.java)
                startActivity(intent)
        }

    }

    private fun login() {
        val email = atvEmailLog.text.toString()
        val password = atvPasswordLog.text.toString()

//        Toast.makeText(
//            this@LoginActivity,
//            "Successfull Login", Toast.LENGTH_SHORT
//        ).show()
        val intent=Intent(this,dashboard::class.java)

        startActivity(intent)


//        CoroutineScope(Dispatchers.IO).launch {
//
//
//
//            try {
//
//                val repository = UserRepository()
//                val response = repository.checkUser(email,password)
//                if (response.success==true) {
//                    Log.i("em",email)
//                    Log.i("pw",password)
//
//
//                    ServiceBuilder.token = "Bearer ${ response.token }"
//
//                    startActivity(
//                        Intent(
//                            this@LoginActivity,
//                            dashboard::class.java
//                        )
//                    )
//                    finish()
//                } else {
//                    withContext(Dispatchers.Main) {
//                        val snack =
//                            Snackbar.make(
//                                linearLayout,
//                                "Invalidlk credentials",
//                                Snackbar.LENGTH_LONG
//                            )
//                        snack.setAction("OK", View.OnClickListener {
//                            snack.dismiss()
//                        })
//                        snack.show()
//                    }
//                }
//
//            } catch (ex: Exception) {
//                withContext(Dispatchers.Main) {
//                    Toast.makeText(
//                        this@LoginActivity,
//                        "Login error", Toast.LENGTH_SHORT
//                    ).show()
//                }
//            }
//        }
    }

    private fun saveSharedPref(){

        val username=atvEmailLog.text.toString()
        val password = atvPasswordLog.text.toString()
        val sharedPref = getSharedPreferences("MyPref", MODE_PRIVATE)

        val editor =sharedPref.edit()
        editor.putString("Username",username)
        editor.putString("Password",password)
        editor.apply()
//        Toast.makeText(
//                this@LoginActivity,
//                "Username and password saved",
//                Toast.LENGTH_SHORT
//        ).show()



    }

    private fun getSharedPref(){


        val sharedPref= getSharedPreferences("MyPref", MODE_PRIVATE)
        val Username=sharedPref.getString("Username","")
        val Password=sharedPref.getString("Password","")
     //   Toast.makeText(this,"Username: $Username and Password : $Password ", Toast.LENGTH_SHORT).show()

        atvEmailLog.setText(Username)
        atvPasswordLog.setText(Password)





    }




    override fun onClick(v: View?) {

    }


//    override fun onClick(v: View?) {
//
//        when(v?.id){
//            R.id.btnSignIn->{
//                val intent=Intent(this,homeScreen::class.java)
//                startActivity(intent)
//            }
//            R.id.btnSignUp->{
//                val intent=Intent(this,registration::class.java)
//                startActivity(intent)
//            }
//
//        }
//
//    }
}