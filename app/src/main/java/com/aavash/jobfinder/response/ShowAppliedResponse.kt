package com.aavash.jobfinder.response

import com.aavash.jobfinder.entity.Job

data class ShowAppliedResponse (
        val success : Boolean? = null,
        val data : Job? = null
)