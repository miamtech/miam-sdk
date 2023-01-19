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
                NavigationView {
                    VStack {
                        BasketTagListModal(showingListModal: $showingListModal,
                                           recipeList: basketTagVm.recipeList ??  NSArray(),
                                           basketTagVm: basketTagVm)
                        
                    }
                }
                
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
                    .miamFontStyle(style: MiamFontStyleProvider.sharedInstance.bodyBigStyle)
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
                            .miamFontStyle(style: MiamFontStyleProvider.sharedInstance.bodyBigStyle)
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
    
    @SwiftUI.State var showingRecipeDetails = false
    var recipeList : NSArray
    var basketTagVm : BasketTagVM
    
    var body: some View {
        NavigationView {
            if let template = Template.sharedInstance.basketTagListModal {
                template($showingListModal, recipeList, basketTagVm)
            } else {
                HStack {
                    VStack(spacing: 10) {
                        HStack {
                            
                            Spacer()
                        }
                        HStack {
                            VStack{
                                let recipes: [Recipe] = recipeList.compactMap({$0 as? Recipe})
                                ForEach(recipes, id: \.self) { recipe in
                                    Text(recipe.attributes?.title ?? "")
                                        .underline()
                                        .miamFontStyle(style: MiamFontStyleProvider.sharedInstance.bodyBigBoldStyle)
                                        .foregroundColor(Color.miamColor(.ternary))
                                        .padding(.horizontal,8)
                                        .padding(.vertical,4)
                                        .onTapGesture {
                                            basketTagVm.goToDetail(recipe: recipe)
                                            showingRecipeDetails = true
                                        }
                                    NavigationLink("Détails de la recette", isActive: $showingRecipeDetails) {
                                        RecipeDetailsView(recipeId: recipe.id, showFooter: false, close: {}, navigateToPreview: {}, buy: {})
                                    }.hidden().frame(height: 1.0)
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
                }
                .navigationBarTitleDisplayMode(.inline)
                .navigationTitle("Ce produit est utilisé pour \(recipeList.count) recettes")
                .padding([.horizontal,.vertical],8)
                .frame( alignment: .top)
                .background(RoundedRectangle(cornerRadius: 27).fill(Color.white.opacity(1)))
            }
        }
        .accentColor(Color.miamColor(.primary))
    }
}

