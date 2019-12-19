package com.mobiledevelopment.ucrefillsystem.viewinterface.auth

import com.mobiledevelopment.ucrefillsystem.model.User

interface LoginView {
    fun showLoading()
    fun hideLoading()
    fun loginFailed(message : String)
    fun loginSuccess(accToken: String, refToken: String)
    fun loadProfile(user: User)
}