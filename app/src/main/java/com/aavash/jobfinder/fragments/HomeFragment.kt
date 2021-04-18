package com.aavash.jobfinder.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.aavash.jobfinder.R
import com.aavash.jobfinder.adapter.AppliedAdapter
import com.aavash.jobfinder.adapter.JobAdapter

import com.aavash.jobfinder.entity.Applied
import com.aavash.jobfinder.entity.Job
import com.aavash.jobfinder.userRepository.appliedRepository
import com.aavash.jobfinder.userRepository.jobRepository
import com.denzcoskun.imageslider.ImageSlider
import com.denzcoskun.imageslider.models.SlideModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class HomeFragment : Fragment() {

    private var JobList = mutableListOf<Job>()
    private lateinit var rvJobs: RecyclerView
    private lateinit var imgSlider: ImageSlider
    val imageList=ArrayList<SlideModel>()

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {


        val root = inflater.inflate(R.layout.fragment_home, container, false)
        rvJobs=root.findViewById(R.id.rvJobs)
        imgSlider = root.findViewById(R.id.imgSlider)

        ViewCompat.setNestedScrollingEnabled(imgSlider, false)


        imageList.add(SlideModel("https://24option-france.com/wp-content/uploads/2019/07/job_search_online.jpg"))
        imageList.add(SlideModel("https://static3.bigstockphoto.com/3/6/6/large1500/6637292.jpg"))
        imageList.add(SlideModel("https://www.thebalancecareers.com/thmb/la332AU3WIapcB8yEiSJtog4f0g=/2121x1414/filters:no_upscale():max_bytes(150000):strip_icc()/GettyImages-1186821733-8293b36141c947c68f635ae24eecfaa3.jpg"))
        imgSlider.setImageList(imageList)


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
                            "No jobs!!!", Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }

        return root

    }



}