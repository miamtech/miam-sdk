//
//  ItemSelector.swift
//  MiamIOSFramework
//
//  Created by Miam on 07/06/2022.
//

import SwiftUI
import miamCore

@available(iOS 14, *)
public struct ItemSelector: View  {
    @ObservedObject var viewModel : ItemSelectorVM = ItemSelectorVM()
    private let recipeId :String
    private let onItemSelected: () -> Void
    public init(recipeId :String,onItemSelected: @escaping () -> Void) {
        self.recipeId = recipeId
        self.onItemSelected = onItemSelected
    }
    
    public var body: some View {
        if(viewModel.state != nil ) {
            ManagementResourceState<BasketPreviewLine, ItemSelectorSuccessView, ItemSelectorLoadingView, ItemSelectorEmptyView> (
                resourceState: viewModel.state?.selectedItem ,
                successView:  ItemSelectorSuccessView(
                    recipeId:recipeId,
                    selectedItem: viewModel.selectedItem,
                    items: viewModel.state?.items ?? [],
                    chooseItem: { bpl, index in  viewModel.chooseItem(selectedItem: bpl, index: index)},
                    onItemSelected: { onItemSelected()}
                ),
                loadingView: ItemSelectorLoadingView(),
                emptyView: ItemSelectorEmptyView()
            )
        }
    }
}


@available(iOS 14, *)
struct ItemSelectorEmptyView: View {
    var body: some View {
        VStack{}
    }
}
