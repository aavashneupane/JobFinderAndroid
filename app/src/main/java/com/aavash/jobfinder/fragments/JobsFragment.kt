package com.aavash.jobfinder.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.aavash.jobfinder.R
import com.aavash.jobfinder.adapter.AppliedAdapter
import com.aavash.jobfinder.entity.Applied
import com.aavash.jobfinder.userRepository.appliedRepository
import com.aavash.jobfinder.userRepository.jobRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext



class JobsFragment : Fragment() {

    private var appliedList = mutableListOf<Applied>()
    private lateinit var rvApplied: RecyclerView


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        val root = inflater.inflate(R.layout.fragment_jobs, container, false)
        rvApplied=root.findViewById(R.id.rvApplied)

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val repository= appliedRepository()
                val response=repository.getAppliedJobs()
                Log.d("jobResponse", response.data?.get(0)?.jobid.toString())
                if (response.success==true){

                    withContext(Dispatchers.Main) {
                        appliedList = response.data!!
                        rvApplied.adapter = context?.let { AppliedAdapter(it, appliedList) }
                        rvApplied.layoutManager = LinearLayoutManager(context)
                    }
                }
            }
            catch (ex:Exception){
                withContext(Dispatchers.Main) {
                    Toast.makeText(
                            context,
                            "Not Applied in any jobs!!!", Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }

        return root

    }



}