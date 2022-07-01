//
//  CatalogVM.swift
//  MiamIOSFramework
//
//  Created by Vincent Kergonna on 24/06/2022.
//

import Foundation
import shared

struct CatalogPackage: Identifiable {
    let package: Package

    public var id: String {
        return UUID.init().uuidString
    }

    var title: String {
        return package.attributes?.title ?? ""
    }

    var recipes: [Recipe] {
        guard let recipes = package.relationships?.recipes?.data else {
            return []
        }

        return recipes
    }
}

enum CatalogModelContent {
    case recipeList
    case categories
}

class CatalogVM: CatalogViewModel, ObservableObject {
    @Published var packages: [CatalogPackage] = []
    @Published var recipePageViewModel: RecipeListPageViewModel?
    @Published var filtersViewModel: CatalogFilterViewModel?

    @Published var content: CatalogModelContent = .categories
    @Published var filterOpen = false
    @Published var searchOpen = false
    @Published var searchString = ""
    @Published var state: CatalogContractState?

    override public init() {
        super.init()
        collect(flow: uiState) { data in
            let state = data as! CatalogContractState
            self.content = state.content == CatalogContent.recipeList ? .recipeList : .categories
            self.state = state
            self.filterOpen = state.filterOpen
            self.searchOpen = state.searchOpen
            self.searchString = state.searchString
            self.recipePageViewModel = state.recipePageVM
            self.filtersViewModel = state.catalogFilterVM
            switch state.categories {
            case let success as BasicUiStateSuccess<NSArray>: // Must use an object, thus NSArray
                if let packages = success.data as? [Package] {
                    self.packages = packages.map { line in
                        CatalogPackage(package: line)
                    }
                }
            default:
                break
            }
        }
    }
}
