//
//  CatalogVM.swift
//  MiamIOSFramework
//
//  Created by Vincent Kergonna on 24/06/2022.
//

import Foundation
import miamCore

public struct CatalogPackage: Identifiable {
    let package: Package

    public var id: String {
        return package.id
    }

    public var title: String {
        return package.attributes?.title ?? ""
    }

    public var subtitle: String? {
        return package.subtitle
    }

    public var recipes: [Recipe] {
        guard let recipes = package.relationships?.recipes?.data else {
            return []
        }

        return recipes
    }
}

@available(iOS 14, *)
public class CatalogVM: CatalogViewModel, ObservableObject {
    @Published var packages: [CatalogPackage] = []
    @Published var content: CatalogContent = .categoriesList
    @Published var state: CatalogContractState?

    override public init() {
        super.init()
        collect(flow: uiState) { data in
            let state = data as? CatalogContractState
            self.content = state?.content ?? .categoriesList
            self.state = state
            switch state?.categories {
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

    public convenience init(categoryID: String, title: String) {
        self.init()
        self.goToCategory(categoryId: categoryID, categoryTitle: title, subtitle: nil)
    }
}
