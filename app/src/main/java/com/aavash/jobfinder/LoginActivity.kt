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
import com.aavash.jobfinder.api.ServiceBuilder
import com.aavash.jobfinder.api.ServiceBuilder.token
import com.aavash.jobfinder.db.UserDB

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
        var user:User?=null
        CoroutineScope(Dispatchers.IO).launch {
            try {
                user= UserDB.getInstance(this@LoginActivity)
                        .getUserDAO().checkUser(email,password)
                val userdao =UserDB.getInstance(this@LoginActivity)
                        .getUserDAO()
                val repository = UserRepository(userdao)
                val response = repository.checkUser(email,password)
                if (response.success==true) {
                    Log.i("em",email)
                    Log.i("pw",password)
                    createNotification(email)

                    ServiceBuilder.token = "Bearer " + response.token

                    startActivity(
                        Intent(
                            this@LoginActivity,
                            MainActivity::class.java
                        )
                    )
                    finish()
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

                }
            }
        }
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


///for notification
fun createNotification(value:String){

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        notificationChannel= NotificationChannel(channelId,description, NotificationManager.IMPORTANCE_HIGH)
        notificationChannel.enableVibration(true)
        notificationManager.createNotificationChannel(notificationChannel)


        builder= Notification.Builder(this@LoginActivity,channelId)
            .setContentTitle("Login Detail")
            .setContentText("Hello ($value). You have successcully logged in to your account. Welcome!!")
            .setSmallIcon(R.drawable.icon_person)
            .setOngoing(true)
        // .setLargeIcon(BitmapFactory.decodeResource(this.resources,R.mipmap.ic_launcher))
        // .setContentIntent(pendingIntent)


    }else
    {
        builder= Notification.Builder(this@LoginActivity)
            .setContentTitle("Login Detail")
            .setContentText("Hello ($value). You have successcully logged in to your account. Welcome!!")
            .setSmallIcon(R.drawable.icon_person)
        // .setLargeIcon(BitmapFactory.decodeResource(this.resources,R.mipmap.ic_launcher))
        //  .setContentIntent(pendingIntent)

    }
    notificationManager.notify(1234,builder.build())
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