package com.mobiledevelopment.ucrefillsystem.helper

import android.content.Context
import com.mobiledevelopment.ucrefillsystem.R
import com.mobiledevelopment.ucrefillsystem.model.History

fun Context.getDummyHistoryData(): List<History> {
    val historys = mutableListOf<History>()
    historys.add(History("1", 30, "29 Oct 2019 11:05", 50, "Lantai 2"))
    historys.add(History("2", 40, "1 Des 2019 12:03", 200, "Lantai 99"))
    historys.add(History("3", 20, "1 Des 2019 11:04", 10, "BMA"))
    historys.add(History("4", 20, "1 Des 2019 11:06", 10, "BMA"))
    historys.add(History("5", 10, "1 Des 2019 12:01", 5, "BMA"))
    return historys
}

fun Context.getDummyVoucherData(): List<Int> {
    val vouchers = mutableListOf<Int>()
    vouchers.add(R.drawable.carousel)
    vouchers.add(R.drawable.carousel)
    vouchers.add(R.drawable.carousel)
    return vouchers
}