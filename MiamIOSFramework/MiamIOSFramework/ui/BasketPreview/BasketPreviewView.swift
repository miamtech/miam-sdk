//
//  BasketPreviewView.swift
//  MiamIOSFramework
//
//  Created by Miam on 21/06/2022.
//

import SwiftUI
import miamCore


@available(iOS 14, *)
struct BasketPreviewView: View {
    
    @ObservedObject private var viewModel: BasketPreviewVM

    private var recipeVm : RecipeViewModel
    private var goToDetail : (_ : RecipeViewModel, Bool) -> Void
    private var close : ()-> Void
    private var goToItemSelector: () -> Void
    
    public init(recipeId: String,
                recipeVm: RecipeViewModel,
                goToDetail: @escaping (_ : RecipeViewModel, Bool) -> Void,
                close: @escaping ()-> Void,
                goToItemSelector: @escaping () -> Void
    )
    {
        viewModel = BasketPreviewVM(recipeId: recipeId)
        self.recipeVm = recipeVm
        self.goToDetail = goToDetail
        self.close = close
        self.goToItemSelector = goToItemSelector
    }
    
    var body: some View {
        if(viewModel.state != nil) {
            ManagementResourceState<BasketPreviewLine, BasketPreviewSuccessView,BasketPreviewLoadingView> (
                resourceState: viewModel.state!.line,
                successView:  BasketPreviewSuccessView(
                    viewModel: viewModel,
                    recipeVm: recipeVm,
                    goToDetail: goToDetail,
                    close: close,
                    goToItemSelector: goToItemSelector
                ) ,
                loadingView: BasketPreviewLoadingView()
            )
        }
    }
}
