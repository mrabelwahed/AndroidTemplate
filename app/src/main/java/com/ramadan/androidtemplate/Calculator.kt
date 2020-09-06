package com.ramadan.androidtemplate

class Calculator {
    fun isPalindrome(inputString: String): Boolean {
        return if (inputString.length == 0) {
            true
        } else {
            val firstChar = inputString[0]
            val lastChar = inputString[inputString.length - 1]
            val mid = inputString.substring(1, inputString.length - 1)
            firstChar == lastChar && isPalindrome(mid)
        }
    }
    fun add(a: Int, b: Int) = a+b
//    fun subtract(a: Int, b: Int) = a-b
}