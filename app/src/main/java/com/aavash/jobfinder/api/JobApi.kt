package com.aavash.jobfinder.api

import com.aavash.jobfinder.entity.Job
import com.aavash.jobfinder.response.*
import okhttp3.MultipartBody
import retrofit2.Response
import retrofit2.http.*

interface JobApi {
    @POST("job/applyJob/{id}")
    suspend fun applyJob(
        @Header("Authorization") token: String,
        @Path("id") id:String
    ): Response<ApplyJobResponse>

    @GET("job/showall")
    suspend fun getAllJobs(
        @Header("Authorization") token :String
    ): Response<JobResponse>

    @GET("job/showSingle/{id}")
    suspend fun getSingleJob(
        @Header("Authorization") token :String,
        @Path("id") id:String
    ): Response<SingleAppliedResponse>


    @GET("job/showMyApplied")
    suspend fun showMyApplied(
            @Header("Authorization") token :String
    ): Response<ShowAppliedResponse>

    @DELETE("job/deleteMyApplied/{id}")
    suspend fun deleteJob(
        @Header("Authorization") token :String,
        @Path("id") id:String
    ):Response<DeleteJobResponse>

    @PUT("job/{id}")
    suspend fun updateJob(
        @Header("Authorization") token: String,
        @Path("id") id: String,
        @Body job: Job
    ):Response<UpdateUserResponse>


    @Multipart
    @PUT("job/{id}/photo")
    suspend fun uploadImage(
        @Header("Authorization") token:String,
        @Path("id")id:String,
        @Part file: MultipartBody.Part
    ):Response<ImageResponse>


}