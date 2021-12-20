plugins {
    id("com.android.application")
    kotlin("android")
}

android {
    compileSdk = 31
    defaultConfig {
        applicationId = "com.example.kmm_miam_sdk.android"
        minSdk = 21
        targetSdk = 31
        versionCode = 1
        versionName = "1.0"
    }
    buildFeatures {
        compose = true
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.1.0-beta04"
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {

    implementation(project(":shared"))
    implementation("io.ktor:ktor-client-core:1.6.7")
    implementation("com.google.android.material:material:1.4.0")
    implementation("androidx.appcompat:appcompat:1.3.1")
    implementation("androidx.constraintlayout:constraintlayout:2.1.0")
    implementation ( "org.jetbrains.kotlinx:kotlinx-coroutines-android:1.0.1")
    implementation("androidx.compose.compiler:compiler:1.1.0-beta04")
    implementation("androidx.compose.ui:ui:1.1.0-beta04")
    // Tooling support (Previews, etc.)
    implementation("androidx.compose.ui:ui-tooling:1.1.0-beta04")
    // Foundation (Border, Background, Box, Image, Scroll, shapes, animations, etc.)
    implementation("androidx.compose.foundation:foundation:1.1.0-beta04")
    // Material Design
    implementation("androidx.compose.material:material:1.1.0-beta04")
    // Material design icons
    implementation("androidx.compose.material:material-icons-core:1.1.0-beta04")
    implementation("androidx.compose.material:material-icons-extended:1.1.0-beta04")
    // Integration with observables
    implementation("androidx.compose.runtime:runtime-livedata:1.1.0-beta04")
    implementation("androidx.compose.runtime:runtime-rxjava2:1.1.0-beta04")
    implementation ("com.google.android.material:compose-theme-adapter:1.0.5")
    implementation("io.coil-kt:coil-compose:1.3.1")
    implementation ("io.coil-kt:coil-svg:1.3.1")

    // UI Tests
    androidTestImplementation("androidx.compose.ui:ui-test-junit4:1.1.0-beta04")



}