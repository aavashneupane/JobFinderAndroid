package com.aavash.jobfinder

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.Toast
import com.aavash.jobfinder.db.UserDB
import com.aavash.jobfinder.entity.User
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LoginActivity : AppCompatActivity(),View.OnClickListener{

    private lateinit var atvEmailLog:AutoCompleteTextView
    private lateinit var atvPasswordLog:AutoCompleteTextView
    private lateinit var btnSignIn:Button
    private lateinit var btnSignUp:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)


        atvEmailLog=findViewById(R.id.atvEmailLog)
        atvPasswordLog=findViewById(R.id.atvPasswordLog)

        btnSignIn=findViewById(R.id.btnSignIn)
        btnSignUp=findViewById(R.id.btnSignUp)

        btnSignIn.setOnClickListener{
            login()
        }

        btnSignUp.setOnClickListener {
            val intent=Intent(this,registration::class.java)
                startActivity(intent)
        }

    }

    private fun login() {
        val username = atvEmailLog.text.toString()
        val password = atvPasswordLog.text.toString()

        var user: User? = null
        CoroutineScope(Dispatchers.IO).launch {
            user = UserDB
                .getInstance(this@LoginActivity)
                .getUserDAO()
                .checkUser(username, password)
            if (user == null) {
                withContext(Dispatchers.Main) {
                    Toast.makeText(this@LoginActivity, "Invalid credentials", Toast.LENGTH_SHORT)
                        .show()
                }
            } else {
                startActivity(
                    Intent(
                        this@LoginActivity,
                        homeScreen::class.java
                    )
                )
            }
        }

    }

    override fun onClick(v: View?) {

    }


//    override fun onClick(v: View?) {
//
//        when(v?.id){
//            R.id.btnSignIn->{
//                val intent=Intent(this,homeScreen::class.java)
//                startActivity(intent)
//            }
//            R.id.btnSignUp->{
//                val intent=Intent(this,registration::class.java)
//                startActivity(intent)
//            }
//
//        }
//
//    }
}