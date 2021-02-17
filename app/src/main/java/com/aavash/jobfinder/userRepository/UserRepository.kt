package com.aavash.jobfinder.userRepository

import com.aavash.jobfinder.api.MyApiRequest
import com.aavash.jobfinder.api.ServiceBuilder
import com.aavash.jobfinder.api.UserApi
import com.aavash.jobfinder.entity.User
import com.aavash.jobfinder.response.LoginResponse

class UserRepository :
    MyApiRequest() {
    val myApi = ServiceBuilder.buildService(UserApi::class.java)
    suspend fun registerUser(user: User): LoginResponse {
        return apiRequest {
            myApi.registerUser(user)
        }
    }
    suspend fun checkUser(username: String, password: String): LoginResponse {
        return apiRequest {
            myApi.checkUser(username, password)
        }
    }
}