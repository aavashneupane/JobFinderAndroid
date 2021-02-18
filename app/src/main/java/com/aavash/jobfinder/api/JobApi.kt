package com.aavash.jobfinder.api

import com.aavash.jobfinder.entity.job
import com.aavash.jobfinder.response.*
import okhttp3.MultipartBody
import retrofit2.Response
import retrofit2.http.*

interface JobApi {
    @POST("job/")
    suspend fun addJob(
        @Header("Authorization") token: String,
        @Body job: job
    ): Response<AddJobResponse>
    @GET("job/")
    suspend fun getAllJobs(
        @Header("Authorization") token :String
    ): Response<JobResponse>

    @DELETE("job/{id}")
    suspend fun deleteJob(
        @Header("Authorization") token :String,
        @Path("id") id:String
    ):Response<DeleteJobResponse>

    @PUT("job/{id}")
    suspend fun updateJob(
        @Header("Authorization") token: String,
        @Path("id") id: String,
        @Body job: job
    ):Response<UpdateJobResponse>


    @Multipart
    @PUT("job/{id}/photo")
    suspend fun uploadImage(
        @Header("Authorization") token:String,
        @Path("id")id:String,
        @Part file: MultipartBody.Part
    ):Response<ImageResponse>


}