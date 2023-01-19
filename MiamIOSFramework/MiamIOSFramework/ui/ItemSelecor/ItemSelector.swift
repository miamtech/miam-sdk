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
    private let analytics = AnalyticsInstance.shared.instance
   
    public init(recipeId :String,onItemSelected: @escaping () -> Void) {
        self.recipeId = recipeId
        self.onItemSelected = onItemSelected
    }
    
    public var body: some View {
        if(viewModel.state != nil ) {
            VStack {
                ScrollView{
                    VStack(alignment: .leading){
                        if let selectedItem = viewModel.state?.selectedItem {
                            if let template = Template.sharedInstance.currentProductTemplate {
                                template(selectedItem)
                            } else {
                                ItemSelectorProductRow(product: selectedItem, isSelected: true)
                            }
                        }
                        
                        if let template = Template.sharedInstance.productOptionListTemplate {
                            template(
                                viewModel.state?.itemList ?? [],
                                viewModel.chooseItem
                            )
                        }
                        else {
                            ForEach((viewModel.state?.itemList ?? []), id: \.self) { item in
                                HStack{
                                    ItemSelectorProductRow(
                                        product: item
                                    )
                                }.onTapGesture {
                                    if let index = viewModel.state?.itemList?.firstIndex(of: item) {
                                        viewModel.chooseItem(index: index)
                                        onItemSelected()
                                    }
                                }
                            }
                        }
                    }.padding([.leading, .trailing], Dimension.sharedInstance.mlPadding)
                }
            }
            .navigationTitle(ItemSelectorText.sharedInstance.swapProduct)
            .onAppear {
                analytics.sendEvent(
                    eventType: Analytics.companion.EVENT_PAGEVIEW,
                    path: "/replace-item",
                    props: Analytics.PlausibleProps(recipe_id: recipeId, category_id: nil, entry_name: nil, basket_id: nil, miam_amount:nil, total_amount: nil, pos_id: nil, pos_total_amount: nil, pos_name: nil, search_term: nil)
                )
            }
        }
    }
}
