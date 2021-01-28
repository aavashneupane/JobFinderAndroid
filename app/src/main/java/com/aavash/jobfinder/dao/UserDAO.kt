package com.aavash.jobfinder.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.aavash.jobfinder.entity.User

@Dao
interface UserDAO {
    @Insert
    suspend fun registerUser(user: User)

    @Query("select * from User where email=(:email) and password=(:password)")
    suspend fun checkUser(email: String, password: String): User
}