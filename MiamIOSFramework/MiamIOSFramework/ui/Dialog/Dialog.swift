//
//  Dialog.swift
//  MiamIOSFramework
//
//  Created by Miam on 03/05/2022.
//

import SwiftUI
import shared


@available(iOS 14, *)
struct Dialog: View {
    
    private var close: () -> Void
    private var itemSelectorVM = ItemSelectorVM()

    @ObservedObject var viewModel: DialogVM
    
    
    public init(close: @escaping () -> Void, initialRoute: RouterContent, routerVm : RouterOutletViewModel) {
        self.close = close
        self.viewModel = DialogVM(routerVm: routerVm)
    }
    
    func goToDetail(vmRecipe :RecipeViewModel, withFooter: Bool = true) {
        viewModel.getKotlinVm().setEvent(event:
                                            RouterOutletContractEvent.GoToDetail(
                                                vm: vmRecipe,
                                                withFooter: withFooter
                                            )
        )
    }
    
    func goToReplaceItem() {
        itemSelectorVM.sharedInstance.setEvent(
            event:
                ItemSelectorContractEvent.SetReturnToBasketPreview(
                    returnToPreview : {
                        if(viewModel.state != nil && viewModel.state?.recipeId != nil && viewModel.state?.rvm != nil){
                            viewModel.getKotlinVm().setEvent(event:
                                    RouterOutletContractEvent.GoToPreview(
                                        recipeId : viewModel.state!.recipeId!,
                                        vm : viewModel.state!.rvm!
                                    )
                                )
                        } else {
                            close()
                        }
                    }
                )
        )
        viewModel.getKotlinVm().setEvent(event:
                                            RouterOutletContractEvent.GoToItemSelector()
        )
    }
    
    var body: some View {
        VStack{
            HStack(alignment: .top){
                switch viewModel.state?.content {
                case RouterContent.recipeDetail : RecpieDetailsView(vmRecipe: viewModel.state?.rvm! as! RecipeCardVM, close: close, showFooter: viewModel.state!.showFooter)
                case RouterContent.basketPreview : BasketPreviewView(recipeId: viewModel.state!.recipeId!,
                                                                     recipeVm:  (viewModel.state?.rvm!)!,
                                                                     goToDetail: goToDetail,
                                                                     close: close,
                                                                     goToItemSelector: goToReplaceItem
                )
               case RouterContent.itemsSelector : ItemSelector()
                default: HStack{}
                }
            }
        }
    }
}
