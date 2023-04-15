package com.joshk.android.mviarchitectureandroid.data.api

import com.joshk.android.mviarchitectureandroid.data.model.User
import retrofit2.http.GET

interface ApiService {
// What makes the API service different from the API Helper interface besides the annotation

    @GET("users")
    suspend fun getUsers(): List<User>
}