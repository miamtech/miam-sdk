package com.miam.kmmMiamCore.services

import com.miam.kmmMiamCore.base.mvi.Action
import com.miam.kmmMiamCore.base.mvi.Effect
import com.miam.kmmMiamCore.base.mvi.State
import com.miam.kmmMiamCore.base.mvi.Store
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

@Suppress("UserPreferencesInstance used by ios and component")
object RouteServiceInstance: KoinComponent {
    val instance: RouteService by inject()
}

abstract class Route(
    open val title: String,
    open val backToRoute: () -> Unit
) {
    val previous: Route? = RouteService.getCurrentRoute()

    abstract fun onDialogBack(route: Route?): Boolean

    abstract fun onDialogClose(): Boolean

    abstract fun onPageBack()

    abstract fun onPrevious()

}

data class DialogRoute(override val title: String, override val backToRoute: () -> Unit, val closeDialog: () -> Unit):
    Route(title, backToRoute) {

    override fun onDialogBack(route: Route?): Boolean {
        route?.backToRoute?.let { it() }
        return false
    }

    override fun onDialogClose(): Boolean {
        return false
    }

    override fun onPageBack() {}

    override fun onPrevious() {
        if (previous == null || previous.onDialogBack(previous)) {
            this.closeDialog()
        }
    }
}

data class PageRoute(override val title: String, override val backToRoute: () -> Unit):
    Route(title, backToRoute) {

    override fun onDialogBack(route: Route?): Boolean {
        return true
    }

    override fun onDialogClose(): Boolean {
        return false
    }

    override fun onPageBack() {
        this.backToRoute()
    }

    override fun onPrevious() {
        previous?.onPageBack()
    }
}

data class RouteServiceState(val route: Route?): State

sealed class RouteServiceAction: Action {
    data class SetRoute(val route: Route): RouteServiceAction()
}

sealed class RouteServiceEffect: Effect {
    data class RouteChanged(val newRoute: Route): RouteServiceEffect()
}

open class RouteService: Store<RouteServiceState, RouteServiceAction, RouteServiceEffect>, CoroutineScope by CoroutineScope(Dispatchers.Main) {

    private val coroutineHandler = CoroutineExceptionHandler { _, exception ->
        println("[ERROR][Miam][RouteService] $exception ${exception.stackTraceToString()}")
    }

    override val state = MutableStateFlow(RouteServiceState(null))
    private val sideEffect = MutableSharedFlow<RouteServiceEffect>()
    override fun observeState(): StateFlow<RouteServiceState> = state
    override fun observeSideEffect(): Flow<RouteServiceEffect> = sideEffect

    override fun dispatch(action: RouteServiceAction): Job {
        return when (action) {
            is RouteServiceAction.SetRoute -> {
                return launch(coroutineHandler) {
                    state.value = state.value.copy(route = action.route)
                    state.value.route?.let { sideEffect.emit(RouteServiceEffect.RouteChanged(it)) }
                }
            }
            else -> launch { }
        }
    }

    fun previous(): Boolean {
        popRoute()?.onPrevious()
        state.value.route?.let { route ->
            launch {
                sideEffect.emit(route.let { RouteServiceEffect.RouteChanged(it) })
            }
            return false
        }
        return true
    }

    fun onCloseDialog() {
        getCurrentRoute()?.let {
            if (it.onDialogClose()) {
                launch {
                    sideEffect.emit(RouteServiceEffect.RouteChanged(it))
                }
            }
        }
        popRoute()?.let { onCloseDialog() }
    }

    private fun popRoute(): Route? {
        val poppedRoute = state.value.route
        state.value = state.value.copy(route = poppedRoute?.previous)
        return poppedRoute
    }

    fun onRouteChange(updateRoute: (miamRoute: Route?) -> Unit) {
        updateRoute(state.value.route)
        launch(coroutineHandler) {
            observeSideEffect()
                .filter { it is RouteServiceEffect.RouteChanged }
                .map { it as RouteServiceEffect.RouteChanged }
                .collect { effect -> updateRoute(effect.newRoute) }
        }
    }

    companion object {
        fun getCurrentRoute(): Route? {
            return RouteServiceInstance.instance.state.value.route
        }
    }
}
