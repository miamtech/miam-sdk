Pod::Spec.new do |spec|

  spec.name         = "MiamIOSFramework"
  spec.version      = "2.2.6"
  spec.summary      = "Miam SDK for iOS"
  spec.description  = <<-DESC
  Miam SDK for iOS.
                   DESC

  spec.homepage     = "https://www.miam.tech"
  spec.license      = "GPLv3"
  spec.license      = { :type => "GPLv3", :file => "LICENSE" }
  spec.author             = { "Vincent Kergonna" => "it@miam.tech" }
  spec.platform     = :ios, "11.0"
  spec.source       = { :git => "https://gitlab.com/miam/kmm-miam-sdk", :tag => "#{spec.version}" }
  spec.source_files  = "MiamIOSFramework/MiamIOSFramework/**/*.{h,c,cpp,swift}", "changelog.md"
  spec.exclude_files = "Classes/Exclude"
  spec.resource  = "MiamIOSFramework/miam.xcassets"
  spec.user_target_xcconfig = { 'EXCLUDED_ARCHS[sdk=iphonesimulator*]' => 'arm64' }
  spec.pod_target_xcconfig = { 'EXCLUDED_ARCHS[sdk=iphonesimulator*]' => 'arm64' }
  spec.vendored_frameworks = "frameworks/miamCore.xcframework"

end
