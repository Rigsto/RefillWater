package com.mobiledevelopment.ucrefillsystem.viewinterface

import com.mobiledevelopment.ucrefillsystem.model.History

interface HistoryView {
    fun showLoading()
    fun hideLoading()
    fun showHistoryList(historys: List<History>)
}