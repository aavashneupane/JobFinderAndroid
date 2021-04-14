package com.aavash.jobfinder.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(



    @PrimaryKey
    @ColumnInfo(name="_id")val _id: String,
    @ColumnInfo(name="firstname")val firstname: String? = null,
    @ColumnInfo(name="lastname")val lastname: String? = null,
    @ColumnInfo(name="age")val age: String? = null,
    @ColumnInfo(name="address")val address: String? = null,
    @ColumnInfo(name="phone")val phone: String? = null,
    @ColumnInfo(name="email")val email: String? = null,
    @ColumnInfo(name="password")val password: String? = null,
    @ColumnInfo(name="projects")val projects: String? = null,
    @ColumnInfo(name="experience")val experience: String? = null,
    @ColumnInfo(name="userbio")val userbio: String? = null


)