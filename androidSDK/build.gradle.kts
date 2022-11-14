plugins {
    kotlin("android")
    id("com.android.library")
    id("kotlin-android")
    id("com.kezong.fat-aar")
}

android {
    compileSdk = 33
    defaultConfig {
        minSdk = 21
        targetSdk = 33
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
        kotlinCompilerExtensionVersion = "1.1.1"
    }

    kotlinOptions {
        jvmTarget = "1.8"
        freeCompilerArgs += "-Xjvm-default=all"

    }
}

dependencies {

    implementation("androidx.activity:activity-compose:1.4.0")
    embed(project(":miamCore"))

    implementation("androidx.appcompat:appcompat:1.3.0-alpha02")

    // dependency injection
    implementation("io.insert-koin:koin-android:3.2.2")
    implementation("io.insert-koin:koin-core:3.2.2")
    // Http Client
    implementation("io.ktor:ktor-client-android:1.6.7")
    implementation("io.ktor:ktor-client-serialization:1.6.7")
    implementation("io.ktor:ktor-client-core:1.6.7")

    implementation("com.google.android.material:material:1.6.0")

    implementation("androidx.compose.ui:ui-tooling:1.3.1")

    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.0.1")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.0.1")


    implementation("androidx.compose.compiler:compiler:1.3.2")
    implementation("androidx.compose.ui:ui:1.3.1")

    // Foundation (Border, Background, Box, Image, Scroll, shapes, animations, etc.)
    implementation("androidx.compose.foundation:foundation:1.3.1")
    // Material Design
    implementation("androidx.compose.material:material:1.3.1")
    // Material design icons
    implementation("androidx.compose.material:material-icons-core:1.3.1")
    implementation("androidx.compose.material:material-icons-extended:1.3.1")
    // Integration with observables
    implementation("androidx.compose.runtime:runtime-livedata:1.3.1")
    implementation("androidx.compose.runtime:runtime-rxjava2:1.3.1")

    implementation("io.coil-kt:coil-compose:1.3.1")
    implementation("io.coil-kt:coil-svg:1.3.1")

    implementation("androidx.legacy:legacy-support-v4:1.0.0")

    // UI Tests
    androidTestImplementation("androidx.compose.ui:ui-test-junit4:1.0.5")
    implementation("androidx.core:core-ktx:1.8.0")


}

val PUBLISH_GROUP_ID by extra("tech.miam.sdk")
val PUBLISH_ARTIFACT_ID by extra { "kmm-miam-sdk" }

apply("${rootDir}/scripts/publish-module.gradle")
