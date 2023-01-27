@file:Suppress("Filename", "MatchingDeclarationName")

package com.miam.test

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import com.miam.core.localisation.I18nResolver
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
public actual abstract class PlatformResourceTest {
    init {
        val ctx: Context = ApplicationProvider.getApplicationContext()
        I18nResolver.registerContext(ctx)
    }
}