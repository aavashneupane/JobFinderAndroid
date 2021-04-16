package com.aavash.jobfinder

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.FrameLayout
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

    lateinit var homeFragment: HomeFragment
    lateinit var jobsfragment: JobsFragment
    lateinit var profileFragment: ProfileFragment
    lateinit var ExtraFragment: ExtraFragment
    lateinit var sensorManager: SensorManager
    private var sensor:Sensor?=null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val HomeFragment=HomeFragment()
        val JobsFragment=JobsFragment()
        val ProfileFragment=ProfileFragment()
        val ExtraFragment=ExtraFragment()
        sensorManager=getSystemService(Context.SENSOR_SERVICE) as SensorManager

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

        if(!checkSensor())
        return
        else{
            sensor=sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
            sensorManager.registerListener(this,sensor,SensorManager.SENSOR_DELAY_NORMAL)
        }



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

    private fun checkSensor():Boolean{
        var flag=true
        if (sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)==null){
            flag=false
        }
        return flag
    }
}


