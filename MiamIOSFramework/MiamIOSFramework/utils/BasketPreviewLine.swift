//
//  BasketPreviewLine.swift
//  MiamIOSFramework
//
//  Created by Vincent Kergonna on 10/06/2022.
//

import Foundation
import miamCore

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

    var pictureURL: URL? {
        return URL(string: picture)
    }

    var basketTitle: String {
        return title
    }

    var basketDescription: String {
        return bplDescription[0]
    }

    var pricePerGuest: String {
        guard let parsedPrice = Double(price) else {
            return ""
        }
        let guardCount = count > 0 ? count : 1
        let price = parsedPrice * 100 / Double(guardCount) / 100
        let formattedPrice = String(format: "%.2f", price)
        return "\(formattedPrice)€ /personne"
    }

    var numberOfGuests: Int {
        return Int(count)
    }

    var productBrand: String {
        return bplDescription.count > 1 ? bplDescription[1] : ""
    }

    var productDescription: String {
        return bplDescription.count > 0 ? bplDescription[0] : ""
    }

    var  numberOfproductsInBasket: Int {
        return entries?.found.count ?? 0
    }

    var productsInBasket: Array<BasketEntry> {
        // Avoid error "Generic parameter 'C' could not be inferred" when accessing entries
        guard let entries = entries?.found as? Array<BasketEntry> else {
            return []
        }

        return entries
    }

    var productsNotFound: Array<BasketEntry> {
        // Avoid error "Generic parameter 'C' could not be inferred" when accessing entries
        guard let entries = entries?.notFound as? Array<BasketEntry> else {
            return []
        }

        return entries
    }

    var productsOftenDeleted: Array<BasketEntry> {
        // Avoid error "Generic parameter 'C' could not be inferred" when accessing entries
        guard let entries = entries?.oftenDeleted as? Array<BasketEntry> else {
            return []
        }

        return entries
    }

    var productsRemoved: Array<BasketEntry> {
        // Avoid error "Generic parameter 'C' could not be inferred" when accessing entries
        guard let entries = entries?.removed as? Array<BasketEntry> else {
            return []
        }

        return entries
    }
}
