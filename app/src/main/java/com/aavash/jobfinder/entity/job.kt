package com.aavash.jobfinder.entity

import android.icu.text.CaseMap
import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable


data class Job(
    val jobtitle: String?,
    val jobtype: String?,
    val jobdescription: String?,
    val requiredexperience: String?,
    val jobprice: String?,
    val creator: String?,
    val createdAt: String?

) : Parcelable{
    @PrimaryKey(autoGenerate = true)
    private var jobid= 0

    constructor(parcel: Parcel) : this(
        parcel.readString(),
      //  parcel.readValue(Int::class.java.classLoader) as? Int,
       parcel.readString(),
        parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString()
    ) {
        jobid = parcel.readInt()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(jobtitle)
        parcel.writeString(jobtype)
        parcel.writeValue(jobdescription)
        parcel.writeValue(requiredexperience)
        parcel.writeValue(jobprice)
        parcel.writeValue(creator)
        parcel.writeValue(createdAt)

        parcel.writeInt(jobid)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Job> {
        override fun createFromParcel(parcel: Parcel): Job {
            return Job(parcel)
        }

        override fun newArray(size: Int): Array<Job?> {
            return arrayOfNulls(size)
        }
    }


}