Pod::Spec.new do |spec|

  spec.name         = "MiamIOSFramework"
  spec.version      = "2.0.0"
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
  spec.source_files  = "MiamIOSFramework/MiamIOSFramework/**/*.{h,c,cpp,swift}",
  spec.exclude_files = "Classes/Exclude"
  spec.resource  = "MiamIOSFramework/miam.xcassets"
  spec.dependency "miamCore", "~> 2.0"

end
