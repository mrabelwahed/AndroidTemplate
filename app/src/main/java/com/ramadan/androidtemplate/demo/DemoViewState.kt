package com.ramadan.androidtemplate.demo


sealed class DemoViewState {
    object Loading :DemoViewState()
    data class Success(val data:Any) : DemoViewState()
    data class Error(val throwable:Throwable) : DemoViewState()
}