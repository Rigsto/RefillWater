package com.mobiledevelopment.ucrefillsystem.model

import com.google.gson.annotations.SerializedName

data class Voucher(
    @SerializedName("title")
    val title: String? = null,

    @SerializedName("description")
    val desc: String? = null,

    @SerializedName("end_date")
    val endDate: String? = null,

    @SerializedName("image_url")
    val url: String? = null
)