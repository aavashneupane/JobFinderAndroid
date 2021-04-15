package com.aavash.jobfinder

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.FrameLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.aavash.jobfinder.fragments.ExtraFragment
//import com.aavash.jobfinder.fragments.ExtraFragment
import com.aavash.jobfinder.fragments.HomeFragment
import com.aavash.jobfinder.fragments.JobsFragment
import com.aavash.jobfinder.fragments.ProfileFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var homeFragment: HomeFragment
    lateinit var jobsfragment: JobsFragment
    lateinit var profileFragment: ProfileFragment
    lateinit var ExtraFragment: ExtraFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val HomeFragment=HomeFragment()
        val JobsFragment=JobsFragment()
        val ProfileFragment=ProfileFragment()
        val ExtraFragment=ExtraFragment()

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

    }
    private fun makeCurrentFragment(fragment: Fragment)=
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.fl_layout,fragment)
                commit()
            }
}


