package com.aavash.jobfinder.userRepository

import android.util.Log
import com.aavash.jobfinder.api.MyApiRequest
import com.aavash.jobfinder.api.ServiceBuilder
import com.aavash.jobfinder.api.UserApi
import com.aavash.jobfinder.entity.Job
//import com.aavash.jobfinder.dao.UserDAO
import com.aavash.jobfinder.entity.User
import com.aavash.jobfinder.response.JobResponse
import com.aavash.jobfinder.response.LoginResponse
import com.aavash.jobfinder.response.UserResponse

class UserRepository:
    MyApiRequest() {
    private val userAPI = ServiceBuilder.buildService(UserApi::class.java)
    suspend fun registerUser(user: User): LoginResponse {
        return apiRequest {
            userAPI.registerUser(user)
        }
    }

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

    suspend fun editUser(id: String,firstname: String,lastname: String,age: String,address: String,phone: String,userbio: String,projects: String,experience: String): UserResponse {
        return apiRequest {
            userAPI.editUser(ServiceBuilder.token!!,id, firstname,lastname,age,address,phone,userbio,projects,experience)
        }
    }

}