package com.example.usersclient.data.repositories

import android.os.Parcelable
import com.example.usersclient.data.local.UserEntity
import kotlinx.parcelize.Parcelize

@Parcelize
data class User(
    val id: Int,
    val avatar: String,
    val first_name: String,
    val last_name: String,
    val email: String,
): Parcelable {
    fun toUserEntity() : UserEntity = UserEntity(
        id = id,
        avatar = avatar,
        first_name = first_name,
        last_name = last_name,
        email = email
    )
}