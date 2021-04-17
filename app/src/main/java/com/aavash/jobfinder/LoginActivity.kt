package com.aavash.jobfinder

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.VibrationEffect
import android.os.Vibrator
import android.provider.Settings
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
    private var sensorManager: SensorManager? = null
    private var gyroscopeSensor: Sensor? = null
    private var lightSensor: Sensor? = null

    lateinit var notificationManager: NotificationManager
    lateinit var notificationChannel: NotificationChannel
    lateinit var builder: Notification.Builder
    private val channelId="com.aavash.jobfinder"
    private val description="Notification"

    private val permissions = arrayOf(
        android.Manifest.permission.CAMERA,
        android.Manifest.permission.WRITE_EXTERNAL_STORAGE,
        android.Manifest.permission.ACCESS_FINE_LOCATION
    )

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        notificationManager=getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)



        atvEmailLog=findViewById(R.id.atvEmailLog)
        atvPasswordLog=findViewById(R.id.atvPasswordLog)

        getSharedPref()


        sensorManager = applicationContext.getSystemService(Context.SENSOR_SERVICE) as SensorManager?
        gyroscopeSensor = sensorManager!!.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
        lightSensor = sensorManager!!.getDefaultSensor(Sensor.TYPE_LIGHT);
        sensorManager!!.registerListener(gyroscopeSensorListener,gyroscopeSensor,SensorManager.SENSOR_DELAY_NORMAL)
        sensorManager!!.registerListener(LightListener,lightSensor,SensorManager.SENSOR_DELAY_NORMAL)

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

        vibratePhone()

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
    fun vibratePhone() {
        val vibrator = this.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
        if (Build.VERSION.SDK_INT >= 26) {
            vibrator.vibrate(VibrationEffect.createOneShot(200, VibrationEffect.DEFAULT_AMPLITUDE))
        } else {
            vibrator.vibrate(200)
        }
    }

    //Gyroscope
    private var gyroscopeSensorListener = object : SensorEventListener {
        override fun onAccuracyChanged(sensor: Sensor, accuracy: Int) {
        }
        override fun onSensorChanged(event: SensorEvent) {
            val params = this@LoginActivity.window.attributes
            if (event.sensor.type == Sensor.TYPE_GYROSCOPE) {
                if(event.values[2] > 0.5f) { // anticlockwise
                    val intent = Intent(this@LoginActivity,RegisterActivity::class.java)
                    startActivity(intent)
                } else if(event.values[2] < -0.5f) { // clockwise
                    Toast.makeText(this@LoginActivity,"text",Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    // Start can modify system settings panel to let user change the write
    // settings permission.
    private fun changeWriteSettingsPermission(context: Context) {
        val intent = Intent(Settings.ACTION_MANAGE_WRITE_SETTINGS)
        context.startActivity(intent)
    }

    // This function only take effect in real physical android device,
    // it can not take effect in android emulator.
    private fun changeScreenBrightness(
        context: Context,
        screenBrightnessValue: Int
    ) {   // Change the screen brightness change mode to manual.
        Settings.System.putInt(
            context.contentResolver,
            Settings.System.SCREEN_BRIGHTNESS_MODE,
            Settings.System.SCREEN_BRIGHTNESS_MODE_MANUAL
        )
        // Apply the screen brightness value to the system, this will change
        // the value in Settings ---> Display ---> Brightness level.
        // It will also change the screen brightness for the device.
        Settings.System.putInt(
            context.contentResolver, Settings.System.SCREEN_BRIGHTNESS, screenBrightnessValue
        )

    }
    @RequiresApi(Build.VERSION_CODES.M)
    private fun hasWriteSettingsPermission(context: Context): Boolean {
        var ret = true
        // Get the result from below code.
        ret = Settings.System.canWrite(context)
        return ret
    }

    private var LightListener = object : SensorEventListener {
        override fun onAccuracyChanged(sensor: Sensor, accuracy: Int) {
        }
        @RequiresApi(Build.VERSION_CODES.M)
        override fun onSensorChanged(event: SensorEvent) {
            val params = this@LoginActivity.window.attributes
            if (event.sensor.type == Sensor.TYPE_GYROSCOPE) {
                if(event.values[2] > 50) { // anticlockwise

                    // If do not have then open the Can modify system settings panel.
                    // Check whether has the write settings permission or not.
                    val settingsCanWrite = hasWriteSettingsPermission(this@LoginActivity)
                    if (!settingsCanWrite) {
                        changeWriteSettingsPermission(this@LoginActivity)
                    } else {
                        changeScreenBrightness(this@LoginActivity, 1)
                    }
                } else if(event.values[2] < 120) { // clockwise
                    val settingsCanWrite = hasWriteSettingsPermission(this@LoginActivity)
                    if (!settingsCanWrite) {
                        changeWriteSettingsPermission(this@LoginActivity)
                    } else {
                        changeScreenBrightness(this@LoginActivity, 255)
                    }
                }
            }
        }
}