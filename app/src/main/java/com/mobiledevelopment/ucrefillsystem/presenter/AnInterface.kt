package com.mobiledevelopment.ucrefillsystem.presenter

import com.mobiledevelopment.ucrefillsystem.model.History
import com.mobiledevelopment.ucrefillsystem.model.User

interface MainView {
    fun showLoading()
    fun hideLoading()
    fun showNameList(data : List<User>)
    fun showHistoryList(data : List<History>)
}