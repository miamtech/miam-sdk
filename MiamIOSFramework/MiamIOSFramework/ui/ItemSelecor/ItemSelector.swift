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
    private let onItemSelected: () -> Void
    public init(onItemSelected: @escaping () -> Void) {
        self.onItemSelected = onItemSelected
    }
    
    public var body: some View {
        if(viewModel.state != nil ) {
            VStack {
                TitleBarView(
                    showBackButton: true, backAction: {
                        viewModel.returnToPreview()
                    }, titleView: AnyView(
                        HStack{
                            Spacer()
                            Text(ItemSelectorText.sharedInstance.swapProduct)
                                .miamFontStyle(style: MiamFontStyleProvider.sharedInstance.titleMediumStyle)
                                .foregroundColor(Color.miamColor(.black))
                            Spacer()
                        }
                    )
                )
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
        }
    }
}
