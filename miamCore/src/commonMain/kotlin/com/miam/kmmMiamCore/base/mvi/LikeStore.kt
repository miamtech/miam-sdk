package com.miam.kmmMiamCore.base.mvi

import com.miam.kmmMiamCore.handler.ToasterHandler
import com.miam.kmmMiamCore.miam_core.data.repository.RecipeLikeRepositoryImp
import com.miam.kmmMiamCore.miam_core.data.repository.RecipeRepositoryImp
import com.miam.kmmMiamCore.miam_core.model.Recipe
import com.miam.kmmMiamCore.miam_core.model.RecipeLike
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

sealed class LikeEffect(open val recipeId: String): Effect {
    data class Disliked(override val recipeId: String): LikeEffect(recipeId)
    data class Liked(val recipe: Recipe): LikeEffect(recipe.id)
}

object LikeStoreInstance: KoinComponent {
    val instance: LikeStore by inject()
}

class LikeStore: KoinComponent, CoroutineScope by CoroutineScope(Dispatchers.Main) {

    private val recipeLikeRepositoryImp: RecipeLikeRepositoryImp by inject()
    private val recipeRepositoryImp: RecipeRepositoryImp by inject()

    private val sideEffect = MutableSharedFlow<LikeEffect>()
    private val recipeLikesState = MutableStateFlow<Map<String, RecipeLike>>(mapOf())

    private fun updateRecipeLikesState(apply: MutableMap<String, RecipeLike>.() -> Unit) {
        val newState = recipeLikesState.value.toMutableMap()
        newState.apply()
        recipeLikesState.value = newState
    }

    fun observeSideEffect(): Flow<LikeEffect> = sideEffect

    private suspend fun emitEffect(le: LikeEffect) {
        if (le is LikeEffect.Liked) ToasterHandler.onLikeRecipe()
        sideEffect.emit(le)
    }

    suspend fun fetchAndGetRecipeLikes(recipeIds: List<String>): List<RecipeLike> {
        val toFetchLikes = recipeIds.filter { recipeId -> !recipeLikesState.value.containsKey(recipeId) }
        if (toFetchLikes.isNotEmpty()) fetchRecipeLikes(toFetchLikes)
        return recipeIds.mapNotNull { recipeId -> recipeLikesState.value[recipeId] }
    }

    private suspend fun fetchRecipeLikes(recipeIds: List<String>) {
        val recipeLikes = recipeLikeRepositoryImp.getRecipeLikes(recipeIds)
        val unsetLikes = recipeIds.toMutableList()
        updateRecipeLikesState {
            recipeLikes.forEach { recipeLike ->
                recipeLike.attributes?.recipeId?.let { recipeId ->
                    this[recipeId.toString()] = recipeLike
                    unsetLikes.remove(recipeId.toString())
                }
            }
            unsetLikes.forEach { recipeId -> this[recipeId] = RecipeLike.createDefault(recipeId) }
        }
    }

    suspend fun toggleLike(recipeId: String): RecipeLike {
        if (!recipeLikesState.value.containsKey(recipeId)) fetchRecipeLikes(listOf(recipeId))

        val recipeLikeToReturn = getOrCreateToggledLike(recipeId)
        updateRecipeLikesState { this[recipeId] = recipeLikeToReturn }
        emitLike(recipeLikeToReturn)
        return recipeLikeToReturn
    }

    private suspend fun getOrCreateToggledLike(recipeId: String): RecipeLike {
        val existingLike = recipeLikesState.value[recipeId]
        return if (existingLike != null && existingLike.exists) {
            recipeLikeRepositoryImp.updateRecipeLike(existingLike.toggle())
        } else if (existingLike != null) {
            recipeLikeRepositoryImp.createRecipeLike(existingLike)
        } else {
            recipeLikeRepositoryImp.createRecipeLike(RecipeLike.createDefault(recipeId))
        }
    }

    private suspend fun emitLike(recipeLike: RecipeLike) {
        if (recipeLike.attributes!!.isPast) {
            emitEffect(LikeEffect.Disliked(recipeLike.attributes.recipeId.toString()))
        } else {
            // TODO fetch outside
            val recipe = recipeRepositoryImp.getRecipeById(recipeLike.attributes.recipeId.toString())
            emitEffect(LikeEffect.Liked(recipe))
        }
    }
}