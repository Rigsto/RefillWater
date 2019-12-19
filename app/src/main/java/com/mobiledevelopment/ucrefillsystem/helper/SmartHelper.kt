package com.mobiledevelopment.ucrefillsystem.helper

import android.content.Context
import android.content.SharedPreferences
import android.view.View
import android.widget.Toast
import java.math.BigInteger
import java.security.MessageDigest
import java.text.NumberFormat

fun Int.readableNumber(): String {
    return "Rp. " + NumberFormat.getInstance().format(this).replace(',', '.')
}

fun String.md5(): String {
    val md = MessageDigest.getInstance("MD5")
    return BigInteger(1, md.digest(toByteArray())).toString(16).padStart(32, '0')
}

fun String.getFloor(): Int {
    val floor = "${this[1]}${this[2]}"
    return floor.toIntOrNull() ?: 0
}

fun Context.getRemainPercentage(remain: Int, max: Int): Int {
    return remain * 100 / max
}

fun Context.getRemainPercentage(remain: Double, max: Double): Double {
    return remain * 100 / max
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

fun Context.comingSoon() {
    Toast.makeText(this, "Coming Soon", Toast.LENGTH_SHORT).show()
}

fun String.checkGallonId(): Boolean {
    val id = this
    if (id.length != 5) return false
    if (id[0] != 'L' && id[0] != 'B') return false

    val floor = ("${id[1]}${id[2]}")
    try {
        val floorInt = floor.toIntOrNull() ?: 0
        if (floorInt in 1..13) {
            val code = ("${id[3]}${id[4]}")
            try {
                val codeInt = code.toIntOrNull() ?: 0
                if (codeInt in 1..99) {
                    return true
                }
            } catch (e: Exception) {
                return false
            }
        } else {
            return false
        }
    } catch (e: Exception) {
        return false
    }
    return false
}