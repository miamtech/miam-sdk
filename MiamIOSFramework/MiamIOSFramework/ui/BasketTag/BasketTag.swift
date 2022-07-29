//
//  BasketTag.swift
//  MiamIOSFramework
//
//  Created by Miam on 07/07/2022.
//

import SwiftUI
import miamCore

@available(iOS 14, *)
public struct BasketTag: View {
    
    @SwiftUI.State private var initialDialogScreen = RouterContent.recipeDetail
    @SwiftUI.State var showingRecipeDetails = false
    @SwiftUI.State var showingListModal = false
    
    @ObservedObject var basketTagVm : BasketTagVM
    
    public init(itemId : String){
        self.basketTagVm = BasketTagVM(productExtId: itemId)
    }
    
    public var body: some View {
        if(basketTagVm.recipeList != nil && basketTagVm.recipeList!.count > 0){
            HStack(){
                Text((basketTagVm.recipeList![0] as! Recipe).attributes?.title ?? "")
                    .lineLimit(1)
                    .padding(.horizontal,8)
                    .padding(.vertical,4)
                    .overlay(
                        RoundedRectangle(cornerRadius: 50)
                            .stroke(.gray, lineWidth: 1)
                    ).onTapGesture {
                        showingListModal = true
                    }
                if(basketTagVm.recipeList != nil && basketTagVm.recipeList!.count > 1){
                ZStack(){
                    Circle()
                        .strokeBorder(Color.miamColor(.primary), lineWidth: 1)
                        .frame(width: 30, height: 30)
                    Text("+" + String(basketTagVm.recipeList!.count - 1))
                        .foregroundColor(Color.miamColor(.primary))
                }
                    
                }
            }
            .sheet(isPresented: $showingListModal)
            {
                ListModal(showingListModal: $showingListModal,
                        showingPopup: $showingRecipeDetails,
                        recipeList: basketTagVm.recipeList ??  NSArray(),
                        basketTagVm: basketTagVm)
            }
            .sheet(isPresented: $showingRecipeDetails) {
                Dialog(
                    close: { showingRecipeDetails = false },
                    initialRoute : initialDialogScreen,
                    routerVm: basketTagVm.vMRouter
                )
            }
        }
    }
}

@available(iOS 14, *)
internal struct ListModal: View {
    
    @Binding var showingListModal:Bool
    @Binding var showingPopup:Bool
    var recipeList : NSArray
    var basketTagVm : BasketTagVM
    
    var body: some View {
        
        HStack {
            VStack(spacing: 10) {
                HStack {
                    Text("Ce produit est utilisé pour 3 recettes :").bold()
                    Spacer()
                }
                HStack {
                    VStack{
                        ForEach(0..<recipeList.count) { i in
                            Text((recipeList[i] as! Recipe).attributes?.title ?? "")
                                .underline()
                                .foregroundColor(Color.miamColor(.ternary))
                                .bold()
                                .padding(.horizontal,8)
                                .padding(.vertical, 4).onTapGesture {
                                    basketTagVm.goToDetail(recipe: recipeList[i] as! Recipe)
                                    showingPopup = true
                                    showingListModal = false                              
                                }
                        }
                    }
                    Spacer()
                }
                Spacer()
                Divider()
                HStack{
                    Spacer()
                    Image.miamImage(icon: .cross)
                        .renderingMode(.original)
                        .frame(
                            width: 24,
                            height: 24,
                            alignment: .center
                        )
                    Spacer()
                }.onTapGesture {
                    showingListModal = false
                }
            }
            Spacer()
        }.padding([.horizontal,.vertical],8)
            .frame( alignment: .top)
            .background(RoundedRectangle(cornerRadius: 27).fill(Color.white.opacity(1)))
    }
}

