package com.mobiledevelopment.ucrefillsystem.model

import com.google.gson.annotations.SerializedName

data class Dispenser (
    @SerializedName("place")
    val place: String?,

    @SerializedName("floor")
    val floor: String?,

    @SerializedName("remain")
    val remain: Double = 0.0
)