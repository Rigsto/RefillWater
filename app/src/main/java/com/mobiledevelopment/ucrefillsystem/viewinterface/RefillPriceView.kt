package com.mobiledevelopment.ucrefillsystem.viewinterface

import com.mobiledevelopment.ucrefillsystem.model.Dispenser
import com.mobiledevelopment.ucrefillsystem.model.RefillPrice

interface RefillPriceView {
    fun showLoading()
    fun hideLoading()
    fun showDispenser(dispenser: Dispenser)
    fun showPrices(prices: List<RefillPrice>)
    fun loadDataFailed()
}