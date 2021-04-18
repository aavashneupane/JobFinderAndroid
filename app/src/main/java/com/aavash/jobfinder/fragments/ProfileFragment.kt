package com.aavash.jobfinder.fragments

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ImageView
import android.widget.PopupMenu
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.aavash.jobfinder.*
import com.aavash.jobfinder.Helper.Notification
import com.aavash.jobfinder.api.ServiceBuilder
import com.aavash.jobfinder.entity.Job
//import com.aavash.jobfinder.db.UserDB
import com.aavash.jobfinder.userRepository.UserRepository
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream
import java.text.SimpleDateFormat
import java.util.*


class ProfileFragment : Fragment() {
    private lateinit var tvFirstName:TextView
    private lateinit var tvLastName:TextView
    private lateinit var tvProfileUserbio:TextView
    private lateinit var tvProfilephone:TextView
    private lateinit var tvProfileAge:TextView
    private lateinit var tvProfileAddress:TextView
    private lateinit var tvProfileExperience:TextView
    private lateinit var tvProfileProjects:TextView

    private lateinit var tvEmail: TextView
    private lateinit var btnEditProfile: TextView

    private lateinit var profileIMG:ImageView
    private lateinit var imgLogout:ImageView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {


        val root: View = inflater.inflate(R.layout.fragment_profile, container, false)


        tvFirstName = root.findViewById(R.id.tvFirstName)

        tvProfileUserbio = root.findViewById(R.id.tvProfileUserbio)
        tvProfilephone = root.findViewById(R.id.tvProfilephone)
        tvProfileAge = root.findViewById(R.id.tvProfileAge)
        tvProfileAddress = root.findViewById(R.id.tvProfileAddress)
        tvProfileExperience = root.findViewById(R.id.tvProfileExperience)
        tvProfileProjects = root.findViewById(R.id.tvProfileProjects)
        tvEmail = root.findViewById(R.id.tvemailProfile)
        btnEditProfile = root.findViewById(R.id.btnEditProfile)
        profileIMG=root.findViewById(R.id.profileIMG)
        imgLogout=root.findViewById(R.id.imgLogout)


        btnEditProfile.setOnClickListener {

//            val a= Notification
//            context?.let { it1 -> a.givenotification(it1,"ooooo") }
            startActivity(
                    Intent(
                            context,
                            UpdateProfile::class.java
                    )
            )

        }


        CoroutineScope(Dispatchers.Main).launch {


                val repository = UserRepository()
                val response = repository.getUser()


                if (response.success==true) {
                    Toast.makeText(
                            context,
                            response.data!!.email, Toast.LENGTH_SHORT
                    ).show()

                    val a=(response.data!!.firstname)

                    val b=(response.data!!.lastname)
                    val name=a+" "+b

                    tvEmail.setText(response.data!!.email)
                    tvProfileUserbio.setText(response.data!!.userbio)
                    tvProfileAddress.setText(response.data!!.address)
                    tvProfilephone.setText(response.data!!.phone)
                    tvProfileAge.setText("Age: "+response.data!!.age)
                    tvProfileExperience.setText(response.data!!.experience)
                    tvProfileProjects.setText(response.data!!.projects)
                    tvProfileAddress.setText(response.data!!.address)
                    tvFirstName.setText(name)
                    val id=(response.data!!._id)

                    val imagePath = ServiceBuilder.loadImagePath() + response.data.photo!!.replace("\\", "/");


                    //load image with Glide library
                    context?.let {
                        Glide.with(it)
                            .load(imagePath)
                            .into(profileIMG)
                    }


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

                        Toast.makeText(
                                context,
                                "Error", Toast.LENGTH_SHORT
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

    @RequiresApi(Build.VERSION_CODES.O)
    fun logout(){
        val a= Notification

        context?.let { a.givenotification(it,"You have successfully logged out.") }

        context?.deleteSharedPreferences("MyPref");
    }




}