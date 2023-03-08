package com.miam.kmmMiamCore.base


internal fun interface ParameterisedUseCase<I, R> {
    operator fun invoke(input: I): R
}

internal fun interface UseCase<R> {
    operator fun invoke(): R
}