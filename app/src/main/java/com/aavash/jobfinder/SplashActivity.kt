package com.aavash.jobfinder

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.AutoCompleteTextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch



class SplashActivity : AppCompatActivity() {

    private lateinit var atvEmailLog: AutoCompleteTextView
    private lateinit var atvPasswordLog: AutoCompleteTextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)





        CoroutineScope(Dispatchers.Main).launch {
            delay(2000)

            startActivity(Intent(this@SplashActivity, LoginActivity::class.java))
//            putExtra(atvEmailLog,toString());
//            putExtra(atvPasswordLog,toString());
            finish()
        }





    }



}