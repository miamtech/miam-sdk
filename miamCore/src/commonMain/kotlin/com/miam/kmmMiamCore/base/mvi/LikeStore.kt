package com.miam.kmmMiamCore.base.mvi

import com.miam.kmmMiamCore.miam_core.model.Recipe
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent

sealed class LikeEffect : Effect {
    data class Disliked(val recipeId: String) : LikeEffect()
    data class LikeRecipe(val recipe: Recipe) : LikeEffect()
}

class LikeStore :
    KoinComponent,
    CoroutineScope by CoroutineScope(Dispatchers.Main) {

    private val coroutineHandler = CoroutineExceptionHandler { _, exception ->
        println("[ERROR][Miam][LikeStore] $exception ${exception.stackTraceToString()}")
    }

    private val sideEffect = MutableSharedFlow<LikeEffect>()

    fun observeSideEffect(): Flow<LikeEffect> = sideEffect

    fun emitEffect(le: LikeEffect) {
        launch {
            sideEffect.emit(le)
        }
    }
}