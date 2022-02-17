import SwiftUI
import MiamIOSFramework

class ViewModel : ObservableObject {

    init(){
        let a = Analytics()
        a.log(message: "toto")
    }

}

struct ContentView: View {
        
     @ObservedObject
     var viewModel = ViewModel()
    
    
//    analytics.log(message: "analytics initialized")

    var body: some View {
            VStack {
                Text("hellop me").padding()
                RecipeCardView(recipeId: 1)
                RecipeCardView(recipeId: 2)
    }
    
}
    
    

struct ContentView_Previews: PreviewProvider {
	static var previews: some View {
        ContentView()
	}
    }
}
