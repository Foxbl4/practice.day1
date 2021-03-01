package com.example.involtaday1.ui.database_orm

import androidx.lifecycle.LiveData

class UserRepository(private val userDao: UserDao) {

    val readAllData: LiveData<List<User>> = userDao.readAllData()

    suspend fun addUser(user: User){
        userDao.addUser(user)
    }

    suspend fun deleteUser(user: User){
        userDao.deleteUser(user)
    }

    suspend fun deleteByID(query: Int){
           userDao.deleteByID(query)
    }

    suspend fun deleteByValue(query: String){
        userDao.deleteByValue(query)
    }

     fun searchByValue(query: String): LiveData<List<User>>{
        return userDao.searchByValue(query)
    }

}