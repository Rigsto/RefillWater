package com.mobiledevelopment.ucrefillsystem.viewinterface

import com.mobiledevelopment.ucrefillsystem.model.response.LoginResponse

interface LoginView {
    fun showLoading()
    fun hideLoading()
    fun loginSuccess(data: LoginResponse)
    fun loginFailed()
}