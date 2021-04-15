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
//    private var listJob: ArrayList<Job> = data
//    class JobViewHolder(view: View) : RecyclerView.ViewHolder(view) {
//        val imgProfile : ImageView
//        val jobtitle : TextView
//        val jobtype : TextView
//        val jobdescription : TextView
//        val requiredexperience : TextView
//        val jobprice : TextView
//        val creator : TextView
//        val createdAt : TextView
//        val btnApply : Button
//
//
//        init {
//               imgProfile = view.findViewById(R.id.imgJob)
//            jobtitle = view.findViewById(R.id.tvJobTitle)
//            jobtype = view.findViewById(R.id.tvJobType)
//            jobdescription = view.findViewById(R.id.tvJobdescription)
//            requiredexperience = view.findViewById(R.id.tvrequiredexperience)
//            jobprice = view.findViewById(R.id.tvJobprice)
//            creator= view.findViewById(R.id.tvCreator)
//            createdAt = view.findViewById(R.id.tvCreatedAt)
//
//
//            btnApply = view.findViewById(R.id.btnApply)
//
//        }
//
//    }
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JobViewHolder {
//        val view = LayoutInflater.from(parent.context)
//            .inflate(R.layout.addjobrecycle, parent, false)
//        return JobViewHolder(view)
//    }
//
//    override fun getItemCount(): Int {
//        if (listJob.size > 4) {
//            return 4
//        } else {
//            return listJob.size
//        }
//    }
//
//    override fun onBindViewHolder(holder: JobViewHolder, position: Int) {
//        val productData = listJob[position]
//        holder.jobtitle.text = productData.jobtitle
//        holder.jobtype.text = productData.jobtype
//
//
//        CoroutineScope(Dispatchers.IO).launch {
//            try {
//                val repository = jobRepository()
//                val response = repository.getJobs()
//                if (response.success == true) {
//                    withContext(Dispatchers.Main) {
//                        Toast.makeText(context, "Recycler view displayed", Toast.LENGTH_SHORT).show()
//                    }
//                }
//
//            } catch (ex: Exception) {
//                withContext(Dispatchers.Main) {
//                    Toast.makeText(context, ex.toString(), Toast.LENGTH_SHORT).show()
//
//                }
//            }
//        }
//
//
////        load image with Glide library
//        val imagePath = ServiceBuilder.loadImagePath() + productData.photo
//        Glide.with(context)
//            .load(imagePath)
//            .into(holder.imgProfile)
//
//
//        holder.btnApply.setOnClickListener {
//
//
//
//
//        }
//
//
//    }
//
////    private fun applyNotification() {
////
////        val notificationManager = NotificationManagerCompat.from(context)
////
////        val notificationChannels = NotificationChannels(context)
////        notificationChannels.createNotificationChannels()
////
////        val notification = NotificationCompat.Builder(context, notificationChannels.addtocart)
////            .setSmallIcon(R.drawable.ic_notifications_black_24dp)
////            .setContentTitle("Apply")
////            .setContentText("Apply successfull")
////            .setColor(Color.BLUE)
////            .build()
////        notificationManager.notify(1, notification)
////    }
//}
//
//

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
                .inflate(R.layout.myapplied, parent, false)
        return JobViewHolder(view)
    }

    override fun onBindViewHolder(holder: JobViewHolder, position: Int) {
        val jobs = lstJobs[position]
        holder.jobtitle.text = jobs.jobtitle

        holder.btnApply.setOnClickListener {

            val builder = android.app.AlertDialog.Builder(context)
            builder.setTitle("Delete application?")
            //builder.setMessage("Are you sure you want to delete ${applied.jobid} ??")
            builder.setIcon(android.R.drawable.ic_dialog_alert)
            builder.setPositiveButton("Yes") { _, _ ->
                //             deleteBooking(booking)
            }
            builder.setNegativeButton("No") { _, _ ->
                Toast.makeText(context, "Action cancelled", Toast.LENGTH_SHORT).show()
            }
            val alertDialog: android.app.AlertDialog = builder.create()
            alertDialog.setCancelable(false)
            alertDialog.show()
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