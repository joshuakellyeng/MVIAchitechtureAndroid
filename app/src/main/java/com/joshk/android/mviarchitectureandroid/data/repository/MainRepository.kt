package com.joshk.android.mviarchitectureandroid.data.repository

import com.joshk.android.mviarchitectureandroid.data.api.ApiHelper

class MainRepository(private val apiHelper: ApiHelper) {

    suspend fun getUsers() = apiHelper.getUsers()
}