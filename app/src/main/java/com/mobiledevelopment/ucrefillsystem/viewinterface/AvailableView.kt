package com.mobiledevelopment.ucrefillsystem.viewinterface

import com.mobiledevelopment.ucrefillsystem.model.Dispenser

interface AvailableView {
    fun showLoading()
    fun hideLoading()
    fun showAvailableList(data: List<Dispenser>)
}