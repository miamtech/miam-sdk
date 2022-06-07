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
    
    public init(close: @escaping () -> Void, initialRoute: RouterContent) {
        self.close = close
        viewModel.content = initialRoute
    }
    
    
    func goToDetail(vmRecipe :RecipeViewModel){
        viewModel.setEvent(event:
                            RouterOutletContractEvent.GoToDetail(
                                vm: vmRecipe,
                                withFooter: false
                            )
        )
    }
    
    func goToPreview(recipeId: String ,vmRecipe: RecipeViewModel) {
        viewModel.setEvent(event:
                            RouterOutletContractEvent.GoToPreview(
                                recipeId: recipeId,
                                vm: vmRecipe
                            )
        )
    }
    

    
    var body: some View {
        VStack{
            HStack(alignment: .top){
                switch viewModel.content {
                case RouterContent.recipeDetail : RecpieDetailsView(recipeId: "1", close: close)
                case RouterContent.basketPreview : BasketPreview()
                default: HStack{}
                    
                }
            }
        }
    }
}
