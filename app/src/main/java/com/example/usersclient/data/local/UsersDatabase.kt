package com.example.usersclient.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [
    UserEntity::class
], version = 1, exportSchema = true)
abstract class UsersDatabase: RoomDatabase() {
    abstract fun usersDao() : UsersDao

    companion object{

        @Volatile
        private var INSTANCE: UsersDatabase? = null

        fun create(context: Context): UsersDatabase{

            synchronized(this){
                if(INSTANCE == null){
                    INSTANCE =  Room.databaseBuilder(context.applicationContext, UsersDatabase::class.java, "users_table").build()
                }
            }
            return INSTANCE!!
        }
    }
}