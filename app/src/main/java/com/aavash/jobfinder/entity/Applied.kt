package com.aavash.jobfinder.entity

import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Applied(
        var _id:String?=null,
        var confirmStatus: String? = null,
        var userid: User ?= null,
        var jobid: Job? = null,
        var createdAt: String? = null
) : Parcelable {
    @PrimaryKey(autoGenerate = true)
    var stdId: Int = 0

    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readString(),
            TODO("userid"),
            parcel.readParcelable(Job::class.java.classLoader),
            parcel.readString()) {
        stdId = parcel.readInt()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(_id)
        parcel.writeString(confirmStatus)
        parcel.writeParcelable(jobid, flags)
        parcel.writeString(createdAt)
        parcel.writeInt(stdId)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Applied> {
        override fun createFromParcel(parcel: Parcel): Applied {
            return Applied(parcel)
        }

        override fun newArray(size: Int): Array<Applied?> {
            return arrayOfNulls(size)
        }
    }
}