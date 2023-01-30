package com.miam.kmmMiamCore.base.executor

import com.miam.kmmMiamCore.domain.MainDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

public abstract class MainExecutor : IExecutorScope, CoroutineScope {

    private val mainDispatcher: MainDispatcher = MainDispatcher()

    private var job = SupervisorJob()

    override val coroutineContext: CoroutineContext
        get() = job + mainDispatcher.dispatcher

    override fun attach() {
        job = SupervisorJob()
    }

    override fun detach() {
        job.cancel()
    }

    protected fun <T> collect(flow: Flow<T>, collect: (T) -> Unit) {
        launch {
            flow.collect { collect(it) }
        }
    }
}