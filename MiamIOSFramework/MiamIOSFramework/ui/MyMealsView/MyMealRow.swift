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
    private let myMealViewModel: MyMealVM
    private let basketPreviewViewModel: BasketPreviewVM
    private let recipeViewModel: RecipeViewModel
    private let meal: MyMeal
    @SwiftUI.State private var count: Int

    public init(myMealViewModel: MyMealVM, meal: MyMeal) {
        self.meal = meal
        self.myMealViewModel = myMealViewModel
        self.basketPreviewViewModel = BasketPreviewVM(recipeId: meal.id)
        self.recipeViewModel = RecipeCardVM(routerVM: RouterOutletViewModel())
        self.count = meal.basketPreviewLine.numberOfGuests
    }

    private func updateGuest(count: Int) {
        if(basketPreviewViewModel.basketPreviewLine != nil) {

            let currentBPL = meal.basketPreviewLine

            let newBPL = BasketPreviewLine.init(
                id: currentBPL.id,
                record: currentBPL.record,
                isRecipe: currentBPL.isRecipe,
                inlineTag: currentBPL.inlineTag,
                title: currentBPL.title,
                picture: currentBPL.picture,
                bplDescription: currentBPL.bplDescription,
                price: currentBPL.price,
                count: Int32(count),
                entries: currentBPL.entries,
                _displayMode: currentBPL._displayMode)

            basketPreviewViewModel.setEvent(event: BasketPreviewContractEvent.CountChange(
                bpl: newBPL, recipeVm : recipeViewModel )
            )
        }
    }

    func increaseGuestsCount() {
        if(basketPreviewViewModel.basketPreviewLine != nil && count != 100){
            count += 1
            updateGuest(count: count)
        }
    }

    func decreaseGuestsCount() {
        if(basketPreviewViewModel.basketPreviewLine != nil && count != 1){
            count-=1
            updateGuest(count: count)
        }
    }

    func addIngredient(_ entry: BasketEntry) {
        basketPreviewViewModel.setEvent(event: BasketPreviewContractEvent.AddEntry(entry: entry))
    }

    func removeProduct(_ entry: BasketEntry) {
        basketPreviewViewModel.setEvent(event: BasketPreviewContractEvent.RemoveEntry(entry: entry))
    }

    func replaceProduct(_ previewLine: BasketPreviewLine) {
        basketPreviewViewModel.setEvent(event: BasketPreviewContractEvent.OpenItemSelector(bpl: previewLine))
        //goToItemSelector()
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
                    decreaseGuestsCount()
                } increaseGuestsCount: {
                    increaseGuestsCount()
                }
                VStack {
                    Button {
                        myMealViewModel.setEvent(event: MyMealContractEvent.RemoveRecipe.init(recipeId: meal.id))
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
                                removeProduct(entry)
                            }, replaceProductAction: {
                                replaceProduct(previewLine)
                            })
                    }
                }

                if meal.basketPreviewLine.productsOftenDeleted.count > 0 {
                    IngredientsFoldableView(title: MiamText.sharedInstance.mealRowAlready, products: meal.basketPreviewLine.productsOftenDeleted, addIngredientAction: { entry in
                        addIngredient(entry)
                    })

                }

                if meal.basketPreviewLine.productsNotFound.count > 0 {
                    IngredientsFoldableView(title: MiamText.sharedInstance.mealRowNotFound, products: meal.basketPreviewLine.productsNotFound, addIngredientAction: { entry in
                        addIngredient(entry)
                    })
                }

                if meal.basketPreviewLine.productsRemoved.count > 0 {
                    IngredientsFoldableView(title: MiamText.sharedInstance.mealRowRemoved, products: meal.basketPreviewLine.productsRemoved, addIngredientAction: { entry in
                        addIngredient(entry)
                    })
                }
            }
        }
    }
}
