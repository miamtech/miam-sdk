package com.miam.kmmMiamCore.usecase

import com.miam.core.sdk.di.MiamDI
import com.miam.kmmMiamCore.base.ParameterisedUseCase
import com.miam.kmmMiamCore.base.mvi.PointOfSaleAction
import com.miam.kmmMiamCore.base.mvi.PointOfSaleStore
import com.miam.kmmMiamCore.miam_core.data.repository.SupplierRepository
import com.miam.kmmMiamCore.services.Analytics
import dev.icerock.moko.resources.desc.StringDesc
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

public data class SupplierInfo(val supplierId: Int)

public class SetSupplierUseCase(private val supplierRepository: SupplierRepository, private val pointOfSaleStore: PointOfSaleStore):
    ParameterisedUseCase<SupplierInfo, Unit>, CoroutineScope by MainScope() {

    override fun invoke(input: SupplierInfo) {
        val (supplierId) = input
        if (pointOfSaleStore.sameSupplier(supplierId)) return
        pointOfSaleStore.dispatch(PointOfSaleAction.SetSupplierId(supplierId))
        launch {
            val supplier = supplierRepository.getSupplier(supplierId)
            supplier.attributes?.languageId?.let { lang ->
                StringDesc.localeType = StringDesc.LocaleType.Custom(lang)
            }
            supplier.attributes?.name?.let { name ->
                pointOfSaleStore.setOrigin(name)
            }
        }
    }

    public companion object {
        public val create : SetSupplierUseCase = SetSupplierUseCase(MiamDI.supplierRepository, MiamDI.pointOfSaleStore)
    }
}