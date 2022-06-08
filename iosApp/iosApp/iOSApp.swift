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


class Basket {
    @Published var items = [MyProduct]()

    init(items: Array<MyProduct>) {
        self.items = items
    }

    func add(addedProduct: MyProduct) -> Void {
        
        let results = items.firstIndex(where: { $0.id.isEqual(addedProduct.id) }  )
        
        if(results != nil ){
            // TODO update quantity
        } else {
            items.append(addedProduct)
            self.items = items
        }
    }
    
    func remove(removedProduct : MyProduct){
        let results = items.firstIndex(where: { $0.id.isEqual(removedProduct.id) } )
        if(results == nil ){ return }
        items.remove(at: results!)
    }
}


public class AppBasket {
    public static let sharedInstance = AppBasket()
    let basket = Basket(items:[])
    
    private init(){
        
    }
}
