import SwiftUI
import MiamIOSFramework
import shared

struct ContentView: View {
    let applicationBasket: MyBasket = MyBasket.shared

    var criteria = SuggestionsCriteria(
        shelfIngredientsIds: ["5319173","970417"],
        currentIngredientsIds:nil,
        basketIngredientsIds: nil,
        groupId: nil
    )

    var body: some View {
        NavigationView {
            VStack {
                MyBasketView(basket: basket)
                ScrollView {
                    VStack {
                        RecipeCardView(recipeId: "1")
                        RecipeCardView(criteria: criteria)
                    }
                }
            }
            .navigationTitle("Miam demo app").navigationBarTitleDisplayMode(.inline)
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
    }

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
