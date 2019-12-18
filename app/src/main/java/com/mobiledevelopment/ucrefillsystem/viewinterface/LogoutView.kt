package com.mobiledevelopment.ucrefillsystem.viewinterface

interface LogoutView {
    fun showLoading()
    fun hideLoading()
    fun logoutSuccess()
    fun logoutFailed(message: String)
}