//
//  BasketTag.swift
//  MiamIOSFramework
//
//  Created by Miam on 07/07/2022.
//

import SwiftUI
import miamCore

@available(iOS 14, *)
public struct BasketTagView: View {
    
    @SwiftUI.State private var initialDialogScreen = RouterContent.recipeDetail
    @SwiftUI.State var showingRecipeDetails = false
    @SwiftUI.State var showingListModal = false
    
    @ObservedObject var basketTagVm : BasketTagVM
    
    public init(itemId : String){
        self.basketTagVm = BasketTagVM(productExtId: itemId)
    }
    
    public var body: some View {
        if let recipes = basketTagVm.recipeList as? [Recipe] {
            TagView(recipes: recipes, tagTappedAction: { showingListModal.toggle() })
            .sheet(isPresented: $showingListModal)
            {
                BasketTagListModal(showingListModal: $showingListModal,
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
internal struct TagView: View {
    let recipes: [Recipe]
    let tagTappedAction: () -> Void
    
    var body: some View {
        if let template = Template.sharedInstance.tagViewTemplate {
            template(recipes, tagTappedAction)
        } else {
            HStack() {
                Text(recipes[0].attributes?.title ?? "")
                    .lineLimit(1)
                    .padding(.horizontal,8)
                    .padding(.vertical,4)
                    .overlay(
                        RoundedRectangle(cornerRadius: 50)
                            .stroke(.gray, lineWidth: 1)
                    ).onTapGesture {
                        tagTappedAction()
                    }
                
                if recipes.count > 1 {
                    ZStack(){
                        Circle()
                            .strokeBorder(Color.miamColor(.primary), lineWidth: 1)
                            .frame(width: 30, height: 30)
                        Text("+" + String(recipes.count - 1))
                            .foregroundColor(Color.miamColor(.primary))
                    }
                }
            }
        }
    }
}

@available(iOS 14, *)
internal struct BasketTagListModal: View {
    @Binding var showingListModal:Bool
    @Binding var showingPopup:Bool
    var recipeList : NSArray
    var basketTagVm : BasketTagVM
    
    var body: some View {
        if let template = Template.sharedInstance.basketTagListModal {
            template($showingListModal, $showingPopup, recipeList, basketTagVm)
        } else {
            HStack {
                VStack(spacing: 10) {
                    HStack {
                        Text("Ce produit est utilisé pour 3 recettes :").bold()
                        Spacer()
                    }
                    HStack {
                        VStack{
                            let recipes: [Recipe] = recipeList.compactMap({$0 as? Recipe})
                            ForEach(recipes, id: \.self) { recipe in
                                Text(recipe.attributes?.title ?? "")
                                    .underline()
                                    .foregroundColor(Color.miamColor(.ternary))
                                    .bold()
                                    .padding(.horizontal,8)
                                    .padding(.vertical,4)
                                    .onTapGesture {
                                        basketTagVm.goToDetail(recipe: recipe)
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
}
