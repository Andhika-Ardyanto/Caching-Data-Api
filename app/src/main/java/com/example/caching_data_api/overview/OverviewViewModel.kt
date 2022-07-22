package com.example.caching_data_api.overview

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.caching_data_api.database.SampleDatabase
import com.example.caching_data_api.database.User
import com.example.caching_data_api.database.UserDAO
import com.example.caching_data_api.database.UserRepository
import com.example.caching_data_api.network.SampleApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class OverviewViewModel(application : Application): AndroidViewModel(application) {

    //addRespository
    private val repository : UserRepository
    private val userDAO : UserDAO

    private var _items : LiveData<List<User>>

    val items : LiveData<List<User>>
        get() = _items

    private val _response = MutableLiveData<String>()

    val response: LiveData<String>
        get() = _response

    private var vmJob = Job()
    private val crScope = CoroutineScope(vmJob + Dispatchers.Main)

    init {
        userDAO = SampleDatabase.getInstance(application).userDAO()
        repository = UserRepository(userDAO)
        _response.value = "loading...."

        crScope.launch {
            try {
                repository.refreshUsers()
            }catch (t: Throwable){
                _response.value = "Failed, Internet OFF"
            }
        }

        _items = repository.users
        _response.value = "OK"
    }

    override fun onCleared() {
        super.onCleared()
        vmJob.cancel()
    }
}