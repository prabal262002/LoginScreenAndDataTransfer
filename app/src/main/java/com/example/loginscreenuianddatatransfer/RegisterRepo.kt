package com.example.loginscreenuianddatatransfer

class RegisterRepo(private val dao:daoClass) {
    val users = dao.getAllUsers()

    suspend fun insert(user: tableDefine) {
        return dao.insert(user)
    }

}