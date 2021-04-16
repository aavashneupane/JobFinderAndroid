package com.aavash.jobfinder

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.annotation.RequiresApi
import com.aavash.jobfinder.api.ServiceBuilder
import com.aavash.jobfinder.userRepository.UserRepository
import com.aavash.jobfinder.userRepository.jobRepository
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_splash.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class apply : AppCompatActivity() {
   lateinit var textJobname: TextView
        private lateinit var jobdesc:TextView
    private lateinit var jobtype:TextView
    private lateinit var jobprice:TextView
    private lateinit var requiredexperience:TextView
    private lateinit var creator:TextView
    private lateinit var createdat:TextView
    private lateinit var buttonApply: Button
    private lateinit var pimage: ImageView

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_apply)
        textJobname=findViewById(R.id.textJobname)
        jobdesc=findViewById(R.id.jobdesc)
        jobtype=findViewById(R.id.jobtype)
        jobprice=findViewById(R.id.jobprice)
        requiredexperience=findViewById(R.id.requiredexperience)
        creator=findViewById(R.id.creator)
        createdat=findViewById(R.id.createdat)
        buttonApply=findViewById(R.id.buttonApply)
        pimage=findViewById(R.id.pimage)


        val id=intent.getStringExtra("id")
//        textJobname.setText(id)
        if (id != null) {
            getValue(id)
        }


        }

    @RequiresApi(Build.VERSION_CODES.O)
    fun applyForJob(id: String, a:String){
        CoroutineScope(Dispatchers.Main).launch {
            try {

                val repository = jobRepository()
                val response = repository.applyJob(id)
                if (response.success==true) {
                    Toast.makeText(
                        this@apply,
                        "Applied successfully", Toast.LENGTH_SHORT
                    ).show()
                    val b= com.aavash.jobfinder.Helper.Notification

                    b.givenotification(this@apply,"Booked for $a job.")

                } else {
                    withContext(Dispatchers.Main) {
                        Toast.makeText(
                            this@apply,
                            "Cannot apply.", Toast.LENGTH_SHORT
                        ).show()
                    }
                }

            } catch (ex: Exception) {
                withContext(Dispatchers.Main) {
                    Toast.makeText(
                        this@apply,
                        "Error", Toast.LENGTH_SHORT
                    ).show()

                }
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun getValue(id:String){
        CoroutineScope(Dispatchers.Main).launch {
            val repository = jobRepository()
            val response=repository.getSingleJob(id)

            if (response != null) {
                if (response.success==true){
                    textJobname.text = (response.data!!.jobtitle)
                    jobdesc.setText(response.data!!.jobdescription)
                    jobtype.setText(response.data!!.jobtype)
                    jobprice.setText(response.data!!.jobprice)
                    requiredexperience.setText(response.data!!.requiredexperience)
                    creator.setText(response.data!!.creator?.company)
                    createdat.setText(response.data!!.createdAt)
//
//                    //to set image
                    val imagePath = ServiceBuilder.loadImagePath() + response.data!!.photo!!.replace("\\", "/");
//
//
//                    //load image with Glide library
                    Glide.with(this@apply)
                        .load(imagePath)
                        .into(this@apply.pimage)

                    var c=response.data!!.jobtitle
//                    ///dialog box
                    buttonApply.setOnClickListener {
                        val builder = android.app.AlertDialog.Builder(this@apply)
                        builder.setTitle("Apply for this job?")
                        builder.setMessage("Are you sure you want to apply in ${id}??")
                        builder.setIcon(android.R.drawable.ic_dialog_alert)
                        builder.setPositiveButton("Yes") { _, _ ->
                            if (id != null) {
                                if (c != null) {
                                    applyForJob(id,c)
                                }

                            }
                            Toast.makeText(this@apply, "Job applied successfully", Toast.LENGTH_SHORT).show()
                        }
                        builder.setNegativeButton("No") { _, _ ->
                            Toast.makeText(this@apply, "Action cancelled", Toast.LENGTH_SHORT).show()
                        }
                        val alertDialog: android.app.AlertDialog = builder.create()
                        alertDialog.setCancelable(false)
                        alertDialog.show()

                    }

                } else {
                    withContext(Dispatchers.Main) {
                        Toast.makeText(
                            this@apply,
                            "Error", Toast.LENGTH_SHORT
                        ).show()

                    }

                }
            }
        }
    }

    }


