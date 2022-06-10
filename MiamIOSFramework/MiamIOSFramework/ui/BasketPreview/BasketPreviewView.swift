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
                BasketPreviewHeader(basketTitle: viewModel.basketTitle, basketDescription: viewModel.basketDescription, pricePerGuest: viewModel.pricePerGuest, numberOfGuests: Int(viewModel.numberOfGuests), price: viewModel.price, pictureURL: viewModel.pictureURL, descreaseGuestsCount: {}, increaseGuestsCount: {})

                //List
                VStack {
                    ForEach(viewModel.productsInBasket, id: \.self) { entry in
                        let previewLine = BasketPreviewLine.fromBasketEntry(entry: entry)

                        BasketPreviewRow(productName: previewLine.title, productPictureURL: URL(string: previewLine.picture), productBrandName: previewLine.productBrand,
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
