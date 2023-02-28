@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.android.library)
    id("com.kezong.fat-aar")
}

android {
    val compileSdkVersion: String by project
    val targetSdkVersion: String by project
    val minSdkVersion: String by project
    compileSdk = Integer.parseInt(compileSdkVersion)

    defaultConfig {
        minSdk = Integer.parseInt(minSdkVersion)
        targetSdk = Integer.parseInt(targetSdkVersion)
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.composeCompilerPlugin.get()
    }

    testOptions {
        unitTests {
            isIncludeAndroidResources = true
        }
    }

    kotlinOptions {
        jvmTarget = "1.8"
        freeCompilerArgs += listOf(
            "-Xjvm-default=all",
            "-opt-in=coil.annotation.ExperimentalCoilApi"
        )
    }
}

dependencies {
    embed(project(":miamCore"))
    embed(project(":core"))

    api(libs.kotlinx.coroutines.core)
    api(libs.kotlinx.coroutines.android)

    api(libs.ktor.client.core)
    api(libs.ktor.client.android)
    api(libs.ktor.client.serialization)
    api(libs.ktor.client.contentNegotiation)
    api(libs.ktor.client.logging)

    api(libs.androidx.activity.compose)
    api(libs.androidx.core.ktx)
    api(libs.android.material)
    api(libs.android.legacy)

    api(libs.compose.compiler)
    api(libs.compose.ui.tooling)
    api(libs.compose.ui.core)
    api(libs.compose.foundation)
    api(libs.compose.material.core)
    api(libs.compose.material.icons.core)
    api(libs.compose.material.icons.extended)
    api(libs.compose.runtime.livedata)
    api(libs.compose.runtime.rxjava2)

    api(libs.koin.core)
    api(libs.koin.android)
    api(libs.coil.compose)
    api(libs.coil.svg)

    api(libs.moko.resources.core)
    api(libs.moko.resources.android)

    api(libs.android.exoplayer)

    androidTestImplementation(libs.compose.ui.test)
}

val PUBLISH_GROUP_ID by extra("tech.miam.sdk")
val PUBLISH_ARTIFACT_ID by extra { "kmm-miam-sdk" }

apply("${rootDir}/scripts/publish-module.gradle")
