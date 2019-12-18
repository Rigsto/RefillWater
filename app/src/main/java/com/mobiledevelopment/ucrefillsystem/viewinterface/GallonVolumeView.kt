package com.mobiledevelopment.ucrefillsystem.viewinterface

import com.mobiledevelopment.ucrefillsystem.model.Gallon

interface GallonVolumeView {
    fun getGallons(gallons: List<Gallon>)
}