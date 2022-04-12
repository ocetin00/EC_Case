package com.oguzhancetin.androidprojecttemplate.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.oguzhancetin.androidprojecttemplate.model.User
import com.oguzhancetin.androidprojecttemplate.model.UserDetail

@Database(entities = [User::class,UserDetail::class], version = 1)
abstract class AppDatabase: RoomDatabase() {

    abstract fun userDao(): UserDao
}