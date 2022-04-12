package com.oguzhancetin.androidprojecttemplate.data

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.oguzhancetin.androidprojecttemplate.model.User
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val repository: UserRepository,

) : ViewModel() {

    private var _stateFLowUserList: MutableStateFlow<NetworkResult<List<User>>> = MutableStateFlow(NetworkResult.Loading())
    val stateFlowUserList = _stateFLowUserList

    fun fetchUserList(){
        viewModelScope.launch (Dispatchers.IO){
            repository.getUserList().collect {
                _stateFLowUserList.value = NetworkResult.Success(it)
            }
        }
    }


}