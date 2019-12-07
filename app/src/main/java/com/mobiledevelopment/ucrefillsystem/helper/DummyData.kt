package com.mobiledevelopment.ucrefillsystem.helper

import android.content.Context
import com.mobiledevelopment.ucrefillsystem.R
import com.mobiledevelopment.ucrefillsystem.model.Gallon
import com.mobiledevelopment.ucrefillsystem.model.History

fun Context.getDummyGallonData(): List<Gallon> {
    val gallons = mutableListOf<Gallon>()
    gallons.add(Gallon("1", "Lantai 1", 1, 3000, 15.0))
    gallons.add(Gallon("2", "Lantai 2", 2, 6000, 20.0))
    gallons.add(Gallon("3", "Lantai 3", 3, 20000, 100.0))
    gallons.add(Gallon("4", "Lantai 13", 13, 10000, 50.0))
    gallons.add(Gallon("5", "BMA", 2, 1000, 5.0))
    return gallons
}

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