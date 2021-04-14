package com.aavash.jobfinder.userRepository

import com.aavash.jobfinder.api.JobApi
import com.aavash.jobfinder.api.MyApiRequest
import com.aavash.jobfinder.api.ServiceBuilder
import com.aavash.jobfinder.entity.Job
import com.aavash.jobfinder.response.*
import okhttp3.MultipartBody

class appliedRepository : MyApiRequest() {
    private val jobApi = ServiceBuilder.buildService(JobApi::class.java)

    suspend fun getAppliedJobs(): ShowAppliedResponse {
        return apiRequest {
            jobApi.showMyApplied(ServiceBuilder.token!!)
        }
    }
    suspend fun deleteJob(id: String) : DeleteJobResponse {
        return apiRequest {
            jobApi.deleteJob(ServiceBuilder.token!!,id)
        }
    }
    suspend fun uploadImage(id: String, body: MultipartBody.Part)
            : ImageResponse {
        return apiRequest {
            jobApi.uploadImage(ServiceBuilder.token!!, id, body)
        }
    }

}