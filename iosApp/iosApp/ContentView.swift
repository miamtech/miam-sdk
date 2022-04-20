import SwiftUI
import MiamIOSFramework

class ViewModel : ObservableObject {

    init(){
    }

}

struct ContentView: View {
        
     @ObservedObject
     var viewModel = ViewModel()


    var body: some View {
            VStack {
                RecipeCardView(recipeId: "1")
                RecipeCardView(recipeId: "2")
    }
    
}
    
    

struct ContentView_Previews: PreviewProvider {
	static var previews: some View {
        ContentView()
	}
    }
}
