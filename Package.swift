// swift-tools-version:5.3
import PackageDescription

let package = Package(
    name: "MiamIOSFramework",
    platforms: [
        .iOS(.v14),
    ],
    products: [
        .library(
            name: "MiamIOSFramework",
            targets: ["MiamIOSFramework", "miamCore"])
    ],
    targets: [
        // Targets are the basic building blocks of a package. A target can define a module or a test suite.
        // Targets can depend on other targets in this package, and on products in packages this package depends on.
        .target(
            name: "MiamIOSFramework",
            dependencies: ["miamCore"],
            path: "MiamIOSFramework/MiamIOSFramework",
            resources: [
                .process("miam.xcassets"),
            ]
        ),
        .binaryTarget(
            name: "miamCore",
            path: "frameworks/miamCore.xcframework"
        ),
        .testTarget(
            name: "MiamIOSFrameworkTests",
            dependencies: ["MiamIOSFramework"],
            path: "MiamIOSFramework/MiamIOSFrameworkTests"
        )
    ]
)
