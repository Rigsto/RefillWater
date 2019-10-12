package com.mobiledevelopment.ucrefillsystem.network

import com.mobiledevelopment.ucrefillsystem.BuildConfig

object RefillWaterAPI {
    private const val address = BuildConfig.BASE_URL + "/api"

    fun getAvailableDispenser(): String {
        return "$address/available.php"
    }

    fun getRefillHistory(api: String): String {
        return "$address/history.php?api=$api"
    }

    fun login(email: String, password: String): String {
        return "$address/login.php?email=$email&pass=$password"
    }

    fun getDispenserInfo(code: Int): String {
        return "$address/dispenser_info.php?id=$code"
    }

    fun getRefillPrices(): String {
        return "$address/price.php"
    }

    fun payRefill(api: String, idDispenser: Int, priceCode: Int): String {
        return "$address/refill_payment.php?api=$api&dis=$idDispenser&code=$priceCode"
    }
}