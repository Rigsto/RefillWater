package com.mobiledevelopment.ucrefillsystem.viewinterface

import com.mobiledevelopment.ucrefillsystem.model.response.CodeResponse

interface CodeView {
    fun showLoading()
    fun hideLoading()
    fun dataSuccess(data: CodeResponse)
}