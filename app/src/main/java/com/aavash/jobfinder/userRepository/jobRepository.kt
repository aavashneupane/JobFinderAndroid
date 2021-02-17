package com.aavash.jobfinder.userRepository

import com.aavash.jobfinder.api.JobApi
import com.aavash.jobfinder.api.MyApiRequest
import com.aavash.jobfinder.api.ServiceBuilder
import com.aavash.jobfinder.entity.job
import com.aavash.jobfinder.response.AddJobResponse
import com.aavash.jobfinder.response.ImageResponse
import com.aavash.jobfinder.response.JobResponse
import okhttp3.MultipartBody


class jobRepository : MyApiRequest() {
    private val jobApi = ServiceBuilder.buildService(JobApi::class.java)
    suspend fun addJob(job: job):AddJobResponse{
        return  apiRequest {
            jobApi.addJob(ServiceBuilder.token!!,job)
        }
    }
    suspend fun getStudents(): JobResponse {
        return apiRequest {
            jobApi.getAllJobs(ServiceBuilder.token!!)
        }
    }
    suspend fun uploadImage(id: String, body: MultipartBody.Part)
            : ImageResponse {
        return apiRequest {
            jobApi.uploadImage(ServiceBuilder.token!!, id, body)
        }
    }

}