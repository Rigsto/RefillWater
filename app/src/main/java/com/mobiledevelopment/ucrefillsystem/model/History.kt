package com.mobiledevelopment.ucrefillsystem.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class History (
    @SerializedName("id")
    var id: String? = null,

    @SerializedName("cost")
    var price: Int? = 0,

    @SerializedName("transaction_date")
    var date: String? = null,

    @SerializedName("bottle_size")
    var amount: Int = 0,

    @SerializedName("gallon_id")
    var idGallon: String? = null
) : Parcelable