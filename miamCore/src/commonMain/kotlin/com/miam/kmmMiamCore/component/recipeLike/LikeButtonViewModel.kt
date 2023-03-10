package com.miam.kmmMiamCore.component.recipeLike

import com.miam.kmmMiamCore.base.mvi.BaseViewModel
import com.miam.kmmMiamCore.base.mvi.BasicUiState
import com.miam.kmmMiamCore.base.mvi.LikeEffect
import com.miam.kmmMiamCore.base.mvi.LikeStore
import com.miam.kmmMiamCore.base.mvi.LikeStoreInstance
import com.miam.kmmMiamCore.handler.LogHandler
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.launch

public open class LikeButtonViewModel: BaseViewModel<RecipeLikeContract.Event, RecipeLikeContract.State, RecipeLikeContract.Effect>() {
    override fun createInitialState(): RecipeLikeContract.State = RecipeLikeContract.State()

    private val recipeLikeStore: LikeStore = LikeStoreInstance.instance

    private val coroutineHandler = CoroutineExceptionHandler { _, exception ->
        LogHandler.error(" [ERROR][Miam][RecipeLikeViewModel] $exception  ${exception.stackTraceToString()}")
    }
    private val viewModelScope = CoroutineScope(coroutineContext)

    override fun handleEvent(event: RecipeLikeContract.Event) {
        TODO("Not yet implemented")
    }

    public fun setRecipe(recipeId: String) {
        viewModelScope.launch(coroutineHandler) {
            val isLiked = recipeLikeStore.fetchAndGetRecipeLikes(listOf(recipeId)).any { recipeLike -> recipeLike.isLiked }
            setState { copy(recipeId = recipeId, isLiked = BasicUiState.Success(isLiked)) }
        }
        listenRecipeLikeChanges()
    }

    public fun listenRecipeLikeChanges() {
        val listenerJob = viewModelScope.launch(coroutineHandler) {
            recipeLikeStore.observeSideEffect().filter { likeEffect ->
                likeEffect.recipeId == currentState.recipeId
            }.collect { likeEffect ->
                setState { copy(isLiked = BasicUiState.Success(likeEffect is LikeEffect.Liked)) }
            }
        }
        setState { copy(likeListenerJob = listenerJob) }
    }

    public fun stopListenRecipeLikeChanges() {
        currentState.likeListenerJob?.cancel()
    }

    public fun toggleLike() {
        if (currentState.recipeId == null) return
        viewModelScope.launch(coroutineHandler) {
            recipeLikeStore.toggleLike(currentState.recipeId!!)
        }
    }
}