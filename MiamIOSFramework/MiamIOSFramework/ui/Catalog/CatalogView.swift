//
//  CatalogView.swift
//  MiamIOSFramework
//
//  Created by Vincent Kergonna on 23/06/2022.
//

import Foundation
import shared
import SwiftUI


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
        VStack(spacing: 0.0) {
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
                }
                Spacer()
            } else {
                let model = RecipeListPageVM(model: catalog.recipePageViewModel!)
                CatalogRecipesPageSuccessView(viewModel: model)
                Spacer()
            }
        }.popover(isPresented: $catalog.filterOpen) {
            CatalogFiltersView(catalogFiltersModel: CatalogFilterVM(model: catalog.filtersViewModel!)) {
                self.catalog.setEvent(event: CatalogContractEvent.OnFilterValidation())
            } close: {
                self.catalog.setEvent(event: CatalogContractEvent.ToggleFilter())
            }
        }.popover(isPresented: $catalog.searchOpen) {
            CatalogSearchView(catalog: catalog)
        }
    }
}

internal struct CatalogPackageRow: View {
    let package: CatalogPackage
    let showRecipes: (CatalogPackage) -> Void
    var body: some View {
        VStack(alignment: .leading) {
            Text(package.title).font(.system(size: 18.0, weight: .bold, design: .default)).padding(Dimension.sharedInstance.mlPadding)
            HStack {
                Spacer()
                Button {
                    showRecipes(package)
                } label: {
                    Text("Tout voir").foregroundColor(MiamColor.sharedInstance.primaryText).underline().padding([.trailing], 16.0).padding([.top], 8)
                }
            }
            ScrollView(.horizontal) {
                LazyHStack {
                    ForEach(package.recipes, id: \.self) { recipe in
                        RecipeCardView(recipeId: recipe.id).frame(width: 300).padding(Dimension.sharedInstance.mlPadding)
                    }
                }
            }
        }
    }
}

internal struct CatalogViewHeader: View {
    var body: some View {
        HStack {
            Image("ideerepas", bundle: Bundle(for: RecipeCardVM.self))
                .renderingMode(.template)
                .foregroundColor(.white)
                .frame(width: 30, height: 30)

            VStack (alignment: .leading) {
                Spacer()
                Text("Idées repas en 1 clic")
                    .foregroundColor(.white)
                    .font(.system(size: 20)).bold()
                Image("yellow_underline", bundle: Bundle(for: RecipeCardVM.self))
                    .position(x: 145.0, y: -12.0)
            }
            Spacer()
        }
        .padding(EdgeInsets(top: 16, leading: 11, bottom: 0, trailing: 11))
        .frame(maxWidth: .infinity, minHeight: 0.0)
        .background(MiamColor.sharedInstance.backgroundDark)
    }
}

internal struct CatalogViewToolbar: View {
    let myIdeas = "Mes idées repas"

    let showBackButton: Bool
    let favoritesFilterActive: Bool
    let backTapped: () -> Void
    let filtersTapped: () -> Void
    let searchTapped: () -> Void
    let favoritesTapped: () -> Void

    var body: some View {
        HStack(spacing: 16.0) {
            if (showBackButton) {
                Button {
                    backTapped()
                } label: {
                    Image("back", bundle: Bundle(for: RecipeCardVM.self))
                }.frame(width: 40, height: 40)
                Spacer()
            }
            Button {
                searchTapped()
            } label: {
                Image("search", bundle: Bundle(for: RecipeCardVM.self))
                    .renderingMode(.template)
                    .foregroundColor(MiamColor.sharedInstance.primary)
            }.frame(width: 40, height: 40).background(Color.white).clipShape(Circle())
            if (!showBackButton) {
                Spacer()
            }

            Button {
                filtersTapped()
            } label: {
                Image("filters", bundle: Bundle(for: RecipeCardVM.self))
                    .renderingMode(.template)
                    .foregroundColor(MiamColor.sharedInstance.primary)
            }.frame(width: 40, height: 40).background(Color.white).clipShape(Circle())

            if (!favoritesFilterActive) {
                if (showBackButton) {
                    Button {
                        favoritesTapped()
                    } label: {
                        Image("heart", bundle: Bundle(for: RecipeCardVM.self))
                            .renderingMode(.template)
                            .foregroundColor(MiamColor.sharedInstance.primary)
                    }
                    .frame(width: 40, height: 40)
                    .background(Color.white)
                    .clipShape(Circle())
                } else {
                    Button {
                        favoritesTapped()
                    } label: {
                        Image("heart", bundle: Bundle(for: RecipeCardVM.self))
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
        .background(MiamColor.sharedInstance.backgroundDark)
    }
}

struct CatalogView_Preview: PreviewProvider {
    static var previews: some View {
        CatalogView()
    }
}
