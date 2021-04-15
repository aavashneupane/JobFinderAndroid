package com.aavash.jobfinder.entity

import android.icu.text.CaseMap
import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
data class Job(
  //  val _id:String?=null,
    val jobtitle: String?,
    val jobtype: String?,
    val jobdescription: String?,
    val requiredexperience: String?,
    val jobprice: String?,
    val creator: String?,
    val createdAt: String?,
    val photo:String?=null

) : Parcelable {
    @PrimaryKey(autoGenerate = true)
    var jobid: Int = 0

    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readString(),
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
        parcel.writeValue(jobtype)
        parcel.writeString(jobdescription)
        parcel.writeString(requiredexperience)
        parcel.writeString(jobprice)
        parcel.writeString(creator)
        parcel.writeString(createdAt)
        parcel.writeString(photo)
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
