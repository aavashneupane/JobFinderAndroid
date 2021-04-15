package com.aavash.jobfinder.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.aavash.jobfinder.R
import com.aavash.jobfinder.entity.Applied

class AppliedAdapter(
        private val context: Context,
        private val lstApplied: List<Applied>
): RecyclerView.Adapter<AppliedAdapter.BookingViewHolder>() {
    class BookingViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvAppliedTitle: TextView = view.findViewById(R.id.tvAppliedTitle)
        val tvAppliedType: TextView = view.findViewById(R.id.tvAppliedType)
        val tvAppliedstatus: TextView = view.findViewById(R.id.tvAppliedstatus)
        val tvAppliedCreator: TextView = view.findViewById(R.id.tvAppliedCreator)
        val btnDeleteApplied: TextView = view.findViewById(R.id.btnDeleteApplied)


    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookingViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.myapplied, parent, false)
        return BookingViewHolder(view)
    }

    override fun onBindViewHolder(holder: BookingViewHolder, position: Int) {
        val applied = lstApplied[position]
        holder.tvAppliedTitle.text = applied.jobid?.jobtitle
        holder.tvAppliedType.text = applied.jobid?.jobtype
        holder.tvAppliedstatus.text = applied.confirmStatus
        holder.tvAppliedCreator.text = applied.jobid?.creator

        holder.btnDeleteApplied.setOnClickListener {

            val builder = android.app.AlertDialog.Builder(context)
            builder.setTitle("Delete application?")
            builder.setMessage("Are you sure you want to delete ${applied.jobid} ??")
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
        return lstApplied.size
    }
}