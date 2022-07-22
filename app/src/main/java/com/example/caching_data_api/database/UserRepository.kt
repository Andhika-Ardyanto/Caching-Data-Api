package com.example.caching_data_api.database

import com.example.caching_data_api.network.SampleApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class UserRepository (private val userDAO: UserDAO) {
    val users = userDAO.loadUsers()

    suspend fun refreshUsers() {
        withContext(Dispatchers.IO) {
            val users = SampleApi.retrofitService.showList()
            userDAO.insertAll(users)
        }
    }
}