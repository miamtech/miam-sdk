package com.miam.kmmMiamCore.base.executor

import com.miam.kmmMiamCore.domain.MainDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import kotlin.coroutines.CoroutineContext

abstract class MainIoExecutor : com.miam.kmmMiamCore.base.executor.IExecutorScope, CoroutineScope,
    KoinComponent {

    private val mainDispatcher: MainDispatcher by inject()
    val ioDispatcher: CoroutineDispatcher by inject()

    private var job = SupervisorJob()

    override val coroutineContext: CoroutineContext
        get() = job + mainDispatcher.dispatcher

    override fun attach() {
        job = SupervisorJob()
    }

    override fun detach() {
        job.cancel()
    }

    protected fun <T> launch(
        flow: Flow<T>,
        onSuccess: (T) -> Unit,
        onError: ((Throwable) -> Unit)? = null
    ) {
        launch {
            flow
                .flowOn(ioDispatcher)
                .catch {
                    onError?.invoke(it)
                }
                .collect {
                    onSuccess(it)
                }
        }
    }

    protected fun <T> collect(flow: Flow<T>, collect: (T) -> Unit) {
        launch {
            flow.collect { collect(it) }
        }
    }
}