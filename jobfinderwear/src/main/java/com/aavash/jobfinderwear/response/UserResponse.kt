package com.aavash.jobfinderwear.response

import com.aavash.jobfinderwear.entity.User

data class UserResponse(
    val success :Boolean?=null,
    val user: User? = null
)