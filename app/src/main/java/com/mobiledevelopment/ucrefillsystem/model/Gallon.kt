package com.mobiledevelopment.ucrefillsystem.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Gallon(
    @SerializedName("id")
    var id: String? = null,

    @SerializedName("default_ml")
    var max: Int = 0,

    @SerializedName("current_ml")
    var remain: Int = 0,

    @SerializedName("description")
    var desc: String? = null
) : Parcelable