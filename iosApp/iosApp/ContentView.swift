import SwiftUI
import MiamIOSFramework
import shared


class ViewModel : ObservableObject {
    
    init(){
    }

}

struct ContentView: View {
    
    private  let productSample = [
          TestProduct(id:"12726",name: "Farine de blé T45 FRANCINE, 1k",quantity:  1 , price: 0.88, identifier: "id_12726"),
          TestProduct(id:"484202",name:"Lait UHT entier U, 6x1L",quantity:1, price:5.46, identifier: "id_484202"),
          TestProduct(id:"809586",name:"Mascarpone GALBANI - 250g",quantity:1, price:2.14, identifier: "id_809586"),
          TestProduct(id:"970417",name:"Beurre doux U, 125",quantity:1, price:2.12, identifier: "id_970417"),
          TestProduct(id:"1298293",name:"Sucre en morceaux prédécoupé n°4 DADDY, 1kg",quantity:1, price:1.35, identifier: "id_1298293"),
          TestProduct(id:"1922350",name:"Oeufs Plein air ELEVEURS ENGAGES L'OEUF DE NOS VILLAGES - Boîte de 12",quantity:1, price:3.23, identifier: "id_1922350"),
          TestProduct(id:"1941111",name:"Chocolat noir bio 75% Pérou ALTER ECO - Tablette 100g",quantity:1, price:1.95, identifier: "id_1941111"),
          TestProduct(id:"2021117",name:"Mandarine Nadorcott à feuilles, calibre 3, catégorie 1, Espagne",quantity:1, price:1.79, identifier: "id_2021117"),
          TestProduct(id:"2276426",name:"Sucre vanillé ALSA, 12 sachets, 90g",quantity:1, price:1.91, identifier: "id_2276426"),
          TestProduct(id:"2540700",name:"Levure chimique AlSA, sachet de 8 soit 88g",quantity:1, price:0.62, identifier: "id_2540700"),
          TestProduct(id:"3895532",name:"Cassonade fine DADDY, 600g",quantity:1, price:1.95, identifier: "id_3895532"),
          TestProduct(id:"4671939",name:"Pain d'épices au miel BROSSARD, 350g",quantity:1, price:1.96, identifier: "id_4671939"),
          TestProduct(id:"5068663",name:"Huile de tournesol U, 3l",quantity:1, price:5.99, identifier: "id_5068663"),
          TestProduct(id:"5774130",name:"Cannelle moulue U, format petit, 17g",quantity:1, price:0.60, identifier:"id_5774130"),
          TestProduct(id:"6134471",name:"Banane Cavendish BIO, calibre P14, catégorie 2, Republique Dominicaine, ruban 5 fruits",quantity:1, price:1.99, identifier: "id_6134471"),
          TestProduct(id:"6182231",name:"Beurre de cacahuète creamy MENGUY'S 454g",quantity:1, price:3.99, identifier: "id_6182231"),
          TestProduct(id:"352902000000909790",name:"Baguette Triskel",quantity:1, price:1.0, identifier: "id_352902000000909790"),
          TestProduct(id:"88455",name:"Emmental français rapé U, 29%mg, 100g",quantity:1, price:0.84, identifier: "id_88455"),
          TestProduct(id:"2107653",name:"Lait de coco KARA, 200ml",quantity:1, price:0.94, identifier: "id_2107653"),
          TestProduct(id:"5319173",name:"Filet de blanc de poulet U, France, barquette",quantity:1, price:6.06, identifier: "id_5319173"),
          TestProduct(id:"6511680",name:"Curry tradition en poudre DUCROS, 53g",quantity:1, price:3.40, identifier: "id_6511680"),
      ]
    
    var criteria = SuggestionsCriteria(
        shelfIngredientsIds: ["5319173","970417"],
            currentIngredientsIds:nil,
            basketIngredientsIds: nil,
            groupId: nil
        
    )
    
     @ObservedObject
     var viewModel = ViewModel()


    var body: some View {
        ScrollView {
            VStack {
                Button(action: addRandomProduct) {
                    Text("Add Random product")
                }
                Button(action: removeRandomProduct) {
                    Text("Remove random product")
                }
                RecipeCardView(recipeId: "1")
                RecipeCardView(criteria: criteria)
    }
    }
    }
    private func addRandomProduct(){
        AppBasket.sharedInstance.basket.add(addedProduct: productSample.randomElement()!)
        
    }
    
    private func removeRandomProduct(){
        AppBasket.sharedInstance.basket.remove(removedProduct: AppBasket.sharedInstance.basket.items.randomElement()!)
        
    }
    
struct ContentView_Previews: PreviewProvider {
	static var previews: some View {
        ContentView()
	}
    }
}
