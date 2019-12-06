package com.mobiledevelopment.ucrefillsystem.helper

import android.content.Context
import com.mobiledevelopment.ucrefillsystem.model.Gallon

fun Context.getDummyGallonData(): List<Gallon> {
    val gallons = mutableListOf<Gallon>()
    gallons.add(Gallon("1", "Lantai 1", 1, 3000, 60.0))
    gallons.add(Gallon("2", "Lantai 2", 2, 6000, 20.0))
    gallons.add(Gallon("3", "Lantai 99", 99, 20000, 100.00))
    return gallons
}