
plugins {
    id("com.android.library")
    kotlin("android")
    id("kotlin-android")
    id("com.kezong.fat-aar")
}

android {
    compileSdk = 30
    defaultConfig {
        minSdk = 21
        targetSdk = 30
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
        kotlinCompilerExtensionVersion = "1.1.0-rc02"
    }

    kotlinOptions {
        jvmTarget = "1.8"
        freeCompilerArgs += "-Xopt-in=kotlin.RequiresOptIn"
    }
}

dependencies {

    implementation("androidx.activity:activity-compose:1.4.0")
    embed(project(":shared"))

    // dependency injection
    embed("io.insert-koin:koin-android:3.1.2")
    embed("io.insert-koin:koin-core:3.1.2")

    // Http Client
    implementation( "io.ktor:ktor-client-android:1.6.7")
    implementation("io.ktor:ktor-client-serialization:1.6.7")
    implementation("io.ktor:ktor-client-core:1.6.7")


    implementation("com.google.android.material:material:1.4.0")

    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.0.1")
    implementation ( "org.jetbrains.kotlinx:kotlinx-coroutines-android:1.0.1")


    implementation("androidx.compose.compiler:compiler:1.0.5")
    implementation("androidx.compose.ui:ui:1.0.5")
    // Tooling support (Previews, etc.)
    implementation("androidx.compose.ui:ui-tooling:1.0.5")
    // Foundation (Border, Background, Box, Image, Scroll, shapes, animations, etc.)
    implementation("androidx.compose.foundation:foundation:1.0.5")
    // Material Design
    implementation("androidx.compose.material:material:1.0.5")
    // Material design icons
    implementation("androidx.compose.material:material-icons-core:1.0.5")
    implementation("androidx.compose.material:material-icons-extended:1.0.5")
    //implementation("androidx.activity:activity-compose:1.4.0")
    // Integration with observables
    implementation("androidx.compose.runtime:runtime-livedata:1.0.5")
    implementation("androidx.compose.runtime:runtime-rxjava2:1.0.5")
    implementation ("com.google.android.material:compose-theme-adapter:1.0.5")
    implementation("io.coil-kt:coil-compose:1.3.1")
    implementation ("io.coil-kt:coil-svg:1.3.1")
    //implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.5.2")

    implementation("androidx.legacy:legacy-support-v4:1.0.0")
    implementation("androidx.databinding:databinding-runtime:4.1.3")
    implementation("org.slf4j:slf4j-nop:1.7.25")

    // UI Tests
    androidTestImplementation("androidx.compose.ui:ui-test-junit4:1.0.5")




}