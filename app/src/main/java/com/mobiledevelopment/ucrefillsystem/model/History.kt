package com.mobiledevelopment.ucrefillsystem.model

import com.google.gson.annotations.SerializedName

data class History(
    @SerializedName("type")
    val type: String,

    @SerializedName("balance")
    val balance: Int,

    @SerializedName("size")
    val size: Int?,

    @SerializedName("dispenser_place")
    val disPlace: String?,

    @SerializedName("dispenser_floor")
    val disFloor: String?,

    @SerializedName("time")
    val time: String
)