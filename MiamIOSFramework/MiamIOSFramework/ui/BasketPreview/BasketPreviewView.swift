//
//  ProductView.swift
//  MiamIOSFramework
//
//  Created by John on 15/05/2022.
//

import SwiftUI
import shared

public struct BasketPreviewView: View {
    @ObservedObject private var viewModel: BasketPreviewVM
    public init(recipeId: String) {
        viewModel = BasketPreviewVM(recipeId: recipeId)
    }

    func increaseGuestsCount() {
        
    }

    func decreaseGuestsCount() {

    }

    func addIngredient(_ entry: BasketEntry) {
        viewModel.setEvent(event: BasketPreviewContractEvent.AddEntry(entry: entry))
    }

    func removeProduct(_ entry: BasketEntry) {
        viewModel.setEvent(event: BasketPreviewContractEvent.RemoveEntry(entry: entry))
    }

    func replaceProduct(_ entry: BasketEntry) {

    }

    public var body: some View {
        VStack {
            // Top Bar
            HStack {
                Image("Arrow")
                    .resizable()
                    .aspectRatio(contentMode: .fit)
                    .frame(width: 30, height: 30, alignment: .center)
                    .padding(.leading, Dimension.sharedInstance.lPadding)
                
                Text("\(viewModel.numberOfproductsInBasket) produits ajoutés à votre panier")
                    .font(.system(size: 16.0, weight: .bold, design: .default))
                    .padding(.leading, Dimension.sharedInstance.lPadding)
                Spacer()
            }.frame(height: 50, alignment: .leading)

            ScrollView {
                BasketPreviewHeader(basketTitle: viewModel.basketTitle, basketDescription: viewModel.basketDescription, pricePerGuest: viewModel.pricePerGuest, numberOfGuests: Int(viewModel.numberOfGuests), price: viewModel.price, pictureURL: viewModel.pictureURL!, descreaseGuestsCount: {}, increaseGuestsCount: {})

                //List
                VStack {
                    ForEach(viewModel.productsInBasket, id: \.self) { entry in
                        let previewLine = BasketPreviewLine.fromBasketEntry(entry: entry)

                        BasketPreviewRow(productName: previewLine.title, productBrandName: previewLine.productBrand,
                                         productDescription: previewLine.productDescription, productPrice: previewLine.price, removeProductAction: {
                            removeProduct(entry)
                        }, replaceProductAction: {

                        })
                    }
                }

                if (viewModel.basketPreviewLine?.entries?.oftenDeleted ?? []).count > 0 {
                    HStack {
                        Text(MiamText.sharedInstance.mealRowAlready)
                            .font(.system(size: 16.0, weight: .bold, design: .default))
                            .foregroundColor(MiamColor.sharedInstance.bodyText)

                        Spacer()

                        Image("Caret")
                            .resizable()
                            .aspectRatio( contentMode: .fit)
                            .frame(width: 30, height: 30, alignment: .center)
                    }
                    .padding(Dimension.sharedInstance.lPadding)
                    .background(MiamColor.sharedInstance.primaryLight)
                    .cornerRadius(10).padding(.all, Dimension.sharedInstance.lPadding)

                    ForEach(viewModel.productsOftenDeleted, id: \.self) { entry in
                        let productName = entry.relationships?.groceriesEntry?.data.attributes?.name ?? ""
                        IngredientNotInBasketRow(name: productName, addIngredientAction: {
                            addIngredient(entry)
                        })
                    }
                }

                if (viewModel.basketPreviewLine?.entries?.notFound ?? []).count > 0 {
                    HStack {
                        Text(MiamText.sharedInstance.mealRowNotFound)
                            .font(.system(size: 16.0, weight: .bold, design: .default))
                            .foregroundColor(MiamColor.sharedInstance.bodyText)

                        Spacer()

                        Image("Caret")
                            .resizable()
                            .aspectRatio( contentMode: .fit)
                            .frame(width: 30, height: 30, alignment: .center)
                    }
                    .padding(Dimension.sharedInstance.lPadding)
                    .background(MiamColor.sharedInstance.greySurface)
                    .cornerRadius(10).padding(.all, Dimension.sharedInstance.lPadding)

                    ForEach(viewModel.productsNotFound, id: \.self) { entry in
                        let productName = entry.relationships?.groceriesEntry?.data.attributes?.name ?? ""
                        IngredientNotInBasketRow(name: productName, addIngredientAction: {
                            addIngredient(entry)
                        })
                    }
                }

                if (viewModel.basketPreviewLine?.entries?.removed ?? []).count > 0 {
                    HStack {
                        Text(MiamText.sharedInstance.mealRowRemoved)
                            .font(.system(size: 16.0, weight: .bold, design: .default))
                            .foregroundColor(MiamColor.sharedInstance.bodyText)

                        Spacer()

                        Image("Caret")
                            .resizable()
                            .aspectRatio( contentMode: .fit)
                            .frame(width: 30, height: 30, alignment: .center)
                    }
                    .padding(Dimension.sharedInstance.lPadding)
                    .background(MiamColor.sharedInstance.greySurface)
                    .cornerRadius(10).padding(.all, Dimension.sharedInstance.lPadding)


                    ForEach(viewModel.productsRemoved, id: \.self) { entry in
                        let productName = entry.relationships?.groceriesEntry?.data.attributes?.name ?? ""
                        IngredientNotInBasketRow(name: productName, addIngredientAction: {
                            addIngredient(entry)
                        })
                    }
                }
            }

            BasketPreviewBottomView {

            } continueShoppingAction: {

            }
        }
    }
}

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
