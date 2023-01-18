//
//  Dialog.swift
//  MiamIOSFramework
//
//  Created by Miam on 03/05/2022.
//

import SwiftUI
import miamCore


@available(iOS 14, *)
struct Dialog: View {
    
    private var close: () -> Void
    private var itemSelectorVM = ItemSelectorVM()

    private let recipeId: String
    @ObservedObject var viewModel: DialogVM
    
    
    public init(recipeId: String, close: @escaping () -> Void, initialRoute: RouterContent, routerVm : RouterOutletViewModel) {
        self.close = close
        self.recipeId = recipeId
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
        VStack {
            HStack(alignment: .top){
                switch viewModel.state?.content {
                    case RouterContent.recipeDetail :
                        RecipeDetailsView(recipeId: recipeId, showFooter: viewModel.state!.showFooter, close: close, navigateToPreview: {}, buy:  {})
                    case RouterContent.basketPreview :
                        if let recipeId = viewModel.state?.recipeId, let recipeViewModel = viewModel.state?.rvm  {
                            BasketPreviewView(recipeId: recipeId,
                                              recipeVm:  recipeViewModel,
                                              goToDetail: goToDetail,
                                              close: close,
                                              goToItemSelector: goToReplaceItem)
                        } else {
                            DialogEmptyView(closeAction: close)
                        }
                    case RouterContent.itemsSelector : ItemSelector {
                        ()
                    }
                    case RouterContent.empty: DialogEmptyView(closeAction: close)
                    default: DialogEmptyView(closeAction: close)
                }
            }
        }
    }
}
