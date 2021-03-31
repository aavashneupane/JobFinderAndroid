package com.aavash.jobfinder.response

import com.aavash.jobfinder.entity.User

data class UserResponse(
        val success :Boolean?=null,
        val user: User? = null
)