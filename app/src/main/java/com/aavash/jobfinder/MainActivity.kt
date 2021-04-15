package com.aavash.jobfinder

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.FrameLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
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
    lateinit var ExtraFragment: ProfileFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val HomeFragment=HomeFragment()
        val JobsFragment=JobsFragment()
        val ProfileFragment=ProfileFragment()
        val ExtraFragment=ProfileFragment()

        makeCurrentFragment(HomeFragment)
        bottomNavigationView.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.icHome->makeCurrentFragment(HomeFragment)
                R.id.icJobs->makeCurrentFragment(JobsFragment)
                R.id.icProfile->makeCurrentFragment(ProfileFragment)
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


        //new
//        var bottomnav = findViewById<BottomNavigationView>(R.id.bottomNavigationView)
//        var frame = findViewById<FrameLayout>(R.id.fl_layout)
//        //Now let's the deffault Fragment
//        homeFragment = HomeFragment()
//        supportFragmentManager
//                .beginTransaction()
//                .replace(R.id.fl_layout,homeFragment)
//                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
//                .commit()
//        //now we will need to create our different fragemnts
//        //Now let's add the menu evenet listener
//        bottomnav.setOnNavigationItemSelectedListener { item ->
//            //we will select each menu item and add an event when it's selected
//            when(item.itemId){
//                R.id.icHome -> {
//                    homeFragment = HomeFragment()
//                    supportFragmentManager
//                            .beginTransaction()
//                            .replace(R.id.fl_layout,homeFragment)
//                            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
//                            .commit()
//                }
//                R.id.icJobs -> {
//                    jobsfragment = JobsFragment()
//                    supportFragmentManager
//                            .beginTransaction()
//                            .replace(R.id.fl_layout,jobsfragment)
//                            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
//                            .commit()
//                }
//
//
//
//                R.id.icProfile -> {
//                    profileFragment = ProfileFragment()
//                    supportFragmentManager
//                            .beginTransaction()
//                            .replace(R.id.fl_layout,profileFragment)
//                            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
//                            .commit()
//                }
//
//
//            }
//
//            true
//        }
//
//
//    }
//}