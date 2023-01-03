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
    open val backToRoute: () -> Unit,
    open val previous: Route?
) {

    abstract fun onDialogBack(route: DialogRoute)

    abstract fun onDialogClose(): PageRoute?

    abstract fun onPrevious()

}

data class DialogRoute(override val title: String, override val backToRoute: () -> Unit, override val previous: Route?, val closeDialog: () -> Unit):
    Route(title, backToRoute, previous) {

    override fun onDialogBack(route: DialogRoute) {
        this.backToRoute()
    }

    override fun onDialogClose(): PageRoute? {
        closeDialog()
        return previous?.onDialogClose()
    }

    override fun onPrevious() {
        if (previous == null) {
            this.closeDialog()
        } else {
            previous.onDialogBack(this)
        }
    }
}

data class PageRoute(override val title: String, override val backToRoute: () -> Unit, override val previous: Route?):
    Route(title, backToRoute, previous) {

    override fun onDialogBack(route: DialogRoute) {
        route.closeDialog()
    }

    override fun onDialogClose(): PageRoute {
        return this
    }

    override fun onPrevious() {
        previous?.let { it.backToRoute() }
    }
}

data class RouteServiceState(val route: Route?): State

sealed class RouteServiceAction: Action {
    data class SetDialogRoute(val title: String, val backToRoute: () -> Unit, val closeDialog: () -> Unit): RouteServiceAction()
    data class SetPageRoute(val title: String, val backToRoute: () -> Unit): RouteServiceAction()
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
         when (action) {
            is RouteServiceAction.SetDialogRoute -> {
                return launch(coroutineHandler) {
                    state.value = state.value.copy(route = DialogRoute(action.title, action.backToRoute, getCurrentRoute(), action.closeDialog))
                    state.value.route?.let { sideEffect.emit(RouteServiceEffect.RouteChanged(it)) }
                }
            }
            is RouteServiceAction.SetPageRoute -> {
                return launch(coroutineHandler) {
                    state.value = state.value.copy(route = PageRoute(action.title, action.backToRoute, getCurrentRoute()))
                    state.value.route?.let { sideEffect.emit(RouteServiceEffect.RouteChanged(it)) }
                }
            }   
        }
    }

    fun previous(): Route? {
        popRoute()?.let { poppedRoute ->
            poppedRoute.onPrevious()
            launch { poppedRoute.previous?.let { sideEffect.emit(RouteServiceEffect.RouteChanged(it)) } }
            return poppedRoute.previous
        }
        return null
    }

    private fun popRoute(): Route? {
        val poppedRoute = state.value.route
        state.value = state.value.copy(route = poppedRoute?.previous)
        return poppedRoute
    }

    fun onCloseDialog() {
        getCurrentRoute()?.let { currentRoute ->
            val lastPageRoute = currentRoute.onDialogClose()
            state.value = state.value.copy(route = lastPageRoute)
            launch { lastPageRoute?.let { route -> sideEffect.emit(RouteServiceEffect.RouteChanged(route)) } }
        }
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

    fun getCurrentRoute(): Route? {
        return RouteServiceInstance.instance.state.value.route
    }
}
