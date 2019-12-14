package com.mobiledevelopment.ucrefillsystem.model.response

import com.google.gson.annotations.SerializedName

data class LoginResponse (
    @SerializedName("message")
    var message: String? = null


)