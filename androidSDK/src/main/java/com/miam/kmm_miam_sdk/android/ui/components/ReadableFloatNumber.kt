package com.miam.kmm_miam_sdk.android.ui.components

import java.math.RoundingMode
import kotlin.math.roundToLong

fun ReadableFloatNumber(value: String, unit: String?): String {
    if (isUnitCountless(unit)) {
        return unit.toString()
    }

    val valueToNumber = value.toFloat()
    var unitToDisplay = if (unit != null && unit.isNotEmpty()) unit else ""
    if (valueToNumber < 1) {
        unitToDisplay = singularize(unit.toString())
        return frac(valueToNumber).plus(" ").plus(unitToDisplay)
    } else if (valueToNumber.equals(1)) {
        unitToDisplay = singularize(unitToDisplay)
    } else if (valueToNumber > 1) {
        unitToDisplay = pluralize(unitToDisplay)
    }

    if (isInteger(valueToNumber)) {
        return valueToNumber.toInt().toString().plus(" ").plus(unitToDisplay)
    }

    return valueToNumber.toBigDecimal().setScale(2, RoundingMode.UP).toString().plus(" ").plus(unitToDisplay)
}

fun frac(value: Float, maxdenominator: Int = 10): String {
    var num1 = 0f
    var den1 = 1f
    var num2 = 1f
    var den2 = 1f
    while (den1 <= maxdenominator && den2 <= maxdenominator) {
        val mediant = (num1 + num2) / (den1 + den2)
        if (value == mediant) {
            if (den1 + den2 <= maxdenominator) {
                return render_frac(value, num1 + num2, den1 + den2)
            } else if (den2 > den1) {
                return render_frac(value, num2, den2)
            } else {
                return render_frac(value, num1, den1)
            }
        } else if (value > mediant) {
            num1 = num1 + num2
            den1 = den1 + den2
        } else {
            num2 = num1 + num2
            den2 = den1 + den2
        }
    }
    if (den1 > maxdenominator) {
        return render_frac(value, num2, den2)
    } else {
        return render_frac(value, num1, den1)
    }
}

fun render_frac(original_value: Float, num: Float, denom: Float): String {
    if (num === 0f) {
        return original_value.toBigDecimal().setScale(2, RoundingMode.UP).toString()

    }
    return num.toInt().toString().plus('/').plus(denom.toInt())
}

fun pluralize(unit: String): String {
    if (unit.isEmpty() || unit.length <= 3) {
        return unit;
    }

    val unitArray = unit.split(' ').toMutableList()
    unitArray[0] = unitArray[0].replace("/(. * [^s])s*$/", "$1s")
    return unitArray.joinToString(" ")
}

fun singularize(unit: String): String {
    if (unit.isEmpty() || unit.length <= 3) {
        return unit
    }
    val unitArray = unit.split(' ').toMutableList()

    unitArray[0] = unitArray[0].replace("/(. * [^s])s*$/", "$1")
    return unitArray.joinToString(" ")
}

fun isUnitCountless(unit: String?): Boolean {
    val arr = arrayOf("un peu", "un petit peu")
    return arr.contains(unit)
}

fun isInteger(N: Float): Boolean {
    val X = N.toInt()
    val reste = N - X

    return if (reste > 0) {
        false
    } else true
}