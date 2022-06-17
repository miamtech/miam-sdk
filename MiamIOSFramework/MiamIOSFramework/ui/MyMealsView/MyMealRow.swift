//
//  MyMealRow.swift
//  MiamIOSFramework
//
//  Created by John on 16/05/2022.
//

import SwiftUI
import shared

public struct MyMealRow: View {
    @SwiftUI.State private var isExpanded: Bool = false
    private let meal: MyMeal
    public init(meal: MyMeal) {
        self.meal = meal
    }

    public var body: some View {
        content
            .frame(maxWidth: .infinity)
    }
    
    private var content: some View {
        VStack(alignment: .leading) {
            HStack {
                BasketPreviewHeader(basketTitle: meal.basketPreviewLine.basketTitle,
                                    basketDescription: meal.basketPreviewLine.basketDescription,
                                    pricePerGuest: meal.basketPreviewLine.pricePerGuest,
                                    numberOfGuests: meal.basketPreviewLine.numberOfGuests,
                                    price: meal.basketPreviewLine.price,
                                    pictureURL: meal.basketPreviewLine.pictureURL) {

                } increaseGuestsCount: {

                }
                VStack {
                    Button {
                        // TODO: Delete recipe
                    } label: {
                        Image("Bin")
                    }
                    Spacer()
                    Button {
                        isExpanded.toggle()
                    } label: {
                        Image("chevron-down")
                    }.padding([.trailing], Dimension.sharedInstance.lPadding)
                }.frame(width: 30.0, alignment: .trailing)
            }.padding(Dimension.sharedInstance.mlPadding)
            if isExpanded {
                VStack {
                    ForEach(meal.basketPreviewLine.productsInBasket, id: \.self) { entry in
                        let previewLine = BasketPreviewLine.fromBasketEntry(entry: entry)

                        BasketPreviewRow(
                            productName: previewLine.title,
                            productPictureURL: URL(string: previewLine.picture),
                            productBrandName: previewLine.productBrand,
                            productDescription: previewLine.productDescription,
                            productPrice: previewLine.price,
                            removeProductAction: {
                            //removeProduct(entry)
                        }, replaceProductAction: {
                            //replaceProduct(previewLine)
                        })
                    }
                }

                if meal.basketPreviewLine.productsOftenDeleted.count > 0 {
                    IngredientsFoldableView(title: MiamText.sharedInstance.mealRowAlready, products: meal.basketPreviewLine.productsOftenDeleted, addIngredientAction: { entry in
                        //addIngredient(entry)
                    })

                }

                if meal.basketPreviewLine.productsNotFound.count > 0 {
                    IngredientsFoldableView(title: MiamText.sharedInstance.mealRowNotFound, products: meal.basketPreviewLine.productsNotFound, addIngredientAction: { entry in
                        //addIngredient(entry)
                    })
                }

                if meal.basketPreviewLine.productsRemoved.count > 0 {
                    IngredientsFoldableView(title: MiamText.sharedInstance.mealRowRemoved, products: meal.basketPreviewLine.productsRemoved, addIngredientAction: { entry in
                        //addIngredient(entry)
                    })
                }
            }
        }
    }
}
