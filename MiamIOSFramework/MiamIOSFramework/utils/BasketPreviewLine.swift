//
//  BasketPreviewLine.swift
//  MiamIOSFramework
//
//  Created by Vincent Kergonna on 10/06/2022.
//

import Foundation
import shared

internal extension BasketPreviewLine {
    static func fromBasketEntry(entry: BasketEntry) -> BasketPreviewLine   {
        let item = entry.selectedItem
        let beI = entry.attributes!.basketEntriesItems?.first(where: { item in
            item.itemId == entry.attributes?.selectedItemId
        })
        var price = 0.0
        if let beItem = beI, let quantity = entry.attributes?.quantity, let itemPrice = beItem.unitPrice?.doubleValue {
            price = itemPrice * quantity.doubleValue
        }
        let gEntry = entry.relationships?.groceriesEntry?.data
        let recipesCount =  gEntry?.attributes?.recipeIds?.count ?? 1
        let title = entry.relationships?.groceriesEntry?.data.attributes?.name ?? ""
        var description = ""
        if let name = item?.attributes?.name, let capacityUnit =  item?.attributes?.capacityUnit {
            description = "\(name) \n (\(capacityUnit)"
        }
        var brand = ""
        if let productBrand = item?.attributes?.brand {
            brand = productBrand
        }
        return BasketPreviewLine(
            id: entry.id,
            record: entry,
            isRecipe: false,
            inlineTag: recipesCount > 1 ? "Pour \(recipesCount) recettes" : nil,
            title: title,
            picture: item?.attributes?.image ?? "",
            bplDescription: [description, brand],
            price: "\(price)",
            count: entry.attributes?.quantity?.int32Value ?? 1,
            entries: nil,
            _displayMode: false
        )
    }

    var productBrand: String {
        return bplDescription.count > 1 ? bplDescription[1] : ""
    }

    var productDescription: String {
        return bplDescription.count > 0 ? bplDescription[0] : ""
    }
}
