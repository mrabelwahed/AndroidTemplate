package com.ramadan.androidtemplate.demo

import com.ramadan.test_utils.ContextProvider
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class CoroutineContextProvider @Inject constructor() : ContextProvider {
    override fun Main(): CoroutineContext = Dispatchers.Main
    override fun IO(): CoroutineContext = Dispatchers.IO
}