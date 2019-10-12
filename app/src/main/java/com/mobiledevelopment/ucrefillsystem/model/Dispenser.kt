package com.mobiledevelopment.ucrefillsystem.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Dispenser (
    @SerializedName("code")
    val code: Int,

    @SerializedName("place")
    val place: String?,

    @SerializedName("floor")
    val floor: String?,

    @SerializedName("remain")
    val remain: Double = 0.0
) : Parcelable