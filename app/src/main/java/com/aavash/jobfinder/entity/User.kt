package com.aavash.jobfinder.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


data class User(

    val _id: String?=null,
    val firstname: String? = null,
    val lastname: String? = null,
    val age: String? = null,
    val address: String? = null,
    val phone: String? = null,
    val email: String? = null,
    val password: String? = null,
    val role: String? = null,
    val projects: String? = null,
    val experience: String? = null,
    val userbio: String? = null


)
