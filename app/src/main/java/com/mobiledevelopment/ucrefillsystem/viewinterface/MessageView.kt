package com.mobiledevelopment.ucrefillsystem.viewinterface

interface MessageView {
    fun showMessageLoading()
    fun hideMessageLoading()
    fun getMessage(message: String)
}