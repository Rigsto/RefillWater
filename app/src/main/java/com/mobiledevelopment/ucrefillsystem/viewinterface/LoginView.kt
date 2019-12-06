package com.mobiledevelopment.ucrefillsystem.viewinterface

import com.mobiledevelopment.ucrefillsystem.model.User

interface LoginView {
    fun showLoading()
    fun hideLoading()
    fun getUser(user: User)
}