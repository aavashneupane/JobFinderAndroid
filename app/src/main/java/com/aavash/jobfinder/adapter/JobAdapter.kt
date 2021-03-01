package com.aavash.jobfinder.adapter

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.aavash.jobfinder.R
import com.aavash.jobfinder.UpdateJobActivity
import com.aavash.jobfinder.afterLogin
import com.aavash.jobfinder.db.JobDB
import com.aavash.jobfinder.entity.Job
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class JobAdapter(
    private val context: Context,
    private val lstJob: List<Job>

) : RecyclerView.Adapter<JobAdapter.JobViewHolder>() {

    class JobViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val imgProfile : ImageView
        val tvName : TextView
        val tvAddress : TextView
        val tvAge : TextView
        val tvGender : TextView
        val imgBtnUpdate : ImageView
        val imgBtnDelete : ImageView

        init {
            imgProfile = view.findViewById(R.id.imgProfile)
            tvName = view.findViewById(R.id.tvName)
            tvAddress = view.findViewById(R.id.tvAddress)
            tvAge = view.findViewById(R.id.tvAge)
            tvGender = view.findViewById(R.id.tvGender)
            imgBtnUpdate = view.findViewById(R.id.imgBtnUpdate)
            imgBtnDelete = view.findViewById(R.id.imgBtnDelete)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JobViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.addjobrecycle,parent,false)
        return JobViewHolder(view)
    }

    // Data tanne kam
    override fun onBindViewHolder(holder: JobViewHolder, position: Int) {
        val jobs = lstJob[position]
        holder.tvName.text =  jobs.fullName
        holder.tvAddress.text = jobs.address
        holder.tvAge.text = jobs.age.toString()
        holder.tvGender.text = jobs.gender

        holder.imgBtnUpdate.setOnClickListener {
            val intent = Intent(context, UpdateJobActivity::class.java)
            intent.putExtra("job",jobs)
            context.startActivity(intent)
        }
        holder.imgBtnDelete.setOnClickListener {

            val builder = AlertDialog.Builder(context)
            builder.setTitle("Delete Job")
            builder.setMessage("Are you sure you want to delete ${jobs.fullName} ??")
            builder.setIcon(android.R.drawable.ic_dialog_alert)
            builder.setPositiveButton("Yes") { _, _ ->
                deleteJob(jobs)
            }
            builder.setNegativeButton("No") { _, _ ->
                Toast.makeText(context, "Action cancelled", Toast.LENGTH_SHORT).show()
            }
            val alertDialog: AlertDialog = builder.create()
            alertDialog.setCancelable(false)
            alertDialog.show()
        }



//        val imagePath = ServiceBuilder.loadImagePath() + jobs.photo
//        if (!jobs.photo.equals("no-photo.jpg")) {
//            Glide.with(context)
//                    .load(imagePath)
//                    .fitCenter()
//                    .into(holder.imgProfile)
//        }

//        holder.imgBtnDelete.setOnClickListener{
//            val builder = AlertDialog.Builder(context)
//            builder.setTitle("Delete job")
//            builder.setMessage("Are you sure you want to delete ${jobs.fullName} ??")
//            builder.setIcon(android.R.drawable.ic_delete)
//            builder.setPositiveButton("Yes") { _, _ ->
//                CoroutineScope(Dispatchers.IO).launch {
//                    try {
//                        val jobRepository = jobRepository()
//                        val response = jobRepository.deleteJob(jobs._id!!)
//                        if (response.success == true) {
//                            withContext(Dispatchers.Main) {
//                                Toast.makeText(
//                                        context,
//                                        "Job Deleted",
//                                        Toast.LENGTH_SHORT
//                                ).show()
//                            }
//                            withContext(Dispatchers.Main) {
//                                lstJobs.remove(jobs)
//                                notifyDataSetChanged()
//                            }
//                        }
//                    } catch (ex: Exception) {
//                        withContext(Dispatchers.Main) {
//                            Toast.makeText(
//                                    context,
//                                    ex.toString(),
//                                    Toast.LENGTH_SHORT
//                            ).show()
//                        }
//                    }
//                }
//            }
//            builder.setNegativeButton("No") { _, _ ->
//            }
//            val alertDialog: AlertDialog = builder.create()
//            alertDialog.setCancelable(false)
//            alertDialog.show()
//
//
//            notifyDataSetChanged()
//        }
//
//        holder.imgBtnUpdate.setOnClickListener{
//            val intent = Intent(context, UpdateJobActivity::class.java)
//            intent.putExtra("jobs", jobs)
//            context.startActivity(intent)
//        }
    }

    private fun deleteJob(job: Job) {
        CoroutineScope(Dispatchers.IO).launch {
            JobDB.getInstance(context).getJobDAO()
                    .DeleteJob(job)
            withContext(Dispatchers.Main) {
                Toast.makeText(context, "Deleted", Toast.LENGTH_SHORT).show()
            }
        }

    }

    override fun getItemCount(): Int {
        return lstJob.size
    }
}