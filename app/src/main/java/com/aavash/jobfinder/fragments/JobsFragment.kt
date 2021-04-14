package com.aavash.jobfinder.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.aavash.jobfinder.R
import com.aavash.jobfinder.entity.Applied
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


        return root



    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val repository=jobRepository()
                val response=repository.getAppliedJobs()
                if (response.success==true){
                    appliedList=response.data!!
                }

            }catch (ex:Exception){
                withContext(Dispatchers.Main) {
                    Toast.makeText(
                            context,
                            "Not Applied in any jobs!!!", Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }


}