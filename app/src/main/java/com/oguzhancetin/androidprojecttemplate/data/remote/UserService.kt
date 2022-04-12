package com.oguzhancetin.androidprojecttemplate.data.remote

import com.oguzhancetin.androidprojecttemplate.model.User
import com.oguzhancetin.androidprojecttemplate.model.UserDetail
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface UserService {

    @GET("/users?since=0")
    suspend fun getUsers(): Response<List<User>>

    @GET("/users/{username}")
    suspend fun getUserInfo(@Path("username") userName:String): Response<UserDetail>

}