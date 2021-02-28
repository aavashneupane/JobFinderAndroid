package com.aavash.jobfinder

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.aavash.jobfinder.entity.job
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
            val job = intent.getParcelableExtra<job>("job")
            val jobs =
                    job(fullname = etFullNameUpdate.text.toString(), age = etAgeUpdate.text.toString().toInt())
            CoroutineScope(Dispatchers.IO).launch {
                val repository = jobRepository()
                val response = repository.updateJob(job?._id!!,jobs)
                if(response.success == true){

                    withContext(Dispatchers.Main){
                        Toast.makeText(this@UpdateJobActivity, "Success", Toast.LENGTH_SHORT).show()
                    }
                }else{
                    withContext(Dispatchers.Main){
                        Toast.makeText(this@UpdateJobActivity, "Error updating job", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }


    }
}