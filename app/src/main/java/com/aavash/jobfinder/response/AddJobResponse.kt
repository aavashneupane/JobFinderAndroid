package com.aavash.jobfinder.response

import com.aavash.jobfinder.entity.job

data class AddJobResponse (
    val success : Boolean? = null,
    val data : job? = null
)