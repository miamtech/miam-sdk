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
        VStack(spacing: 0) {
            TitleBarView(showBackButton: true, backAction: close, titleView: AnyView(
                HStack {
                    HStack(){
                        Image.miamImage(icon: .ideeRepas)
                            .resizable()
                            .scaledToFill()
                            .frame(width:24, height:24)
                        Text(RecipeCardText.sharedInstance.recipeFlag)
                            .miamFontStyle(style: MiamFontStyleProvider.sharedInstance.bodyMediumStyle)
                    }.padding(.horizontal,16)
                        .padding(.vertical,4)
                        .background(Color.miamColor(.musterd))
                        .cornerRadius(8).rotationEffect(Angle(degrees: -2.0))
                    if (showTitleInHeader) {
                        Text(viewModel.recipe?.attributes?.title ?? "")
                            .miamFontStyle(style: MiamFontStyleProvider.sharedInstance.titleMediumStyle)
                            .foregroundColor(Color.miamColor(.black))
                            .padding(.horizontal, Dimension.sharedInstance.lPadding)
                            .frame( alignment: .topLeading)
                            .lineLimit(1)
                    }
                }
            ))
            
            ScrollView {
                if let recipe = viewModel.recipe {
                    if let template = Template.sharedInstance.recipeDetailInfosTemplate {
                        template(recipe)
                    } else {
                        VStack {
                            VStack {
                                VStack {
                                    RecipeDetailsHeaderView(mediaURL: viewModel.recipe?.attributes?.mediaUrl,
                                                            title: viewModel.recipe?.attributes?.title ?? "",
                                                            difficulty: Int(truncating: viewModel.recipe?.attributes?.difficulty ?? 1),
                                                            totalTime: viewModel.recipe?.totalTime ?? "10 min",
                                                            showTitleInHeader: $showTitleInHeader,
                                                            isLikeEnabled: viewModel.isLikeEnabled,
                                                            recipeId: recipeId ?? recipe.id)
                                }
                                
                                RecipeTimeView(preparationTime: viewModel.recipe!.preparationTimeIos,
                                               cookingTime: viewModel.recipe!.cookingTimeIos,
                                               restingTime: viewModel.recipe!.restingTimeIos)
                            }
                            
                            if let ingredients = viewModel.recipe?.relationships?.ingredients?.data {
                                RecipeDetailsIngredientsView(ingredients: ingredients,
                                                             recipeGuests: Int(viewModel.recipe?.attributes?.numberOfGuests ?? 0),
                                                             currentGuests: Int(viewModel.state?.guest ?? 0),
                                                             updateGuestsAction: { newGuest in viewModel.updateGuest(nbGuest:Int32(newGuest)) }
                                )
                            }
                            
                            RecipeDetailsStepsView(steps: viewModel.sortedSteps)
                        }
                    }
                }
            }.coordinateSpace(name: "scroll")
            
            if (showFooter) {
                RecipeDetailsFooter(recipeVM: viewModel)
            }
        }.onAppear(perform: {
            if (recipeId != nil) {
                viewModel.fetchRecipe(recipeId: self.recipeId!)
            }
        }
        )
    }
}

struct ViewOffsetKey: PreferenceKey {
    typealias Value = CGFloat
    static var defaultValue = CGFloat.zero
    static func reduce(value: inout Value, nextValue: () -> Value) {
        value += nextValue()
    }
}
