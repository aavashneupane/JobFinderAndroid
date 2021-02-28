package com.aavash.jobfinder.dao

import androidx.room.*
import com.aavash.jobfinder.entity.Job


@Dao
interface JobDAO {
    @Insert
    suspend fun insertJob(job : Job)

    @Query("SELECT * FROM job")
    suspend fun getAllJobs() : List<Job>

    @Update
    suspend fun updateJob(job : Job)

    @Delete
    suspend fun DeleteJob(job : Job)
}