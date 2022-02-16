package com.example.usersclient.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.usersclient.UIStateUsers
import com.example.usersclient.data.local.UserEntity
import com.example.usersclient.data.local.UsersDao
import com.example.usersclient.data.local.UsersDatabase
import com.example.usersclient.data.repositories.User
import com.example.usersclient.data.repositories.UsersRepository
import com.example.usersclient.data.repositories.UsersRepositoryImpl
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import com.example.usersclient.ui.ViewState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class UsersViewModel(application: Application) : AndroidViewModel(application) {

    private val usersRepository: UsersRepository
    private val mutableUsersList: MutableStateFlow<ViewState<List<User>, String?>> =
        MutableStateFlow(ViewState.loading())
    val usersListSateFlow: StateFlow<ViewState<List<User>, String?>>
        get() = mutableUsersList

    private var usersList: List<User> = mutableListOf()

    init {
        val usersDao = UsersDatabase.create(application).usersDao()
        usersRepository = UsersRepositoryImpl(usersDao)
        getUsers()
    }

    fun addUser(user: UserEntity) {
        viewModelScope.launch {
            usersRepository.addUser(user)
        }
    }

    fun loadAllUsers(): Flow<List<UserEntity>> {
        return usersRepository.getAllUsersFromDB()
    }

    fun deleteUser(user: UserEntity) {
        viewModelScope.launch {
            usersRepository.deleteUser(user)
        }
    }

    fun updateUser(user: UserEntity){
        viewModelScope.launch {
            usersRepository.updateUser(user)
        }
    }

    fun getUsers() {
        viewModelScope.launch {
            when (val result = usersRepository.getUsers()) {
                is UIStateUsers.Success -> {
                    usersList = result.data
                    for (user in result.data) {
                        addUser(user.toUserEntity())
                    }
                    mutableUsersList.value = ViewState.success(usersList)
                }
                is UIStateUsers.Error -> {
                    mutableUsersList.value = ViewState.error(usersList, result.data)
                }
            }
        }
    }
}