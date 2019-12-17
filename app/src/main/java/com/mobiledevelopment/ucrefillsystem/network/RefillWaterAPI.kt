package com.mobiledevelopment.ucrefillsystem.network

import com.mobiledevelopment.ucrefillsystem.BuildConfig

object RefillWaterAPI {
    private const val address = BuildConfig.BASE_URL

    fun login():String{
        return "https://refill.test/api/login"
        //address +"login?email=$name&password=$password"
    }

    fun register():String{
        return address
    }

    fun getProfile(email:String?):String{
        return address+"profile"
    }

    fun editProfile(name : String?, gender: String?, majors:String?, password1:String?, password2: String):String{
        return address+""
    }


    fun historyTopUp (name:String?): String{
        return address+"my-topUp?name=$name"
    }

    fun accessToken (): String{
        return address+"test-token"
    }
}