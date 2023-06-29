package com.example.loginscreenuianddatatransfer

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface daoClass {
    @Insert suspend fun insert(register: tableDefine)
    @Query("SELECT * from registerUsers")
    fun getAllUsers(): LiveData<List<tableDefine>>

}