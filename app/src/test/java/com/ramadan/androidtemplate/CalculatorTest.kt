package com.ramadan.androidtemplate

import org.junit.Assert
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class CalculatorTest {

    @Test
    fun add() {
        val cal = Calculator()
        val res= cal.add(1, 2)
        Assert.assertEquals(3, res)
    }

    @Test
    fun whenEmptyString_thenAccept() {
        val palindromeTester = Calculator()
        assertTrue(palindromeTester.isPalindrome(""))
    }

    @Test
    fun whenPalindrom_thenAccept() {
        val palindromeTester = Calculator()
        assertTrue(palindromeTester.isPalindrome("noon"))
    }

    @Test
    fun whenNearPalindrom_thanReject() {
        val palindromeTester = Calculator()
        assertFalse(palindromeTester.isPalindrome("neon"))
    }
}