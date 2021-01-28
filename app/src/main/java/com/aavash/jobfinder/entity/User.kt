package com.aavash.jobfinder.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(
    var fullname: String? = null,
    var age: String? = null,
    var country: String? = null,
    var phone: String? = null,
    var email: String? = null,
    var password: String? = null


) {
    @PrimaryKey(autoGenerate = true)
    var userId: Int = 0
}