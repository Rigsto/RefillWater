package com.mobiledevelopment.ucrefillsystem.viewinterface

import com.mobiledevelopment.ucrefillsystem.model.Voucher

interface VoucherView {
    fun showVouchers(vouchers: List<Voucher>)
}