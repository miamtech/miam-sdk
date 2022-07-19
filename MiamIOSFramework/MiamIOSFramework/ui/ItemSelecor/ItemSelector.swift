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
    
    @ObservedObject var viewModel : ItemSelectorVM
    
    public init() {
        viewModel = ItemSelectorVM()
    }
    
    
    public var body: some View {
        if(viewModel.state != nil ) {
            VStack {
                TitleBarView(showBackButton: true, backAction: {
                    viewModel.returnToPreview()
                }, titleView: AnyView(
                    Text(ItemSelectorText.sharedInstance.swapProduct)
                        .foregroundColor(MiamColor.sharedInstance.black)
                        .font(.system(size: 16, weight: .heavy, design: .default)))
                )

                ScrollView{
                    VStack(alignment: .leading){
                        if (Template.sharedInstance.currentProductTemplate != nil) {
                            Template.sharedInstance.currentProductTemplate!(
                                viewModel.state!.selectedItem!
                            )
                        } else {
                            ItemSelectorProductRow(product: viewModel.state!.selectedItem! ,isSelected: true)
                        }
                        if(Template.sharedInstance.productOptionListTemplate != nil) {
                            Template.sharedInstance.productOptionListTemplate!(
                                viewModel.state!.itemList ?? [],
                                viewModel.chooseItem
                            )
                        }
                        else {
                            ForEach((viewModel.state!.itemList ?? []).indices, id: \.self)
                            { i in
                                HStack{
                                    ItemSelectorProductRow(
                                        product: viewModel.state!.itemList![i]
                                    )
                                }.onTapGesture {
                                    viewModel.chooseItem(index: i)
                                }

                            }
                        }
                    }.padding([.leading, .trailing], Dimension.sharedInstance.mlPadding)
                }
            }
        }
    }
}
