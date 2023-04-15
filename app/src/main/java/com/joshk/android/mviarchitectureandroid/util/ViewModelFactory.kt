package com.joshk.android.mviarchitectureandroid.util

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.joshk.android.mviarchitectureandroid.data.api.ApiHelper
import com.joshk.android.mviarchitectureandroid.data.repository.MainRepository
import com.joshk.android.mviarchitectureandroid.ui.main.viewmodel.MainViewModel

class ViewModelFactory(private val apiHelper: ApiHelper) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(MainRepository(apiHelper)) as T
        }
        throw java.lang.IllegalArgumentException("Unknown Class Name")
    }
}