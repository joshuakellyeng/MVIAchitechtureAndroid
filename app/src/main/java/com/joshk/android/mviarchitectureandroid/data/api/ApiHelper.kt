package com.joshk.android.mviarchitectureandroid.data.api

import com.joshk.android.mviarchitectureandroid.data.model.User

interface ApiHelper {
// What makes the API service different from the API Helper interface besides the annotation

    suspend fun getUsers(): List<User>
}