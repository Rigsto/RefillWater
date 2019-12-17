package com.mobiledevelopment.ucrefillsystem.viewinterface


import com.mobiledevelopment.ucrefillsystem.model.User
import com.mobiledevelopment.ucrefillsystem.model.response.LoginResponse

interface LoginView {
    fun showLoading()
    fun hideLoading()
    fun loginSuccess(name : String?, balance : String?)
    fun loginFailed(message : String)
}