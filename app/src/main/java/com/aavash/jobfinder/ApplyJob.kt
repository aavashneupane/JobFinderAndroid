package com.aavash.jobfinder

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView

class ApplyJob : AppCompatActivity() {

    private lateinit var txtTest:TextView
//    private lateinit var jobdesc:TextView
//    private lateinit var jobtype:TextView
//    private lateinit var jobprice:TextView
//    private lateinit var requiredexperience:TextView
//    private lateinit var creator:TextView
//    private lateinit var createdat:TextView
//    private lateinit var buttonApply:Button

    override fun onCreate(savedInstanceState: Bundle?) {

      txtTest=findViewById(R.id.txtTest)
//        jobdesc=findViewById(R.id.jobdesc)
//        jobtype=findViewById(R.id.jobtype)
//        jobprice=findViewById(R.id.jobprice)
//        requiredexperience=findViewById(R.id.requiredexperience)
//        creator=findViewById(R.id.creator)
//        createdat=findViewById(R.id.createdat)
//        buttonApply=findViewById(R.id.buttonApply)


        super.onCreate(savedInstanceState)
        setContentView(R.layout.testlayout)

//        val id=intent.getStringExtra("id")
//
//        txtTest.setText(id)
//
//        if (id != null) {
//            Log.d("id",id)
//        }



    }
}