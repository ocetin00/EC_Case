package com.oguzhancetin.androidprojecttemplate.data.local

import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.RoomDatabase
import com.oguzhancetin.androidprojecttemplate.model.User
import com.oguzhancetin.androidprojecttemplate.model.UserDetail


@Database(
    version = 1,
    entities = [User::class,UserDetail::class],
)
abstract class AppDatabase: RoomDatabase() {

    abstract fun userDao(): UserDao
}