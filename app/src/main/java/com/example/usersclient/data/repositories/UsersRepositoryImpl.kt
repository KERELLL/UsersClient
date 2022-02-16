package com.example.usersclient.data.repositories

import android.content.Context
import androidx.lifecycle.LiveData
import com.example.usersclient.UIStateUsers
import com.example.usersclient.data.local.UserEntity
import com.example.usersclient.data.local.UsersDao
import com.example.usersclient.data.local.UsersDatabase
import com.example.usersclient.data.network.api.ApiProvider
import com.example.usersclient.data.network.api.ApiService
import com.example.usersclient.data.network.models.UsersResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.withContext
import java.lang.Exception
import java.net.UnknownHostException

class UsersRepositoryImpl(private val usersDao: UsersDao) : UsersRepository {

    private val apiService: ApiService = ApiProvider.apiService

    override suspend fun getUsers(): UIStateUsers<List<User>, String?> {
        try{
            val users = withContext(Dispatchers.IO){
                apiService.getUsers().data.map {
                    it.toUser()
                }
            }
            return UIStateUsers.Success(users)
        } catch (e: Exception){
           return UIStateUsers.Error(e.message)
        }
    }

    override fun getAllUsersFromDB(): Flow<List<UserEntity>> {
        return usersDao.getAllUsersFromDB()
    }

    override suspend fun addUser(userEntity: UserEntity) {
        withContext(Dispatchers.IO){
            usersDao.addUser(userEntity)
        }
    }

    override suspend fun deleteUser(userEntity: UserEntity) {
        withContext(Dispatchers.IO) {
            usersDao.deleteUser(userEntity)
        }
    }
    override suspend fun updateUser(userEntity: UserEntity){
        withContext(Dispatchers.IO) {
            usersDao.updateUser(userEntity)
        }
    }
}