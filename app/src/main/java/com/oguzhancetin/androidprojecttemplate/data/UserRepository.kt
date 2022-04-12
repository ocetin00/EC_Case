package com.oguzhancetin.androidprojecttemplate.data

import android.util.Log
import android.view.View
import android.widget.Toast
import com.oguzhancetin.androidprojecttemplate.data.local.LocalDataSource
import com.oguzhancetin.androidprojecttemplate.data.remote.RemoteDataSource
import com.oguzhancetin.androidprojecttemplate.model.User
import com.oguzhancetin.androidprojecttemplate.model.UserDetail
import com.oguzhancetin.androidprojecttemplate.util.BaseApiResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject
import kotlin.coroutines.coroutineContext

class UserRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
) :
    BaseApiResponse() {


    suspend fun getUserList(): Flow<List<User>> {
        with(CoroutineScope(coroutineContext)){
            launch (Dispatchers.IO){
                Log.d("userlist","a")
                val result = safeApiCall { remoteDataSource.getUserList() }
                when (result) {
                    is NetworkResult.Loading -> {
                    }
                    is NetworkResult.Error -> {
                    }
                    is NetworkResult.Success ->{
                        result.data?.let {users ->
                            users.forEach{ user -> localDataSource.insertUser(user) }
                        }
                    }
                }
            }
        }
        Log.d("userlist","b")
        return localDataSource.getAllUser()
    }

    suspend fun getUserInfo(userName: String) = flow<NetworkResult<UserDetail>> {
        emit(safeApiCall { remoteDataSource.getUserDetail(userName) })
    }.flowOn(Dispatchers.IO)

}