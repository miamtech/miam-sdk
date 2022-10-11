import SwiftUI
import MiamIOSFramework
import miamCore

@available(iOS 15, *)
struct MealIdeas: View {
    @SwiftUI.State private var recipeCount: Int = 0
    @SwiftUI.State private var showTag: Bool = false
    @SwiftUI.State private var showRecipeCount: Bool = false
    
    var criteria = SuggestionsCriteria(
        shelfIngredientsIds: ["5319173", "970417", "1088020"],
        currentIngredientsIds:nil,
        basketIngredientsIds: nil,
        groupId: nil
    )
    
    var body: some View {
        ZStack {
            Color.white.edgesIgnoringSafeArea(.all)
            NavigationView {
                VStack {
                    ScrollView {             
                        Button("toggle tag", action: { showTag = !showTag})
                        CategoriesMenu(categorie: MiamManager.sharedInstance.categories)                                  
                        VStack(spacing: 24.0) {
                            if(showTag){
                                BasketTag(itemId: "1088020")
                            }

                            RecipeCardView(recipeId: "9422")

                            ForEach(0..<5) { _ in
                                if let product = MyProductsRepository().getRandomProduct() {
                                    RecipeCardView(criteria: SuggestionsCriteria(
                                        shelfIngredientsIds: [product.id],
                                        currentIngredientsIds:nil,
                                        basketIngredientsIds: nil,
                                        groupId: nil
                                    ))
                                }
                            }
                        }
                    }.padding([.leading, .trailing, .bottom], 16.0)
                }.navigationTitle("Idées repas").navigationBarTitleDisplayMode(.inline)
            }
        }
    }

    @available(iOS 15, *)
    struct ContentView_Previews: PreviewProvider {
        static var previews: some View {
            MealIdeas()
        }
    }
}
