package com.example.involtaday1.ui.database_orm

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import org.jetbrains.anko.doAsyncResult
import java.util.concurrent.Future

@Suppress("UNCHECKED_CAST")
class UserViewModel(application: Application): AndroidViewModel(application) {

    val readAllData: LiveData<List<User>>

    private val repository: UserRepository

    init {
        val userDao = UserDataBase.getDataBase(application).userDao()
        repository = UserRepository(userDao)
        readAllData = repository.readAllData
    }

    fun addUser(user: User) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addUser(user)
        }
    }

    fun deleteUser(user: User) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteUser(user)
        }
    }

    fun deleteByID(query: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteByID(query)
        }
    }

    fun deleteByValue(query: String) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteByValue(query)
        }
    }

    fun searchByValue(query: String): LiveData<List<User>> =  repository.searchByValue(query)
}