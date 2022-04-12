package com.oguzhancetin.androidprojecttemplate.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.oguzhancetin.androidprojecttemplate.model.User
import com.oguzhancetin.androidprojecttemplate.model.UserDetail
import kotlinx.coroutines.flow.Flow


@Dao
interface UserDao {

    @Query("SELECT * FROM user")
    fun getAllUser(): Flow<List<User>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUser(user: User)

    @Query("SELECT * FROM user_detail where login = :userName")
    fun getUserDetailByUserName(userName: String): Flow<UserDetail>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUserDetail(userDetail: UserDetail)
}