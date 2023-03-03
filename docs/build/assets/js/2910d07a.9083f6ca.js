"use strict";(self.webpackChunkmiam_sdk_doc=self.webpackChunkmiam_sdk_doc||[]).push([[582],{3905:(e,t,n)=>{n.d(t,{Zo:()=>u,kt:()=>k});var a=n(7294);function r(e,t,n){return t in e?Object.defineProperty(e,t,{value:n,enumerable:!0,configurable:!0,writable:!0}):e[t]=n,e}function i(e,t){var n=Object.keys(e);if(Object.getOwnPropertySymbols){var a=Object.getOwnPropertySymbols(e);t&&(a=a.filter((function(t){return Object.getOwnPropertyDescriptor(e,t).enumerable}))),n.push.apply(n,a)}return n}function o(e){for(var t=1;t<arguments.length;t++){var n=null!=arguments[t]?arguments[t]:{};t%2?i(Object(n),!0).forEach((function(t){r(e,t,n[t])})):Object.getOwnPropertyDescriptors?Object.defineProperties(e,Object.getOwnPropertyDescriptors(n)):i(Object(n)).forEach((function(t){Object.defineProperty(e,t,Object.getOwnPropertyDescriptor(n,t))}))}return e}function s(e,t){if(null==e)return{};var n,a,r=function(e,t){if(null==e)return{};var n,a,r={},i=Object.keys(e);for(a=0;a<i.length;a++)n=i[a],t.indexOf(n)>=0||(r[n]=e[n]);return r}(e,t);if(Object.getOwnPropertySymbols){var i=Object.getOwnPropertySymbols(e);for(a=0;a<i.length;a++)n=i[a],t.indexOf(n)>=0||Object.prototype.propertyIsEnumerable.call(e,n)&&(r[n]=e[n])}return r}var l=a.createContext({}),p=function(e){var t=a.useContext(l),n=t;return e&&(n="function"==typeof e?e(t):o(o({},t),e)),n},u=function(e){var t=p(e.components);return a.createElement(l.Provider,{value:t},e.children)},d="mdxType",c={inlineCode:"code",wrapper:function(e){var t=e.children;return a.createElement(a.Fragment,{},t)}},m=a.forwardRef((function(e,t){var n=e.components,r=e.mdxType,i=e.originalType,l=e.parentName,u=s(e,["components","mdxType","originalType","parentName"]),d=p(n),m=r,k=d["".concat(l,".").concat(m)]||d[m]||c[m]||i;return n?a.createElement(k,o(o({ref:t},u),{},{components:n})):a.createElement(k,o({ref:t},u))}));function k(e,t){var n=arguments,r=t&&t.mdxType;if("string"==typeof e||r){var i=n.length,o=new Array(i);o[0]=m;var s={};for(var l in t)hasOwnProperty.call(t,l)&&(s[l]=t[l]);s.originalType=e,s[d]="string"==typeof e?e:r,o[1]=s;for(var p=2;p<i;p++)o[p]=n[p];return a.createElement.apply(null,o)}return a.createElement.apply(null,n)}m.displayName="MDXCreateElement"},8482:(e,t,n)=>{n.r(t),n.d(t,{assets:()=>l,contentTitle:()=>o,default:()=>c,frontMatter:()=>i,metadata:()=>s,toc:()=>p});var a=n(7462),r=(n(7294),n(3905));const i={sidebar_position:3,label:"Initialisation"},o="Initialisation",s={unversionedId:"ios/overview/initialisation",id:"ios/overview/initialisation",title:"Initialisation",description:"Miam class",source:"@site/docs/ios/overview/initialisation.md",sourceDirName:"ios/overview",slug:"/ios/overview/initialisation",permalink:"/kmm-miam-sdk/docs/ios/overview/initialisation",draft:!1,tags:[],version:"current",sidebarPosition:3,frontMatter:{sidebar_position:3,label:"Initialisation"},sidebar:"iosSidebar",previous:{title:"demo",permalink:"/kmm-miam-sdk/docs/ios/overview/demo"},next:{title:"Usage",permalink:"/kmm-miam-sdk/docs/category/usage-1"}},l={},p=[{value:"Miam class",id:"miam-class",level:2},{value:"Minimun requirement",id:"minimun-requirement",level:2},{value:"User setup",id:"user-setup",level:2},{value:"Store setup",id:"store-setup",level:2},{value:"Basket synchronization Setup",id:"basket-synchronization-setup",level:2}],u={toc:p},d="wrapper";function c(e){let{components:t,...n}=e;return(0,r.kt)(d,(0,a.Z)({},u,n,{components:t,mdxType:"MDXLayout"}),(0,r.kt)("h1",{id:"initialisation"},"Initialisation"),(0,r.kt)("h2",{id:"miam-class"},"Miam class"),(0,r.kt)("p",null,"We recommend that all the mapping functions that will define the interactions between the SDK and\nthe host app be wrapped in a main ",(0,r.kt)("strong",{parentName:"p"},"Miam")," class.\nThis class will use methods and attributes defined in SDK ",(0,r.kt)("strong",{parentName:"p"},"handler")," classes to manage objects such as the User profile, the Basket, or the selected Store. These haldlers are all singletons."),(0,r.kt)("admonition",{type:"tip"},(0,r.kt)("p",{parentName:"admonition"},"Make sure this main ",(0,r.kt)("strong",{parentName:"p"},"Miam")," class is a singleton and instantiated only once in your runtime. Here is a basic implementation")),(0,r.kt)("pre",null,(0,r.kt)("code",{parentName:"pre",className:"language-swift"},"// file Miam.swift\nimport Foundation\n\npublic class Miam {\n  // Will contain calls to Miam SDK handler classes (User, Basket, Store...)\n  \n  public static let sharedInstance = Miam()\n}\n")),(0,r.kt)("pre",null,(0,r.kt)("code",{parentName:"pre",className:"language-swift"},"//IosApp.swift\n\nimport SwiftUI\n\n@main\nstruct ios_miam_integrationApp: App {\n    @UIApplicationDelegateAdaptor(AppDelegate.self) var appDelegate\n    var body: some Scene {\n        WindowGroup {\n            ContentView()\n        }\n    }\n}\n\nclass AppDelegate: NSObject, UIApplicationDelegate {\n    func application(_ application: UIApplication, didFinishLaunchingWithOptions launchOptions: [UIApplication.LaunchOptionsKey: Any]?) -> Bool {\n        // init miam\n        let _ = Miam.sharedInstance\n        return true\n    }\n}\n\n")),(0,r.kt)("h2",{id:"minimun-requirement"},"Minimun requirement"),(0,r.kt)("admonition",{type:"info"},(0,r.kt)("p",{parentName:"admonition"},"You need to ask Miam team for your supplier id and your origin (unique for all your apps and websites integrations).")),(0,r.kt)("p",null,"To differentiate ",(0,r.kt)("strong",{parentName:"p"},"platforms")," and ",(0,r.kt)("strong",{parentName:"p"},"retailers")," in Miam API Miam sdk require  ",(0,r.kt)("strong",{parentName:"p"},"supplier id")," and your ",(0,r.kt)("strong",{parentName:"p"},"origin"),". Once you got your ids you can implement your ",(0,r.kt)("strong",{parentName:"p"},"Miam")," class as follow :"),(0,r.kt)("pre",null,(0,r.kt)("code",{parentName:"pre",className:"language-swift"},"// file Miam.swift\nimport miamCore\n\nclass Miam {\n  \n  private init() {\n    //  CODE\n      PointOfSaleHandler.shared.setSupplierOrigin(origin : <string>YOUR_SUPPLIER_ORIGIN)\n      PointOfSaleHandler.shared.setSupplier(supplierId: <string>YOUR_SUPPLIER_ID)\n  }\n\n  //  CODE\n}\n\n")),(0,r.kt)("admonition",{type:"tip"},(0,r.kt)("p",{parentName:"admonition"},"Make sure to specify a different origin between your development and production environments")),(0,r.kt)("p",null,"Miam initialization process will only start after the user is ",(0,r.kt)("strong",{parentName:"p"},"logged")," and has ",(0,r.kt)("strong",{parentName:"p"},"selected a valid store"),".\nthen it'll need the ",(0,r.kt)("strong",{parentName:"p"},"basket synchronization")),(0,r.kt)("h2",{id:"user-setup"},"User setup"),(0,r.kt)("p",null,"Here is how to pass the user ID to the SDK, directly within the host app:"),(0,r.kt)("pre",null,(0,r.kt)("code",{parentName:"pre",className:"language-swift"},"// From anywhere\nimport miamCore\n\n// USER_ID_IN_HOST_APP is your user id type String is expected\nUserHandler.shared.updateUserId(userId: USER_ID_IN_HOST_APP)\n")),(0,r.kt)("p",null,"Here is how to inform the SDK whenever the user login state changes. We recommend using Observables or EventListeners to that end."),(0,r.kt)("pre",null,(0,r.kt)("code",{parentName:"pre",className:"language-swift"},"// file Miam.swift\nimport miamCore\n\npublic class Miam {\n  // CODE\n\n  private init() {\n    // CODE\n\n    OBSERVABLE_ON_USER_OBJECT.sink { _  in\n      // USER_ID_IN_HOST_APP is your user id type String is expected\n      UserHandler.shared.updateUserId(userId: USER_ID_IN_HOST_APP)\n    }\n  }\n\n  // CODE\n}\n\n")),(0,r.kt)("admonition",{type:"info"},(0,r.kt)("p",{parentName:"admonition"},"   To get full list of user feature check ",(0,r.kt)("a",{parentName:"p",href:"../advanced/user-configuration"},(0,r.kt)("strong",{parentName:"a"},"User handler")),".")),(0,r.kt)("h2",{id:"store-setup"},"Store setup"),(0,r.kt)("p",null,"Has user you can inform the SDK whenever the user selected store changes :"),(0,r.kt)("pre",null,(0,r.kt)("code",{parentName:"pre",className:"language-swift"},"// From anywhere\nimport miamCore\n\n// STORE_ID_IN_HOST_APP is your user id type String is expected\nPointOfSaleHandler.updateStoreId(storeId: <string>STORE_ID_IN_HOST_APP)\n\n")),(0,r.kt)("h2",{id:"basket-synchronization-setup"},"Basket synchronization Setup"),(0,r.kt)("p",null,"Last but not least, the SDK embeds a complex synchronization system that will ensure Miam always\nkeeps the knowledge of what products have been pushed to or removed from the in-app basket. This\nmechanism is ",(0,r.kt)("strong",{parentName:"p"},"mandatory")," to ensure products added via Miam recipes are kept consistent with the\ninteractions users will have with the basket outside of Miam components."),(0,r.kt)("admonition",{type:"tip"},(0,r.kt)("p",{parentName:"admonition"}," If at some point, you feel like products magically disappear from Miam recipes, or are not removed from the app basket while they should be, this is probably related to this part.")),(0,r.kt)("p",null,"By convenience, we recommend to define a mapping function that transforms the host app ",(0,r.kt)("strong",{parentName:"p"},"YourProduct"),"\nobjects to ",(0,r.kt)("strong",{parentName:"p"},"Miam products")," objects (named ",(0,r.kt)("inlineCode",{parentName:"p"},"RetailerProduct")," in the SDK). The opposite function can\nalso be defined:"),(0,r.kt)("pre",null,(0,r.kt)("code",{parentName:"pre",className:"language-swift"},'import miamCore\n\n// Defined in the kotlin SDK, but can be used in swift\n// data class RetailerProduct(val retailerId :String, val quantity: Int, val name: String?)\n\n private func yourProductsToRetailerProducts(products: Array<YourProduct>) -> Array<RetailerProduct> {\n      return products.map {\n       return RetailerProduct(\n            retailerId: $0.id,\n            quantity: Int32($0.quantity),\n            name: $0.name,\n            productIdentifier: nil\n        )\n      }\n    }\n\nprivate func retailerProductsToYourProducts(products: Array<RetailerProduct>) -> Array<YourProduct> {\n  return RetailerProduct.map { \n    return YourProduct(\n       id: $0.retailerId,\n       name: "\\($0.name)",\n       quantity: Int($0.quantity)\n    )\n  }\n}     \n\n')),(0,r.kt)("p",null,"For the next step ",(0,r.kt)("strong",{parentName:"p"},"Miam")," needs to listen to any change applied to your basket in your app. To that end, you have to pass to ",(0,r.kt)("strong",{parentName:"p"},"Miam")," a closure to ",(0,r.kt)("inlineCode",{parentName:"p"},"BasketHandler")," with the flowing signature:\n",(0,r.kt)("inlineCode",{parentName:"p"},"(callback : (products: List<RetailerProduct>) -> Unit) -> Unit")),(0,r.kt)("p",null,"It can be done this way :"),(0,r.kt)("pre",null,(0,r.kt)("code",{parentName:"pre",className:"language-swift"},"class Miam {\n\n   private let basketHandler: BasketHandler \n\n  init {\n    \n    basketHandler = BasketHandlerInstance.shared.instance\n     // give miam a function walled when everything is ready to listen to your basket\n    basketHandler.listenToRetailerBasket(func: initBasketListener) \n    // push a first basket to Miam so we can sync your current basket we Miam ones\n    // then Miam will call initBasketListener function to listen to any change\n    val firstRetailerbasket = yourProductsToRetailerProducts(basket.productsList)\n    basketHandler.pushProductsToMiamBasket(firstRetailerbasket)\n\n    // CODE\n  }\n\n  private func initBasketListener() {\n    OBSERVABLE_ON_BASKET_OBJECT.sink  { receiveValue in\n            // callback will be triggered on every basket change\n             basketHandler.pushProductsToMiamBasket(\n               yourProductsToRetailerProducts(receiveValue)\n               )\n       }\n  }\n\n  // CODE\n}\n\n")),(0,r.kt)("p",null,"Almost there ! now the other way around : everytime Miam's basket changes (every time a recipe is added or removed\nfor example), the added or removed subsequent products have to be pushed to the in-app basket.\nAnother function has to be defined on BasketHandler, with the\nsignature: ",(0,r.kt)("inlineCode",{parentName:"p"},"(products: List<RetailerProduct>) -> Unit"),"."),(0,r.kt)("pre",null,(0,r.kt)("code",{parentName:"pre",className:"language-swift"},'// file Miam.swift\nimport miamCore\n\nclass Miam {\n   // CODE\n  private let basketHandler: BasketHandler\n\n  private init() {\n    basketHandler = BasketHandlerInstance.shared.instance\n    basketHandler.pushProductsToRetailerBasket(func: pushProductsToYourBasket)\n    // CODE\n  }\n\n  private func pushProductsToYourBasket (products: [RetailerProduct]) {\n    // Convert "Miam products" to your own product objects\n    retailerProductsToYourProducts(products).foreach( { \n      if ($0.quantity <= 0) {\n        // Removes product from host app basket\n        yourDeleteFunction($0)\n      } else if (yourTestFunctionAlreadyInBasket($0.id)){\n        // Updates quantity of product in host app basket\n        yourUpdateFunction($0)\n      } else {\n        // Add product to host app basket\n        yourAddFunction($0)\n      }\n    })\n  }\n\n  // CODE\n}   \n\n')),(0,r.kt)("p",null,"Finally, Miam basket will be confirmed and cleared once the payment has been validated by the user.\nWe have to trigger this event on the BasketHandler as well:"),(0,r.kt)("pre",null,(0,r.kt)("code",{parentName:"pre",className:"language-swift"},"class Miam {\n\n    private let basketHandler: BasketHandler = BasketHandler()\n\n    private init()\n    {\n        // CODE\n        basketHandler.paymentTotal = getTotalPayment\n    }\n\n    private func getTotalPayment() -> KotlinDouble\n    {\n        return ORDER_PAID_AMOUNT_IN_APP()\n    }\n\n    // CODE\n}\n\n// Confirm basket when payment confirmed in app:\nMiam.getInstance().basketHandler.handlePayment()\n\n")),(0,r.kt)("p",null,"Congratulation ",(0,r.kt)("strong",{parentName:"p"},"Miam")," is good to go \ud83e\udd73"))}c.isMDXComponent=!0}}]);