package com.aavash.jobfinder.response

import com.aavash.jobfinder.entity.Job

data class JobResponse (
    val success : Boolean? = null,
    val data : MutableList<Job>? =null
)