package com.mobiledevelopment.ucrefillsystem.viewinterface

import com.mobiledevelopment.ucrefillsystem.model.Bottle

interface BottleView {
    fun showLoading()
    fun hideLoading()
    fun showBottles(default: List<Bottle>, my: List<Bottle>)
}