package com.mobiledevelopment.ucrefillsystem.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Bottle(
    @SerializedName("id")
    val id: String? = null,

    @SerializedName("capacity")
    val capacity: Int = 0,

    @SerializedName("price")
    val price: Int = 0,

    @SerializedName("own_by")
    val owner: String? = null
) : Parcelable