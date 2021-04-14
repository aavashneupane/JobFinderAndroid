package com.aavash.jobfinderwear.api

import com.aavash.jobfinderwear.response.LoginResponse
import com.aavash.jobfinderwear.response.UserResponse
import retrofit2.Response
import retrofit2.http.*

interface UserApi {


    //Login user
    @FormUrlEncoded
    @POST("user/login")
    suspend fun checkUser(
        @Field("email") email : String,
        @Field("password") password : String
    ): Response<LoginResponse>

    //get users detail
    @GET("profile2")
    suspend fun getLoginUser(
        @Header("Authorization") token: String
    ): Response<UserResponse>
}