package com.aavash.jobfinder

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.aavash.jobfinder.api.ServiceBuilder
import com.aavash.jobfinder.entity.User
import com.aavash.jobfinder.userRepository.UserRepository
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class UpdateProfile : AppCompatActivity() {

    private lateinit var tvUpdateFirstName:TextView
    private lateinit var tvUpdateLastName:TextView
    private lateinit var tvUpdateAge:TextView
    private lateinit var tvUpdatePhone:TextView
    private lateinit var tvUpdateAddress:TextView
    private lateinit var tvUpdateExperience:TextView
    private lateinit var tvUpdateProjects:TextView
    private lateinit var tvUpdateUserbio:TextView
    private lateinit var tvUpdateEmail:TextView
    private lateinit var btnApplyEdit:Button
    private lateinit var imgUpdateProf:ImageView
    public  lateinit var id:String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_profile)



        tvUpdateUserbio = findViewById(R.id.tvUpdateUserbio)
        tvUpdatePhone = findViewById(R.id.tvUpdatePhone)
        tvUpdateAge = findViewById(R.id.tvUpdateAge)
        tvUpdateAddress =findViewById(R.id.tvUpdateAddress)
        tvUpdateEmail =findViewById(R.id.tvUpdateEmail)
        tvUpdateExperience =findViewById(R.id.tvUpdateExperience)
        tvUpdateProjects = findViewById(R.id.tvUpdateProjects)
        tvUpdateUserbio = findViewById(R.id.tvUpdateUserbio)
        btnApplyEdit = findViewById(R.id.btnApplyEdit)

        tvUpdateFirstName=findViewById(R.id.tvUpdateFirstName)
        tvUpdateLastName=findViewById(R.id.tvUpdateLastName)
        imgUpdateProf=findViewById(R.id.imgUpdateProf)
        btnApplyEdit=findViewById(R.id.btnApplyEdit)


        CoroutineScope(Dispatchers.Main).launch {


            val repository = UserRepository()
            val response = repository.getUser()




            id =response.data!!._id.toString()

            if (response.success==true) {

                tvUpdateFirstName.setText(response.data!!.firstname)
                tvUpdateLastName.setText(response.data!!.lastname)
                tvUpdateUserbio.setText(response.data!!.userbio)
                tvUpdateAddress.setText(response.data!!.address)
                tvUpdatePhone.setText(response.data!!.phone)
                tvUpdateEmail.setText(response.data!!.email)
                tvUpdateAge.setText(response.data!!.age)
                tvUpdateExperience.setText(response.data!!.experience)
                tvUpdateProjects.setText(response.data!!.projects)
                tvUpdateAddress.setText(response.data!!.address)

                val imagePath = ServiceBuilder.loadImagePath() + response.data.photo!!.replace("\\", "/");


                //load image with Glide library
                this@UpdateProfile?.let {
                    Glide.with(it)
                        .load(imagePath)
                        .into(imgUpdateProf)
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
                        this@UpdateProfile,
                        "Error", Toast.LENGTH_SHORT
                    ).show()
                }
            }


        }

        btnApplyEdit.setOnClickListener {
            CoroutineScope(Dispatchers.Main).launch {


                val repository = UserRepository()
                val firstname = tvUpdateFirstName.text.toString()
                val lastname = tvUpdateLastName.text.toString()
                val age = tvUpdateAge.text.toString()
                val address = tvUpdateAddress.text.toString()
                val phone = tvUpdatePhone.text.toString()
                val projects = tvUpdateProjects.text.toString()
                val experience = tvUpdateExperience.text.toString()
                val userbio = tvUpdateUserbio.text.toString()

                val response = repository.editUser(id,firstname,lastname,age,address,phone,projects,experience,userbio)


                if (response.success==true) {
                    Toast.makeText(
                        this@UpdateProfile,
                        response.data!!.firstname, Toast.LENGTH_SHORT
                    ).show()


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
                            this@UpdateProfile,
                            "Error", Toast.LENGTH_SHORT
                        ).show()
                    }
                }


            }


        }




    }
}