package com.aavash.jobfinder.dao

import androidx.room.*
import com.aavash.jobfinder.entity.Job


@Dao
interface JobDAO {
    @Insert
    suspend fun insertStudent(job : Job)

    @Query("SELECT * FROM job")
    suspend fun getAllStudents() : List<Job>

    @Update
    suspend fun updateStudent(job : Job)

    @Delete
    suspend fun DeleteStudent(job : Job)
}