#!/bin/bash

./gradlew clean && ./gradlew assembleReleaseXCFramework

cd MiamIOSFramework
rm -rf build
xcodebuild archive -scheme MiamIOSFramework -configuration Release -destination 'generic/platform=iOS' -archivePath './build/MiamIOSFramework.framework-iphoneos.xcarchive' SKIP_INSTALL=NO BUILD_LIBRARIES_FOR_DISTRIBUTION=YES
xcodebuild archive -scheme MiamIOSFramework -configuration Release -destination 'generic/platform=iOS Simulator' -archivePath './build/MiamIOSFramework.framework-iphonesimulator.xcarchive' SKIP_INSTALL=NO BUILD_LIBRARIES_FOR_DISTRIBUTION=YES
xcodebuild -create-xcframework -framework './build/MiamIOSFramework.framework-iphoneos.xcarchive/Products/Library/Frameworks/MiamIOSFramework.framework' -framework './build/MiamIOSFramework.framework-iphonesimulator.xcarchive/Products/Library/Frameworks/MiamIOSFramework.framework' -output './build/MiamIOSFramework.xcframework'
