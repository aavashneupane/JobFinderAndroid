package com.aavash.jobfinder.entity

import android.icu.text.CaseMap
import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
data class Job(
    var jobtitle: String? = null,
    var jobtype: String? = null,
    var jobdescription: String? = null,
    var requiredexperience: String? = null,
    var jobprice: String? = null,
    var creator: String? = null,
    var createdAt: String? = null

) : Parcelable{
    @PrimaryKey(autoGenerate = true)
    var stdId: Int = 0

    constructor(parcel: Parcel) : this(
        parcel.readString(),
      //  parcel.readValue(Int::class.java.classLoader) as? Int,
       parcel.readString(),
        parcel.readString()
    ) {
        stdId = parcel.readInt()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(jobtitle)
        parcel.writeString(jobtype)
        parcel.writeValue(jobdescription)
        parcel.writeValue(requiredexperience)
        parcel.writeValue(jobprice)
        parcel.writeValue(creator)
        parcel.writeValue(createdAt)

        parcel.writeInt(stdId)
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