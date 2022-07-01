//
//  MyMealRow.swift
//  MiamIOSFramework
//
//  Created by John on 16/05/2022.
//

import SwiftUI
import shared


@available(iOS 14, *)
public struct MyMealRow: View {
    @SwiftUI.State private var isExpanded: Bool = false
    private let myMealViewModel: MyMealVM
    private let basketPreviewViewModel: BasketPreviewVM
    private let recipeViewModel: RecipeViewModel
    private let meal: MyMeal
    @SwiftUI.State private var count: Int = 4
    @SwiftUI.State private var chevronAngle = -90.0

    @SwiftUI.State private var initialDialogScreen = RouterContent.recipeDetail
    @SwiftUI.State private var showingPopup = false

    public init(myMealViewModel: MyMealVM, meal: MyMeal) {
        self.meal = meal
        self.myMealViewModel = myMealViewModel
        self.basketPreviewViewModel = BasketPreviewVM(recipeId: meal.id)
        self.recipeViewModel = RecipeCardVM(routerVM: RouterOutletViewModel())
        self.count = meal.basketPreviewLine.numberOfGuests
    }

    func increaseGuestsCount() {
        if(basketPreviewViewModel.basketPreviewLine != nil && count != 100) {
            count += 1
            recipeViewModel.setEvent(event: RecipeContractEvent.IncreaseGuest())
        }
    }

    func decreaseGuestsCount() {
        if(basketPreviewViewModel.basketPreviewLine != nil && count != 1) {
            count -= 1
            recipeViewModel.setEvent(event: RecipeContractEvent.DecreaseGuest())
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
                                    numberOfGuests: count,
                                    price: meal.basketPreviewLine.price,
                                    pictureURL: meal.basketPreviewLine.pictureURL) {
                    decreaseGuestsCount()
                } increaseGuestsCount: {
                    increaseGuestsCount()
                } goToDetail: {
                    recipeViewModel.routerVM.goToDetail(vmRecipe: recipeViewModel, showDetailsFooter: false)
                    showingPopup = true
                }
                VStack {
                    Button {
                        myMealViewModel.setEvent(event: MyMealContractEvent.RemoveRecipe.init(recipeId: meal.id))
                    } label: {
                        Image("Bin", bundle: Bundle(for: MyMealVM.self))
                    }
                    Spacer()
                    Button {
                        withAnimation(.default) {
                            isExpanded.toggle()
                            chevronAngle = isExpanded ? 0.0 : -90.0
                        }
                    } label: {
                        Image("chevron-down", bundle: Bundle(for: MyMealVM.self)).rotationEffect(Angle.degrees(chevronAngle))
                    }.padding([.trailing], Dimension.sharedInstance.lPadding)
                }.frame(width: 30.0, alignment: .trailing)
            }.padding(Dimension.sharedInstance.mlPadding)
            if isExpanded {
                VStack {
                    ForEach(meal.basketPreviewLine.productsInBasket, id: \.self) { entry in
                        let previewLine = BasketPreviewLine.fromBasketEntry(entry: entry)

                        BasketPreviewRow(viewModel: basketPreviewViewModel, previewLine: previewLine) {
                            removeProduct(entry)
                        } replaceProductAction: {
                            replaceProduct(previewLine)
                        }
                    }
                }

                if meal.basketPreviewLine.productsOftenDeleted.count > 0 {
                    IngredientsFoldableView(title: MiamText.sharedInstance.mealRowAlready, products: meal.basketPreviewLine.productsOftenDeleted, isAddable: true, addIngredientAction: { entry in
                        addIngredient(entry)
                    })

                }

                if meal.basketPreviewLine.productsNotFound.count > 0 {
                    IngredientsFoldableView(title: MiamText.sharedInstance.mealRowNotFound, products: meal.basketPreviewLine.productsNotFound, isAddable: false, addIngredientAction: { entry in
                        addIngredient(entry)
                    })
                }

                if meal.basketPreviewLine.productsRemoved.count > 0 {
                    IngredientsFoldableView(title: MiamText.sharedInstance.mealRowRemoved, products: meal.basketPreviewLine.productsRemoved, isAddable: true, addIngredientAction: { entry in
                        addIngredient(entry)
                    })
                }
            }
        }.popover(isPresented: $showingPopup) {
            Dialog(
                close: { showingPopup = false },
                initialRoute : initialDialogScreen,
                routerVm: recipeViewModel.routerVM
            )
        }.onAppear(perform: {
            recipeViewModel.setEvent(
                event: RecipeContractEvent.OnGetRecipe(idRecipe: meal.id))
        })
    }
}
