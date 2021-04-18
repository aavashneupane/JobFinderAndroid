package com.aavash.jobfinder

import com.aavash.jobfinder.entity.User
import com.aavash.jobfinder.userRepository.UserRepository
import com.aavash.jobfinder.userRepository.appliedRepository
import com.aavash.jobfinder.userRepository.jobRepository
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test


class UnitTesting {
    private lateinit var userRepository: UserRepository

    private lateinit var jobRepository: jobRepository
    private lateinit var appliedRepository:appliedRepository

    // ......................User Testing.......................//
    @Test
    fun checkLogin()= runBlocking {
        userRepository= UserRepository()
        val response = userRepository.checkUser("aavashe@gmail.com", "password")
        val expectedResult =true
        val actualResult=response.success
        Assert.assertEquals(expectedResult,actualResult)
    }
    @Test
    fun RegisterUser()= runBlocking {

        val user= User("","test","unit","23","nepal","2323232323","testunis@gmail.com","password","Customer")
        userRepository= UserRepository()
        val response = userRepository.registerUser(user)
        val expectedResult =true
        val actualResult=response.success
        Assert.assertEquals(expectedResult,actualResult)
    }
    @Test
    fun DuplicateRegisterUser()= runBlocking {

        val user= User("","test","unit","23","nepal","2323232323","testunis@gmail.com","password","Customer")
        userRepository= UserRepository()
        val response = userRepository.registerUser(user)
        val expectedResult =true
        val actualResult=response.success
        Assert.assertEquals(expectedResult,actualResult)
    }
//
    @Test
    fun getuserprofile()= runBlocking {
    userRepository= UserRepository()
    val response = userRepository.checkUser("aavashe@gmail.com", "password")
    val expectedResult =true
    val actualResult=response.success
    Assert.assertEquals(expectedResult,actualResult)
    }
//
//

//

}