package com.ramadan.test_utils

import kotlin.coroutines.CoroutineContext

interface ContextProvider {
    fun Main() : CoroutineContext
    fun IO() : CoroutineContext
}