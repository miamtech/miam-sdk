package com.miam.kmm_miam_sdk.miam_core.model

import kotlin.math.truncate

interface BasketPreviewEntry

class LineEntries {
    val found: MutableList<BasketEntry> = mutableListOf()
    val notFound: MutableList<BasketEntry> = mutableListOf()
    val oftenDeleted: MutableList<BasketEntry> = mutableListOf()
    val removed: MutableList<BasketEntry> = mutableListOf()

    fun copy() : LineEntries {
        val newEntries = LineEntries()
        found.let { newEntries.found.addAll(it) }
        removed.let { newEntries.removed.addAll(it) }
        oftenDeleted.let { newEntries.oftenDeleted.addAll(it) }
        notFound.let { newEntries.notFound.addAll(it) }
        return  newEntries
    }

    fun updateBasketEntries(basketEntries: List<BasketEntry>) {
        basketEntries.forEach { updateBasketEntry(it) }
    }

    private fun updateBasketEntry(basketEntry: BasketEntry) {
        updateBasketEntryInList(found, basketEntry)
        updateBasketEntryInList(removed, basketEntry)
        updateBasketEntryInList(oftenDeleted, basketEntry)
        updateBasketEntryInList(notFound, basketEntry)
    }

    private fun updateBasketEntryInList(basketEntries: MutableList<BasketEntry>, basketEntry: BasketEntry) {
        val existingEntryIdx = basketEntries.indexOfFirst{ be ->
            be.id == basketEntry.id
        }
        if (existingEntryIdx >= 0) {
            var existingEntry = basketEntries[existingEntryIdx]
            existingEntry = existingEntry.updateQuantity(basketEntry.attributes.quantity?: 0)
            basketEntries[existingEntryIdx] = existingEntry
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
    val description: List<String>? = emptyList(),
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
                description=  listOf("$itemsCount article${if (itemsCount > 1) 's' else ' '}"),
                count= guestNum ?: 4 ,
                entries = entries,
                price= recipePrice ?: "",
            )

            if(pricePerGuest != null) {
                bpl.description?.plus("${truncate(pricePerGuest)} € / personne")
            }

            return bpl
        }
        fun fromBasketEntry(entry: BasketEntry) : BasketPreviewLine {
            val item = entry.selectedItem
            val beItem = entry.attributes.basketEntriesItems?.find { bei ->bei.itemId ==  entry.attributes.selectedItemId }
            val price = if(beItem?.unitPrice != null && entry.attributes.quantity != null ) beItem.unitPrice * entry.attributes.quantity else 0.0
            val gEntry = entry._relationships?.groceriesEntry
            val recipesCount =  gEntry?.attributes?.recipeIds?.size ?: 1
            return BasketPreviewLine(
                // TODO : id is string
                id = entry.id.toString(),
                record = entry,
                isRecipe = false,
                inlineTag =  if (recipesCount > 1) "Pour $recipesCount recettes" else null,
                title= entry._relationships?.groceriesEntry?.attributes?.name ?: "",
                picture = item?.attributes?.image ?: "",
                description = listOf("${item?.attributes?.brand ?: ' '} ${item?.attributes?.name ?: ' '} | ${item?.attributes?.capacityUnit}"),
                price= "$price",
                count= entry.attributes.quantity ?: 1,
                entries = null,
            )
        }

        fun fromBasketEntryItem(entry: BasketEntry, item: Item) : BasketPreviewLine{
            val beItem = entry.attributes.basketEntriesItems?.find { bei ->bei.itemId ==  item.id }
            val price = if(beItem?.unitPrice != null && entry.attributes.quantity != null ) beItem.unitPrice * entry.attributes.quantity else 0.0
            val gEntry = entry._relationships?.groceriesEntry
            val recipesCount =  gEntry?.attributes?.recipeIds?.size ?: 1
            return BasketPreviewLine(
                id = item.id.toString(),
                record= entry,
                isRecipe = false,
                inlineTag =  if (recipesCount > 1) "Pour $recipesCount recettes" else null,
                title= entry._relationships?.groceriesEntry?.attributes?.name ?: "",
                picture = item.attributes.image ?: "",
                description = listOf("${item.attributes.brand ?: ' '} ${item.attributes.name ?: ' '} | ${item.attributes.capacityUnit}"),
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
                            entry.attributes.basketEntriesItems?.find { item -> item.itemId == entry.attributes.selectedItemId }
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