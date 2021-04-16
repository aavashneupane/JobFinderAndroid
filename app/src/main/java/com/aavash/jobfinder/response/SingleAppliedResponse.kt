package com.aavash.jobfinder.response

import com.aavash.jobfinder.entity.Applied
import com.aavash.jobfinder.entity.Job
import java.util.*

data class SingleAppliedResponse (
    val success : Boolean? = null,
    val data : Job? = null
)