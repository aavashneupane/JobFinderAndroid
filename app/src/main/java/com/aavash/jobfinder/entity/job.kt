package com.aavash.jobfinder.entity

import android.icu.text.CaseMap
import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
data class Job(
    val _id:String="",
    val jobtitle: String?,
    val jobtype: String?,
    val jobdescription: String?,
    val requiredexperience: String?,
    val jobprice: String?,
    val creator: String?,
    val createdAt: String?,
    val photo:String?=null

)
{
    @PrimaryKey(autoGenerate = true)
    var jobid:Int= 0



}