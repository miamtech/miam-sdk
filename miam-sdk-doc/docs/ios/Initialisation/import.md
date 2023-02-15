---
sidebar_position: 1
---

# Import

Miam can be imported in several ways

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

``` java
implementation 'tech.miam.sdk:kmm-miam-sdk:X.X.X'
```

With Gradle Kotlin DSL

``` kotlin
implementation("tech.miam.sdk:kmm-miam-sdk:X.X.X")
```

All information and version
available [here]('https://search.maven.org/artifact/tech.miam.sdk/kmm-miam-sdk')

#### Build and import an AAR

If you want to use directly on AAR file, you will need to clone this repository and build the
archive in production mode.

The archive will be generated as `miam-sdk-release.aar` in `/androidSDK/build/outputs`.

Import it into your project, in the `libs` folder.

Finally, import it to your Gradle configuration:

```kotlin
// In the app build.gradle file
implementation(name:'miam-SDK-release', ext:'aar')
```
