package com.aavash.jobfinder.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(




    val _id: String?=null,
    var firstname: String? = null,
    var lastname: String? = null,
    var age: String? = null,
    var address: String? = null,
    var phone: String? = null,
    var email: String? = null,
    var password: String? = null,
    var projects: String? = null,
    var experience: String? = null,
    var userbio: String? = null


)
{
    @PrimaryKey(autoGenerate = true)
    var userId:Int=0
}