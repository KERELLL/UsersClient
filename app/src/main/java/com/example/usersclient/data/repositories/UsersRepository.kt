package com.example.usersclient.data.repositories

import androidx.lifecycle.LiveData
import com.example.usersclient.UIStateUsers
import com.example.usersclient.data.local.UserEntity
import com.example.usersclient.data.local.UsersDatabase
import com.example.usersclient.data.network.models.UsersResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

interface UsersRepository {

    suspend fun getUsers() : UIStateUsers<List<User>, String?>

    fun getAllUsersFromDB(): Flow<List<UserEntity>>

    suspend fun addUser(userEntity: UserEntity)

    suspend fun deleteUser(userEntity: UserEntity)

    suspend fun updateUser(userEntity: UserEntity)
}