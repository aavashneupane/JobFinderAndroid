package com.aavash.jobfinder.entity

import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Applied(
        var _id:String="",
        var confirmStatus: String? = null,
        var jobtitle: String ?= null,
        var jobtype: String ?= null,
        var company: String?,
        var createdAt: String? = null,
        var creator: String? = null
){
    @PrimaryKey(autoGenerate = true)
    var stdId: Int = 0
}