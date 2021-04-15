package com.aavash.jobfinder.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.aavash.jobfinder.R
import com.aavash.jobfinder.adapter.AppliedAdapter
import com.aavash.jobfinder.adapter.JobAdapter
import com.aavash.jobfinder.entity.Job
import com.aavash.jobfinder.userRepository.appliedRepository
import com.aavash.jobfinder.userRepository.jobRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class HomeFragment : Fragment() {
    lateinit var rvJobs : RecyclerView
    private var JobList = mutableListOf<Job>()



    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val view= inflater.inflate(R.layout.fragment_home, container, false)
        rvJobs = view.findViewById(R.id.rvJobs)
      //  rvJobs.adapter?.notifyDataSetChanged()

        return view

    }



    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
//        CoroutineScope(Dispatchers.IO).launch {
//        try {
//            val repository = jobRepository()
//                val response = repository.getJobs()
//            if (response.success == true) {
//                JobList = response.data!!
//                withContext(Main){
//                    rvJobs.apply {
//                        layoutManager = GridLayoutManager(context,2)
//                        this.adapter = JobAdapter(JobList,context)
//                    }
//                }
//            }
//
//        } catch (ex: Exception) {
//            withContext(Dispatchers.Main) {
//                Toast.makeText(
//                        context,
//                        "No Jobs!!!", Toast.LENGTH_SHORT
//                ).show()
//            }
//        }
//    }
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val repository= jobRepository()
                val response=repository.getJobs()
                if (response.success==true){
                    withContext(Dispatchers.Main) {
                        JobList = response.data!!
                        rvJobs.adapter = context?.let { JobAdapter(it, JobList) }
                        rvJobs.layoutManager = LinearLayoutManager(context)
                    }
                }
            }
            catch (ex:Exception){
                withContext(Dispatchers.Main) {
                    Toast.makeText(
                            context,
                            "No jobs available right now!!!", Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
}
}
