package com.mobiledevelopment.ucrefillsystem.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Gallon(
    @SerializedName("id")
    var id: String? = null,

    var place: String? = null,
    var floor: Int? = null,
    var remain: Int = 0,
    var remainPercentage: Double = 0.0
) : Parcelable