package com.aavash.jobfinder

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.aavash.jobfinder.fragments.HomeFragment
import com.aavash.jobfinder.fragments.JobsFragment
import com.aavash.jobfinder.fragments.ProfileFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val HomeFragment=HomeFragment()
        val JobsFragment=JobsFragment()
        val ProfileFragment=ProfileFragment()

        makeCurrentFragment(HomeFragment)
        bottomNavigationView.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.icHome->makeCurrentFragment(HomeFragment)
                R.id.icJobs->makeCurrentFragment(JobsFragment)
                R.id.icProfile->makeCurrentFragment(ProfileFragment)
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