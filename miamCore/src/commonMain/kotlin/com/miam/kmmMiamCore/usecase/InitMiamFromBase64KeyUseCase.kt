package com.miam.kmmMiamCore.usecase

import com.miam.core.sdk.di.MiamDI
import com.miam.kmmMiamCore.base.ParameterisedUseCase
import com.miam.kmmMiamCore.miam_core.model.MiamContext
import com.miam.kmmMiamCore.services.Analytics
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope

internal class InitMiamFromBase64KeyUseCase(
    val setSupplierUseCase: SetSupplierUseCase,
    val analytics: Analytics,
) : ParameterisedUseCase<MiamContext, Unit>, CoroutineScope by MainScope() {

    override fun invoke(input: MiamContext) {
        setSupplierUseCase(SupplierInfo(input.SupplierID))
        analytics.init(input.plausibleDomain)
    }

    internal companion object {
         val create : InitMiamFromBase64KeyUseCase = InitMiamFromBase64KeyUseCase(SetSupplierUseCase.create, MiamDI.analyticsService)
    }
}