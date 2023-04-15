package com.joshk.android.mviarchitectureandroid.ui.main.viewstate

import com.joshk.android.mviarchitectureandroid.data.model.User

sealed class MainState {
//    This main state handles UI appearance in the event of success or error
    object Idle : MainState()
    object Loading : MainState()
    data class Users(val user: List<User>) : MainState()
    data class Error(val error: String?) : MainState()
}
