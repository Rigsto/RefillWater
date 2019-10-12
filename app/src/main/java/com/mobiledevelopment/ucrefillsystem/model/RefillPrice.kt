package com.mobiledevelopment.ucrefillsystem.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class RefillPrice(
    @SerializedName("id")
    val id: Int,

    @SerializedName("size")
    val size: Int,

    @SerializedName("price")
    val price: Int
) : Parcelable