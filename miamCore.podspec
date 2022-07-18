Pod::Spec.new do |spec|
  spec.name         = "miamCore"
  spec.version      = "2.0.0"
  spec.summary      = "Miam SDK for Android/iOS"

  spec.description  = <<-DESC
  Miam Core SDK for iOS.
                   DESC

  spec.homepage     = "https://www.miam.tech"
  spec.license      = "GPLv3"
  spec.license      = { :type => "GPLv3", :file => "LICENSE" }

  spec.author             = { "Vincent Kergonna" => "it@miam.tech" }
  spec.platform     = :ios, "11.0"
  spec.source       = { :git => "https://gitlab.com/miam/kmm-miam-sdk", :tag => "#{spec.version}" }
  spec.vendored_frameworks = "frameworks/miamCore.xcframework"
end
