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
    
    private var recipeVm : RecipeViewModel
    private var goToDetail : (_ : RecipeViewModel, Bool) -> Void
    private var close : ()-> Void
    private var goToItemSelector: () -> Void
    
    public init(viewModel: BasketPreviewVM,
                recipeVm: RecipeViewModel,
                goToDetail: @escaping (_ : RecipeViewModel, Bool) -> Void,
                close: @escaping ()-> Void,
                goToItemSelector: @escaping () -> Void
    )
    {
        self.viewModel = viewModel
        self.recipeVm = recipeVm
        self.goToDetail = goToDetail
        self.close = close
        self.goToItemSelector = goToItemSelector
       
    }
    
    func updateGuests(value:Int){
        recipeVm.updateGuest(nbGuest: Int32(value))
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
                                    pictureURL: viewModel.pictureURL ??  URL(string:""),
                                    updateGuest: { guestNumber in updateGuests(value:guestNumber) }
                                    , goToDetail: {
                    recipeVm.goToDetail()
                })
                //List
                VStack(spacing: 0) {
                    ForEach(viewModel.productsInBasket, id: \.self) { entry in
                        let previewLine = BasketPreviewLine.fromBasketEntry(entry: entry)
                        
                        BasketPreviewRow(
                            viewModel:viewModel,
                            previewLine: previewLine,
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
            
            BasketPreviewFooter (
                removeFromBasketAction: {
                    viewModel.setEvent(event: BasketPreviewContractEvent.RemoveRecipe(recipeId: viewModel.basketPreviewLine!.id! ))
                    close()
                }
                ,continueShoppingAction: {
                    close()
                }
            )
        }
        .navigationTitle("\(viewModel.numberOfproductsInBasket) produits ajoutés à votre panier")
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
        if (Template.sharedInstance.ingredientFoldableHeaderTemplate != nil) {
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
    var products: Array<BasketEntry>
    var isAddable : Bool
    @SwiftUI.State var folded: Bool = true
    let addIngredientAction: (BasketEntry) -> Void
    var body: some View {
        IngredientsHeader(title: title, folded: $folded)
        
        if (!folded) {
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
