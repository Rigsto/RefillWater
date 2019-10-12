package com.mobiledevelopment.ucrefillsystem.model.response

import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @SerializedName("code")
    val code: Int,

    @SerializedName("name")
    val name: String? = null,

    @SerializedName("api")
    val api: String? = null,

    @SerializedName("money")
    val money: Int? = null
)