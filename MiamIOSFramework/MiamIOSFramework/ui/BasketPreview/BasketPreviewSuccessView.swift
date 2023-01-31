//
//  ProductView.swift
//  MiamIOSFramework
//
//  Created by John on 15/05/2022.
//

import SwiftUI
import miamCore

@available(iOS 14, *)
public struct BasketPreviewSuccessView: View {
    @ObservedObject private var viewModel: BasketPreviewVM

    private var recipeVm: RecipeViewModel
    private var title: String?
    private var goToDetail: (_: RecipeViewModel, Bool) -> Void
    private var close: () -> Void
    private var goToItemSelector: () -> Void
    private let analytics = AnalyticsInstance.shared.instance
    private var isReloading: Bool
    private var updatingBasketEntryId: String?

    public init(viewModel: BasketPreviewVM,
                title: String?,
                recipeVm: RecipeViewModel,
                goToDetail: @escaping (_: RecipeViewModel, Bool) -> Void,
                close: @escaping () -> Void,
                goToItemSelector: @escaping () -> Void,
                isReloading: Bool,
                updatingBasketEntryId: String?
    ) {
        self.viewModel = viewModel
        self.title = title
        self.recipeVm = recipeVm
        self.goToDetail = goToDetail
        self.close = close
        self.goToItemSelector = goToItemSelector
        self.isReloading = isReloading
        self.updatingBasketEntryId = updatingBasketEntryId
    }

    var navigationTitle: String {
        var navigationTitle = ""

        if let title {
            navigationTitle = title
        } else {
            navigationTitle = "\(viewModel.numberOfproductsInBasket) produits ajoutés à votre panier"
        }
        return navigationTitle
    }

    func updateGuests(value: Int) {
        viewModel.updateGuest(
            onUpdateGuest: { guestNumber in recipeVm.updateGuest(nbGuest: Int32(guestNumber)) },
            guestCount: Int32(value)
        )
    }

    func addIngredient(_ entry: BasketEntry) {
        viewModel.setEvent(event: BasketPreviewContractEvent.AddEntry(entry: entry))
    }

    func removeProduct(_ entry: BasketEntry) {
        viewModel.removeBasketEntry(entry: entry)
    }

    func replaceProduct(_ previewLine: BasketPreviewLine) {
        viewModel.setEvent(event: BasketPreviewContractEvent.OpenItemSelector(bpl: previewLine))
        goToItemSelector()
    }

    public var body: some View {
        VStack {
            ScrollView {
                BasketPreviewHeader(basketTitle: viewModel.basketTitle,
                                    basketDescription: viewModel.basketDescription,
                                    pricePerGuest: viewModel.pricePerGuest,
                                    numberOfGuests: Int(recipeVm.currentState.guest),
                                    price: viewModel.price,
                                    isReloading: isReloading,
                                    pictureURL: viewModel.pictureURL ??  URL(string: ""),
                                    updateGuest: { guestNumber in updateGuests(value: guestNumber) }
                                    , goToDetail: {
                    goToDetail(recipeVm, true)
                })
                // List
                VStack(spacing: 0) {
                    ForEach(viewModel.productsInBasket, id: \.self) { entry in
                        let previewLine = BasketPreviewLine.fromBasketEntry(entry: entry)

                        BasketPreviewRow(
                            viewModel: viewModel,
                            previewLine: previewLine,
                            updatingBasketEntryId: updatingBasketEntryId,
                            removeProductAction: {
                                removeProduct(entry)
                            }, replaceProductAction: {
                                replaceProduct(previewLine)
                            })
                    }
                }

                if (viewModel.basketPreviewLine?.entries?.oftenDeleted ?? []).count > 0 {
                    IngredientsFoldableView(title: MiamText.sharedInstance.mealRowAlready, products: viewModel.productsOftenDeleted, isAddable: true, addIngredientAction: { entry in
                        addIngredient(entry)
                    })

                }

                if (viewModel.basketPreviewLine?.entries?.notFound ?? []).count > 0 {
                    IngredientsFoldableView(title: MiamText.sharedInstance.mealRowNotFound, products: viewModel.productsNotFound, isAddable: false, addIngredientAction: { entry in
                        addIngredient(entry)})

                }

                if (viewModel.basketPreviewLine?.entries?.removed ?? []).count > 0 {
                    IngredientsFoldableView(title: MiamText.sharedInstance.mealRowRemoved, products: viewModel.productsRemoved, isAddable: true, addIngredientAction: { entry in
                        addIngredient(entry)})
                }
            }

            BasketPreviewFooter(
                removeFromBasketAction: {
                    viewModel.setEvent(event: BasketPreviewContractEvent.RemoveRecipe(recipeId: viewModel.basketPreviewLine!.id! ))
                    close()
                }, continueShoppingAction: {
                    close()
                }
            )
        }
        .navigationTitle(navigationTitle).onAppear(perform: {
            analytics.sendEvent(
                eventType: Analytics.companion.EVENT_PAGEVIEW,
                path: "/basket-preview",
                props: Analytics.PlausibleProps(recipe_id: recipeVm.recipeId, category_id: nil, entry_name: nil, basket_id: nil, miam_amount: nil, total_amount: nil, pos_id: nil, pos_total_amount: nil, pos_name: nil, search_term: nil)
            )})
    }
}

@available(iOS 14, *)
internal struct IngredientsHeader: View {
    let title: String
    @Binding var folded: Bool
    @SwiftUI.State private var caretAngle = 0.0

    init(title: String, folded: Binding<Bool> = .constant(true)) {
        self.title = title
        _folded = folded
    }

    var body: some View {
        if Template.sharedInstance.ingredientFoldableHeaderTemplate != nil {
            Template.sharedInstance.ingredientFoldableHeaderTemplate!(title, $folded)
        } else {
            HStack {
                Text(title)
                    .miamFontStyle(style: MiamFontStyleProvider.sharedInstance.titleMediumStyle)
                    .foregroundColor(Color.miamColor(.secondaryText))

                Spacer()

                Image.miamImage(icon: .caret)
                    .resizable()
                    .aspectRatio( contentMode: .fit).rotationEffect(Angle(degrees: caretAngle))
                    .frame(width: 30, height: 30, alignment: .center)
            }
            .padding(Dimension.sharedInstance.lPadding)
            .background(Color.miamColor(.greySurface))
            .cornerRadius(10).padding(.all, Dimension.sharedInstance.lPadding).onTapGesture {
                withAnimation(.default) {
                    folded.toggle()
                    caretAngle = folded ? 0.0 : 90.0
                }
            }
        }
    }
}

@available(iOS 14, *)
internal struct IngredientsFoldableView: View {
    var title: String
    var products: [BasketEntry]
    var isAddable: Bool
    @SwiftUI.State var folded: Bool = true
    let addIngredientAction: (BasketEntry) -> Void
    var body: some View {
        IngredientsHeader(title: title, folded: $folded)

        if !folded {
            VStack(spacing: 0) {
                ForEach(products, id: \.self) { entry in
                    let productName = entry.relationships?.groceriesEntry?.data.attributes?.name ?? ""
                    IngredientNotInBasketRow(name: productName, addIngredientAction: {
                        addIngredientAction(entry)
                    }, isAddable: isAddable)
                }
            }
        }
    }
}

@available(iOS 14, *)
public struct BasketPreviewTitleBar: View {

    let numberOfProductsInBasket: Int

    public var body: some View {
        if let templateTitle = Template.sharedInstance.basketPreviewTitleTemplate {
            templateTitle(numberOfProductsInBasket)
        } else {
            Text("\(numberOfProductsInBasket) produits ajoutés à votre panier")
                .miamFontStyle(style: MiamFontStyleProvider.sharedInstance.titleMediumStyle)
                .padding(.leading, Dimension.sharedInstance.lPadding)
        }
    }
}
