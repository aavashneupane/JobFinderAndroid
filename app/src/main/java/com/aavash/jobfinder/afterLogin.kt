package com.aavash.jobfinder

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class afterLogin : AppCompatActivity() {
    lateinit var rvDisplayStudents : RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_after_login)
      //  rvDisplayStudents = findViewById(R.id.rvJobs)
        //for api

//        CoroutineScope(Dispatchers.IO).launch {
//            val repository = jobRepository()
//            val response = repository.getJobs()
//            val lst = response.data
//            withContext(Dispatchers.Main){
//                val adapter = JobAdapter(lst as ArrayList<Job>,this@afterLogin)
//                rvDisplayStudents.adapter=adapter
//                rvDisplayStudents.layoutManager = LinearLayoutManager(this@afterLogin)
//            }
//        }
        //for db

//        CoroutineScope(Dispatchers.IO).launch {
////            val lstStudents = StudentDB(this@ViewStudentsActivity).getStudentDAO().getAllStudents()
//            val lstJobs =
//                JobDB.getInstance(this@afterLogin)
//                    .getJobDAO().getAllJobs()
//
//
//            withContext(Dispatchers.Main){
//
////                rvDisplayStudents.adapter = JobAdapter(this@afterLogin,lstJobs)
//                rvDisplayStudents.layoutManager = LinearLayoutManager(this@afterLogin)
//
//            }
//        }

    }


}