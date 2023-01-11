//
//  CatalogView.swift
//  MiamIOSFramework
//
//  Created by Vincent Kergonna on 23/06/2022.
//

import Foundation
import miamCore
import SwiftUI


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
    private let routeService = RouteServiceInstance.shared.instance
    public var willNavigateTo: ((CatalogContent, String, CatalogVM) -> Void)?
    
    public init(usesPreferences: Bool = false, closeCatalogAction: (() -> Void)? = nil,
                recipesListColumns: Int = 1, recipesListSpacing: CGFloat = 12, recipeCardHeight: CGFloat = 400,
                willNavigateTo: ((CatalogContent, String, CatalogVM) -> Void)? = nil) {
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
                willNavigateTo: ((CatalogContent, String, CatalogVM) -> Void)? = nil) {
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
                willNavigateTo: ((CatalogContent, String, CatalogVM) -> Void)? = nil) {
        self.init(usesPreferences: usesPreferences, closeCatalogAction: closeCatalogAction, recipesListColumns: recipesListColumns, recipesListSpacing: recipesListSpacing, recipeCardHeight: recipeCardHeight, willNavigateTo: willNavigateTo)
        catalog = catalogViewModel
    }

    public var body: some View {
        VStack(alignment: .center, spacing: 0.0) {
            CatalogViewHeader(closeCatalogAction: closeCatalogAction)
                .frame(height: catalog.content == .categoriesList ? 60.0 : 0.0)

            CatalogToolbarView(showBackButton: (catalog.content != .categoriesList),
                               favoritesFilterActive: showingFavorites, useFilters: true, usePreferences: usesPreferences) {
                if let willNavigateTo {
                    willNavigateTo(.categoriesList, "Idées repas", catalog)
                }
                catalog.goToCategoriesList()
                showingFavorites = false
                headerHeight = 50.0
            } filtersTapped: {
                catalog.openFilter()
                showingFilters = true
            } searchTapped: {
                catalog.openSearch()
                showingSearch = true
            } favoritesTapped: {
                if let willNavigateTo {
                    willNavigateTo(.favorite, "Favoris", catalog)
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
                        recipesListColumns: recipesListColumns,
                        recipesListSpacing: recipesListSpacing,
                        recipeCardHeight: recipeCardHeight,
                        packages: catalog.packages,
                        content: catalog.content,
                        showingPackageRecipes: $showingPackageRecipes,
                        showingFavorites: $showingFavorites,
                        headerHeight: $headerHeight,
                        browseCatalogAction: {
                            if let willNavigateTo {
                                willNavigateTo(.categoriesList, MiamText.sharedInstance.mealIdeas, catalog)
                            }
                            catalog.goToCategoriesList()
                        }, navigateToRecipeAction: { package in	
                            if let willNavigateTo {
                                willNavigateTo(.categoriesList, package.attributes?.title ?? "", catalog)
                            }
                            catalog.goToCategory(categoryId: package.id, categoryTitle: package.attributes?.title ?? "", subtitle: package.subtitle)
                        },
                        categoryId: catalog.currentState.openedCategoryId,
                        categoryTitle: catalog.currentState.openedCategoryTitle,
                        subtitle: catalog.currentState.subtitle ?? ""),
                    loadingView: CatalogLoadingView(loadingText: MiamText.sharedInstance.simmering),
                    emptyView: CatalogEmptyView())
                        .frame(maxWidth: .infinity, maxHeight: .infinity)
            }
        }.sheet(isPresented: $showingSearch, onDismiss: {
            showingSearch = false
        }) {
            CatalogSearchView(catalog: catalog, close: {
                showingSearch = false
            }) {
                showingSearch = false
                catalog.onSimpleSearch(content: CatalogContent.wordSearch)
            }
        }.sheet(isPresented: $showingFilters, onDismiss: {
            showingFilters = false
        }) {
            CatalogFiltersView() {
                showingFilters = false
                catalog.onSimpleSearch(content: CatalogContent.filterSearch)
            } close: {
                showingFilters = false
            }
        }.sheet(isPresented: $showingPreferences, onDismiss: {
            routeService.onCloseDialog()
            showingPreferences = false
        }) {
            CatalogPreferencesView {
                routeService.onCloseDialog()
                showingPreferences = false
            }
        }
    }
}

@available(iOS 14, *)
internal struct CatalogSuccessView: View {
    let packages: [CatalogPackage]
    let catalogContent: CatalogContent
    let categoryId:String
    let categoryTitle :String
    var categorySubtitle :String? = nil
    @Binding var showingPackageRecipes: Bool
    @Binding var showingFavorites: Bool
    @Binding var headerHeight: Double
    let browseCatalogAction: () -> Void
    let navigateToRecipeAction: (Package) -> Void
    let recipesListColumns : Int
    let recipesListSpacing: CGFloat
    let recipeCardHeight: CGFloat
    
    init(recipesListColumns: Int, recipesListSpacing: CGFloat, recipeCardHeight: CGFloat, packages: [CatalogPackage], content: CatalogContent, showingPackageRecipes: Binding<Bool>, showingFavorites: Binding<Bool>,
         headerHeight: Binding<Double>,
         browseCatalogAction: @escaping () -> Void,
         navigateToRecipeAction: @escaping (Package) -> Void,
         categoryId: String,
         categoryTitle : String,
         subtitle: String? = nil) {
        self.packages = packages
        self.catalogContent = content
        _showingPackageRecipes = showingPackageRecipes
        _showingFavorites = showingFavorites
        _headerHeight = headerHeight
        self.browseCatalogAction = browseCatalogAction
        self.navigateToRecipeAction = navigateToRecipeAction
        self.recipesListColumns = recipesListColumns
        self.recipesListSpacing = recipesListSpacing
        self.recipeCardHeight = recipeCardHeight
        self.categoryId = categoryId
        self.categoryTitle = categoryTitle
        if let catSubtitle = subtitle { self.categorySubtitle = catSubtitle }
    }

    var body: some View {
        switch catalogContent {
            case .categoriesList:
                if(packages.isEmpty){
                    CatalogRecipePageNoResultsView( browseCatalogAction: {} ).frame(maxHeight: .infinity)
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
            case .wordSearch:
            let title = "\(MiamText.sharedInstance.prefixWordSearchTitle) \"\( FilterViewModelInstance.shared.instance.currentState.searchString ?? "" )\""
            RecipesView(title: title, recipesListColumns: recipesListColumns, recipeListSpacing: recipesListSpacing, recipeCardHeight: recipeCardHeight, browseCatalogAction: {
                browseCatalogAction()},  showingFavorites: showingFavorites,specialTitleTemplate:  Template.sharedInstance.recipesListSearchTitleTemplate)
            case .filterSearch:
                RecipesView(title: MiamText.sharedInstance.filterSearchTitle , recipesListColumns: recipesListColumns, recipeListSpacing: recipesListSpacing, recipeCardHeight: recipeCardHeight, browseCatalogAction: {
                        browseCatalogAction()},  showingFavorites: showingFavorites)
        case .category: RecipesView(categoryId :categoryId,categoryTitle: categoryTitle,categorySubtitle: categorySubtitle,recipesListColumns: recipesListColumns, recipeListSpacing: recipesListSpacing, recipeCardHeight: recipeCardHeight, browseCatalogAction: {
                browseCatalogAction()},  showingFavorites: showingFavorites)
            case .favorite: RecipesView(title: MiamText.sharedInstance.favoriteTitle, recipesListColumns: recipesListColumns, recipeListSpacing: recipesListSpacing, recipeCardHeight: recipeCardHeight, browseCatalogAction: {
                    browseCatalogAction()},  showingFavorites: showingFavorites)
            default:  HStack{}
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
                if let subtitle = package.subtitle {
                    Text(subtitle)
                        .miamFontStyle(style: MiamFontStyleProvider.sharedInstance.titleSmallStyle)
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
                
                if (!showBackButton) {
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
