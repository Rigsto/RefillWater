package com.mobiledevelopment.ucrefillsystem.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class User (
    @SerializedName ("name")
    var name: String? = null,

    @SerializedName ("email")
    var email: String? = null,

    @SerializedName ("gender")
    var gender: String? = null,

    @SerializedName ("majors")
    var majors: String? = null,

    @SerializedName ("balance")
    var balance: String? = null
) : Parcelable