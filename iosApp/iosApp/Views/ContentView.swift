import SwiftUI
import MiamIOSFramework
import shared

struct ContentView: View {
    let applicationBasket: MyBasket = MyBasket.shared
    
    @SwiftUI.State private var recipeCount: Int = 0
    
    var criteria = SuggestionsCriteria(
        shelfIngredientsIds: ["5319173","970417"],
        currentIngredientsIds:nil,
        basketIngredientsIds: nil,
        groupId: nil
    )
    
    var body: some View {
        ZStack{
            Color.white.edgesIgnoringSafeArea(.all)
            NavigationView {
                VStack {
                    MyBasketView(basket: applicationBasket)
                    ScrollView {
                        Button("Reset recipe list", action: { GroceriesListHandler.shared.resetGroceriesList()})
                        Text("Recette dans le panier : \(recipeCount)")
                        VStack {
                            RecipeCardView(recipeId: "9422")
                            RecipeCardView(criteria: criteria)
                        }
                    }
                }.navigationTitle("Miam demo app").navigationBarTitleDisplayMode(.inline)
                .toolbar(content: {
                    ToolbarItemGroup(placement: .navigationBarLeading) {
                        Button(action: addRandomProduct) {
                            Text("Add")
                        }.buttonStyle(.automatic)
                        Button(action: removeRandomProduct) {
                            Text("Remove")
                        }
                    }
                })
            }
        }.onAppear(
            perform: {  GroceriesListHandler.shared.onRecipeCountChange(
                updateRecipeCount: {count in recipeCount = Int(count) }
            )
            }
        )    }
    
    private func addRandomProduct() {
        if let product = MyProductsRepository.sharedInstance.getRandomProduct() {
            applicationBasket.add(addedProduct: product)
        }
    }
    
    private func removeRandomProduct(){
        guard let product = applicationBasket.items.randomElement() else {
            return
        }
        applicationBasket.remove(removedProduct: product)
    }
    
    struct ContentView_Previews: PreviewProvider {
        static var previews: some View {
            ContentView()
        }
    }
}
