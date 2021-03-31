package com.aavash.jobfinder.fragments

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aavash.jobfinder.entity.User
import com.aavash.jobfinder.userRepository.UserRepository
import kotlinx.coroutines.launch

class ProfileViewModel(val repository: UserRepository) : ViewModel() {

    private val _user = MutableLiveData<User>()
    val user: LiveData<User> = _user

    fun getLogggedInUser() {
        viewModelScope.launch {
            try {
                _user.value = repository.getLoginUser()
            } catch (ex: Exception) {
                Log.d("ProfileViewModel", ex.toString())
            }
        }
    }


}