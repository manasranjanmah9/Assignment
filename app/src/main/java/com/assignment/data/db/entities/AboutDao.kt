package com.assignment.data.db.entities

import androidx.lifecycle.LiveData
import androidx.room.*
import com.assignment.data.About
import com.assignment.util.AppConstants

@Dao
interface AboutDao {

    @Insert
    fun insertData(list: List<About>)

    @Update
    fun updateData(list: List<About>)

    @Query("DELETE FROM ${AppConstants.TBL_ABOUT}")
    fun deleteAllData()

    @Query("SELECT * FROM ${AppConstants.TBL_ABOUT}")
    fun getAboutList(): LiveData<List<About>>
}