package com.mobiledevelopment.ucrefillsystem.model

import com.google.gson.annotations.SerializedName

data class History (
    @SerializedName("id")
    var historyid :String? = null,

    @SerializedName("price")
    var historyPrice :String? = null,

    @SerializedName("updated_at")
    var historyDate :String? = null,

    @SerializedName("nRefill")
    var historyAmount :String? = null
    )