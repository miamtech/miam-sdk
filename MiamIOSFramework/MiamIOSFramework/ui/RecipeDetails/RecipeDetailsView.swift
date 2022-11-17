//
//  RecpieDetailsView.swift
//  MiamIOSFramework
//
//  Created by Miam on 11/05/2022.
//

import SwiftUI
import miamCore


@available(iOS 14, *)
public struct RecipeDetailsView: View {
    public var recipeId: String?
    public var close: () -> ()
    public var showFooter = true
    @SwiftUI.State var showTitleInHeader = false
    @ObservedObject var viewModel: RecipeCardVM
    
    public init(recipeId: String, close: @escaping () -> (), showFooter: Bool = true) {
        self.recipeId = recipeId
        self.close = close
        viewModel = RecipeCardVM(routerVM: RouterOutletViewModel())
        self.showFooter = showFooter
    }
    
    public init(vmRecipe: RecipeCardVM, close: @escaping () -> (), showFooter: Bool = true) {
        self.close = close
        self.viewModel = vmRecipe
        self.showFooter = showFooter
    }
    
    public var body: some View {
        VStack {
            TitleBarView(showBackButton: true, backAction: close, titleView: AnyView(
                HStack {
                    HStack(){
                        Image.miamImage(icon: .ideeRepas)
                            .resizable()
                            .scaledToFill()
                            .frame(width:24, height:24)
                        Text(RecipeCardText.sharedInstance.recipeFlag)
                            .font(.system(size: 14.0, design: .default))
                    }.padding(.horizontal,16)
                        .padding(.vertical,4)
                        .background(Color.miamColor(.musterd))
                        .cornerRadius(8).rotationEffect(Angle(degrees: -2.0))
                    if (showTitleInHeader) {
                        Text(viewModel.recipe?.attributes?.title ?? "").foregroundColor(Color.miamColor(.black))
                            .font(.system(size: 16, weight: .heavy, design: .default))
                            .padding(.horizontal, Dimension.sharedInstance.lPadding)
                            .frame( alignment: .topLeading)
                            .lineLimit(1)
                    }
                }
            ))
            
            ScrollView {
                ZStack {
                    VStack {
                        if (viewModel.recipe != nil) {
                            if (Template.sharedInstance.recipeDetailInfosTemplate != nil) {
                                Template.sharedInstance.recipeDetailInfosTemplate!(
                                    viewModel.recipe!
                                )
                            } else {
                                VStack {
                                    RecipeDetailsHeaderView(mediaURL: viewModel.recipe?.attributes?.mediaUrl,
                                                            title: viewModel.recipe?.attributes?.title ?? "",
                                                            difficulty: Int(truncating: viewModel.recipe?.attributes?.difficulty ?? 1),
                                                            totalTime: viewModel.recipe?.totalTime ?? "10 min",
                                                            showTitleInHeader: $showTitleInHeader,
                                                            isLikeEnabled: viewModel.isLikeEnabled,
                                                            recipeId: recipeId ?? viewModel.recipe?.id)
                                }
                                
                                RecipeTimeView(preparationTime: viewModel.recipe!.preparationTimeIos,
                                               cookingTime: viewModel.recipe!.cookingTimeIos,
                                               restingTime: viewModel.recipe!.restingTimeIos)
                            }
                            
                            
                            if let ingredients = viewModel.recipe?.relationships?.ingredients?.data {
                                RecipeDetailsIngredientsView(ingredients: ingredients,
                                                          recipeGuests: Int(viewModel.recipe?.attributes?.numberOfGuests ?? 0),
                                                          currentGuests: Int(viewModel.currentState.guest),
                                                          increaseGuestsAction: {viewModel.increaseGuest()},
                                                          decreaseGuestsAction: {viewModel.decreaseGuest()})
                            }
                        
                            RecipeDetailsStepsView(steps: viewModel.sortedSteps)
                        }
                    }.padding(.bottom, 100.0)
                }.onAppear(perform: {
                    if (recipeId != nil) {
                        viewModel.fetchRecipe(recipeId: self.recipeId!)
                    }
                }
                )
            }.coordinateSpace(name: "scroll")
            
            if (showFooter) {
                RecipeDetailsFooter(recipeVM: viewModel)
            }
        }
    }
}

struct ViewOffsetKey: PreferenceKey {
    typealias Value = CGFloat
    static var defaultValue = CGFloat.zero
    static func reduce(value: inout Value, nextValue: () -> Value) {
        value += nextValue()
    }
}
