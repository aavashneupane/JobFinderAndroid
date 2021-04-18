package com.aavash.jobfinder

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.WindowManager
import android.widget.FrameLayout
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.aavash.jobfinder.fragments.ExtraFragment
//import com.aavash.jobfinder.fragments.ExtraFragment
import com.aavash.jobfinder.fragments.HomeFragment
import com.aavash.jobfinder.fragments.JobsFragment
import com.aavash.jobfinder.fragments.ProfileFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(),SensorEventListener {
    private var sensorManager: SensorManager? = null
    private var mProximitySensor: Sensor? = null
    private var gyroscopeSensor: Sensor? = null
    lateinit var homeFragment: HomeFragment
    lateinit var jobsfragment: JobsFragment
    lateinit var profileFragment: ProfileFragment
    lateinit var ExtraFragment: ExtraFragment
    lateinit var proximitySensor: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        proximitySensor = findViewById(R.id.proximitySensor)

        val HomeFragment=HomeFragment()
        val JobsFragment=JobsFragment()
        val ProfileFragment=ProfileFragment()
        val ExtraFragment=ExtraFragment()

        //Sensor Implementation
        sensorManager = applicationContext.getSystemService(Context.SENSOR_SERVICE) as SensorManager?
        mProximitySensor = sensorManager!!.getDefaultSensor(Sensor.TYPE_PROXIMITY)
        gyroscopeSensor = sensorManager!!.getDefaultSensor(Sensor.TYPE_GYROSCOPE);

        if (mProximitySensor == null) {
            proximitySensor.text = "No Proximity Sensor!"
        } else {
            sensorManager!!.registerListener(
                proximitySensorEventListener,
                mProximitySensor,
                SensorManager.SENSOR_DELAY_NORMAL
            )
        }
        sensorManager!!.registerListener(gyroscopeSensorListener,gyroscopeSensor,SensorManager.SENSOR_DELAY_NORMAL)

        makeCurrentFragment(HomeFragment)
        bottomNavigationView.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.icHome->makeCurrentFragment(HomeFragment)
                R.id.icJobs->makeCurrentFragment(JobsFragment)
                R.id.icProfile->makeCurrentFragment(ProfileFragment)
                R.id.icExtra->makeCurrentFragment(ExtraFragment)
                //R.id.ic->makeCurrentFragment(ExtraFragment)

            }
            true
        }

//        if(!checkSensor())
//        return
//        else{
////            sensor=sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
////            sensorManager.registerListener(this,sensor,SensorManager.SENSOR_DELAY_NORMAL)
//        }



    }
    private fun makeCurrentFragment(fragment: Fragment)=
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.fl_layout,fragment)
                commit()
            }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {


    }

    override fun onSensorChanged(event: SensorEvent?) {
        val values=event!!.values
        val xAxis=values[0]
        val yAxis=values[1]
        val zAxis=values[2]

        if (zAxis >0.5){
            Toast.makeText(
                this@MainActivity,
                "Accelerometer triggered",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

//    private fun checkSensor():Boolean{
//        var flag=true
//        if (sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)==null){
//            flag=false
//        }
//        return flag
//    }
private var proximitySensorEventListener = object : SensorEventListener {
    override fun onAccuracyChanged(sensor: Sensor, accuracy: Int) {
    }

    override fun onSensorChanged(event: SensorEvent) {
        val params = this@MainActivity.window.attributes
        if (event.sensor.type == Sensor.TYPE_PROXIMITY) {

            if (event.values[0] == 0f) {
                params.flags = params.flags or WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON
                params.screenBrightness = 0f
                window.attributes = params
                Log.d("low Fragment", "low brightness")
            } else {
                params.flags = params.flags or WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON
                params.screenBrightness = -1f
                window.attributes = params

            }
        }
    }
}
    private var gyroscopeSensorListener = object : SensorEventListener {
        override fun onAccuracyChanged(sensor: Sensor, accuracy: Int) {

        }
        override fun onSensorChanged(event: SensorEvent) {
            val params = this@MainActivity.window.attributes
            if (event.sensor.type == Sensor.TYPE_GYROSCOPE) {
                if(event.values[2] > 0.5f) { // anticlockwise
                    //Toast.makeText(this@MainActivity,"message",Toast.LENGTH_SHORT).show()
                } else if(event.values[2] < -0.5f) { // clockwise
                    //Toast.makeText(this@MainActivity,"text",Toast.LENGTH_SHORT).show()
                }
//                val rotationMatrix = FloatArray(16)
//                SensorManager.getRotationMatrixFromVector(
//                    rotationMatrix, event.values
//                )
//                // Remap coordinate system
//
//                // Remap coordinate system
//                val remappedRotationMatrix = FloatArray(16)
//                SensorManager.remapCoordinateSystem(
//                    rotationMatrix,
//                    SensorManager.AXIS_X,
//                    SensorManager.AXIS_Z,
//                    remappedRotationMatrix
//                )
//                // Convert to orientations
//
//                // Convert to orientations
//                val orientations = FloatArray(3)
//                SensorManager.getOrientation(remappedRotationMatrix, orientations)
//                for (i in 0..2) {
//                    orientations[i] =
//                        Math.toDegrees(orientations.get(i).toDouble()).toFloat()
//                }
//
//                if(orientations[2] > 45) {
//                   Toast.makeText(this@MainActivity,"Gyro right", Toast.LENGTH_SHORT).show()
//
//                } else if(orientations[2] < -45) {
//                    Toast.makeText(this@MainActivity,"Gyro Left", Toast.LENGTH_SHORT).show()
//                }
            }
        }
    }

}


