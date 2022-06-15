//
//  ItemSelector.swift
//  MiamIOSFramework
//
//  Created by Miam on 07/06/2022.
//

import SwiftUI
import shared

public struct ItemSelector: View  {
    
    @ObservedObject var viewModel : ItemSelectorVM
    
    public init() {
        viewModel = ItemSelectorVM()
    }
    
    
    public var body: some View {
        if(viewModel.state != nil ) {
            ScrollView{
                ZStack{
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
                    }.padding(.top , 80)
                    
                    VStack(alignment: .leading){
                        HStack(spacing: 20){
                            Button(
                                action: {
                                    viewModel.returnToPreview()
                                }
                            ) {
                                Image("Caret")
                                    .renderingMode(.original)
                                    .frame(
                                        width: 24,
                                        height: 24,
                                        alignment: .center
                                    )
                            }
                            .frame( alignment: .center)
                            .rotationEffect(.degrees(180))
                            .padding(.horizontal , 8)
                            Text(ItemSelectorText.sharedInstance.swapProduct)
                                .foregroundColor(MiamColor.sharedInstance.black)
                                .font(.system(size: 16, weight: .heavy, design: .default))
                        }.padding(16)
                        Divider()
                    }.frame(minWidth: 0, maxWidth: .infinity, minHeight: 0, maxHeight: .infinity, alignment: .topLeading)
                }
            }
        }
    }
}
