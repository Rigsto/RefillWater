package com.mobiledevelopment.ucrefillsystem.viewinterface.auth

interface LogoutView {
    fun showLoading()
    fun hideLoading()
    fun logoutSuccess()
    fun logoutFailed(message: String)
}