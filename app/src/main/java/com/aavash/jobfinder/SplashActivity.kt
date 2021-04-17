package com.aavash.jobfinder

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.AutoCompleteTextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.aavash.jobfinder.api.ServiceBuilder
//import com.aavash.jobfinder.db.UserDB
import com.aavash.jobfinder.userRepository.UserRepository
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.coroutines.*


class SplashActivity : AppCompatActivity() {

    private lateinit var atvEmailLog: AutoCompleteTextView
    private lateinit var atvPasswordLog: AutoCompleteTextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)





        CoroutineScope(Dispatchers.IO).launch {
           login()

//            startActivity(Intent(this@SplashActivity, LoginActivity::class.java))
////            putExtra(atvEmailLog,toString());
////            putExtra(atvPasswordLog,toString());
//            finish()
        }






    }

    @RequiresApi(Build.VERSION_CODES.O)
    private suspend fun login() {
        val sharedPref = getSharedPreferences("MyPref", MODE_PRIVATE)
        val email = sharedPref.getString("email", "")
        val password = sharedPref.getString("password", "")
        withContext(Dispatchers.IO) {
            try {

                val repository = UserRepository()
                val response = repository.checkUser(email!!, password!!)
                if (response.success == true) {
                    ServiceBuilder.token = "Bearer ${response.token}"
                    startActivity(
                            Intent(
                                    this@SplashActivity,
                                    MainActivity::class.java
                            )
                    )
                    val a= com.aavash.jobfinder.Helper.Notification

                    a.givenotification(this@SplashActivity,"Welcome Back")
                } else {
                    withContext(Dispatchers.Main) {
                        startActivity(Intent(this@SplashActivity, LoginActivity::class.java))
                    }
                }
            } catch (ex: Exception) {
                withContext(Dispatchers.Main) {
                    startActivity(Intent(this@SplashActivity, LoginActivity::class.java))
                }
            }
        }
    }
}