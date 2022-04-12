package com.oguzhancetin.androidprojecttemplate.data.remote

import javax.inject.Inject

class RemoteDataSource @Inject constructor(private val userService: UserService) {
    suspend fun getUserList() =
        userService.getUsers()
    suspend fun getUserDetail(userName:String) =
        userService.getUserInfo(userName)
}