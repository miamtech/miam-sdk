pluginManagement {
    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
    }
}

dependencyResolutionManagement {
    repositories {
        mavenCentral()
        google()
    }
    versionCatalogs {
        create("libs")
    }
}

rootProject.name = "kmmMiamSdk"
include(":androidSDK")
include(":miamCore")
include(":androidtestapp")

include(":core")
//include(":miam-sdk")

include(":test-utils")
