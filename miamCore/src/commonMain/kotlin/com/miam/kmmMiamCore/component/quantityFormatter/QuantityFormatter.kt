package com.miam.kmmMiamCore.component.quantityFormatter

import kotlin.math.ceil

public class QuantityFormatter {
    public companion object default {
        public fun realQuantities(quantity: String, currentGuest: Int, recipeGuest: Int): String {
            return (quantity.toFloat() * currentGuest / recipeGuest).toString()
        }


        public fun readableFloatNumber(value: String, unit: String?): String {
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


            return ceil(valueToNumber).toString().plus(" ").plus(unitToDisplay)
        }

        public fun frac(value: Float, maxdenominator: Int = 10): String {
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

        public fun render_frac(original_value: Float, num: Float, denom: Float): String {
            if (num == 0f) {
                return original_value.toString()

            }
            return num.toInt().toString().plus('/').plus(denom.toInt())
        }

        public fun pluralize(unit: String): String {
            if (unit.isEmpty() || unit.length <= 3) {
                return unit
            }

            val unitArray = unit.split(' ').toMutableList()
            unitArray[0] = unitArray[0].replace("/(. * [^s])s*$/", "$1s")
            return unitArray.joinToString(" ")
        }

        public fun singularize(unit: String): String {
            if (unit.isEmpty() || unit.length <= 3) {
                return unit
            }
            val unitArray = unit.split(' ').toMutableList()

            unitArray[0] = unitArray[0].replace("/(. * [^s])s*$/", "$1")
            return unitArray.joinToString(" ")
        }

        private fun isUnitCountless(unit: String?): Boolean {
            val arr = arrayOf("un peu", "un petit peu")
            return arr.contains(unit)
        }

        private fun isInteger(N: Float): Boolean {
            // TODO : check return N.toInt().toFloat() == N
            val intPart = N.toInt()
            val decimalPart = N - intPart

            return decimalPart <= 0
        }
    }
}