package com.aavash.jobfinder.fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.aavash.jobfinder.LoginActivity
import com.aavash.jobfinder.MainActivity
import com.aavash.jobfinder.R
import com.aavash.jobfinder.api.ServiceBuilder
import com.aavash.jobfinder.db.UserDB
import com.aavash.jobfinder.userRepository.UserRepository
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class ProfileFragment : Fragment() {
    private lateinit var tvFirstName:TextView
    private lateinit var tvLastName:TextView
    private lateinit var tvEmail: TextView
    private lateinit var imgLogout:ImageView




    var firstname:String=""
    var lastname:String=""
    var email:String=""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {


        val root: View = inflater.inflate(R.layout.fragment_profile, container, false)
        tvFirstName = root.findViewById(R.id.tvFirstName)
        tvEmail = root.findViewById(R.id.tvemailProfile)
        imgLogout=root.findViewById(R.id.imgLogout)

//        val userdao = context?.let {
//            UserDB.getInstance(it)
//                    .getUserDAO()
//        }
//        val repo = UserRepository(userdao!!)
//
//        profileViewModel =
//                ViewModelProvider(this, ProfileViewModelFactory(repo)).get(ProfileViewModel::class.java)
//
//        profileViewModel.getLogggedInUser()
//
//        profileViewModel.user.observe(viewLifecycleOwner, Observer { user ->
//           // tvFirstName.text = user.firstname
//            tvEmail.text = user.email
//        })

        CoroutineScope(Dispatchers.IO).launch {
            try {
//                user= UserDB.getInstance(this@LoginActivity)
//                        .getUserDAO().checkUser(email,password)
//                val userdao =UserDB.getInstance(this@LoginActivity)
//                        .getUserDAO()
                val repository = UserRepository()
                val response = repository.checkUser()
                if (response.success==true) {
                    Log.i("em",email)
                    Log.i("pw",password)
                    createNotification(email)

                    ServiceBuilder.token = "Bearer " + response.token

                    //to save user details

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

                }
            }
        }



        imgLogout.setOnClickListener {
            logout()
            Toast.makeText(activity, "Logged out successfully", Toast.LENGTH_LONG).show()

            startActivity(
                    Intent(
                            activity,
                            LoginActivity::class.java
                    )
            )

        }

        return root



    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }

    fun logout(){
        context?.deleteSharedPreferences("MyPref");
    }

}