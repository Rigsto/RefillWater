package com.mobiledevelopment.ucrefillsystem.model

import com.google.gson.annotations.SerializedName

data class User (
    @SerializedName ("name")
    var name: String? = null,

    @SerializedName ("email")
    var email: String? = null,

//    @SerializedName ("password")
//    var userPassword :String? = null,

    @SerializedName ("gender")
    var gender: String? = null,

    @SerializedName ("majors")
    var majors: String? = null,

    @SerializedName ("balance")
    var balance: Int = 0,

    @SerializedName ("role_id")
    var role: Int = 0,

    var nim: String? = null,
    var api: String? = null
)