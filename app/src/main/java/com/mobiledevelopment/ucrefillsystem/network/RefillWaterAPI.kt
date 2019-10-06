package com.mobiledevelopment.ucrefillsystem.network

import com.mobiledevelopment.ucrefillsystem.BuildConfig

object RefillWaterAPI {
    private const val address = BuildConfig.BASE_URL + "/api"
    lateinit var api: String

    fun getAvailableDispenser(): String {
        return "$address/available.php"
    }

    fun getRefillHistory(): String {
        return "$address/history.php?$api"
    }

    fun login(email: String, password: String): String {
        return "$address/login.php?email=$email&pass=$password"
    }
}