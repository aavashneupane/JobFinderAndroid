package com.aavash.jobfinder.dao

import androidx.room.Dao
import androidx.room.Insert
import com.aavash.jobfinder.entity.Applied
import com.aavash.jobfinder.entity.Job

@Dao
interface JobDAO {
    @Insert
    suspend fun applyJob(job:Applied)
}