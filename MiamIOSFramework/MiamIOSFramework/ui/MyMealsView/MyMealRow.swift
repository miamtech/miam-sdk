//
//  MyMealRow.swift
//  MiamIOSFramework
//
//  Created by John on 16/05/2022.
//

import SwiftUI
import miamCore

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
    @SwiftUI.State private var showingItemSelector = false

    private let itemSelectorViewModel = ItemSelectorVM()

    private func goToItemSelector() {
        itemSelectorViewModel.sharedInstance.setEvent(event: ItemSelectorContractEvent.SetReturnToBasketPreview(returnToPreview: { closeItemSelector() }))
        showingItemSelector.toggle()
    }

    private func closeItemSelector() {
        showingItemSelector.toggle()
    }

    public init(myMealViewModel: MyMealVM, meal: MyMeal) {
        self.meal = meal
        self.myMealViewModel = myMealViewModel
        self.basketPreviewViewModel = BasketPreviewVM(recipeId: meal.id)
        self.recipeViewModel = RecipeCardVM(routerVM: RouterOutletViewModel())
        self.count = meal.basketPreviewLine.numberOfGuests
    }

    func updateGuest(value: Int) {
        recipeViewModel.updateGuest(nbGuest: Int32(value))
    }

    func addIngredient(_ entry: BasketEntry) {
        basketPreviewViewModel.setEvent(event: BasketPreviewContractEvent.AddEntry(entry: entry))
    }

    func removeProduct(_ entry: BasketEntry) {
        basketPreviewViewModel.removeBasketEntry(entry: entry)
    }

    func replaceProduct(_ previewLine: BasketPreviewLine) {
        basketPreviewViewModel.setEvent(event: BasketPreviewContractEvent.OpenItemSelector(bpl: previewLine))
        goToItemSelector()
    }

    public var body: some View {
        content
            .frame(maxWidth: .infinity)
    }

    private var content: some View {

        let parameters =  MyMealsActionColumnTemplateParameters(
            delete: { myMealViewModel.setEvent(event: MyMealContractEvent.RemoveRecipe.init(recipeId: meal.id))},
            expand: { withAnimation(.default) {
                isExpanded.toggle()
                chevronAngle = isExpanded ? 0.0 : -90.0
            }}
        )
        
        return VStack(alignment: .leading, spacing: 0) {
            HStack(spacing: 0) {
                BasketPreviewHeader(basketTitle: meal.basketPreviewLine.basketTitle,
                                    basketDescription: meal.basketPreviewLine.basketDescription,
                                    pricePerGuest: meal.basketPreviewLine.pricePerGuest,
                                    numberOfGuests: count,
                                    price: meal.basketPreviewLine.price,
                                    isReloading: basketPreviewViewModel.state?.isReloading ?? false,
                                    pictureURL: meal.basketPreviewLine.pictureURL) { guestNumber in
                    updateGuest(value: guestNumber)
                }  goToDetail: {
//                    recipeViewModel.routerVM.goToDetail(vmRecipe: recipeViewModel, showDetailsFooter: false)
                    showingPopup = true
                }

                if let actionColumnTemplate = Template.sharedInstance.myMealsActionColumnTemplate {
                    actionColumnTemplate(parameters)
                } else {
                    VStack(spacing: 0) {
                        Button {
                            myMealViewModel.setEvent(event: MyMealContractEvent.RemoveRecipe.init(recipeId: meal.id))
                        } label: {
                            Image.miamImage(icon: .bin)
                        }
                        Spacer()
                        Button {
                            withAnimation(.default) {
                                isExpanded.toggle()
                                chevronAngle = isExpanded ? 0.0 : -90.0
                            }
                        } label: {
                            Image.miamImage(icon: .chevronDown).rotationEffect(Angle.degrees(chevronAngle))
                        }.padding([.trailing], Dimension.sharedInstance.lPadding)
                    }
                    .frame(width: 30.0, height: 30, alignment: .trailing)
                }
            }
            if isExpanded {
                VStack(spacing: 0) {
                    ForEach(meal.basketPreviewLine.productsInBasket, id: \.self) { entry in
                        let previewLine = BasketPreviewLine.fromBasketEntry(entry: entry)

                        BasketPreviewRow(viewModel: basketPreviewViewModel, previewLine: previewLine, updatingBasketEntryId: basketPreviewViewModel.state?.updatingBasketEntryId ) {
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
        }
       .sheet(isPresented: $showingPopup) {
            RecipeModal(recipeId:  meal.id, showFooter: false) {
                showingPopup = false
            }
        }.sheet(isPresented: $showingItemSelector) {
            NavigationView {
                ItemSelector(recipeId: meal.id) {
                    ()
                }
                .navigationBarTitleDisplayMode(.inline)
                .toolbar {
                    ToolbarItem(placement: .navigationBarLeading) {
                        Button {
                            showingItemSelector = false
                        } label: {
                            Image.miamImage(icon: MiamIcon.back)
                                .renderingMode(.template)
                                .foregroundColor(Color.miamColor(.primary))
                        }
                    }
                }
            }
            .accentColor(Color.miamColor(.primary))
        }.onAppear(perform: {
            recipeViewModel.fetchRecipe(recipeId: meal.id)
        })
    }
}
