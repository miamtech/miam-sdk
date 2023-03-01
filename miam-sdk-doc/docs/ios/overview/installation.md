---
sidebar_position: 1
label: "Installation"
---

# Installation

You can use Miam with Maven. Just add the following tags to your build dependencies

## Cocoapods

To integrate MiamIOSFramework into your Xcode project using CocoaPods, specify it in your Podfile:

```
pod 'MiamIOSFramework'
```

## Swift package manager installation

To add MiamIOSFramework as a dependency directly from Xcode.

![Add Swift Package step 1](./img/addSwiftPackage.png)
![Add Swift Package step 2](./img/addMiamSwiftPackage.png)

## Manual Installation


You will need to clone this repository and build the archive in production mode, you need for it to install AndroidStudio and get gradle dependencies
then run :

```
run ./gradlew assembleXCFramework
```

Then go in your project click on your app name with two fingers, add select add file to
![Add Framwork step 1](./img/addFrameworkStep1.png)

Navigate to miam kmm repo and select `MiamIOSFramework.xcodeproj`

![Add Framwork step 2](./img/addFrameworkStep2.png)

You can now select your project and select Build Phases tab open "Link Binaries With Libraries"
expander. then click on + button and select your framwork

![Add Framwork step 3](./img/addFrameworkStep3.png)

