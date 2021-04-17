package com.aavash.jobfinder.adapter

import android.content.Context
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.aavash.jobfinder.Helper.Notification
import com.aavash.jobfinder.R
import com.aavash.jobfinder.entity.Applied
import com.aavash.jobfinder.userRepository.appliedRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.time.Instant
import java.util.*

class AppliedAdapter(
        private val context: Context,
        private val lstApplied: List<Applied>
): RecyclerView.Adapter<AppliedAdapter.BookingViewHolder>() {
    class BookingViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvAppliedTitle: TextView = view.findViewById(R.id.tvAppliedTitle)
        val tvAppliedType: TextView = view.findViewById(R.id.tvAppliedType)
        val tvAppliedstatus: TextView = view.findViewById(R.id.tvAppliedstatus)
        val tvAppliedCreator: TextView = view.findViewById(R.id.tvAppliedCreator)
        val tvAppliedCreatedAt: TextView = view.findViewById(R.id.tvAppliedCreatedAt)
        val btnDeleteApplied: TextView = view.findViewById(R.id.btnDeleteApplied)


    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookingViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.myapplied, parent, false)



        return BookingViewHolder(view)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: BookingViewHolder, position: Int) {
        val applied = lstApplied[position]
        holder.tvAppliedTitle.text = applied.jobtitle
        holder.tvAppliedType.text = applied.jobtype
        holder.tvAppliedstatus.text = applied.confirmStatus

        var date=(applied.createdAt)



        holder.tvAppliedCreatedAt.text ="You applied in: "+ date?.let { convertDate(it) }
        holder.tvAppliedCreator.text = "This job was posted by"+(applied.creator)

        if (applied.confirmStatus=="Confirmed"||applied.confirmStatus=="denied"){
            val a= Notification
            context?.let { it1 -> a.givenotification(it1,"Your application for ${applied.jobtitle} in ${applied.company} has been (${applied.confirmStatus})") }
        }

        var id=applied._id



        holder.btnDeleteApplied.setOnClickListener {

            val builder = android.app.AlertDialog.Builder(context)
            builder.setTitle("Delete application?")
     //       builder.setMessage("Are you sure you want to delete ${applied.jobid?.jobtitle} from your list??")
            builder.setIcon(android.R.drawable.ic_dialog_alert)
            builder.setPositiveButton("Yes") { _, _ ->
                if (id != null) {
                    deleteApplied(id)



                }
                Toast.makeText(context, "Application deleted successfully", Toast.LENGTH_SHORT).show()
            }
            builder.setNegativeButton("No") { _, _ ->
                Toast.makeText(context, "Action cancelled", Toast.LENGTH_SHORT).show()
            }
            val alertDialog: android.app.AlertDialog = builder.create()
            alertDialog.setCancelable(false)
            alertDialog.show()
        }

    }


    fun deleteApplied(id: String) {
    CoroutineScope(Dispatchers.IO).launch {
        try {

            val repository = appliedRepository()
            val response = repository.deleteJob(id)
            if (response.success==true) {
                Toast.makeText(
                        context,
                        "Deleted successfully", Toast.LENGTH_SHORT
                ).show()

            } else {
                withContext(Dispatchers.Main) {
                    Toast.makeText(
                            context,
                            "Cannot delete.", Toast.LENGTH_SHORT
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

    override fun getItemCount(): Int {
        return lstApplied.size
    }
    @RequiresApi(Build.VERSION_CODES.O)
    fun convertDate(date:String):Date{
        val dates = Date.from(Instant.parse(date))

        return dates
    }
}