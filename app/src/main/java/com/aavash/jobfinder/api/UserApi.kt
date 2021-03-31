package com.aavash.jobfinder.api

import com.aavash.jobfinder.entity.User
import com.aavash.jobfinder.response.LoginResponse
import com.aavash.jobfinder.response.UserResponse
import retrofit2.Response
import retrofit2.http.*

interface UserApi {
    //register user
    @POST("user/add")
    suspend fun registerUser(
        @Body user: User
    ): Response<LoginResponse>

    //Login user
    @FormUrlEncoded
    @POST("user/login")
    suspend fun checkUser(
            @Field ("email") email : String,
            @Field ("password") password : String
    ):Response<LoginResponse>

    //get users detail
    @GET("user/profile2")
    suspend fun getLoginUser(
            @Header("Authorization") token: String
    ): Response<UserResponse>
}