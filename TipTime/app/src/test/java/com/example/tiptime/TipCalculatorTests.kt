package com.example.tiptime

import org.junit.Test

import org.junit.Assert.*
import java.text.NumberFormat

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class TipCalculatorTests {
    @Test
    fun calculateTip_20PercentNoRoundup() {
        val amount = 10.00
        val tipPercent = 20.00
        var expectedTip = NumberFormat.getCurrencyInstance().format(2)
        var actualTip  = calculateTip(amount, tipPercent, false)

        assertEquals(expectedTip, actualTip)

    }
}