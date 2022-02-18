import SwiftUI
import shared

@main
struct ios_miam_integrationApp: App {
    @UIApplicationDelegateAdaptor(AppDelegate.self) var appDelegate
    var body: some Scene {
        WindowGroup {
            ContentView()
        }
    }
}

class AppDelegate: NSObject, UIApplicationDelegate {
    func application(_ application: UIApplication, didFinishLaunchingWithOptions launchOptions: [UIApplication.LaunchOptionsKey: Any]?) -> Bool {
        KoinKt.doInitKoin()
        PointOfSaleHandler.shared.updateStoreId(storeId: "35290")
        PointOfSaleHandler.shared.setSupplier(supplierId: 7)
        UserHandler.shared.updateUserId(userId: "ed0a471a4bdc755664db84068119144b3a1772d8a6911057a0d6be6a3e075120")
        return true
    }
}
