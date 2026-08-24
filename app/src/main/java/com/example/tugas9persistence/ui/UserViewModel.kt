package com.example.tugas9persistence.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.tugas9persistence.data.AppDatabase
import com.example.tugas9persistence.data.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class UserViewModel(application: Application) : AndroidViewModel(application) {
    private val database = AppDatabase.getInstance(application)
    private val userDao = database.userDao()

    val users: StateFlow<List<User>> = userDao.getAllUsers().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = emptyList()
    )

    fun addUser(name: String, email: String) {
        viewModelScope.launch(Dispatchers.IO) {
            userDao.insertUser(User(name = name, email = email))
        }
    }

    fun updateUser(user: User) {
        viewModelScope.launch(Dispatchers.IO) { userDao.updateUser(user) }
    }

    fun deleteUser(user: User) {
        viewModelScope.launch(Dispatchers.IO) { userDao.deleteUser(user) }
    }

    fun deleteAllUsers() {
        viewModelScope.launch(Dispatchers.IO) { userDao.deleteAllUsers() }
    }
}
