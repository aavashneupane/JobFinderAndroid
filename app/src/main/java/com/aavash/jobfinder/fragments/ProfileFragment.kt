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
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class ProfileFragment : Fragment() {
    private lateinit var tvFirstName:TextView
    private lateinit var tvLastName:TextView
    private lateinit var tvEmail: TextView
    private lateinit var imgLogout:ImageView




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {


        val root: View = inflater.inflate(R.layout.fragment_profile, container, false)
        tvFirstName = root.findViewById(R.id.tvFirstName)
        //tvLastName = root.findViewById(R.id.tvLastName)

        tvEmail = root.findViewById(R.id.tvemailProfile)
        imgLogout=root.findViewById(R.id.imgLogout)
        //tvFirstName.setText("Vayo?")

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

                val repository = UserRepository()
                val response = repository.getUser()
                if (response.success==true) {
                    Toast.makeText(
                            context,
                            "error in success", Toast.LENGTH_SHORT
                    ).show()
                    tvFirstName.setText(response.user?.firstname)
//                    tvFirstName.setText("Vayo?")
                    response.user?.firstname?.let { Log.d("Firstname", it) }



                    tvEmail.setText(response.user!!.email)



                } else {
                    withContext(Dispatchers.Main) {
                        val snack =
                                Snackbar.make(
                                        linearLayout,
                                        "Unexpected error",
                                        Snackbar.LENGTH_LONG
                                )
                        snack.setAction("OK", View.OnClickListener {
                            snack.dismiss()

                        })
                        snack.show()
                        Toast.makeText(
                                context,
                                "Error after success", Toast.LENGTH_SHORT
                        ).show()
                    }
                }

            } catch (ex: Exception) {
                withContext(Dispatchers.Main) {
                    Toast.makeText(
                            context,
                            "Profile error", Toast.LENGTH_SHORT
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