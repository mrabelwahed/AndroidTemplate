package com.ramadan.takeaway.domain.interactor
interface Usecase<P, R> {
    fun execute(param: P): R
}
