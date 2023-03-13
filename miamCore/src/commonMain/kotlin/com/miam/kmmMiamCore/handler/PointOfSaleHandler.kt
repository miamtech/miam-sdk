package com.miam.kmmMiamCore.handler


import com.miam.core.sdk.di.MiamDI
import com.miam.kmmMiamCore.base.mvi.PointOfSaleAction
import com.miam.kmmMiamCore.base.mvi.PointOfSaleStore
import com.miam.kmmMiamCore.helpers.letElse
import com.miam.kmmMiamCore.miam_core.data.repository.PackageRepositoryImp
import com.miam.kmmMiamCore.services.Analytics
import com.miam.kmmMiamCore.usecase.SetSupplierUseCase
import com.miam.kmmMiamCore.usecase.SupplierInfo
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import kotlin.native.concurrent.ThreadLocal

public open class CatalogCategory(
    public val id: String,
    public val title: String
)

@ThreadLocal
public object PointOfSaleHandler: CoroutineScope by MainScope() {

    private val coroutineHandler = CoroutineExceptionHandler { _, exception ->
        println("Miam error in BasketStore $exception ${exception.stackTraceToString()}")
    }

    private val packageRepositoryImp: PackageRepositoryImp by lazy { MiamDI.packageRepository }

    public var isAvailable: () -> Boolean = fun(): Boolean { return true }
    private val store: PointOfSaleStore = MiamDI.pointOfSaleStore
    private val analytics: Analytics = MiamDI.analyticsService

    public fun updateStoreId(storeId: String?) {
        if (store.samePos(storeId)) return

        store.dispatch(PointOfSaleAction.SetExtId(storeId))
    }

    public fun setSupplier(supplierId: Int) {
        SetSupplierUseCase.create.invoke(SupplierInfo(supplierId))
    }

    public fun setSupplierOrigin(origin: String) {
        analytics.init(origin)
    }

    public fun getCatalogCategories(setLocalCategories: (catalog: List<CatalogCategory>) -> Unit) {
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
