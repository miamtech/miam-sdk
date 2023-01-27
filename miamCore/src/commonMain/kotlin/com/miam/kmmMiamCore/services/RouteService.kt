package com.miam.kmmMiamCore.services

import com.miam.kmmMiamCore.base.mvi.Action
import com.miam.kmmMiamCore.base.mvi.Effect
import com.miam.kmmMiamCore.base.mvi.State
import com.miam.kmmMiamCore.base.mvi.Store
import com.miam.kmmMiamCore.handler.LogHandler
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
public object RouteServiceInstance: KoinComponent {
    public val instance: RouteService by inject()
}

public abstract class Route(
    public open val title: String,
    public open val backToRoute: () -> Unit,
    public open val previous: Route?
) {

    public abstract fun onDialogBack(route: DialogRoute)

    public abstract fun onDialogClose(): PageRoute?

    public abstract fun onPrevious()

}

public data class DialogRoute(override val title: String, override val backToRoute: () -> Unit, override val previous: Route?, val closeDialog: () -> Unit):
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

public data class PageRoute(override val title: String, override val backToRoute: () -> Unit, override val previous: Route?):
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

public data class RouteServiceState(val route: Route?): State

public sealed class RouteServiceAction: Action {
    public data class SetDialogRoute(val title: String, val backToRoute: () -> Unit, val closeDialog: () -> Unit): RouteServiceAction()
    public data class SetPageRoute(val title: String, val backToRoute: () -> Unit): RouteServiceAction()
    public data class SetInitialPageRoute(val title: String, val backToRoute: () -> Unit): RouteServiceAction()
}

public sealed class RouteServiceEffect: Effect {
    public data class RouteChanged(val newRoute: Route): RouteServiceEffect()
}

public open class RouteService: Store<RouteServiceState, RouteServiceAction, RouteServiceEffect>, CoroutineScope by CoroutineScope(Dispatchers.Main) {

    private val coroutineHandler = CoroutineExceptionHandler { _, exception ->
        println("[ERROR][Miam][RouteService] $exception ${exception.stackTraceToString()}")
    }

    override val state: MutableStateFlow<RouteServiceState> = MutableStateFlow(RouteServiceState(null))
    private val sideEffect = MutableSharedFlow<RouteServiceEffect>()
    override fun observeState(): StateFlow<RouteServiceState> = state
    override fun observeSideEffect(): Flow<RouteServiceEffect> = sideEffect

    override fun dispatch(action: RouteServiceAction): Job {
        return when (action) {
            is RouteServiceAction.SetDialogRoute -> {
                LogHandler.info("Route dialog ${action.title}")
                launch(coroutineHandler) {
                    state.value = state.value.copy(route = DialogRoute(action.title, action.backToRoute, getCurrentRoute(), action.closeDialog))
                    state.value.route?.let { sideEffect.emit(RouteServiceEffect.RouteChanged(it)) }
                }
            }
            is RouteServiceAction.SetPageRoute -> {
                LogHandler.info("Route page ${action.title}")
                launch(coroutineHandler) {
                    state.value = state.value.copy(route = PageRoute(action.title, action.backToRoute, getCurrentRoute()))
                    state.value.route?.let { sideEffect.emit(RouteServiceEffect.RouteChanged(it)) }
                }
            }
            is RouteServiceAction.SetInitialPageRoute -> {
                LogHandler.info("Route page ${action.title}")
                launch(coroutineHandler) {
                    state.value = state.value.copy(route = PageRoute(action.title, action.backToRoute, null))
                    state.value.route?.let { sideEffect.emit(RouteServiceEffect.RouteChanged(it)) }
                }
            }
        }
    }

    public fun previous(): Route? {
        popRoute()?.let { poppedRoute ->
            poppedRoute.onPrevious()
            launch { poppedRoute.previous?.let { sideEffect.emit(RouteServiceEffect.RouteChanged(it)) } }
            LogHandler.info("Route ${poppedRoute.previous}")
            return poppedRoute.previous
        }
        return null
    }

    private fun popRoute(): Route? {
        val poppedRoute = state.value.route
        state.value = state.value.copy(route = poppedRoute?.previous)
        return poppedRoute
    }

    public fun onCloseDialog() {
        getCurrentRoute()?.let { currentRoute ->
            val lastPageRoute = currentRoute.onDialogClose()
            state.value = state.value.copy(route = lastPageRoute)
            launch { lastPageRoute?.let { route -> sideEffect.emit(RouteServiceEffect.RouteChanged(route)) } }
        }
    }

    public fun onRouteChange(updateRoute: (miamRoute: Route?) -> Unit) {
        updateRoute(state.value.route)
        launch(coroutineHandler) {
            observeSideEffect()
                .filter { it is RouteServiceEffect.RouteChanged }
                .map { it as RouteServiceEffect.RouteChanged }
                .collect { effect -> updateRoute(effect.newRoute) }
        }
    }

    public fun getCurrentRoute(): Route? {
        return RouteServiceInstance.instance.state.value.route
    }
}
