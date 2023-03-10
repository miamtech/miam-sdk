package com.miam.kmmMiamCore.component.basketPreview

import com.miam.core.sdk.di.MiamDI
import com.miam.kmmMiamCore.base.mvi.*
import com.miam.kmmMiamCore.component.itemSelector.ItemSelectorContract
import com.miam.kmmMiamCore.component.itemSelector.ItemSelectorViewModel
import com.miam.kmmMiamCore.handler.ContextHandler
import com.miam.kmmMiamCore.handler.LogHandler
import com.miam.kmmMiamCore.miam_core.model.BasketEntry
import com.miam.kmmMiamCore.miam_core.model.BasketPreviewLine
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.launch


public open class BasketPreviewViewModel(public val recipeId: String?):
    BaseViewModel<BasketPreviewContract.Event, BasketPreviewContract.State, BasketPreviewContract.Effect>() {

    private val coroutineHandler = CoroutineExceptionHandler { _, exception ->
        LogHandler.error(" [ERROR][Miam][Basket preview] $exception ${exception.stackTraceToString()}")
    }

    private val basketStore: BasketStore = MiamDI.basketStore
    private val groceriesListStore: GroceriesListStore = MiamDI.groceriesListStore
    private val itemSelectorViewModel: ItemSelectorViewModel = MiamDI.itemSelectorViewModel
    private val miamContext: ContextHandler = MiamDI.contextHandler
    private val lineEntriesSubject = MutableSharedFlow<List<BasketEntry>>()

    public data class LineUpdateState(val lineUpdates: List<BasketEntry>): State

    private val lineUpdateState: MutableStateFlow<LineUpdateState> =
        MutableStateFlow(LineUpdateState(listOf()))

    override fun createInitialState(): BasketPreviewContract.State =
        BasketPreviewContract.State(
            recipeId = null,
            showLines = false,
            line = BasicUiState.Loading,
            bpl = null,
            isReloading = false,
            job = null
        )

    init {
        if (miamContext.isReady()) {
            if (recipeId != null) {
                basketChange()
                val job = launch(coroutineHandler) {
                    basketStore.observeSideEffect()
                        .filter { basketEffect -> basketEffect == BasketEffect.BasketPreviewChange }
                        .collect {
                            basketChange()
                        }
                }
                setState { copy(job = job) }
                listenEntriesChanges()
            }
        } else {
            setState { copy(line = BasicUiState.Error("Miam is not well configure check if miam is ready")) }
        }

    }

    public fun dispose() {
        currentState.job?.cancel()
    }

    private fun listenEntriesChanges() {
        CoroutineScope(Dispatchers.Default).launch(coroutineHandler) {
            lineEntriesSubject.debounce(500).collect { entries ->
                LogHandler.info("launching update of [ ${entries.map { entrie -> "${entrie.selectedItem?.attributes?.name}/ ${entrie.attributes?.quantity}--" }}]")
                val newBpl = updateBplEntries(entries)
                setState { copy(updatingBasketEntryId = entries[0].id) }
                lineUpdateState.value = lineUpdateState.value.copy(lineUpdates = listOf())
                setState { copy(line = BasicUiState.Success(newBpl), bpl = newBpl) }
                // create a copy of the list so you can clear it here
                basketStore.dispatch(
                    BasketAction.UpdateBasketEntries(
                        mutableListOf(*entries.toTypedArray())
                    )
                ).invokeOnCompletion {
                    setState { copy(updatingBasketEntryId = null) }
                }
            }
        }
    }

    override fun handleEvent(event: BasketPreviewContract.Event) {
        when (event) {
            is BasketPreviewContract.Event.SetRecipeId -> setRecipeId(event.newRecipeId)
            is BasketPreviewContract.Event.SetLines -> setLines(event.newlines)
            is BasketPreviewContract.Event.AddEntry -> addEntry(event.entry)
            is BasketPreviewContract.Event.ReplaceItem -> replaceItem(event.entry)
            is BasketPreviewContract.Event.ToggleLine -> toggleLine()
            is BasketPreviewContract.Event.OpenItemSelector -> openItemSelector(event.bpl)
            is BasketPreviewContract.Event.KillJob -> dispose()
            is BasketPreviewContract.Event.RemoveRecipe -> {
                groceriesListStore.dispatch(GroceriesListAction.RemoveRecipe(recipeId = event.recipeId))
            }
            else -> {
                setState { copy(line = BasicUiState.Error("Event not found")) }
            }
        }
    }

    private fun setRecipeId(newRecipeId: Int) {
        setState { copy(recipeId = newRecipeId) }
    }

    private fun reloadState() {
        setState { copy(isReloading = true) }
    }

    /**
     * BPL comes from the basket store that does not get relathionships
     * Will get them all at need only and then fill the entryLines with this basket entries
     * TODO : if we have include request working the get of relationships can be done in basket store directly
     */
    private fun setLines(newline: BasketPreviewLine) {
        LogHandler.debug("[Miam] newline ${newline.title}")
        setState { copy(line = BasicUiState.Success(newline), bpl = newline, isReloading = false) }
    }

    private fun toggleLine() {
        setState { copy(showLines = !uiState.value.showLines) }
    }

    public fun updateGuest(onUpdateGuest: (guestCount: Int) -> Unit, guestCount: Int) {
        reloadState()
        onUpdateGuest(guestCount)
    }

    private fun updateBplEntries(basketEntries: List<BasketEntry>): BasketPreviewLine {
        val newBplEntries = currentState.bpl!!.entries!!.updateBasketEntries(basketEntries)
        return currentState.bpl!!.copy(
            entries = newBplEntries,
            price = newBplEntries.totalPrice().toString()
        )
    }

    private fun addEntry(entry: BasketEntry) {
        LogHandler.debug("[Miam] add entry ${entry.selectedItem?.attributes?.name} / ${entry.attributes?.quantity}")
        if (currentState.bpl == null || currentState.bpl!!.entries == null) {
            LogHandler.error("Trying to add entry with null bpl")
            return
        }

        val currentEntries = currentState.bpl!!.entries!!
        val newEntries = currentEntries.copy(
            found = currentEntries.found.toMutableList().plus(entry)
                .sortedBy { basketEntry -> basketEntry.id },
            oftenDeleted = currentEntries.oftenDeleted.toMutableList()
                .filter { e -> e.id != entry.id },
            removed = currentEntries.removed.toMutableList().filter { e -> e.id != entry.id }
        )
        val newBpl = currentState.bpl!!.copy(
            entries = newEntries,
            price = newEntries.totalPrice().toString()
        )
        setState { copy(bpl = newBpl, line = BasicUiState.Success(newBpl)) }
        basketStore.dispatch(BasketAction.AddBasketEntry(entry))
    }

    public fun removeBasketEntry(entry: BasketEntry) {
        LogHandler.debug("[Miam] remove ${entry.selectedItem?.attributes?.name}")
        if (currentState.bpl == null || currentState.bpl!!.entries == null) {
            LogHandler.error("Trying to add entry with null bpl")
            return
        }

        val currentEntries = currentState.bpl!!.entries!!
        val newEntries = currentEntries.copy(
            found = currentEntries.found.toMutableList().filter { e -> e.id != entry.id }
                .sortedBy { basketEntry -> basketEntry.id },
            removed = currentEntries.removed.toMutableList().plus(entry)
        )
        val newBpl = currentState.bpl!!.copy(
            entries = newEntries,
            price = newEntries.totalPrice().toString()
        )
        val newLineUpdates = lineUpdateState.value.lineUpdates.filter { be -> be.id != entry.id }
        lineUpdateState.value = lineUpdateState.value.copy(lineUpdates = newLineUpdates)
        setState { copy(bpl = newBpl, line = BasicUiState.Success(newBpl)) }
        basketStore.dispatch(BasketAction.RemoveEntry(entry))
    }

    public fun updateBasketEntry(entry: BasketEntry, finalQty: Int) {
        val newEntry = entry.updateQuantity(finalQty)

        val newLineUpdates =
            lineUpdateState.value.lineUpdates.filter { be -> be.id != entry.id }.plus(newEntry)
        lineUpdateState.value = lineUpdateState.value.copy(lineUpdates = newLineUpdates)
        //setState { copy(lineUpdates = newLineUpdates) }
        launch(Dispatchers.Default) {
            lineEntriesSubject.emit(newLineUpdates)
        }
    }

    private fun replaceItem(entry: BasketEntry) {
        val newBpl = updateBplEntries(listOf(entry))
        setState { copy(line = BasicUiState.Success(newBpl), bpl = newBpl) }
        setEvent(BasketPreviewContract.Event.CloseItemSelector)
    }

    private fun basketChange() {
        val bpl =
            basketStore.state.value.basketPreview?.find { basketPreviewLine -> basketPreviewLine.id == recipeId.toString() }
        if (bpl != null) {
            setEvent(BasketPreviewContract.Event.SetLines(bpl))
        }
    }

    private fun openItemSelector(bpl: BasketPreviewLine) {
        itemSelectorViewModel.setSelectedItem(bpl)
        itemSelectorViewModel.setEvent(ItemSelectorContract.Event.SetReplaceItemInPreview(replace = fun(
            be: BasketEntry
        ) {
            this.replaceItem(be)
        }))
    }
}