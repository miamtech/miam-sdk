import org.jetbrains.kotlin.gradle.plugin.mpp.apple.XCFramework

@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.moko.resources)
}

multiplatformResources {
    multiplatformResourcesPackage = "com.miam.sdk.resources"
    multiplatformResourcesClassName = "MiamSdkResources"
    multiplatformResourcesVisibility = dev.icerock.gradle.MRVisibility.Internal
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
            export(project(":core"))
            xcf.add(this)
        }
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                api(project(":core"))
                implementation(libs.kotlinx.coroutines.core.mt)
                implementation(libs.ktor.client.serialization)
                implementation(libs.ktor.client.logging)
                implementation(libs.koin.core)
                implementation(libs.moko.resources.core)
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

        sourceSets["iosMain"].dependencies {
            api(libs.ktor.client.darwin)
        }

        sourceSets["iosX64Main"].dependsOn(sourceSets["iosMain"])
        sourceSets["iosArm64Main"].dependsOn(sourceSets["iosMain"])
        sourceSets["iosSimulatorArm64Main"].dependsOn(sourceSets["iosMain"])
        sourceSets["iosSimulatorArm64Test"].dependsOn(sourceSets["iosTest"])

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
