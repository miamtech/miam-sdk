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
    
    @ObservedObject var viewModel: DialogVM
    
    
    public init(close: @escaping () -> Void, initialRoute: RouterContent, routerVm : RouterOutletViewModel ) {
        self.close = close
        self.viewModel = DialogVM(routerVm: routerVm)
    }
    
    func goToDetail(vmRecipe :RecipeViewModel){
        viewModel.getKotlinVm().setEvent(event:
                            RouterOutletContractEvent.GoToDetail(
                                vm: vmRecipe,
                                withFooter: false
                            )
        )
    }
    

    
    var body: some View {
        VStack{
            HStack(alignment: .top){
                switch viewModel.state?.content {
                case RouterContent.recipeDetail : RecpieDetailsView(vmRecipe: viewModel.state?.rvm! as! RecipeCardVM, close: close)
                case RouterContent.basketPreview : BasketPreview()
                case RouterContent.itemsSelector : HStack{}
                default: HStack{}
                    
                }
            }
        }
    }
}
