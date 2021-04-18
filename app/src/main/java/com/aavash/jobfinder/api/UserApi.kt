package com.aavash.jobfinder.api

import com.aavash.jobfinder.entity.Job
import com.aavash.jobfinder.entity.User
import com.aavash.jobfinder.response.LoginResponse
import com.aavash.jobfinder.response.RegisterResponse
import com.aavash.jobfinder.response.UserResponse
import retrofit2.Response
import retrofit2.http.*

interface UserApi {
    //register user
    @POST("user/add1")
    suspend fun registerUser(
        @Body user: User
    ): Response<RegisterResponse>

    //Login user
    @FormUrlEncoded
    @POST("user/login")
    suspend fun checkUser(
            @Field ("email") email : String,
            @Field ("password") password : String
    ):Response<LoginResponse>

    //get users detail
    @GET("profile2")
    suspend fun getLoginUser(
            @Header("Authorization") token: String
    ): Response<UserResponse>

    @FormUrlEncoded
    @PUT("profile/editProfileCustomer2/{id}")
    suspend fun     editUser(
        @Header("Authorization") token: String,
        @Path("id") id: String,
        @Field ("firstname") firstname : String,
        @Field ("lastname") lastname : String,
        @Field ("age") age : String,
        @Field ("address") address : String,
        @Field ("phone") phone : String,
        @Field ("userbio") userbio : String,
        @Field ("projects") projects : String,
        @Field ("experience") experience : String

    ):Response<UserResponse>
}