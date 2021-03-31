package com.aavash.jobfinder.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.aavash.jobfinder.R
import com.aavash.jobfinder.adapter.JobAdapter
import com.aavash.jobfinder.db.JobDB
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class HomeFragment : Fragment() {
    lateinit var rvDisplayStudents : RecyclerView
    private var param1 : String? =null
    private var param2 : String? = null


    override fun onCreate(  savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }


    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val view= inflater.inflate(R.layout.fragment_home, container, false)

//        CoroutineScope(Dispatchers.IO).launch {
////            val lstStudents = StudentDB(this@ViewStudentsActivity).getStudentDAO().getAllStudents()
//            val lstJobs =
//                    JobDB.getInstance(this@HomeFragment)
//                            .getJobDAO().getAllJobs()
//
//
//            withContext(Dispatchers.Main){
//
//                rvDisplayStudents.adapter = JobAdapter(this@HomeFragment,lstJobs)
//                rvDisplayStudents.layoutManager = LinearLayoutManager(this@afterLogin)
//
//            }
//        }


        return view

    }

    companion object{
        fun newInstance(param1: String, param2: String) =
                HomeFragment().apply {
                    arguments = Bundle().apply {
                        putString(ARG_PARAM1, param1)
                        putString(ARG_PARAM2, param2)
                    }
                }
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
}