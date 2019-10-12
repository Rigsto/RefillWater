package com.mobiledevelopment.ucrefillsystem.viewinterface

interface PaymentView {
    fun showLoading()
    fun hideLoading()
    fun loadData(status: Int)
}