package com.example.usersclient.data.local

import androidx.lifecycle.LiveData
import androidx.room.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

@Dao
interface UsersDao {

    @Query("SELECT * FROM users_table")
    fun getAllUsersFromDB(): Flow<List<UserEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addUser(userEntity: UserEntity)

    @Delete()
    suspend fun deleteUser(userEntity: UserEntity)

    @Update()
    suspend fun updateUser(userEntity: UserEntity)
}