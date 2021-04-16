package com.aavash.jobfinder

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.LinearLayout
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.aavash.jobfinder.api.ServiceBuilder
import com.aavash.jobfinder.api.ServiceBuilder.token
//import com.aavash.jobfinder.db.UserDB

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

    lateinit var notificationManager: NotificationManager
    lateinit var notificationChannel: NotificationChannel
    lateinit var builder: Notification.Builder
    private val channelId="com.aavash.jobfinder"
    private val description="Notification"

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        notificationManager=getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)



        atvEmailLog=findViewById(R.id.atvEmailLog)
        atvPasswordLog=findViewById(R.id.atvPasswordLog)

        getSharedPref()

        btnSignIn=findViewById(R.id.btnSignIn)
        btnSignUp=findViewById(R.id.btnSignUp)

        btnSignIn.setOnClickListener{

            if (isValid()) {
                login()

            }
        }

        btnSignUp.setOnClickListener {
            val intent=Intent(this,registration::class.java)
                startActivity(intent)
        }

    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun login() {
        val email = atvEmailLog.text.toString()
        val password = atvPasswordLog.text.toString()
        var user:User?=null
        CoroutineScope(Dispatchers.IO).launch {
            try {
//                user= UserDB.getInstance(this@LoginActivity)
//                        .getUserDAO().checkUser(email,password)
//                val userdao =UserDB.getInstance(this@LoginActivity)
//                        .getUserDAO()
                val repository = UserRepository()
                val response = repository.checkUser(email,password)
                if (response.success==true) {
                    Log.i("em",email)
                    Log.i("pw",password)
                 //   createNotification(email)

                    ServiceBuilder.token = "Bearer " + response.token

                    //to save user details

                        val a= com.aavash.jobfinder.Helper.Notification

                    a.givenotification(this@LoginActivity,"You have successfully logged in.")

                    startActivity(
                        Intent(
                            this@LoginActivity,
                            MainActivity::class.java
                        )
                    )
                    finish()
                    saveSharedPref()


                } else {
                    withContext(Dispatchers.Main) {
                        val snack =
                            Snackbar.make(
                                linearLayout,
                                "Invalidlk credentials",
                                Snackbar.LENGTH_LONG
                            )
                        snack.setAction("OK", View.OnClickListener {
                            snack.dismiss()
                        })
                        snack.show()
                    }
                }

            } catch (ex: Exception) {
                withContext(Dispatchers.Main) {
                    Toast.makeText(
                        this@LoginActivity,
                        "Login error", Toast.LENGTH_SHORT
                    ).show()
                    val a= com.aavash.jobfinder.Helper.Notification

                    a.givenotification(this@LoginActivity,"Error loggin in.")

                }
            }
        }
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



    private fun getSharedPref(){


        val sharedPref= getSharedPreferences("MyPref", MODE_PRIVATE)
        val email=sharedPref.getString("email","")
        val password=sharedPref.getString("Password","")
     //   Toast.makeText(this,"Username: $Username and Password : $Password ", Toast.LENGTH_SHORT).show()

        atvEmailLog.setText(email)
        atvPasswordLog.setText(password)





    }


///for notification
//fun createNotification(value:String){
//
//    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//        notificationChannel= NotificationChannel(channelId,description, NotificationManager.IMPORTANCE_HIGH)
//        notificationChannel.enableVibration(true)
//        notificationManager.createNotificationChannel(notificationChannel)
//
//
//        builder= Notification.Builder(this@LoginActivity,channelId)
//            .setContentTitle("Login Detail")
//            .setContentText("Hello $value. You have successfully logged in to your account. Welcome!!")
//            .setSmallIcon(R.drawable.icon_person)
//            .setOngoing(false)
//        // .setLargeIcon(BitmapFactory.decodeResource(this.resources,R.mipmap.ic_launcher))
//        // .setContentIntent(pendingIntent)
//
//
//    }else
//    {
//        builder= Notification.Builder(this@LoginActivity)
//            .setContentTitle("Login Detail")
//            .setContentText("Hello ($value). You have successcully logged in to your account. Welcome!!")
//            .setSmallIcon(R.drawable.icon_person)
//        // .setLargeIcon(BitmapFactory.decodeResource(this.resources,R.mipmap.ic_launcher))
//        //  .setContentIntent(pendingIntent)
//
//    }
//    notificationManager.notify(1234,builder.build())
//}

    override fun onClick(v: View?) {

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
}