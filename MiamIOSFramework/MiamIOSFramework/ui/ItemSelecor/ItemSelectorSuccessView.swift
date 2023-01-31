//
//  ItemSelectorSucessView.swift
//  MiamIOSFramework
//
//  Created by Miam on 23/01/2023.
//  Copyright © 2023 Miam. All rights reserved.
//

import SwiftUI
import miamCore

@available(iOS 14, *)
struct ItemSelectorSuccessView: View {

    let recipeId: String
    let selectedItem: BasketPreviewLine?
    let items: [BasketPreviewLine]
    let chooseItem: (BasketPreviewLine, Int) -> Void
    let onItemSelected: () -> Void

    private let analytics = AnalyticsInstance.shared.instance

    var body: some View {

        if let item = selectedItem {
            VStack {
                ScrollView {
                    VStack(alignment: .leading) {

                        if let template = Template.sharedInstance.currentProductTemplate {
                            template(item)
                        } else {
                            ItemSelectorProductRow(product: item, isSelected: true)
                        }

                        if let template = Template.sharedInstance.productOptionListTemplate {
                            template(
                                items,
                                chooseItem
                            )
                        } else {
                            ForEach(items, id: \.self) { item in
                                HStack {
                                    ItemSelectorProductRow(
                                        product: item
                                    )
                                }.onTapGesture {
                                    if let index = items.firstIndex(of: item) {
                                        chooseItem(item, index)
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
                    props: Analytics.PlausibleProps(recipe_id: recipeId, category_id: nil, entry_name: nil, basket_id: nil, miam_amount: nil, total_amount: nil, pos_id: nil, pos_total_amount: nil, pos_name: nil, search_term: nil)
                )
            }
        }
    }
}
