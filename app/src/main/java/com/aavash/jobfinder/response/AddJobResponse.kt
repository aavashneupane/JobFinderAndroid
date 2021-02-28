package com.aavash.jobfinder.response

import com.aavash.jobfinder.entity.Job

data class AddJobResponse (
    val success : Boolean? = null,
    val data : Job? = null
)