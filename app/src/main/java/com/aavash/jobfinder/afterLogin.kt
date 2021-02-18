package com.aavash.jobfinder

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.aavash.jobfinder.adapter.JobAdapter
import com.aavash.jobfinder.entity.job
import com.aavash.jobfinder.userRepository.jobRepository
import kotlinx.coroutines.*

class afterLogin : AppCompatActivity() {
    lateinit var rvDisplayStudents : RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_after_login)
        rvDisplayStudents = findViewById(R.id.rvJobs)
        CoroutineScope(Dispatchers.IO).launch {
            val repository = jobRepository()
            val response = repository.getJobs()
            val lst = response.data
            withContext(Dispatchers.Main){
                val adapter = JobAdapter(lst as ArrayList<job>,this@afterLogin)
                rvDisplayStudents.adapter=adapter
                rvDisplayStudents.layoutManager = LinearLayoutManager(this@afterLogin)
            }
        }

    }


}