Pod::Spec.new do |spec|
  spec.name         = "miamCore"
  spec.version      = "1.2.0"
  spec.summary      = "Miam SDK for Android/iOS"

  spec.description  = <<-DESC
  Miam Core SDK for iOS.
                   DESC

  spec.homepage     = "https://www.miam.tech"
  spec.license      = "GPLv3"
  # spec.license      = { :type => "MIT", :file => "FILE_LICENSE" }

  spec.author             = { "Vincent Kergonna" => "kergonna@k-interactive.fr" }
  spec.platform     = :ios, "14.0"
  spec.source       = { :git => "https://gitlab.com/miam/kmm-miam-sdk", :tag => "#{spec.version}" }
  spec.vendored_frameworks = "frameworks/shared.xcframework"
end
