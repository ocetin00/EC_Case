package com.oguzhancetin.androidprojecttemplate.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
@Entity(tableName = "user")
data class User(
    @PrimaryKey(autoGenerate = false)
    @SerializedName("login")
    val userName: String

)