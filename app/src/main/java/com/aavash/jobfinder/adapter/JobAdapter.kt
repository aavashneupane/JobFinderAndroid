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
import com.aavash.jobfinder.api.ServiceBuilder
import com.aavash.jobfinder.entity.job
import com.aavash.jobfinder.userRepository.jobRepository
import com.bumptech.glide.Glide
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class JobAdapter(
        val lstJobs : ArrayList<job>,
        val context : Context

) : RecyclerView.Adapter<JobAdapter.StudentViewHolder>() {

    class StudentViewHolder(view: View) : RecyclerView.ViewHolder(view){
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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.addjobrecycle,parent,false)
        return StudentViewHolder(view)
    }

    // Data tanne kam
    override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
        val users = lstJobs[position]
        holder.tvName.text =  users.fullname
        holder.tvAddress.text = users.address
        holder.tvAge.text = users.age.toString()
        holder.tvGender.text = users.gender

        val imagePath = ServiceBuilder.loadImagePath() + users.photo
        if (!users.photo.equals("no-photo.jpg")) {
            Glide.with(context)
                    .load(imagePath)
                    .fitCenter()
                    .into(holder.imgProfile)
        }

        holder.imgBtnDelete.setOnClickListener{
            val builder = AlertDialog.Builder(context)
            builder.setTitle("Delete job")
            builder.setMessage("Are you sure you want to delete ${users.fullname} ??")
            builder.setIcon(android.R.drawable.ic_delete)
            builder.setPositiveButton("Yes") { _, _ ->
//                CoroutineScope(Dispatchers.IO).launch {
//                    try {
//                        val jobRepository = jobRepository()
//                        val response = jobRepository.deleteJobs(users._id!!)
//                        if (response.success == true) {
//                            withContext(Dispatchers.Main) {
//                                Toast.makeText(
//                                        context,
//                                        "Job Deleted",
//                                        Toast.LENGTH_SHORT
//                                ).show()
//                            }
//                            withContext(Dispatchers.Main) {
//                                lstJobs.remove(job)
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
            }
            builder.setNegativeButton("No") { _, _ ->
            }
            val alertDialog: AlertDialog = builder.create()
            alertDialog.setCancelable(false)
            alertDialog.show()


            notifyDataSetChanged()
        }

//        holder.imgBtnUpdate.setOnClickListener{
//            val intent = Intent(context, UpdateStudentActivity::class.java)
//            intent.putExtra("student", student)
//            context.startActivity(intent)
//        }
    }

    override fun getItemCount(): Int {
        return lstJobs.size
    }
}