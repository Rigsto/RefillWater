package com.mobiledevelopment.ucrefillsystem.network

import com.mobdev.refillwater.BuildConfig

object RefillWaterAPI {
    private const val address = BuildConfig.BASE_URL

    fun getUser(name : String?):String{
        return address +""
    }

    fun CreateUser (name : String?):String{
        return address +""
    }

    fun SeeHistory (name: String?): String{
        return address+""
    }
}