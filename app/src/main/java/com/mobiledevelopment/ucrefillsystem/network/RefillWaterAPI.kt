package com.mobiledevelopment.ucrefillsystem.network

import com.mobiledevelopment.ucrefillsystem.BuildConfig

object RefillWaterAPI {
    private const val address = BuildConfig.BASE_URL + "/api/${BuildConfig.API_KEY}"

    fun getAvailableDispenser(): String {
        return "$address/available.php"
    }
}