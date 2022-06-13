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
    
    private var recipeVm : RecipeViewModel
    private var goToDetail : (_ : RecipeViewModel) -> Void
    private var close : ()-> Void
    private var goToItemSelector: () -> Void
    
    
    @SwiftUI.State private var count: Int
    
    public init(recipeId: String,
                recipeVm: RecipeViewModel,
                goToDetail: @escaping (_ : RecipeViewModel) -> Void,
                close: @escaping ()-> Void,
                goToItemSelector: @escaping () -> Void
    )
    {
        viewModel = BasketPreviewVM(recipeId: recipeId)
        self.recipeVm = recipeVm
        self.goToDetail = goToDetail
        self.close = close
        self.goToItemSelector = goToItemSelector
        self.count = Int(recipeVm.currentState.guest)
    }
    
    
    private func updateGuest(count: Int) {
        if(viewModel.basketPreviewLine != nil){
            
            let currentBPL = viewModel.basketPreviewLine!
            
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
            
            viewModel.setEvent(event: BasketPreviewContractEvent.CountChange(
                bpl: newBPL, recipeVm : recipeVm )
            )}
    }
    
    func increaseGuestsCount() {
        if(viewModel.basketPreviewLine != nil && count != 100){
            count+=1
            updateGuest(count: count)
            
        }
    }
    
    func decreaseGuestsCount() {
        if(viewModel.basketPreviewLine != nil && count != 1){
            count-=1
            updateGuest(count: count)
        }
    }
    
    func addIngredient(_ entry: BasketEntry) {
        viewModel.setEvent(event: BasketPreviewContractEvent.AddEntry(entry: entry))
    }
    
    func removeProduct(_ entry: BasketEntry) {
        viewModel.setEvent(event: BasketPreviewContractEvent.RemoveEntry(entry: entry))
    }
    
    func replaceProduct(_ previewLine: BasketPreviewLine) {
        viewModel.setEvent(event: BasketPreviewContractEvent.OpenItemSelector(bpl: previewLine))
        goToItemSelector()
    }
    
    public var body: some View {
        VStack {
            // Top Bar
            HStack {
                Button(
                    action: {
                        goToDetail(recipeVm)
                    }
                ) {
                    Image("Caret")
                        .renderingMode(.original)
                        .frame(
                            width: 24,
                            height: 24,
                            alignment: .center
                        )
                }
                .frame( alignment: .center)
                .rotationEffect(.degrees(180))
                .padding(.horizontal , 8)
                
                Text("\(viewModel.numberOfproductsInBasket) produits ajoutés à votre panier")
                    .font(.system(size: 16.0, weight: .bold, design: .default))
                    .padding(.leading, Dimension.sharedInstance.lPadding)
                Spacer()
            }.frame(height: 50, alignment: .leading)
            
            ScrollView {
                BasketPreviewHeader(basketTitle: viewModel.basketTitle,
                                    basketDescription: viewModel.basketDescription,
                                    pricePerGuest: viewModel.pricePerGuest,
                                    numberOfGuests: count,
                                    price: viewModel.price,
                                    pictureURL: viewModel.pictureURL ??  URL(string:""),
                                    decreaseGuestsCount: decreaseGuestsCount,
                                    increaseGuestsCount: increaseGuestsCount)
                //List
                VStack {
                    ForEach(viewModel.productsInBasket, id: \.self) { entry in
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
                
                if (viewModel.basketPreviewLine?.entries?.oftenDeleted ?? []).count > 0 {
                    IngredientsFoldableView(title: MiamText.sharedInstance.mealRowAlready, products: viewModel.productsOftenDeleted, addIngredientAction: { entry in
                        addIngredient(entry)
                    })
                    
                }
                
                if (viewModel.basketPreviewLine?.entries?.notFound ?? []).count > 0 {
                    IngredientsFoldableView(title: MiamText.sharedInstance.mealRowNotFound, products: viewModel.productsNotFound, addIngredientAction: { entry in
                        addIngredient(entry)})
                    
                }
                
                if (viewModel.basketPreviewLine?.entries?.removed ?? []).count > 0 {
                    IngredientsFoldableView(title: MiamText.sharedInstance.mealRowRemoved, products: viewModel.productsRemoved, addIngredientAction: { entry in
                        addIngredient(entry)})
                }
            }
            
            BasketPreviewFooter {
                viewModel.setEvent(event: BasketPreviewContractEvent.RemoveRecipe())
                close()
            } continueShoppingAction: {
                close()
            }
        }
    }
}


internal struct IngredientsHeader: View {
    let title: String
    @Binding var folded: Bool
    private let foldedCarretImageName = "Caret"
    @SwiftUI.State private var caretAngle = 0.0
    
    init(title: String, folded: Binding<Bool> = .constant(true)) {
        self.title = title
        _folded = folded
    }
    
    var body: some View {
        HStack {
            Text(title)
                .font(.system(size: 16.0, weight: .bold, design: .default))
                .foregroundColor(MiamColor.sharedInstance.bodyText)
            
            Spacer()
            
            Image(foldedCarretImageName)
                .resizable()
                .aspectRatio( contentMode: .fit).rotationEffect(Angle(degrees: caretAngle))
                .frame(width: 30, height: 30, alignment: .center)
        }
        .padding(Dimension.sharedInstance.lPadding)
        .background(MiamColor.sharedInstance.greySurface)
        .cornerRadius(10).padding(.all, Dimension.sharedInstance.lPadding).onTapGesture {
            folded.toggle()
            caretAngle = folded ? 0.0 : 90.0
        }
    }
}

internal struct IngredientsFoldableView: View {
    var title: String
    var products: Array<BasketEntry>
    @SwiftUI.State var folded: Bool = true
    let addIngredientAction: (BasketEntry) -> Void
    var body: some View {
        IngredientsHeader(title: title, folded: $folded)
        
        if (!folded) {
            ForEach(products, id: \.self) { entry in
                let productName = entry.relationships?.groceriesEntry?.data.attributes?.name ?? ""
                IngredientNotInBasketRow(name: productName, addIngredientAction: {
                    addIngredientAction(entry)
                })
            }
        }
    }
}
