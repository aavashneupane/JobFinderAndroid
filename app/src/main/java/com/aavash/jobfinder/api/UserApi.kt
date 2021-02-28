package com.aavash.jobfinder.api

import com.aavash.jobfinder.entity.User
import com.aavash.jobfinder.response.LoginResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

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
}