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
import com.aavash.jobfinder.entity.Job
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class JobAdapter(

    val lstJob: MutableList<Job>,
    val context: Context

) : RecyclerView.Adapter<JobAdapter.JobViewHolder>() {

    class JobViewHolder(view: View) : RecyclerView.ViewHolder(view){
     //   val imgProfile : ImageView
        val jobtitle : TextView
        val jobtype : TextView
        val jobdescription : TextView
        val requiredexperience : TextView
        val jobprice : TextView
        val creator : TextView
        val createdAt : TextView
        val imgBtnUpdate : ImageView
        val imgBtnDelete : ImageView

        init {
         //   imgProfile = view.findViewById(R.id.imgProfile)
            jobtitle = view.findViewById(R.id.tvJobTitle)
            jobtype = view.findViewById(R.id.tvJobType)
            jobdescription = view.findViewById(R.id.tvJobdescription)
            requiredexperience = view.findViewById(R.id.tvrequiredexperience)
            jobprice = view.findViewById(R.id.tvJobprice)
            creator= view.findViewById(R.id.tvCreator)
            createdAt = view.findViewById(R.id.tvCreatedAt)


            imgBtnUpdate = view.findViewById(R.id.imgBtnUpdate)
            imgBtnDelete = view.findViewById(R.id.imgBtnDelete)
        }


    }





    // Data tanne kam
    override fun onBindViewHolder(holder: JobViewHolder, position: Int) {
        val jobs = lstJob[position]
        println("Adapter: "+jobs)
        holder.jobtitle.text =  jobs.jobtitle
        holder.jobtype.text = jobs.jobtype
        holder.jobdescription.text = jobs.jobdescription
        holder.requiredexperience.text = jobs.requiredexperience
        holder.jobprice.text = jobs.jobprice
        holder.creator.text = jobs.creator
        holder.createdAt.text = jobs.createdAt

//
//        holder.imgBtnUpdate.setOnClickListener {
//            val intent = Intent(context, UpdateJobActivity::class.java)
//            intent.putExtra("job",jobs)
//            context.startActivity(intent)
//        }

//        holder.imgBtnDelete.setOnClickListener {
//
//            val builder = AlertDialog.Builder(context)
//            builder.setTitle("Delete Job")
//            builder.setMessage("Are you sure you want to delete ${jobs.jobtitle} ??")
//            builder.setIcon(android.R.drawable.ic_dialog_alert)
//            builder.setPositiveButton("Yes") { _, _ ->
//
//                notifyItemChanged(itemCount)
//                notifyItemRemoved(itemCount)
//                notifyDataSetChanged()
//
//
//
//
//            }
//            builder.setNegativeButton("No") { _, _ ->
//                Toast.makeText(context, "Action cancelled", Toast.LENGTH_SHORT).show()
//            }
//            val alertDialog: AlertDialog = builder.create()
//            alertDialog.setCancelable(false)
//            alertDialog.show()
//            notifyDataSetChanged()
//        }




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
//    fun deleteJob(job: Job) {
//        CoroutineScope(Dispatchers.IO).launch {
//            JobDB.getInstance(context).getJobDAO()
//                    .DeleteJob(job)
//            notifyDataSetChanged()
//
//            withContext(Dispatchers.Main) {
//                Toast.makeText(context, "Deleted", Toast.LENGTH_SHORT).show()
//            }
//        }
//
//    }
    override fun getItemCount(): Int {

        return lstJob.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JobViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.addjobrecycle,parent,false)
        return JobViewHolder(view)


    }
}