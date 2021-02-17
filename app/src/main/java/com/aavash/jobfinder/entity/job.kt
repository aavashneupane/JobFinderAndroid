package com.aavash.jobfinder.entity

import android.os.Parcel
import android.os.Parcelable


data class job(
    val _id: String? = null,
    val fullname: String? = null,
    val age: Int? = null,
    val gender: String? = null,
    val address: String? = null,
    val photo: String?= null
): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(_id)
        parcel.writeString(fullname)
        parcel.writeValue(age)
        parcel.writeString(gender)
        parcel.writeString(address)
        parcel.writeString(photo)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<job> {
        override fun createFromParcel(parcel: Parcel): job {
            return job(parcel)
        }

        override fun newArray(size: Int): Array<job?> {
            return arrayOfNulls(size)
        }
    }
}