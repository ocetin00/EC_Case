package com.oguzhancetin.androidprojecttemplate.data.local

import androidx.room.Insert
import androidx.room.Query
import com.oguzhancetin.androidprojecttemplate.model.User
import com.oguzhancetin.androidprojecttemplate.model.UserDetail
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class LocalDataSource @Inject constructor(private val dao: UserDao) {

    fun getAllUser() = dao.getAllUser()

    fun insertUser(user: User) = dao.insertUser(user)

    fun getUserDetailByUserName(userName: String) = dao.getUserDetailByUserName(userName)

    fun insertUserDetail(userDetail: UserDetail) = dao.insertUserDetail(userDetail)
}