import SwiftUI
import shared

class ViewModel : ObservableObject {

    init(){
    }

}

struct ContentView: View {
        
     @ObservedObject
     var viewModel = ViewModel()

    var body: some View {
            VStack {
                RecipeCardView(recipeId: 1637)
                RecipeCardView(recipeId: 1639)
    }
    
}
    
    

struct ContentView_Previews: PreviewProvider {
	static var previews: some View {
        ContentView()
	}
}

