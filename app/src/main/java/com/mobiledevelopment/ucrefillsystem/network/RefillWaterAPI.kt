package com.mobiledevelopment.ucrefillsystem.network

import com.mobiledevelopment.ucrefillsystem.BuildConfig

object RefillWaterAPI {
    private const val address = BuildConfig.BASE_URL

    fun Login():String{
        return "https://refill.test/api/login"
        //address +"login?email=$name&password=$password"
    }

    fun Register():String{
        return address
    }

    fun GetProfile(email:String?):String{
        return address+"profile"
    }

    fun EditProfile(name : String?, gender: String?, majors:String?, password1:String?, password2: String):String{
        return address+""
    }


    fun HistoryTopUp (name:String?): String{
        return address+"my-topUp?name=$name"
    }

    fun AccessToken (): String{
        return address+"test-token"
    }
}