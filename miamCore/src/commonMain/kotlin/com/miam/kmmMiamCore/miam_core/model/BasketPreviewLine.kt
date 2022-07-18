package com.miam.kmmMiamCore.miam_core.model

import kotlin.math.truncate

interface BasketPreviewEntry

data class LineEntries(
    val found: List<BasketEntry> = listOf(),
    val notFound: List<BasketEntry> = listOf(),
    val oftenDeleted: List<BasketEntry> = listOf(),
    val removed: List<BasketEntry> = listOf()
) {
    fun updateBasketEntries(basketEntries: List<BasketEntry>): LineEntries {
        return this.copy(
            found = updateBasketEntryInList(found, basketEntries),
            notFound = updateBasketEntryInList(notFound, basketEntries),
            oftenDeleted = updateBasketEntryInList(oftenDeleted, basketEntries),
            removed = updateBasketEntryInList(removed, basketEntries),
        )
    }

    private fun updateBasketEntryInList(existingBasketEntries: List<BasketEntry>, newBasketEntries: List<BasketEntry>): List<BasketEntry> {
        return existingBasketEntries.map { be ->
            newBasketEntries.find { newBe -> newBe.id == be.id }?: be
        }
    }

    fun totalPrice(): Double {
        return found.sumOf { be ->
            val beItem = be.attributes!!.basketEntriesItems?.find { bei -> bei.itemId == be.attributes.selectedItemId }
            if (beItem?.unitPrice != null && be.attributes.quantity != null) beItem.unitPrice * be.attributes.quantity else 0.0
        }
    }
}

data class BasketPreviewLine(
    val id : String? = null,
    val record : BasketPreviewEntry,
    val isRecipe: Boolean,
    val inlineTag : String? = null,
    val title: String,
    val picture: String,
    // description is a key work in swift
    val bplDescription: List<String> = emptyList(),
    val price: String,
    val count: Int,
    val entries : LineEntries?,
    val _displayMode: Boolean = false,
) {

    fun hasEntries(): Boolean {
        return this.entries != null && (
                this.entries.found.isNotEmpty() ||
                        this.entries.notFound.isNotEmpty()||
                        this.entries.oftenDeleted.isNotEmpty() ||
                        this.entries.removed.isNotEmpty())
    }

    companion object {
        fun fromRecipe( recipe: Recipe,
                        itemsCount: Int,
                        pricePerGuest: Double? = null,
                        guestNum: Int? = 4,
                        recipePrice: String? = "",
                        entries: LineEntries? = null) : BasketPreviewLine {
            var bpl = BasketPreviewLine(
                id= recipe.id,
                record= recipe,
                isRecipe= true,
                title= recipe.attributes!!.title,
                picture= recipe.attributes!!.mediaUrl ?: "",
                bplDescription=  listOf("$itemsCount article${if (itemsCount > 1) 's' else ' '}"),
                count= guestNum ?: 4 ,
                entries = entries,
                price= recipePrice ?: "",
            )

            if(pricePerGuest != null) {
                bpl.bplDescription?.plus("${truncate(pricePerGuest)} € / personne")
            }

            return bpl
        }
        fun fromBasketEntry(entry: BasketEntry) : BasketPreviewLine {
            val item = entry.selectedItem
            val beItem = entry.attributes!!.basketEntriesItems?.find { bei ->bei.itemId ==  entry.attributes.selectedItemId }
            val price = if(beItem?.unitPrice != null && entry.attributes.quantity != null ) beItem.unitPrice * entry.attributes.quantity else 0.0
            val gEntry = entry.relationships?.groceriesEntry?.data
            val recipesCount =  gEntry?.attributes?.recipeIds?.size ?: 1
            return BasketPreviewLine(
                // TODO : id is string
                id = entry.id.toString(),
                record = entry,
                isRecipe = false,
                inlineTag =  if (recipesCount > 1) "Pour $recipesCount recettes" else null,
                title= entry.relationships?.groceriesEntry?.data?.attributes?.name ?: "",
                picture = item?.attributes?.image ?: "",
                bplDescription = listOf("${item?.attributes?.name ?: ' '} \n ${item?.attributes?.capacityUnit}", "${item?.attributes?.brand ?: ' '}"),
                price= "$price",
                count= entry.attributes.quantity ?: 1,
                entries = null,
            )
        }

        fun fromBasketEntryItem(entry: BasketEntry, item: Item) : BasketPreviewLine{
            val beItem = entry.attributes!!.basketEntriesItems?.find { bei ->bei.itemId.toString() ==  item.id }
            val price = if(beItem?.unitPrice != null && entry.attributes.quantity != null ) beItem.unitPrice * entry.attributes.quantity else 0.0
            val gEntry = entry.relationships?.groceriesEntry?.data
            val recipesCount =  gEntry?.attributes?.recipeIds?.size ?: 1
            return BasketPreviewLine(
                id = item.id.toString(),
                record= entry,
                isRecipe = false,
                inlineTag =  if (recipesCount > 1) "Pour $recipesCount recettes" else null,
                title= entry.relationships?.groceriesEntry?.data?.attributes?.name ?: "",
                picture = item.attributes!!.image ?: "",
                bplDescription = listOf("${item.attributes.brand ?: ' '} ${item.attributes.name ?: ' '} | ${item.attributes.capacityUnit}"),
                price= "$price",
                count= entry.attributes.quantity ?: 1,
                entries = null,
            )
        }

        fun  recipesAndLineEntriesToBasketPreviewLine(groceriesList: GroceriesList, lineEntries: List<LineEntries>) : List<BasketPreviewLine> {
            val recipes = groceriesList.recipes

            return recipes.mapIndexed { idx, recipe ->
                val itemsCount = lineEntries[idx].found.size
                var recipePrice = 0.0
                val guests =  groceriesList.guestsForRecipe(recipe.id)
                lineEntries[idx].found.forEach { entry ->
                        val selectedItem =
                            entry.attributes!!.basketEntriesItems?.find { item -> item.itemId == entry.attributes.selectedItemId }
                                ?: null
                        val qty = entry.attributes.quantity ?: 1
                        val unitPrice = selectedItem?.unitPrice ?: 0.0
                        val numberOfRecipesForEntry = entry.attributes.recipeIds?.size ?: 1
                        recipePrice += (qty * unitPrice) / numberOfRecipesForEntry
                }

                val pricePerGuest = recipePrice / guests;

                 fromRecipe(recipe, itemsCount, pricePerGuest,guests, recipePrice.toString(), lineEntries[idx]);
            }
        }

    }
}