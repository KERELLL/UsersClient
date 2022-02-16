package com.example.usersclient.data.repositories

import com.example.usersclient.ui.UIStateUsers
import com.example.usersclient.data.local.UserEntity
import kotlinx.coroutines.flow.Flow

interface UsersRepository {

    suspend fun getUsers() : UIStateUsers<List<User>, String?>

    fun getAllUsersFromDB(): Flow<List<UserEntity>>

    suspend fun addUser(userEntity: UserEntity)

    suspend fun deleteUser(userEntity: UserEntity)

    suspend fun updateUser(userEntity: UserEntity)
}