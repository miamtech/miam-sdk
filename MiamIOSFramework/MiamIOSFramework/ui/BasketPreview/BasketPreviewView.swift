//
//  BasketPreviewView.swift
//  MiamIOSFramework
//
//  Created by Miam on 21/06/2022.
//

import SwiftUI
import miamCore

@available(iOS 14, *)
struct BasketPreviewEmptyView: View {
    var body: some View {
        HStack {}
    }
}

@available(iOS 14, *)
public struct BasketPreviewView: View {

    @ObservedObject private var viewModel: BasketPreviewVM

    private var recipeVm: RecipeViewModel
    private let title: String?
    private var goToDetail: (_: RecipeViewModel, Bool) -> Void
    private var close: () -> Void
    private var goToItemSelector: () -> Void

    public init(recipeId: String,
                recipeVm: RecipeViewModel,
                title: String? = nil,
                goToDetail: @escaping (_: RecipeViewModel, Bool) -> Void,
                close: @escaping () -> Void,
                goToItemSelector: @escaping () -> Void
    ) {
        viewModel = BasketPreviewVM(recipeId: recipeId)
        self.recipeVm = recipeVm
        self.goToDetail = goToDetail
        self.title = title
        self.close = close
        self.goToItemSelector = goToItemSelector
    }

    public var body: some View {
        if let state = viewModel.state {
            ManagementResourceState<BasketPreviewLine, BasketPreviewSuccessView, BasketPreviewLoadingView, BasketPreviewEmptyView>(
                resourceState: state.line,
                successView: BasketPreviewSuccessView(
                    viewModel: viewModel,
                    title: title,
                    recipeVm: recipeVm,
                    goToDetail: goToDetail,
                    close: close,
                    goToItemSelector: goToItemSelector,
                    isReloading: state.isReloading,
                    updatingBasketEntryId: state.updatingBasketEntryId ??  nil
                ),
                loadingView: BasketPreviewLoadingView(),
                emptyView: BasketPreviewEmptyView()
            )
        }
    }
}
