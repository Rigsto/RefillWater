package com.mobiledevelopment.ucrefillsystem.model.response

import com.google.gson.annotations.SerializedName

data class MoneyResponse(
    @SerializedName("money")
    val money: Int
)