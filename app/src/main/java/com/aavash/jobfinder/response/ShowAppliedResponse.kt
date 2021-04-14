package com.aavash.jobfinder.response

import com.aavash.jobfinder.entity.Applied


data class ShowAppliedResponse (
        val success : Boolean? = null,
        val data : MutableList<Applied>? = null
)