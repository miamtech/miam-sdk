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
    @SwiftUI.State private var showingPackageRecipes = false

    @SwiftUI.State private var headerHeight = 80.0
    public init() {

    }

    public var body: some View {
        VStack {
            CatalogViewHeader()
                .frame(height: headerHeight)
                .animation(.default, value: headerHeight)

            CatalogViewToolbar {
                catalog.setEvent(event: CatalogContractEvent.ToggleFilter())
                showingFilters = true
            } searchTapped: {
                catalog.setEvent(event: CatalogContractEvent.ToggleSearch())
                showingSearch = true
            } favoritesTapped: {
                catalog.setEvent(event: CatalogContractEvent.GoToFavorite())
            }
            if case .categories = catalog.content {
                ScrollView {
                    ForEach(catalog.packages) { package in
                        CatalogPackageRow(package: package) { package in
                            catalog.setEvent(event: CatalogContractEvent.GoToRecipeListFromCategory(category: package.package))
                            showingPackageRecipes = true
                                headerHeight = 0.0
                        }
                    }
                }
                Spacer()
            } else {
                CatalogRecipesPageSuccessView(recipesListPageModel: RecipeListPageVM(model: catalog.recipePageViewModel!))
            }
        }.popover(isPresented: $catalog.filterOpen) {
            CatalogFiltersView()
        }.popover(isPresented: $catalog.searchOpen) {
        }
    }
}

internal struct CatalogPackageRow: View {
    let package: CatalogPackage
    let showRecipes: (CatalogPackage) -> Void
    var body: some View {
        VStack(alignment: .leading) {
            Text(package.title).font(.system(size: 18.0, weight: .bold, design: .default)).padding([.leading], 16.0)
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
                        RecipeCardView(recipeId: recipe.id)
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
        .padding(EdgeInsets(top: 17, leading: 11, bottom: 17, trailing: 11))
        .frame(maxWidth: .infinity, maxHeight: 80.0)
        .background(MiamColor.sharedInstance.backgroundDark)
    }
}

internal struct CatalogViewToolbar: View {
    let myIdeas = "Mes idées repas"
    let filtersTapped: () -> Void
    let searchTapped: () -> Void

    var body: some View {
        HStack {
            Spacer()
            Button {
                searchTapped()
            } label: {
                Image("search", bundle: Bundle(for: RecipeCardVM.self))
            }.frame(width: 40, height: 40).background(MiamColor.sharedInstance.primary).clipShape(Circle())
            Spacer()
            Button {
                filtersTapped()
            } label: {
                Image("filters", bundle: Bundle(for: RecipeCardVM.self))
            }.frame(width: 40, height: 40).background(MiamColor.sharedInstance.primary).clipShape(Circle())
            Spacer()
            Button {
                favoritesTapped()
            } label: {
                Image("Like", bundle: Bundle(for: RecipeCardVM.self))
                Text(myIdeas).foregroundColor(MiamColor.sharedInstance.primary)
            }
            .padding(EdgeInsets(top: 9, leading: 20, bottom: 9, trailing: 20))
            .overlay(Capsule().stroke(MiamColor.sharedInstance.primary, lineWidth: 1.0))
            Spacer()
        }.padding(10)
    }
}

struct CatalogView_Preview: PreviewProvider {
    static var previews: some View {
        CatalogView()
    }
}
