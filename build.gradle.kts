

plugins {
    id("io.github.gradle-nexus.publish-plugin") version "1.1.0"
    `kotlin-dsl`
}

buildscript {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
    dependencies {
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.6.10")
        classpath("com.android.tools.build:gradle:7.1.3")
        classpath ("com.github.kezong:fat-aar:1.3.6")
    }
}

allprojects {
    repositories {
        google()
        mavenCentral()
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}

extra.apply{
    set("PUBLISH_GROUP_ID" , "tech.miam.sdk")
    set("PUBLISH_VERSION" ,"2.1.0")
    set("PUBLISH_ARTIFACT_ID", "miam-android-sdk")
}

/**
 * Maven central deploy script
 * */
apply("${rootDir}/scripts/publish-root.gradle")