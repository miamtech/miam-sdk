//
//  CatalogView.swift
//  MiamIOSFramework
//
//  Created by Vincent Kergonna on 23/06/2022.
//

import Foundation
import miamCore
import SwiftUI

public enum CatalogViewDestination {
    case categories
    case recipesList
    case favorites
}

@available(iOS 14, *)
public struct CatalogEmptyView: View {
    public var body: some View {
        HStack{}
    }
}

@available(iOS 14, *)
public struct CatalogView: View {
    @ObservedObject public var catalog: CatalogVM
    @SwiftUI.State private var showingFilters = false
    @SwiftUI.State private var showingSearch = false
    @SwiftUI.State private var showingFavorites = false
    @SwiftUI.State private var showingPreferences = false
    @SwiftUI.State private var showingPackageRecipes = false

    @SwiftUI.State private var headerHeight = 50.0
    
    let closeCatalogAction: (() -> Void)?
    private var usesPreferences = false
    private let recipesListColumns : Int
    private let recipesListSpacing: CGFloat
    private let recipeCardHeight: CGFloat
    public var willNavigateTo: ((CatalogViewDestination, String, CatalogVM) -> Void)?
    
    public init(usesPreferences: Bool = false, closeCatalogAction: (() -> Void)? = nil,
                recipesListColumns: Int = 1, recipesListSpacing: CGFloat = 12, recipeCardHeight: CGFloat = 400,
                willNavigateTo: ((CatalogViewDestination, String, CatalogVM) -> Void)? = nil) {
        self.catalog = CatalogVM()
        self.usesPreferences = usesPreferences
        self.closeCatalogAction = closeCatalogAction
        self.recipesListColumns = recipesListColumns
        self.recipesListSpacing = recipesListSpacing
        self.recipeCardHeight = recipeCardHeight

        self.willNavigateTo = willNavigateTo
    }
    
    public init(categoryId: String, title: String, usesPreferences: Bool = false, closeCatalogAction: (() -> Void)? = nil,
                recipesListColumns: Int = 1, recipesListSpacing: CGFloat = 12, recipeCardHeight: CGFloat = 400,
                willNavigateTo: ((CatalogViewDestination, String, CatalogVM) -> Void)? = nil) {
        self.catalog = CatalogVM(categoryID: categoryId, title: title)
        self.closeCatalogAction = closeCatalogAction
        self.recipesListColumns = recipesListColumns
        self.recipesListSpacing = recipesListSpacing
        self.recipeCardHeight = recipeCardHeight

        self.willNavigateTo = willNavigateTo
    }
    
    public init(catalogViewModel: CatalogVM,
                usesPreferences: Bool = false , closeCatalogAction: (() -> Void)? = nil,
                recipesListColumns: Int = 1, recipesListSpacing: CGFloat = 12, recipeCardHeight: CGFloat = 400,
                willNavigateTo: ((CatalogViewDestination, String, CatalogVM) -> Void)? = nil) {
        self.init(usesPreferences: usesPreferences, closeCatalogAction: closeCatalogAction, recipesListColumns: recipesListColumns, recipesListSpacing: recipesListSpacing, recipeCardHeight: recipeCardHeight, willNavigateTo: willNavigateTo)
        catalog = catalogViewModel
    }

    public var body: some View {
        VStack(alignment: .center, spacing: 0.0) {
            CatalogViewHeader(closeCatalogAction: closeCatalogAction)
                .frame(height: catalog.content == .categories ? 60.0 : 0.0)

            CatalogToolbarView(showBackButton: (catalog.content != .categories),
                               favoritesFilterActive: showingFavorites, useFilters: true, usePreferences: usesPreferences) {
                catalog.setEvent(event: CatalogContractEvent.GoToDefault())
                showingFavorites = false
                headerHeight = 50.0
            } filtersTapped: {
                catalog.setEvent(event: CatalogContractEvent.ToggleFilter())
                showingFilters = true
            } searchTapped: {
                catalog.setEvent(event: CatalogContractEvent.ToggleSearch())
                showingSearch = true
            } favoritesTapped: {
                if let willNavigateTo {
                    willNavigateTo(.favorites, "Favoris", catalog)
                }
                catalog.setEvent(event: CatalogContractEvent.GoToFavorite())
                showingFavorites = true
            } preferencesTapped: {
                showingPreferences = true
            }
            if let catalogState = catalog.state {
                ManagementResourceState<NSArray, CatalogSuccessView, CatalogLoadingView, CatalogEmptyView>(
                    resourceState: catalogState.categories,
                    successView: CatalogSuccessView(
                        recipeListPageViewModel: catalog.recipePageViewModel,
                        recipesListColumns: recipesListColumns,
                        recipesListSpacing: recipesListSpacing,
                        recipeCardHeight: recipeCardHeight,
                        packages: catalog.packages,
                        content: catalog.content,
                        showingPackageRecipes: $showingPackageRecipes,
                        showingFavorites: $showingFavorites,
                        headerHeight: $headerHeight,
                        searchString: catalog.searchString,
                        browseCatalogAction: {
                            if let willNavigateTo {
                                willNavigateTo(.categories, MiamText.sharedInstance.mealIdeas, catalog)
                            }
                            catalog.setEvent(event: CatalogContractEvent.GoToDefault())
                        }, navigateToRecipeAction: { package in
                            if let willNavigateTo {
                                willNavigateTo(.recipesList, package.attributes?.title ?? "", catalog)
                            }
                            catalog.setEvent(event: CatalogContractEvent.GoToRecipeListFromCategory(categoryId: package.id,title: package.attributes?.title ?? ""))
                        }),
                    loadingView: CatalogLoadingView(loadingText: MiamText.sharedInstance.simmering),
                    emptyView: CatalogEmptyView())
                        .frame(maxWidth: .infinity, maxHeight: .infinity)
            }
        }.sheet(isPresented: $showingSearch, onDismiss: {
            catalog.setEvent(event: CatalogContractEvent.ToggleSearch())
        }) {
            CatalogSearchView(catalog: catalog, close: {
                showingSearch = false
            }) {
                showingSearch = false
                catalog.setEvent(event: CatalogContractEvent.GoToRecipeList())
            }
        }.sheet(isPresented: $showingFilters, onDismiss: {
            catalog.setEvent(event: CatalogContractEvent.ToggleFilter())
        }) {
            CatalogFiltersView() {
                showingFilters = false
                 catalog.setEvent(event: CatalogContractEvent.GoToRecipeList())
            } close: {
                showingFilters = false
            }
        }.sheet(isPresented: $showingPreferences, onDismiss: {
            catalog.setEvent(event: CatalogContractEvent.TogglePreference())
        }) {
            CatalogPreferencesView {
                showingPreferences = false
            }
        }
    }
}

@available(iOS 14, *)
internal struct CatalogSuccessView: View {
    let recipeListPageViewModel: RecipeListPageViewModel?
    let packages: [CatalogPackage]
    let catalogContent: CatalogModelContent
    @Binding var showingPackageRecipes: Bool
    @Binding var showingFavorites: Bool
    @Binding var headerHeight: Double
    let searchString: String
    let browseCatalogAction: () -> Void
    let navigateToRecipeAction: (Package) -> Void
    let recipesListColumns : Int
    let recipesListSpacing: CGFloat
    let recipeCardHeight: CGFloat
    
    init(recipeListPageViewModel: RecipeListPageViewModel?,recipesListColumns: Int, recipesListSpacing: CGFloat, recipeCardHeight: CGFloat, packages: [CatalogPackage], content: CatalogModelContent, showingPackageRecipes: Binding<Bool>, showingFavorites: Binding<Bool>,
         headerHeight: Binding<Double>, searchString: String,
         browseCatalogAction: @escaping () -> Void,
         navigateToRecipeAction: @escaping (Package) -> Void) {
        self.recipeListPageViewModel = recipeListPageViewModel
        self.packages = packages
        self.catalogContent = content
        _showingPackageRecipes = showingPackageRecipes
        _showingFavorites = showingFavorites
        _headerHeight = headerHeight
        self.searchString = searchString
        self.browseCatalogAction = browseCatalogAction
        self.navigateToRecipeAction = navigateToRecipeAction
        self.recipesListColumns = recipesListColumns
        self.recipesListSpacing = recipesListSpacing
        self.recipeCardHeight = recipeCardHeight
    }

    var body: some View {
        if let template = Template.sharedInstance.catalogSuccessViewTemplate {
            template(recipeListPageViewModel, packages, catalogContent, $showingPackageRecipes,
                     $showingFavorites, $headerHeight, searchString, browseCatalogAction, navigateToRecipeAction)
        } else {
            if case .categories = catalogContent {
                if(packages.isEmpty){
                    CatalogRecipePageNoResultsView(searchString:"avec vos préférences", browseCatalogAction: {} ).frame(maxHeight: .infinity)
                }  else {
                ScrollView {
                        VStack {
                            ForEach(packages) { package in
                                CatalogPackageRow(package: package, recipeCardHeight: recipeCardHeight) { package in
                                    navigateToRecipeAction(package.package)
                                }
                            }
                        }.padding([.top], Dimension.sharedInstance.lPadding)
                    }
                }
            } else {
                if let recipeListPageViewModel  = recipeListPageViewModel {
                    RecipesView(recipesListPageModel: recipeListPageViewModel, recipesListColumns: recipesListColumns, recipeListSpacing: recipesListSpacing, recipeCardHeight: recipeCardHeight, browseCatalogAction: {
                        browseCatalogAction()
                    }, searchString: searchString, showingFavorites: showingFavorites)
                }
            }
        }
    }
}

@available(iOS 14, *)
public struct CatalogPackageRow: View {
    let package: CatalogPackage
    let recipeCardHeight: CGFloat
    let showRecipes: (CatalogPackage) -> Void
    
    public init(package: CatalogPackage, recipeCardHeight: CGFloat, showRecipes: @escaping (CatalogPackage) -> Void) {
        self.package = package
        self.recipeCardHeight = recipeCardHeight
        self.showRecipes = showRecipes
    }
    
    public var body: some View {
        if (Template.sharedInstance.catalogPackageRowTemplate != nil) {
            Template.sharedInstance.catalogPackageRowTemplate!(package, showRecipes)
        } else {
            VStack(alignment: .leading) {
                Text(package.title)
                    .miamFontStyle(style: MiamFontStyleProvider.sharedInstance.titleMediumStyle) // TODO: check mockup
                    .padding(Dimension.sharedInstance.mlPadding)
                if package.subtitle {
                    Text(package.subtitle)
                        .miamFontStyle(style: MiamFontStyleProvider.sharedInstance.TitleSmallFontStyle)
                        .padding(Dimension.sharedInstance.mlPadding)
                }
                HStack {
                    Spacer()
                    Button {
                        showRecipes(package)
                    } label: {
                        Text(MiamText.sharedInstance.showAll)
                            .underline()
                            .miamFontStyle(style: MiamFontStyleProvider.sharedInstance.bodyBigStyle)
                            .foregroundColor(Color.miamColor(.primaryText))
                            .padding([.trailing], 16.0).padding([.top], 8)
                    }
                }
                ScrollView(.horizontal) {
                    LazyHStack {
                        ForEach(package.recipes, id: \.self) { recipe in
                                RecipeCardView(recipe: recipe, showMealIdeaTag: false, recipeCardHeight: recipeCardHeight).frame(width: 300).padding(Dimension.sharedInstance.mlPadding)
                        }
                    }
                }
            }
        }
    }
}

@available(iOS 14, *)
internal struct CatalogViewHeader: View {
    let closeCatalogAction: (() -> Void)?
    var body: some View {
        if (Template.sharedInstance.catalogViewHeaderTemplate != nil) {
            Template.sharedInstance.catalogViewHeaderTemplate!(closeCatalogAction)
        } else {
            HStack {
                Image.miamImage(icon: .ideeRepas)
                    .renderingMode(.template)
                    .foregroundColor(.white)
                    .frame(width: 30, height: 30)

                VStack (alignment: .leading) {
                    Spacer()
                    Text(MiamText.sharedInstance.mealIdeas)
                        .miamFontStyle(style: MiamFontStyleProvider.sharedInstance.titleStyle)
                        .foregroundColor(.white)
                    Image.miamImage(icon: .yellowUnderline)
                        .position(x: 145.0, y: -12.0)
                }
                Spacer()
            }
            .padding(EdgeInsets(top: 16, leading: 11, bottom: 0, trailing: 11))
            .frame(maxWidth: .infinity, minHeight: 0.0)
            .background(Color.miamColor(.primaryDark))
        }
    }
}

@available(iOS 14, *)
internal struct CatalogToolbarView: View {
    let showBackButton: Bool
    let favoritesFilterActive: Bool
    let useFilters: Bool
    let usePreferences: Bool
    let backTapped: () -> Void
    let filtersTapped: () -> Void
    let searchTapped: () -> Void
    let favoritesTapped: () -> Void
    let preferencesTapped: () -> Void
    
    
    var body: some View {
        if (Template.sharedInstance.catalogViewToolbarTemplate != nil) {
            Template.sharedInstance.catalogViewToolbarTemplate!(showBackButton, favoritesFilterActive, backTapped, filtersTapped, searchTapped, favoritesTapped, preferencesTapped)
        } else {
            HStack(spacing: 16.0) {
                if (showBackButton) {
                    Button {
                        backTapped()
                    } label: {
                        Image.miamImage(icon: .back)
                    }.frame(width: 40, height: 40)
                    Spacer()
                }
                Button {
                    searchTapped()
                } label: {
                    Image.miamImage(icon: .search)
                        .renderingMode(.template)
                        .foregroundColor(Color.miamColor(.primary))
                }.frame(width: 40, height: 40).background(Color.white).clipShape(Circle())
                if (!showBackButton) {
                    Spacer()
                }

                if (useFilters) {
                    Button {
                        filtersTapped()
                    } label: {
                        Image.miamImage(icon: .filters)
                            .renderingMode(.template)
                            .foregroundColor(Color.miamColor(.primary))
                    }.frame(width: 40, height: 40).background(Color.white).clipShape(Circle())
                }
                
                if (usePreferences) {
                    Button {
                        preferencesTapped()
                    } label: {
                        Image.miamImage(icon: .preferences)
                            .renderingMode(.template)
                            .foregroundColor(Color.miamColor(.primary))
                    }.frame(width: 40, height: 40).background(Color.white).clipShape(Circle())
                }
                
                if (!favoritesFilterActive) {
                    if (showBackButton) {
                        Button {
                            favoritesTapped()
                        } label: {
                            Image.miamImage(icon: .heart)
                                .renderingMode(.template)
                                .foregroundColor(Color.miamColor(.primary))
                        }
                        .frame(width: 40, height: 40)
                        .background(Color.white)
                        .clipShape(Circle())
                    } else {
                        Button {
                            favoritesTapped()
                        } label: {
                            Image.miamImage(icon: .heart)
                                .renderingMode(.template)
                                .foregroundColor(.white)
                            Text(MiamText.sharedInstance.myMealIdeas)
                                .miamFontStyle(style: MiamFontStyleProvider.sharedInstance.bodyBigStyle)
                                .foregroundColor(.white)
                        }
                        .padding(EdgeInsets(top: 10, leading: 20, bottom: 10, trailing: 20))
                        .overlay(Capsule().stroke(.white, lineWidth: 1.0))
                    }
                }
            }.padding(EdgeInsets(top: Dimension.sharedInstance.mlPadding,
                                 leading: Dimension.sharedInstance.mlPadding, bottom: 16,
                                 trailing: Dimension.sharedInstance.mlPadding))
            .background(Color.miamColor(.primaryDark))
        }
    }
}

@available(iOS 14, *)
struct CatalogView_Preview: PreviewProvider {
    static var previews: some View {
        CatalogView()
    }
}
