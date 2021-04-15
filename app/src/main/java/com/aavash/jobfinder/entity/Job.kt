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
    var _id: String?,
    var jobtitle: String?,
    var jobtype: String?,
    var jobdescription: String?,
    var requiredexperience: String?,
    var jobprice: String?,
    var creator: User?,
    var createdAt: String?,
    var photo:String?=null

) : Parcelable {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0

    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        TODO("creator"),
        parcel.readString(),
        parcel.readString()
    ) {
        id = parcel.readInt()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(_id)
        parcel.writeString(jobtitle)
        parcel.writeString(jobtype)
        parcel.writeString(jobdescription)
        parcel.writeString(requiredexperience)
        parcel.writeString(jobprice)
        parcel.writeString(createdAt)
        parcel.writeString(photo)
        parcel.writeInt(id)
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