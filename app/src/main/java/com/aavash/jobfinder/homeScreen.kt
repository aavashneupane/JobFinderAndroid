package com.aavash.jobfinder

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.aavash.jobfinder.fragments.*
import com.google.android.material.bottomnavigation.BottomNavigationView


class homeScreen : AppCompatActivity() {

    private lateinit var bottomNavigation: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_screen)

//
//        val homeFragment= HomeFragment()
//        val jobsFragment=JobsFragment()
//        val profileFragment=ProfileFragment()
//
//        makeCurrentFragment(homeFragment)
//
//        bottomNavigation=findViewById(R.id.bottomNavigation)
//        bottomNavigation.setOnNavigationItemSelectedListener {item->
//            when(item.itemId){
//                R.id.icHome ->{makeCurrentFragment(homeFragment)
//                    true
//                }
//                R.id.icJobs ->{makeCurrentFragment(jobsFragment)
//                    true
//                }
//                R.id.icProfile ->{makeCurrentFragment(profileFragment)
//                    true
//                }
//
//                else -> false
//            }
//        }

    }
//    private fun makeCurrentFragment(fragment: Fragment) {
//        supportFragmentManager.beginTransaction().apply {
//            replace(R.id.linear, fragment)
//            setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
//            commit()
//        }
//    }
}