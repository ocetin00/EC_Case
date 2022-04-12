package com.oguzhancetin.androidprojecttemplate.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.oguzhancetin.androidprojecttemplate.data.local.AppDatabase
import com.oguzhancetin.androidprojecttemplate.data.local.UserDao
import com.oguzhancetin.androidprojecttemplate.data.remote.UserService
import com.oguzhancetin.androidprojecttemplate.util.Constant
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class MainModule {

    @Provides
    @Singleton
    fun provideUserService(): UserService =
        provideRetrofit(provideConvertFactory()).create(UserService::class.java)

    @Provides
    @Singleton
    fun provideRetrofit(gsonConverterFactory: GsonConverterFactory): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constant.BASE_URL)
            .addConverterFactory(gsonConverterFactory)
            .build()
    }

    @Provides
    @Singleton
    fun provideConvertFactory(): GsonConverterFactory =
        GsonConverterFactory.create()

    @Provides
    @Singleton
    fun provideRoomDb(@ApplicationContext context: Context) : AppDatabase{
        return Room.databaseBuilder(context
            ,AppDatabase::class.java,"ec_db").build()
    }
    @Provides
    @Singleton
    fun provideUserDao(@ApplicationContext context: Context): UserDao{
        return provideRoomDb(context).userDao()
    }


}

