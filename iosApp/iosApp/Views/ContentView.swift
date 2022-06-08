import SwiftUI
import MiamIOSFramework
import shared

struct ContentView: View {
    @ObservedObject var basket: MyBasket = MyBasket(items: [])

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
                ToolbarItem(placement: .navigationBarTrailing) {

                }
            })
        }
    }

    private func addRandomProduct() {
        if let product = MyProductsRepository.sharedInstance.getRandomProduct() {
            basket.add(addedProduct: product)
        }
    }
    
    private func removeRandomProduct(){
        guard let product = basket.items.randomElement() else {
            return
        }
        basket.remove(removedProduct: product)
    }
    
    struct ContentView_Previews: PreviewProvider {
        static var previews: some View {
            ContentView()
        }
    }
}
