package com.aavash.jobfinder.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.aavash.jobfinder.R
import com.aavash.jobfinder.entity.Applied
import com.aavash.jobfinder.userRepository.appliedRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

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

    override fun onBindViewHolder(holder: BookingViewHolder, position: Int) {
        val applied = lstApplied[position]
        holder.tvAppliedTitle.text = applied.jobid?.jobtitle
        holder.tvAppliedType.text = applied.jobid?.jobtype
        holder.tvAppliedstatus.text = applied.confirmStatus
        holder.tvAppliedCreatedAt.text = applied.createdAt
        var id=applied._id

        holder.btnDeleteApplied.setOnClickListener {

            val builder = android.app.AlertDialog.Builder(context)
            builder.setTitle("Delete application?")
            builder.setMessage("Are you sure you want to delete ${applied.jobid?.jobtitle} from your list??")
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
}