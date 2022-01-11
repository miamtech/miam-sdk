package com.miam.kmm_miam_sdk.network.model


import com.miam.kmm_miam_sdk.network.model.DAO.RecipeAttributes
import kotlin.time.Duration
import kotlin.time.Duration.Companion.minutes

class Recipe (val attributes: RecipeAttributes) : MiamObject {

     var ingredients : List<Ingredient> = emptyList()

     val totalTime: String
         get() {
           var duration : Duration = 0.minutes
             duration = duration.plus(this.attributes.preheatingTime ?: 0.minutes)
             duration = duration.plus(this.attributes.cookingTime  ?: 0.minutes )
             duration =  duration.plus(this.attributes.restingTime  ?: 0.minutes)
             if (duration.inWholeMinutes < 10) {
                 return " < 10 min";
             }
             return duration.toString()
         }

    val difficultyLabel : String
        get () {
            return when(this.attributes.difficulty) {
                1 -> "facile"
                2 -> "moyen"
                3-> "difficile"
                else -> "facile"
            }
        }

    val costLabel : String
        get () {
            return when(this.attributes.difficulty) {
                1 -> "faible"
                2 -> "moyen"
                3-> "difficile"
                else -> "moyen"
            }
        }

    companion object {
         var type  = "recipes"
         var relationship = arrayOf("ingredients")
    }

}

