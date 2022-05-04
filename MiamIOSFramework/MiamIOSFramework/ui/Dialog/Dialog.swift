//
//  Dialog.swift
//  MiamIOSFramework
//
//  Created by Miam on 03/05/2022.
//

import SwiftUI
import shared

struct Dialog: View {
    
    private var close: () -> Void
    
    @ObservedObject var viewModel: DialogVM = DialogVM()
    
    public init(close: @escaping () -> Void, initialRoute: RouterContent, recipeVm : RecipeCardVM) {
        self.close = close
        print("Dialog \(initialRoute)")
        switch initialRoute {
        case RouterContent.recipeDetail: goToDetail(vmRecipe: recipeVm)
        case RouterContent.basketPreview: goToPreview(recipeId: recipeVm.recipe!.id, vmRecipe: recipeVm)
        default : return
        }
        viewModel.content = initialRoute
    }
    
    
    func goToDetail(vmRecipe :RecipeViewModel){
        viewModel.setEvent(event:
               RouterContractEvent.GoToDetail(
                vm: vmRecipe
               )
           )
       }

       func goToPreview(recipeId: String ,vmRecipe: RecipeViewModel) {
           viewModel.setEvent(event:
               RouterContractEvent.GoToPreview(
                recipeId: recipeId,
                vm: vmRecipe
               )
           )
       }
    
    
    var body: some View {
        VStack{
            HStack(alignment: .top){
            Button(action: {
                close()
            }){
                Image(systemName: "chevron.left").frame(width: 30, height: 30)
            }
                Spacer()
            }
            switch viewModel.content {
            case RouterContent.recipeDetail : RecipeDetail()
            case RouterContent.basketPreview : BasketPreview()
            default: HStack{}
                
            }
        }
    }
}

struct Dialog_Previews: PreviewProvider {
    static var previews: some View {
        Dialog(close: {},initialRoute: RouterContent.recipeDetail, recipeVm: RecipeCardVM())
    }
}
