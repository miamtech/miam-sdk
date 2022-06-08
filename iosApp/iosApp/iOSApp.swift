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
        let _ = MiamManager.sharedInstance
        return true
    }
}





public class AppBasket {
    public static let sharedInstance = AppBasket()
    let basket = MyBasket(items:[])
    
    private init(){
        
    }
}
