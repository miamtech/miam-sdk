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
        freeCompilerArgs += "-Xjvm-default=all"
    }
}

dependencies {
    api(project(":miamCore"))

    implementation(libs.kotlinx.coroutines.core)
    implementation(libs.kotlinx.coroutines.android)

    implementation(libs.ktor.client.core)
    implementation(libs.ktor.client.android)
    implementation(libs.ktor.client.serialization)

    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.core.ktx)
    implementation(libs.android.material)
    implementation(libs.android.legacy)

    implementation(libs.compose.compiler)
    implementation(libs.compose.ui.tooling)
    implementation(libs.compose.ui.core)
    implementation(libs.compose.foundation)
    implementation(libs.compose.material.core)
    implementation(libs.compose.material.icons.core)
    implementation(libs.compose.material.icons.extended)
    implementation(libs.compose.runtime.livedata)
    implementation(libs.compose.runtime.rxjava2)

    implementation(libs.koin.core)
    implementation(libs.koin.android)
    implementation(libs.coil.compose)
    implementation(libs.coil.svg)

    androidTestImplementation(libs.compose.ui.test)
}

val PUBLISH_GROUP_ID by extra("tech.miam.sdk")
val PUBLISH_ARTIFACT_ID by extra { "kmm-miam-sdk" }

apply("${rootDir}/scripts/publish-module.gradle")
