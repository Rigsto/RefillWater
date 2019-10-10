package com.mobiledevelopment.ucrefillsystem.model

import com.google.gson.annotations.SerializedName

data class RefillPrice(
    @SerializedName("id")
    val id: Int,

    @SerializedName("size")
    val size: Int,

    @SerializedName("price")
    val price: Int
)