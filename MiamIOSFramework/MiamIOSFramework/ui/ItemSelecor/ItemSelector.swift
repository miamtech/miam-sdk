//
//  ItemSelector.swift
//  MiamIOSFramework
//
//  Created by Miam on 07/06/2022.
//

import SwiftUI
import shared

public struct ItemSelector: View {
    
    @ObservedObject var viewModel: ItemSelectorVM = ItemSelectorVM()
    
    public init() {}
    
    private func chooseItem(index : Int) {
        viewModel.choose(index: Int32(index))
        returnToPreview()
    }
    
    private func returnToPreview(){
        viewModel.setEvent(event: ItemSelectorContractEvent.ReturnToBasketPreview())
    }
    
    public var body: some View {
        ScrollView{
            ZStack{
                VStack(alignment: .leading){
                    if (Template.sharedInstance.currentProductTemplate != nil) {
                        Template.sharedInstance.currentProductTemplate!(
                            viewModel.currentState.selectedItem!
                        )
                    } else {
                        ItemSelectorProductRow(product: viewModel.currentState.selectedItem! ,isSelected: true)
                    }
                    if(Template.sharedInstance.productOptionListTemplate != nil) {
                        Template.sharedInstance.productOptionListTemplate!(
                            viewModel.currentState.itemList ?? [],
                            chooseItem
                        )
                    }
                    else {
                        ForEach((viewModel.currentState.itemList ?? []).indices, id: \.self)
                        { i in
                            ItemSelectorProductRow(
                                product: viewModel.currentState.itemList![i]
                            ).onTapGesture {
                                chooseItem(index: i)
                            }
                        }
                    }
                }.padding(.top ,130)
                
                VStack(alignment: .leading){
                    HStack(spacing: 20){
                        Image("Arrow").onTapGesture {
                            returnToPreview()
                        }
                        Text(ItemSelectorText.sharedInstance.swapProduct)
                            .foregroundColor(MiamColor.sharedInstance.black)
                            .font(.system(size: 16, weight: .heavy, design: .default))
                    }.padding(16)
                    Divider()
                    HStack{
                        Text("Pour \(viewModel.currentState.selectedItem!.count) \(viewModel.currentState.selectedItem!.title)")
                            .foregroundColor(MiamColor.sharedInstance.primary)
                            .font(.system(size: 16, weight: .heavy, design: .default))
                    }.padding(16)
                }.frame(minWidth: 0, maxWidth: .infinity, minHeight: 0, maxHeight: .infinity, alignment: .topLeading)
            }
        }
    }
}
