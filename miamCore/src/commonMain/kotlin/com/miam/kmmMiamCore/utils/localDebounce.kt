package com.miam.kmmMiamCore.utils

import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch


data class DebounceState(val value: Any? = null, val job: Job? = null)

class LocalDebounce(private val timeMillis: Long, val callback: (value: Any) -> Unit): CoroutineScope by CoroutineScope(Dispatchers.Main) {

    val state = MutableStateFlow(DebounceState())

    private val coroutineHandler = CoroutineExceptionHandler { _, exception ->
        println(" [ERROR][Miam][Debounce] $exception")
    }

    fun next(value: Any) {
        if (this.state.value.job != null) {
            this.state.value.job?.let { it.cancel() }
        }
        val job = launch(coroutineHandler) {
            delay(timeMillis)
            callback(value)
        }
        this.state.value = this.state.value.copy(value = value, job = job)
    }

}