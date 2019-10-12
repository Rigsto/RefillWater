package com.mobiledevelopment.ucrefillsystem.model

import com.google.gson.annotations.SerializedName

data class CodeResponse(
    @SerializedName("code")
    val code: Int
)