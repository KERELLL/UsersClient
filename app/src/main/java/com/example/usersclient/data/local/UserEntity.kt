package com.example.usersclient.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.usersclient.data.repositories.User


@Entity(tableName = "users_table")
data class UserEntity(
    @PrimaryKey
    @ColumnInfo(name = "userId")
    val id: Int,
    @ColumnInfo(name = "userAvatar")
    val avatar: String,
    @ColumnInfo(name = "userEmail")
    val email: String,
    @ColumnInfo(name = "userFirstName")
    val first_name: String,
    @ColumnInfo(name = "userLastName")
    val last_name: String,
){
    fun entityToUser() : User = User(
        id = id,
        avatar = avatar,
        first_name = first_name,
        last_name = last_name,
        email = email
    )
}