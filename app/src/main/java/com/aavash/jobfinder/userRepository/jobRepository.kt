package com.aavash.jobfinder.userRepository

import com.aavash.jobfinder.api.JobApi
import com.aavash.jobfinder.api.MyApiRequest
import com.aavash.jobfinder.api.ServiceBuilder
import com.aavash.jobfinder.entity.Job
import com.aavash.jobfinder.response.*
import okhttp3.MultipartBody


class jobRepository : MyApiRequest() {
    private val jobApi = ServiceBuilder.buildService(JobApi::class.java)
    suspend fun applyJob(job: String):ApplyJobResponse{
        return  apiRequest {
            jobApi.applyJob(ServiceBuilder.token!!,job)
        }
    }
    suspend fun getJobs(): JobResponse {
        return apiRequest {
            jobApi.getAllJobs(ServiceBuilder.token!!)
        }
    }



    suspend fun updateJob(id: String,job: Job) : UpdateUserResponse {
        return apiRequest {
            jobApi.updateJob(ServiceBuilder.token!!,id,job)
        }
    }
    suspend fun uploadImage(id: String, body: MultipartBody.Part)
            : ImageResponse {
        return apiRequest {
            jobApi.uploadImage(ServiceBuilder.token!!, id, body)
        }
    }

}