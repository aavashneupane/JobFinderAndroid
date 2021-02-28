package com.aavash.jobfinder

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.Toast
import com.aavash.jobfinder.api.ServiceBuilder
//import com.aavash.jobfinder.db.UserDB
import com.aavash.jobfinder.entity.User
import com.aavash.jobfinder.userRepository.UserRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class registration : AppCompatActivity(), View.OnClickListener {

    private lateinit var atvUsernameReg: AutoCompleteTextView
    private lateinit var ageReg: AutoCompleteTextView
    private lateinit var countryReg: AutoCompleteTextView
    private lateinit var phoneReg: AutoCompleteTextView
    private lateinit var atvEmailReg: AutoCompleteTextView
    private lateinit var atvPasswordReg: AutoCompleteTextView

    private lateinit var btnSignUp: Button
    private lateinit var btnSignIn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)

        btnSignIn = findViewById(R.id.btnSignIn)
        btnSignUp = findViewById(R.id.btnSignUp)

        atvUsernameReg = findViewById(R.id.atvUsernameReg)
        ageReg = findViewById(R.id.ageReg)
        countryReg = findViewById(R.id.countryReg)
        phoneReg = findViewById(R.id.phoneReg)
        atvEmailReg = findViewById(R.id.atvEmailReg)
        atvPasswordReg = findViewById(R.id.atvPasswordReg)

        btnSignUp.setOnClickListener {
            if (isValid()){

                val fullname = atvUsernameReg.text.toString()
                val age = ageReg.text.toString()
                val country = countryReg.text.toString()
                val phone = phoneReg.text.toString()
                val email = atvEmailReg.text.toString()
                val password = atvPasswordReg.text.toString()


                val user = User(fullname=fullname, age=age, country=country,phone=phone, email=email, password= password)
                CoroutineScope(Dispatchers.IO).launch {
                        val repository = UserRepository()
                        val response = repository.registerUser(user)
                        if(response.success==true){
                            ServiceBuilder.token = response.token
                            withContext(Dispatchers.Main){
                                Toast.makeText(this@registration, "Success", Toast.LENGTH_LONG).show()

                            }
                        }else{
                            withContext(Dispatchers.Main){
                                Toast.makeText(this@registration, "Not Successfull", Toast.LENGTH_LONG).show()
                            }
                        }
                    }
//                        val intent=Intent(this,LoginActivity::class.java)
//
//                        startActivity(intent)
            }

        }

        btnSignIn.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }


    }

    private fun isValid(): Boolean {
        when {
            atvUsernameReg.text.isEmpty() -> {
                atvUsernameReg.error = "Field must not be empty"
                return false
            }
            ageReg.text.isEmpty() -> {
                ageReg.error = "Field must not be empty"
                return false
            }

            countryReg.text.isEmpty() -> {
                countryReg.error = "Field must not be empty"
                return false
            }
            phoneReg.text.isEmpty() -> {
                phoneReg.error = "Field must not be empty"
                return false
            }
            atvPasswordReg.text.isEmpty() ->{
                atvPasswordReg.error = "Field must not be empty"
                return false
            }

            atvEmailReg.text.isEmpty() -> {
                atvEmailReg.error = "Field must not be empty"
                return false
            }
        }
        return true


    }

    override fun onClick(v: View?) {
        TODO("Not yet implemented")
    }
}