package com.ramadan.test_utils

import kotlinx.coroutines.Dispatchers
import kotlin.coroutines.CoroutineContext

class TestContextProvider : ContextProvider{
    override fun Main(): CoroutineContext  = Dispatchers.Unconfined
    override fun IO(): CoroutineContext  = Dispatchers.Unconfined
}