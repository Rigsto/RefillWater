package com.mobiledevelopment.ucrefillsystem.viewinterface

import com.mobiledevelopment.ucrefillsystem.model.Refill

interface HistoryView {
    fun showLoading()
    fun hideLoading()
    fun showHistory(data: List<Refill>)
}