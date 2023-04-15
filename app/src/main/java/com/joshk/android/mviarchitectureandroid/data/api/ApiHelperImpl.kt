package com.joshk.android.mviarchitectureandroid.data.api

import com.joshk.android.mviarchitectureandroid.data.model.User

class ApiHelperImpl(private val apiService: ApiService) : ApiHelper {

    override suspend fun getUsers(): List<User> {
        return apiService.getUsers()
    }
}