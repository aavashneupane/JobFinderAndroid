package com.aavash.jobfinder.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.aavash.jobfinder.R
import com.aavash.jobfinder.entity.Job
import com.aavash.jobfinder.userRepository.jobRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class JobAdapter(
        private val context: Context,
        private val lstJobs: List<Job>
) : RecyclerView.Adapter<JobAdapter.JobViewHolder>() {

    class JobViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imgJob : ImageView=view.findViewById(R.id.imgJob)
        val jobtitle : TextView=view.findViewById(R.id.tvJobTitle)
        val jobtype : TextView=view.findViewById(R.id.tvJobType)
        val jobdescription : TextView=view.findViewById(R.id.tvJobdescription)
        val requiredexperience : TextView=view.findViewById(R.id.tvrequiredexperience)
        val jobprice : TextView=view.findViewById(R.id.tvJobprice)
        val creator : TextView=view.findViewById(R.id.tvCreator)
        val createdAt : TextView=view.findViewById(R.id.tvCreatedAt)
        val btnApply : Button=view.findViewById(R.id.btnApply)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JobViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.addjobrecycle, parent, false)
        return JobViewHolder(view)
    }

    override fun onBindViewHolder(holder: JobViewHolder, position: Int) {
        val jobs = lstJobs[position]
        holder.jobtitle.text = jobs.jobtitle
        holder.jobtype.text = jobs.jobtype
        holder.jobdescription.text = jobs.jobdescription
        holder.requiredexperience.text = jobs.requiredexperience
        holder.jobprice.text = jobs.jobprice
        //holder.creator.text = jobs.creator.toString()
        holder.createdAt.text = jobs.createdAt
        var id=jobs._id

        holder.btnApply.setOnClickListener {
            val builder = android.app.AlertDialog.Builder(context)
            builder.setTitle("Apply for this job?")
            builder.setMessage("Are you sure you want to apply in ${id}??")
            builder.setIcon(android.R.drawable.ic_dialog_alert)
            builder.setPositiveButton("Yes") { _, _ ->
                if (id != null) {
                    ApplyJob(id)
                }
                Toast.makeText(context, "Job applied successfully", Toast.LENGTH_SHORT).show()
            }
            builder.setNegativeButton("No") { _, _ ->
                Toast.makeText(context, "Action cancelled", Toast.LENGTH_SHORT).show()
            }
            val alertDialog: android.app.AlertDialog = builder.create()
            alertDialog.setCancelable(false)
            alertDialog.show()

        }

    }



    override fun getItemCount(): Int {
        return lstJobs.size
    }

    fun ApplyJob(id: String) {
        CoroutineScope(Dispatchers.Main).launch {
            try {

                val repository = jobRepository()
                val response = repository.applyJob(id)
                if (response.success==true) {
                    Toast.makeText(
                        context,
                        "Applied successfully", Toast.LENGTH_SHORT
                    ).show()

                } else {
                    withContext(Dispatchers.Main) {
                        Toast.makeText(
                            context,
                            "Cannot apply.", Toast.LENGTH_SHORT
                        ).show()
                    }
                }

            } catch (ex: Exception) {
                withContext(Dispatchers.Main) {
                    Toast.makeText(
                        context,
                        "Error", Toast.LENGTH_SHORT
                    ).show()

                }
            }
        }
    }
}