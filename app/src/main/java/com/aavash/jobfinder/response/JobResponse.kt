package com.aavash.jobfinder.response

import com.aavash.jobfinder.entity.job

data class JobResponse (
    val success : Boolean? = null,
    val data : MutableList<job>? =null
)