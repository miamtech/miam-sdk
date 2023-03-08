---
sidebar_position: 1
label: "Installation"
---

# Installation

You can use Miam with Maven. Just add the following tags to your build dependencies

## From Maven central

``` xml
<dependency>
  <groupId>tech.miam.sdk</groupId>
  <artifactId>kmm-miam-sdk</artifactId>
  <version>X.X.X</version>
  <type>aar</type>
</dependency>
```

Import with Gradle Groovy DSL

``` gradle
implementation 'tech.miam.sdk:kmm-miam-sdk:X.X.X'
```

With Gradle Kotlin DSL

``` kotlin
implementation("tech.miam.sdk:kmm-miam-sdk:X.X.X")
```

All information and version
available <a target="\_blank" href='https://search.maven.org/artifact/tech.miam.sdk/kmm-miam-sdk'> here </a>

## Build and import an AAR

If you want to use directly on AAR file, you will need to clone this repository and build the
archive in production mode.

The archive will be generated as `miam-sdk-release.aar` in `/androidSDK/build/outputs`.

Import it into your project, in the `libs` folder.

Finally, import it to your Gradle configuration:

```kotlin
implementation(name:'miam-SDK-release', ext:'aar')
```

:::danger
 Miam SDK is using  <a target="\_blank" href='https://developer.android.com/jetpack/compose?gclsrc=aw.ds&gclid=CjwKCAjwrfCRBhAXEiwAnkmKmWkwGezGLmmfauda5_ACVVNtTVPUw576netuScD2mLnGacjr2cB30RoCC24QAvD_BwE&hl=fr'> Jetpack Compose </a>  under the hood, you must use the same version as miam supported.
 In addition Jetpack Compose is not mandatory but it's the recommanded way to inject Miam's components.

 Current supported version is `1.3.2`
:::