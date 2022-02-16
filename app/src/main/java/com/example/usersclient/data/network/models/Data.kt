package com.example.usersclient.data.network.models

import com.example.usersclient.data.repositories.User

data class Data(
    val avatar: String,
    val email: String,
    val first_name: String,
    val id: Int,
    val last_name: String
){
    fun toUser() : User = User(
        id = id,
        avatar = avatar,
        first_name = first_name,
        last_name = last_name,
        email = email
    )
}