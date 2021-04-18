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

        val user= User("","test","unit","23","nepal","2323232323","testunit@gmail.com","password","Customer")
        userRepository= UserRepository()
        val response = userRepository.registerUser(user)
        val expectedResult =true
        val actualResult=response.success
        Assert.assertEquals(expectedResult,actualResult)
    }
    @Test
    fun DuplicateRegisterUser()= runBlocking {

        val user= User("","test","unit","23","nepal","2323232323","testunit2@gmail.com","password","Customer")
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
        val response = userRepository.getUser()
        val expectedResult =true
        val actualResult=response.success
        Assert.assertEquals(expectedResult,actualResult)
    }
//
//
//    //...............For Package Testing.....................//
//    @Test
//    fun showalljobs()= runBlocking {
//        jobRepository= jobRepository()
//        val response = jobRepository.getJobs()
//        val expectedResult =true
//        val actualResult=response.success
//        Assert.assertEquals(expectedResult,actualResult)
//    }
//
//    @Test
//    fun getsinglejob(id:String) = runBlocking {
//        userRepository=UserRepository()
//        jobRepository= jobRepository()
//        val response = jobRepository.getSingleJob(id)
//        val expectedResult =true
//        val actualResult=response.success
//        Assert.assertEquals(expectedResult,actualResult)
//    }
//
//    //.................applied Testing.......................///
//
//    @Test
//    fun showMyApplied()= runBlocking {
//        appliedRepository= appliedRepository()
//        val responses = appliedRepository.deleteJob("607b02e081337e153055824b")
//        val expectedResult =true
//        val actualResult=responses.success
//        Assert.assertEquals(expectedResult,actualResult)
//    }
//

}