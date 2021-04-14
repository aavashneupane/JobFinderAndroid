package com.aavash.jobfinderwear.repository

import com.aavash.jobfinderwear.api.MyApiRequest
import com.aavash.jobfinderwear.api.ServiceBuilder
import com.aavash.jobfinderwear.api.UserApi
import com.aavash.jobfinderwear.entity.User
import com.aavash.jobfinderwear.response.LoginResponse
import com.aavash.jobfinderwear.response.UserResponse

class UserRepository:
    MyApiRequest() {
    private val userAPI = ServiceBuilder.buildService(UserApi::class.java)
    suspend fun checkUser(email: String, password: String): LoginResponse {
        return apiRequest {
            userAPI.checkUser(email, password)
        }
    }
    //to get user
    suspend fun getUser(): UserResponse {
        return apiRequest {
            userAPI.getLoginUser(ServiceBuilder.token!!)
        }
    }

}