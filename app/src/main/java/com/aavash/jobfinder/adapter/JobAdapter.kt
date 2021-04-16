package com.aavash.jobfinder.adapter

import android.app.PendingIntent.getActivity
import android.content.Context
import android.content.Intent
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.aavash.jobfinder.ApplyJob
import com.aavash.jobfinder.MainActivity
import com.aavash.jobfinder.R
import com.aavash.jobfinder.api.ServiceBuilder
import com.aavash.jobfinder.entity.Job
import com.aavash.jobfinder.userRepository.jobRepository
import com.bumptech.glide.Glide
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class JobAdapter(
        private val context: Context,
        private val lstJobs: List<Job>
) : RecyclerView.Adapter<JobAdapter.JobViewHolder>() {

    class JobViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var imgJob : ImageView=view.findViewById(R.id.imgJob)
        val jobtitle : TextView=view.findViewById(R.id.tvJobTitle)
        val jobtype : TextView=view.findViewById(R.id.tvJobType)


        val jobprice : TextView=view.findViewById(R.id.tvJobprice)
        val creator : TextView=view.findViewById(R.id.tvCreator)

        val layoutList : LinearLayout=view.findViewById(R.id.layoutList)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JobViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.addjobrecycle, parent, false)
        return JobViewHolder(view)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: JobViewHolder, position: Int) {
        val jobs = lstJobs[position]
        holder.jobtitle.text = jobs.jobtitle
        holder.jobtype.text = jobs.jobtype

        holder.jobprice.text = jobs.jobprice
        holder.creator.text = jobs.creator?.company.toString()
        var a=jobs.jobtitle

        val imagePath = ServiceBuilder.loadImagePath() + jobs.photo!!.replace("\\", "/");


        //load image with Glide library
        Glide.with(context)
            .load(imagePath)
            .into(holder.imgJob)



        //holder.creator.text = jobs.creator.toString()

        var id=jobs._id


        holder.layoutList.setOnClickListener{
        val intent=Intent(context,com.aavash.jobfinder.apply::class.java)
        context.startActivity(intent.putExtra("id",id))
        }


    }



    override fun getItemCount(): Int {
        return lstJobs.size
    }


}