package com.aavash.jobfinder.entity

import android.icu.text.CaseMap
import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
data class Job(
    var title: String? = null,
    var description: String? = null,
   // var gender: String? = null,
    var salary: String? = null
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
        parcel.writeString(title)
        parcel.writeValue(description)
    //    parcel.writeString(gender)
        parcel.writeString(salary)
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