package com.aavash.jobfinder.userRepository

import android.util.Log
import com.aavash.jobfinder.api.MyApiRequest
import com.aavash.jobfinder.api.ServiceBuilder
import com.aavash.jobfinder.api.UserApi
import com.aavash.jobfinder.dao.UserDAO
import com.aavash.jobfinder.entity.User
import com.aavash.jobfinder.response.LoginResponse

class UserRepository(val userDAO: UserDAO) :
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
    //    get login user
    suspend fun getLoginUser():User{
        val _id = persistUser()
        return userDAO.getUser()
    }

    suspend fun persistUser(): String {
        try {
            val response = apiRequest {
                userAPI.getLoginUser(ServiceBuilder.token!!)
            }
            if (response.success == true) {
                userDAO.registerUser(response.user!!)
                return response.user._id
            }

        } catch (ex: Exception) {
            Log.i("UserRepo", ex.toString())
        }

        return ""
    }
}