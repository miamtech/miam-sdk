package com.miam.kmmMiamCore.handler


import com.miam.kmmMiamCore.base.mvi.PointOfSaleAction
import com.miam.kmmMiamCore.base.mvi.PointOfSaleStore
import com.miam.kmmMiamCore.helpers.letElse
import com.miam.kmmMiamCore.miam_core.data.repository.PackageRepositoryImp
import com.miam.kmmMiamCore.services.Analytics
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import kotlin.native.concurrent.ThreadLocal

open class CatalogCategory(
    val id: String,
    val title: String
)

@ThreadLocal
object PointOfSaleHandler: KoinComponent, CoroutineScope by CoroutineScope(Dispatchers.Main) {

    private val coroutineHandler = CoroutineExceptionHandler { _, exception ->
        println("Miam error in BasketStore $exception ${exception.stackTraceToString()}")
    }

    private val packageRepositoryImp: PackageRepositoryImp by inject()

    var isAvailable = fun(): Boolean { return true }
    private val store: PointOfSaleStore by inject()
    private val analytics: Analytics by inject()

    fun updateStoreId(storeId: String?) {
        if (store.samePos(storeId)) return

        store.dispatch(PointOfSaleAction.SetExtId(storeId))
    }

    fun setSupplier(supplierId: Int) {
        if (store.sameSupplier(supplierId)) return

        store.dispatch(PointOfSaleAction.SetSupplierId(supplierId))
    }

    fun setSupplierOrigin(origin: String) {
        store.setOrigin(origin)
        analytics.init(origin)
    }

    fun getCatalogCategories(setLocalCategories: (catalog: List<CatalogCategory>) -> Unit) {
        letElse(
            store.supplierId,
            { supplierId ->
                launch(coroutineHandler) {
                    val categories = packageRepositoryImp.getActivePackageForRetailer(supplierId.toString())
                        .map { CatalogCategory(it.id, it.attributes?.title ?: "") }
                    setLocalCategories(categories)
                }
            },
            {
                LogHandler.error("PointOfSaleHandler.getCatalogCategories with null supplier id")
                setLocalCategories(listOf())
            }
        )
    }
}
