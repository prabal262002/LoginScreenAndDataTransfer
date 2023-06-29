package com.example.loginscreenuianddatatransfer

import android.app.Application
import android.database.Observable
import android.provider.SyncStateContract.Helpers.insert
import android.widget.Toast
import androidx.annotation.RestrictTo
import androidx.databinding.Bindable
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.*

class LoginViewModel (private val repository:RegisterRepo,application:Application):
AndroidViewModel(application){

    val users = repository.users

    @Bindable
    val inputUsername = MutableLiveData<String>()

    @Bindable
    val inputPassword = MutableLiveData<String>()


    private val viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)


    private val _navigatetoUserDetails = MutableLiveData<Boolean>()

    val navigatetoUserDetails: LiveData<Boolean>
        get() = _navigatetoUserDetails

    private val _errorToast = MutableLiveData<Boolean>()

    val errotoast: LiveData<Boolean>
        get() = _errorToast

    private val _errorToastUsername = MutableLiveData<Boolean>()

    val errotoastUsername: LiveData<Boolean>
        get() = _errorToastUsername

    private val _errorToastInvalidPassword = MutableLiveData<Boolean>()

    val errorToastInvalidPassword: LiveData<Boolean>
        get() = _errorToastInvalidPassword


    fun onSubmit(){
        if(inputPassword==null || inputUsername==null)   _errorToast.value=true
        else{
            uiScope.launch {
                    val email = inputUsername.value!!
                    val password = inputPassword.value!!
                    insert(tableDefine(email, password))
                    inputUsername.value = null
                    inputPassword.value = null
                    _navigatetoUserDetails.value = true
            }
        }
    }
    fun doneNavigatingUserDetails() {
        _navigatetoUserDetails.value = false
    }
    fun donetoast() {
        _errorToast.value = false
    }
    private fun insert(user: tableDefine): Job = viewModelScope.launch {
        repository.insert(user)
    }

}

