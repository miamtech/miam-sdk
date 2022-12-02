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

data class Route(val name: String, val title: String, val isModalRoute: Boolean, val goToPreviousAction: () -> Unit, val previous: Route?)


data class RouteServiceState(val route: Route?): State

sealed class RouteServiceAction: Action {
    data class SetRoute(val route: Route): RouteServiceAction()
}

sealed class RouteServiceEffect: Effect {
    data class RouteChanged(val newRoute: Route): RouteServiceEffect()
    object CloseModal: RouteServiceEffect()
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
                    state.value.route?.let {
                        if (state.value.route!!.isModalRoute && !action.route.isModalRoute) {
                            sideEffect.emit(RouteServiceEffect.CloseModal)
                            popRoute()
                        }
                    }
                    state.value = state.value.copy(route = action.route)
                    state.value.route?.let { sideEffect.emit(RouteServiceEffect.RouteChanged(it)) }
                }
            }
            else -> launch { }
        }
    }

    fun previous(): Boolean {
        // no need to use miam previous function
        if (state.value.route == null) return true
        state.value.route?.let { route ->
            // if it was last route of the modal internal routing
            if (route.isModalRoute && (route.previous == null || !route.previous.isModalRoute)) {
                launch(coroutineHandler) {
                    sideEffect.emit(RouteServiceEffect.CloseModal)
                }
            }
        }

        state.value.route?.let {
            it.goToPreviousAction()
        }

        state.value = state.value.copy(route = state.value.route?.previous)
        state.value.route?.let { route ->
            launch {
                sideEffect.emit(route.let { RouteServiceEffect.RouteChanged(it) })
            }
        }
        return false
    }

    fun popRoute() {
        clearModalHistory(state.value.route?.previous)
    }

    fun getCurrentRoute(): Route? {
        return state.value.route
    }

    private fun clearModalHistory(route: Route?) {
        if (route == null || !route.isModalRoute) {
            return
        }
        clearModalHistory(route.previous)
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

    fun onClose(closeAction: () -> Unit) {
        launch(coroutineHandler) {
            observeSideEffect()
                .filter { it is RouteServiceEffect.CloseModal }
                .collect { _ ->
                    popRoute()
                    closeAction()
                }
        }
    }
}
