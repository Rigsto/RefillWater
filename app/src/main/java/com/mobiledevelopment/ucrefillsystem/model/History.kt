package com.mobiledevelopment.ucrefillsystem.model

import com.google.gson.annotations.SerializedName

data class History (
    @SerializedName("id")
    var id: String? = null,

    @SerializedName("price")
    var price: Int? = 0,

    @SerializedName("updated_at")
    var date: String? = null,

    @SerializedName("nRefill")
    var amount: Int = 0,

    var place: String? = null
)