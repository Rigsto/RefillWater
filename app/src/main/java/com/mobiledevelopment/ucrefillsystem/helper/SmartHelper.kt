package com.mobiledevelopment.ucrefillsystem.helper

import android.content.Context
import android.content.SharedPreferences
import android.view.View
import java.math.BigInteger
import java.security.MessageDigest
import java.text.NumberFormat

fun Int.readableNumber(): String {
    return NumberFormat.getInstance().format(this).replace(',', '.')
}

fun String.md5(): String {
    val md = MessageDigest.getInstance("MD5")
    return BigInteger(1, md.digest(toByteArray())).toString(16).padStart(32, '0')
}

fun View.visible() {
    visibility = View.VISIBLE
}

fun View.invisible() {
    visibility = View.INVISIBLE
}

fun View.gone() {
    visibility = View.GONE
}

fun Context.sharePref(): SharedPreferences {
    return this.getSharedPreferences(SharedPreferenceKey.PREF_KEY, Context.MODE_PRIVATE)
}

fun Context.getApi(): String {
    return sharePref().getString(SharedPreferenceKey.API_KEY, "")!!
}

fun SharedPreferences.reset(){
    this.edit().putString(SharedPreferenceKey.NAME_KEY, "")
        .putString(SharedPreferenceKey.EMAIL_KEY, "")
        .putString(SharedPreferenceKey.PASSWORD_KEY, "")
        .putString(SharedPreferenceKey.API_KEY, "")
        .putInt(SharedPreferenceKey.MONEY_KEY, 0)
        .putInt(SharedPreferenceKey.LANG_KEY, 0)
        .apply()
}