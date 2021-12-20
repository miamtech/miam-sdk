package com.example.kmm_miam_sdk.network

import io.ktor.client.*

expect class KtorClientFactory {
    fun build(): HttpClient
}