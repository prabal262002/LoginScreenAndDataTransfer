package com.example.loginscreenuianddatatransfer

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(tableName = "RegisterUsers")
data class tableDefine(
    @ColumnInfo(name = "email")
    var email:String,

    @ColumnInfo(name = "password")
    var password:String
)
