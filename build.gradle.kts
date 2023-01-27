@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    alias(libs.plugins.kotlin.multiplatform) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.kotlin.serialization) apply false
    alias(libs.plugins.moko.resources) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.nexus.publish)
}

buildscript {
    repositories {
        mavenCentral()
    }
    dependencies {
        classpath("com.github.kezong:fat-aar:1.3.8")
    }
}

allprojects {
    repositories {
        google()
        mavenCentral()
    }
}

/**
 * Maven central deploy script
 * */
apply("${rootDir}/scripts/publish-root.gradle")