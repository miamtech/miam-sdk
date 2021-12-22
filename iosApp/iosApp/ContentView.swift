import SwiftUI
import shared

class ViewModel : ObservableObject {
    
    private var networkModule : NetworkModule
    @Published var recipe :Recipe? = nil
    
    init(){
        self.networkModule = NetworkModule()
        load()
    }
    
    func load() -> Void {
        
        networkModule.recipeService.get(id:1)  { result , error in
            self.recipe = result
          }
    }
}

struct ContentView: View {
        
     @ObservedObject
     var viewModel = ViewModel()
    

    var body: some View {
        if(viewModel.recipe != nil){
            VStack {
                RoundedRectangle(cornerRadius: 25, style: .continuous).fill(Color.white)
                AsyncImage(url: URL(string: viewModel.recipe!.attributes.mediaUrl )!,
                           placeholder: { Text("loading ...")})
            Text(viewModel.recipe?.attributes.title  ?? "loading ...")}
        }
       
    }
    
}
    
    

struct ContentView_Previews: PreviewProvider {
	static var previews: some View {
        ContentView()
	}
}

