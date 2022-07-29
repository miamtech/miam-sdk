// swift-tools-version:5.3
import PackageDescription

let package = Package(
    name: "MiamIOSFramework",
    platforms: [
        .iOS(.v14),
    ],
    products: [
        // Products define the executables and libraries a package produces, and make them visible to other packages.
        .library(
            name: "MiamIOSFramework",
            targets: ["MiamIOSFramework", "miamCore"])
    ],
    dependencies: [
        // Dependencies declare other packages that this package depends on.
    ],
    targets: [
        // Targets are the basic building blocks of a package. A target can define a module or a test suite.
        // Targets can depend on other targets in this package, and on products in packages this package depends on.
        .target(
            name: "MiamIOSFramework",
            path: "MiamIOSFramework/MiamIOSFramework",
            resources: [
                .process("miam.xcassets"),
            ]
        ),
        .binaryTarget(
            name: "miamCore",
            path: "frameworks/miamCore.xcframework"
        )
        .testTarget(
            name: "MiamIOSFrameworkTests",
            path: "MiamIOSFramework/MiamIOSFrameworkTests",
            dependencies: ["MiamIOSFramework"]),
    ]
)
