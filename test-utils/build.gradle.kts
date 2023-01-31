@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.android.library)
}

kotlin {
    android()

    ios()
    iosSimulatorArm64()

    sourceSets["commonMain"].dependencies {
        api(project(":core"))
        api(libs.moko.resources.core)
        api(libs.kotlinx.coroutines.test)
        api(libs.test.turbine)
        api(libs.kotlin.test.common)
        api(libs.kotlin.test.annotation.common)
        // Avoid SLF4J to search for a provider in tests
        // https://www.slf4j.org/codes.html#noProviders
        api(libs.test.slf4j.nop)
    }

    sourceSets["androidMain"].dependencies {
        api(libs.test.android.core)
        api(libs.test.android.robolectric)
        api(libs.test.kotlin.junit)
        api(libs.test.junit)
        api(libs.test.android.junit)
    }

    sourceSets["iosSimulatorArm64Main"].dependsOn(
        sourceSets["iosMain"]
    )

    sourceSets.configureEach {
        languageSettings {
            // optIn("kotlin.RequiresOptIn")
        }
    }

    targets.configureEach {
        compilations.configureEach {
            kotlinOptions {
                allWarningsAsErrors = true
            }
        }
    }

    // Workaround on HMPP
    // https://youtrack.jetbrains.com/issue/KT-44459/KMM-cocoapods-integration-Execution-failed-for-task-cinteropReachabilitySwiftIosArm64#focus=Comments-27-4645829.0-0
    metadata {
        compilations.matching { it.name == "iosMain" }.all {
            compileKotlinTaskProvider.configure { enabled = false }
        }
    }
}

android {
    val compileSdkVersion: String by project
    val targetSdkVersion: String by project
    val minSdkVersion: String by project
    compileSdk = Integer.parseInt(compileSdkVersion)
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")

    defaultConfig {
        minSdk = Integer.parseInt(minSdkVersion)
        targetSdk = Integer.parseInt(targetSdkVersion)
    }

    testOptions {
        unitTests {
            isIncludeAndroidResources = true
        }
    }
}