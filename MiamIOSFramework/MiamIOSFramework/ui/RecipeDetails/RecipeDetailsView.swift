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

    public var showFooter = true
    public let showMealIdeaImage: Bool
    public var close: () -> Void
    public var navigateToPreview: () -> Void
    public var buy: () -> Void
    public var sponsorDetailsTapped: (Sponsor) -> Void
    @SwiftUI.State var showTitleInHeader = false
    @ObservedObject var viewModel: RecipeCardVM

    private var recipeTitle: String {
        var recipeTitle = ""

        if let title = viewModel.recipe?.attributes?.title, showTitleInHeader {
            recipeTitle = title
        }

        return recipeTitle
    }

    public init(recipeId: String, showFooter: Bool = true, showMealIdeaImage: Bool = true,
                sponsorDetailsTapped: @escaping (Sponsor) -> Void, close: @escaping () -> Void,
                navigateToPreview: @escaping () -> Void, buy: @escaping () -> Void) {
        self.recipeId = recipeId
        self.close = close
        self.navigateToPreview = navigateToPreview
        self.buy = buy
        viewModel = RecipeCardVM(routerVM: RouterOutletViewModel())
        self.showFooter = showFooter
        self.showMealIdeaImage = showMealIdeaImage
        self.sponsorDetailsTapped = sponsorDetailsTapped
    }

    public init(vmRecipe: RecipeCardVM, showFooter: Bool = true, showMealIdeaImage: Bool = true,
                sponsorDetailsTapped: @escaping (Sponsor) -> Void, close: @escaping () -> Void,
                navigateToPreview: @escaping () -> Void, buy: @escaping () -> Void) {
        self.viewModel = vmRecipe
        self.showFooter = showFooter
        self.close = close
        self.navigateToPreview = navigateToPreview
        self.buy = buy
        self.showMealIdeaImage = showMealIdeaImage
        self.sponsorDetailsTapped = sponsorDetailsTapped
    }

    public var body: some View {
        VStack(spacing: 0) {
            ScrollView {
                if let recipe = viewModel.recipe {
                    if recipe.isSponsored, let sponsors = recipe.relationships?.sponsors?.data {
                        ForEach(sponsors, id: \.id) { sponsor in
                            RecipeDetailsSponsorBanner(sponsor: sponsor) { sponsor in
                                sponsorDetailsTapped(sponsor)
                            }
                        }
                    }
                    if let template = Template.sharedInstance.recipeDetailInfosTemplate {
                        template(recipe)
                    } else {
                        VStack {
                            VStack {
                                VStack {
                                    RecipeDetailsHeaderView(mediaURL: recipe.attributes?.mediaUrl,
                                                            title: recipe.attributes?.title ?? "",
                                                            difficulty: Int(truncating: recipe.attributes?.difficulty ?? 1),
                                                            totalTime: recipe.totalTime,
                                                            showTitleInHeader: $showTitleInHeader,
                                                            isLikeEnabled: viewModel.isLikeEnabled,
                                                            recipeId: recipeId ?? recipe.id)
                                }

                                RecipeTimeView(preparationTime: recipe.preparationTimeIos,
                                               cookingTime: recipe.cookingTimeIos,
                                               restingTime: recipe.restingTimeIos)
                            }

                            if let ingredients = recipe.relationships?.ingredients?.data {
                                RecipeDetailsIngredientsView(ingredients: ingredients,
                                                             recipeGuests: Int(recipe.attributes?.numberOfGuests ?? 0),
                                                             currentGuests: Int(viewModel.state?.guest ?? 0),
                                                             guestUpdating: viewModel.guestUpdating,
                                                             updateGuestsAction: { newGuest in
                                                                viewModel.updateGuest(nbGuest: Int32(newGuest))
                                                            }
                                )
                            }

                            RecipeDetailsStepsView(steps: viewModel.sortedSteps)
                        }
                    }
                }
            }.coordinateSpace(name: "scroll")

            if showFooter {
                RecipeDetailsFooter(recipeVM: viewModel, buy: buy, goToPreview: navigateToPreview)
            }
        }
        .frame(maxHeight: .infinity)
        .toolbar {
            ToolbarItem(placement: .navigationBarLeading) {
                if showMealIdeaImage {
                    HStack {
                        Image.miamImage(icon: .ideeRepas)
                            .resizable()
                            .scaledToFill()
                            .frame(width: 24, height: 24)
                        Text(RecipeCardText.sharedInstance.recipeFlag)
                            .miamFontStyle(style: MiamFontStyleProvider.sharedInstance.bodyMediumStyle)
                    }.padding(.horizontal, 16)
                        .padding(.vertical, 4)
                        .background(Color.miamColor(.musterd))
                        .cornerRadius(8).rotationEffect(Angle(degrees: -2.0))
                }
            }
            ToolbarItem(placement: .principal) {
                Text(recipeTitle)
                    .miamFontStyle(style: MiamFontStyleProvider.sharedInstance.titleMediumStyle)
                    .foregroundColor(Color.miamColor(.black))
                    .padding(.horizontal, Dimension.sharedInstance.lPadding)
                    .frame( alignment: .topLeading)
                    .lineLimit(1)
            }
        }
        .onAppear(perform: {
            if let recipeId = self.recipeId {
                viewModel.fetchRecipe(recipeId: recipeId)
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

@available(iOS 14, *)
public struct RecipeDetailTitleBar: View {

    let showTitleInHeader: Bool
    let title: String

    public var body: some View {
        if let templateTitle = Template.sharedInstance.recipeDetailsTitleBarTemplate {
                templateTitle(showTitleInHeader, title)
        } else {
            HStack {
                HStack {
                    Image.miamImage(icon: .ideeRepas)
                        .resizable()
                        .scaledToFill()
                        .frame(width: 24, height: 24)
                    Text(RecipeCardText.sharedInstance.recipeFlag)
                        .miamFontStyle(style: MiamFontStyleProvider.sharedInstance.bodyMediumStyle)
                }.padding(.horizontal, 16)
                    .padding(.vertical, 4)
                    .background(Color.miamColor(.musterd))
                    .cornerRadius(8).rotationEffect(Angle(degrees: -2.0))
                if showTitleInHeader {
                    Text(title)
                        .miamFontStyle(style: MiamFontStyleProvider.sharedInstance.titleMediumStyle)
                        .foregroundColor(Color.miamColor(.black))
                        .padding(.horizontal, Dimension.sharedInstance.lPadding)
                        .frame( alignment: .topLeading)
                        .lineLimit(1)
                }
            }
        }
    }
}
