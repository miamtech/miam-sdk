import org.gradle.api.tasks.testing.logging.TestExceptionFormat
import org.gradle.api.tasks.testing.logging.TestLogEvent
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.android.library)
    alias(libs.plugins.moko.resources)
}

multiplatformResources {
    multiplatformResourcesPackage = "com.miam.core.resources"
    multiplatformResourcesClassName = "MiamCoreResources"
    multiplatformResourcesVisibility = dev.icerock.gradle.MRVisibility.Internal
}

kotlin {
    android { publishLibraryVariants("release", "debug") }

    ios()
    iosSimulatorArm64()

    sourceSets["commonMain"].dependencies {
        implementation(libs.moko.resources.core)
    }

    sourceSets["androidMain"].dependencies {
        implementation(libs.moko.resources.android)
    }

    sourceSets["iosSimulatorArm64Main"].dependsOn(
        sourceSets["iosMain"]
    )

    sourceSets["commonTest"].dependencies {
        implementation(project(":test-utils"))
        implementation(libs.kotlin.test.common)
        implementation(libs.kotlin.test.annotation.common)
    }

    sourceSets["androidTest"].dependencies {
        implementation(libs.test.kotlin.junit)
        implementation(libs.test.junit)
        implementation(libs.test.android.robolectric)
    }

    sourceSets["iosSimulatorArm64Test"].dependsOn(
        sourceSets["iosTest"]
    )

    explicitApi()

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

// Workaround for https://youtrack.jetbrains.com/issue/KT-48436
project.extensions.findByType<KotlinMultiplatformExtension>()?.let { ext ->
    ext.sourceSets.removeAll { sourceSet ->
        setOf(
            "androidAndroidTestRelease",
            "androidTestFixtures",
            "androidTestFixturesDebug",
            "androidTestFixturesRelease",
        ).contains(sourceSet.name)
    }
}

// Better stacktrace on tests
tasks.withType<AbstractTestTask> {
    testLogging {
        exceptionFormat = TestExceptionFormat.FULL
        events = setOf(
            TestLogEvent.SKIPPED,
            TestLogEvent.PASSED,
            TestLogEvent.FAILED
        )
        showStandardStreams = true
    }
}