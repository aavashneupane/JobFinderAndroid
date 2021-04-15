package com.aavash.jobfinder.adapter

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.recyclerview.widget.RecyclerView
import com.aavash.jobfinder.R
import com.aavash.jobfinder.UpdateJobActivity
import com.aavash.jobfinder.api.ServiceBuilder
import com.aavash.jobfinder.entity.Applied
import com.aavash.jobfinder.entity.Job
import com.aavash.jobfinder.userRepository.jobRepository
import com.bumptech.glide.Glide
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
        holder.creator.text = jobs.creator
        holder.createdAt.text = jobs.createdAt

        holder.btnApply.setOnClickListener {


        }

    }


//    private fun deleteBooking(booking: Booking) {
//        CoroutineScope(Dispatchers.IO).launch {
//            BookingDB.getInstance(context).getBookingDAO()
//                .DeleteBooking(booking)
//            withContext(Dispatchers.Main) {
//                Toast.makeText(context, "Deleted", Toast.LENGTH_SHORT).show()
//            }
//        }
//
//    }

    override fun getItemCount(): Int {
        return lstJobs.size
    }
}