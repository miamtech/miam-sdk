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
    @ObservedObject var catalog: CatalogVM = CatalogVM()
    @SwiftUI.State private var showingFilters = false
    @SwiftUI.State private var showingSearch = false
    @SwiftUI.State private var showingFavorites = false
    @SwiftUI.State private var showingPackageRecipes = false

    @SwiftUI.State private var headerHeight = 50.0
    public init() {

    }

    public var body: some View {
        if (Template.sharedInstance.catalogViewTemplate != nil) {
            Template.sharedInstance.catalogViewTemplate!(catalog)
        } else {
            VStack(alignment: .center, spacing: 0.0) {
                CatalogViewHeader()
                    .frame(height: catalog.content == .categories ? 60.0 : 0.0)

                CatalogViewToolbar(showBackButton: (catalog.content != .categories),
                                   favoritesFilterActive: showingFavorites) {
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
                    catalog.setEvent(event: CatalogContractEvent.GoToFavorite())
                    showingFavorites = true
                }
                if let catalogState = catalog.state {
                    ManagementResourceState<NSArray, CatalogSuccessView, CatalogLoadingView, CatalogEmptyView>(resourceState: catalogState.categories,
                                                                                         successView: CatalogSuccessView(catalog: catalog, showingPackageRecipes: $showingPackageRecipes, headerHeight: $headerHeight),
                                                                                                               loadingView: CatalogLoadingView(loadingText: MiamText.sharedInstance.simmering),
                                                                                                               emptyView: CatalogEmptyView()).padding([.top], Dimension.sharedInstance.lPadding)
                                                                                            .frame(maxWidth: .infinity, maxHeight: .infinity)
                }
            }.sheet(isPresented: $showingSearch, onDismiss: {
                catalog.setEvent(event: CatalogContractEvent.ToggleSearch())
            }) {
                CatalogSearchView(catalog: catalog, close: {
                    showingSearch = false
                })
            }.sheet(isPresented: $showingFilters, onDismiss: {
                catalog.setEvent(event: CatalogContractEvent.ToggleFilter())
            }) {
                CatalogFiltersView(catalogFiltersModel: CatalogFilterVM(model: catalog.filtersViewModel!)) {
                    self.catalog.setEvent(event: CatalogContractEvent.OnFilterValidation())
                } close: {
                    showingFilters = false
                }
            }
        }
    }
}

@available(iOS 14, *)
internal struct CatalogSuccessView: View {
    @ObservedObject var catalog: CatalogVM
    @ObservedObject var recipeListPageModel: RecipeListPageVM

    init(catalog: CatalogVM, showingPackageRecipes: Binding<Bool>, headerHeight: Binding<Double>) {
        self.catalog = catalog
        self.recipeListPageModel = RecipeListPageVM(model: catalog.recipePageViewModel!)
        _showingPackageRecipes = showingPackageRecipes
        _headerHeight = headerHeight
    }

    @Binding var showingPackageRecipes: Bool
    @Binding var headerHeight: Double
    var body: some View {
        if case .categories = catalog.content {
            ScrollView {
                VStack {
                    ForEach(catalog.packages) { package in
                        CatalogPackageRow(package: package) { package in
                            catalog.setEvent(event: CatalogContractEvent.GoToRecipeListFromCategory(category: package.package))
                            showingPackageRecipes = true
                            headerHeight = 0.0
                        }
                    }
                }
            }.padding([.top], Dimension.sharedInstance.lPadding)
        } else {
            if let modelState = recipeListPageModel.state {
                ManagementResourceState<NSArray, CatalogRecipesPageSuccessView, CatalogLoadingView, CatalogEmptyView>(resourceState: modelState.recipes,
                                                                                                successView: CatalogRecipesPageSuccessView(viewModel: recipeListPageModel, catalogViewModel: catalog),
                                                                                                loadingView: CatalogLoadingView(loadingText: MiamText.sharedInstance.simmering),
                                                                                                emptyView: CatalogEmptyView()).padding([.top], Dimension.sharedInstance.lPadding)
            }
        }
    }
}

@available(iOS 14, *)
internal struct CatalogPackageRow: View {
    let package: CatalogPackage
    let showRecipes: (CatalogPackage) -> Void
    var body: some View {
        if (Template.sharedInstance.catalogPackageRowTemplate != nil) {
            Template.sharedInstance.catalogPackageRowTemplate!(package, showRecipes)
        } else {
            VStack(alignment: .leading) {
                Text(package.title).font(.system(size: 18.0, weight: .bold, design: .default)).padding(Dimension.sharedInstance.mlPadding)
                HStack {
                    Spacer()
                    Button {
                        showRecipes(package)
                    } label: {
                        Text("Tout voir").foregroundColor(Color.miamColor(.primaryText)).underline().padding([.trailing], 16.0).padding([.top], 8)
                    }
                }
                ScrollView(.horizontal) {
                    LazyHStack {
                        ForEach(package.recipes, id: \.self) { recipe in
                            RecipeCardView(recipeId: recipe.id, showMealIdeaTag: false).frame(width: 300).padding(Dimension.sharedInstance.mlPadding)
                        }
                    }
                }
            }
        }
    }
}

@available(iOS 14, *)
internal struct CatalogViewHeader: View {
    var body: some View {
        if (Template.sharedInstance.catalogViewHeaderTemplate != nil) {
            Template.sharedInstance.catalogViewHeaderTemplate!
        } else {
            HStack {
                Image.miamImage(icon: .ideeRepas)
                    .renderingMode(.template)
                    .foregroundColor(.white)
                    .frame(width: 30, height: 30)

                VStack (alignment: .leading) {
                    Spacer()
                    Text("Idées repas en 1 clic")
                        .foregroundColor(.white)
                        .font(.system(size: 20)).bold()
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
internal struct CatalogViewToolbar: View {
    let myIdeas = "Mes idées repas"

    let showBackButton: Bool
    let favoritesFilterActive: Bool
    let backTapped: () -> Void
    let filtersTapped: () -> Void
    let searchTapped: () -> Void
    let favoritesTapped: () -> Void

    var body: some View {
        if (Template.sharedInstance.catalogViewToolbarTemplate != nil) {
            Template.sharedInstance.catalogViewToolbarTemplate!(showBackButton, favoritesFilterActive, backTapped, filtersTapped, searchTapped, favoritesTapped)
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

                Button {
                    filtersTapped()
                } label: {
                    Image.miamImage(icon: .filters)
                        .renderingMode(.template)
                        .foregroundColor(Color.miamColor(.primary))
                }.frame(width: 40, height: 40).background(Color.white).clipShape(Circle())

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
                            Text(myIdeas).foregroundColor(.white)
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
