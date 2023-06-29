package com.example.loginscreenuianddatatransfer

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
@Database(entities = [tableDefine::class], version = 1, exportSchema = false)
abstract class registerDatabase : RoomDatabase() {
    abstract val  registerDatabaseDao: daoClass
    companion object{
        @Volatile private var INSTANCE:registerDatabase? = null


        fun getInstance(context: Context): registerDatabase {
            synchronized(this) {

                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        registerDatabase::class.java,
                        "user_details_database"
                    )
                        .fallbackToDestructiveMigration()
                        .build()

                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}