package com.rogok.glideapp.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.rogok.glideapp.data.UserRepository
import com.rogok.glideapp.models.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import java.util.ArrayList

class UserViewModel(
    private val repository: UserRepository
): ViewModel() {

    /*private var _users = MutableLiveData<ArrayList<User>>()
    val users: LiveData<ArrayList<User>>
    get() = _users*/

    //init {
        /*//todo:error handler
        viewModelScope.launch {
            _users.value = repository.getUsers()
        }*/

        fun fetchUsers(): Flow<PagingData<User>> {
            return repository.getUsersFlow()
                .cachedIn(viewModelScope)
        }
    //}
}