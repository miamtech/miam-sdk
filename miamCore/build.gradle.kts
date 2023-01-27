import org.jetbrains.kotlin.gradle.plugin.mpp.apple.XCFramework

@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.serialization)
}

kotlin {
    android()
    ios()

    val xcf = XCFramework()
    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            baseName = "miamCore"
            xcf.add(this)
        }
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(libs.kotlinx.coroutines.core.mt)
                implementation(libs.ktor.client.serialization)
                implementation(libs.ktor.client.logging)
                implementation(libs.koin.core)
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(libs.kotlin.test.common)
                implementation(libs.kotlin.test.annotation.common)
            }
        }

        val androidMain by getting {
            dependencies {
                implementation(libs.ktor.client.android)
            }
        }
        val androidTest by getting {
            dependencies {
                implementation(libs.kotlin.test.junit)
                implementation(libs.test.junit)
            }
        }

        val iosSimulatorArm64Main by getting
        val iosMain by getting {
            dependencies {
                implementation(libs.ktor.client.darwin)
            }
            iosSimulatorArm64Main.dependsOn(this)
        }

        val iosSimulatorArm64Test by getting
        val iosTest by getting {
            iosSimulatorArm64Test.dependsOn(this)
        }

        explicitApi()

        sourceSets.configureEach {
            languageSettings {
                optIn("kotlinx.coroutines.FlowPreview")
                optIn("kotlinx.serialization.ExperimentalSerializationApi")
            }
        }

        targets.configureEach {
            compilations.configureEach {
                kotlinOptions {
                    allWarningsAsErrors = true
                }
            }
        }
    }
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

    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
}
