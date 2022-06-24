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

class CatalogVM: CatalogViewModel, ObservableObject {
    @Published var packages: [CatalogPackage] = []

    override public init() {
        super.init()
        collect(flow: uiState) { data in
            let state = data as! CatalogContractState
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
