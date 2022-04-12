package com.oguzhancetin.androidprojecttemplate.data

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.oguzhancetin.androidprojecttemplate.model.User
import com.oguzhancetin.androidprojecttemplate.model.UserDetail
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserDetailViewModel @Inject constructor(
    private val userRepository: UserRepository
) : ViewModel() {
    private var _userInfoStateFlow = MutableStateFlow<NetworkResult<UserDetail>>(NetworkResult.Loading())
    val userInfoStateFlow = _userInfoStateFlow

    fun getUserInfo(userName:String){
        viewModelScope.launch {
            userRepository.getUserInfo(userName).collect {
                _userInfoStateFlow.value = NetworkResult.Success(it)
            }
        }
    }

}