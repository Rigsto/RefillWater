package com.mobiledevelopment.ucrefillsystem.network

import com.mobiledevelopment.ucrefillsystem.BuildConfig

object RefillWaterAPI {
    private const val address = BuildConfig.BASE_URL

    fun login():String{
        return "$address/login"
    }

    fun register(
        name: String,
        email: String,
        pass1: String,
        pass2: String,
        gender: String,
        majors: String
    ): String {
        return "$address/register"
    }

    fun logout(): String {
        return "$address/logout"
    }

    fun getProfile(): String {
        return "$address/profile"
    }

    fun getAds(): String {
        return "$address/ads"
    }

    fun getGallon(): String {
        return "$address/gallons"
    }

    fun getBottle(): String {
        return "$address/my-bottle"
    }

    fun refill(): String {
        return "$address/refill"
    }

    fun getHistory(): String {
        return "$address/refill"
    }

    fun editProfile(): String {
        return "$address/edit-profile"
    }

    fun newPassword(): String {
        return "$address/new-password"
    }

//    fun editProfile(name : String?, gender: String?, majors:String?, password1:String?, password2: String):String{
//        return address+""
//    }
//
//    fun historyTopUp (name:String?): String{
//        return address+"my-topUp?name=$name"
//    }
//
//    fun accessToken (): String{
//        return address+"test-token"
//    }
}