package com.aavash.jobfinder

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.aavash.jobfinder.db.JobDB
import com.aavash.jobfinder.entity.Job
import com.aavash.jobfinder.userRepository.jobRepository
import kotlinx.coroutines.*

class UpdateJobActivity : AppCompatActivity() {

    lateinit var etFullNameUpdate : EditText
    lateinit var etAgeUpdate: EditText
 //   lateinit var etAddressUpdate: EditText
    lateinit var btnUpdate: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_job)
        etFullNameUpdate = findViewById(R.id.etFullNameUpdate)
        etAgeUpdate = findViewById(R.id.etAgeUpdate)
        btnUpdate = findViewById(R.id.btnUpdate)


        btnUpdate.setOnClickListener{
//           for api
//            val job = intent.getParcelableExtra<Job>("job")
//            val jobs =
//                    Job(fullname = etFullNameUpdate.text.toString(), age = etAgeUpdate.text.toString().toInt())
//            CoroutineScope(Dispatchers.IO).launch {
//                val repository = jobRepository()
//                val response = repository.updateJob(job?._id!!,jobs)
//                if(response.success == true){
//
//                    withContext(Dispatchers.Main){
//                        Toast.makeText(this@UpdateJobActivity, "Success", Toast.LENGTH_SHORT).show()
//                    }
//                }else{
//                    withContext(Dispatchers.Main){
//                        Toast.makeText(this@UpdateJobActivity, "Error updating job", Toast.LENGTH_SHORT).show()
//                    }
//                }
//            }

            //for db
            val job =
                Job(fullName = etFullNameUpdate.text.toString(),
                    age = etAgeUpdate.text.toString().toInt())

            //job.stdId = intent!!.stdId

            CoroutineScope(Dispatchers.IO).launch {
                JobDB.getInstance(this@UpdateJobActivity)
                    .getJobDAO()
                    .updateJob(job)
//                withContext(Main){
                startActivity(Intent(this@UpdateJobActivity, afterLogin::class.java))
//                }
            }

        }


    }
}