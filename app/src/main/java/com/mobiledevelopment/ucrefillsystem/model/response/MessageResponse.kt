package com.mobiledevelopment.ucrefillsystem.model.response

import com.google.gson.annotations.SerializedName

data class MessageResponse(
    @SerializedName("message")
    val message: String? = null
)