package com.mobiledevelopment.ucrefillsystem.model

import com.google.gson.annotations.SerializedName

data class User (
    @SerializedName ("name")
    var userName :String? = null,

    @SerializedName ("email")
    var userEmail :String? = null,

    @SerializedName ("password")
    var userPassword :String? = null,

    @SerializedName ("gender")
    var userGender :String? = null,

    @SerializedName ("majors")
    var userMajors :String? = null,

    @SerializedName ("balance")
    var useBalance :String? = null,

    @SerializedName ("role_id")
    var userRole: String? = null
)