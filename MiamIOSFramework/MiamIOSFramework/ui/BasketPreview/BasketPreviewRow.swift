//
//  ProductRow.swift
//  MiamIOSFramework
//
//  Created by John on 15/05/2022.
//

import SwiftUI
import miamCore

@available(iOS 14, *)
struct BasketPreviewRow: View {
    
    private let productImageDimensions = CGSize(width: 90, height: 90)
    private let productName: String
    private let productPictureURL: URL?
    private let productBrandName: String
    private let productDescription: String
    private let productPrice: String
    private var viewModel : BasketPreviewVM
    private let previewLine: BasketPreviewLine
    private var updatingBasketEntryId: String?
    
    private var removeProductAction: () -> Void = {}
    private var replaceProductAction: () -> Void = {}
    
    private var hasPicture: Bool {
        productPictureURL != nil
    }

    private var formattedProductPrice: String {
        guard let price = Double(previewLine.price) else {
            return ""
        }
        let numberFormatter = NumberFormatter()
        numberFormatter.currencyCode = "EUR"
        numberFormatter.numberStyle = .currency
        numberFormatter.maximumFractionDigits = 2
        guard let formattedPrice = numberFormatter.string(from: NSNumber(floatLiteral: price)) else {
            return ""
        }

        return formattedPrice
    }

    public init(viewModel : BasketPreviewVM,
                previewLine: BasketPreviewLine,
                updatingBasketEntryId: String?,
                removeProductAction: @escaping() -> Void,
                replaceProductAction: @escaping() -> Void){
        
        self.viewModel = viewModel
        self.previewLine = previewLine
        self.productName = previewLine.title
        self.productPictureURL = URL(string: previewLine.picture)
        self.productBrandName =  previewLine.productBrand
        self.productDescription = previewLine.productDescription
        self.updatingBasketEntryId = updatingBasketEntryId
        if let price = Double(previewLine.price) {
            productPrice = String(format: "%.2f", price)
        } else {
            productPrice = ""
        }
        self.removeProductAction = removeProductAction
        self.replaceProductAction = replaceProductAction
    }
    
    func onQuantityChanged(value: Int){
        if(value == 0){
            viewModel.removeBasketEntry(entry:  previewLine.record as! BasketEntry)
            return
        }
        viewModel.updateBasketEntry(entry: previewLine.record as! BasketEntry, finalQty: Int32(value))
    }
    
    var body: some View {
        
        
        if (Template.sharedInstance.basketPreviewRowTemplate != nil) {
            Template.sharedInstance.basketPreviewRowTemplate!(
                productName,
                productPictureURL,
                productBrandName,
                productDescription,
                productPrice,
                Int(previewLine.count),
                Int((previewLine.record as! BasketEntry).itemsCountOrZero),
                updatingBasketEntryId,
                removeProductAction,
                replaceProductAction,
                onQuantityChanged
            )
        } else {
            VStack(alignment: .leading) {
                HStack(alignment: .top) {
                    
                    if let picture = productPictureURL {
                        AsyncImage(url: picture, height: 120.0).frame(width:120, height: 120, alignment: .topLeading).cornerRadius(12.0)
                    } else {
                        Image.miamImage(icon: .empty).frame(width: 120, height: 120)
                    }                    
                    
                    VStack (alignment: .leading) {
                        Text(productName.capitalizingFirstLetter())
                            .miamFontStyle(style: MiamFontStyleProvider.sharedInstance.titleMediumStyle)
                            .foregroundColor(Color.miamColor(.black))
                            .padding(.leading, Dimension.sharedInstance.sPadding)
                        
                        Text(productBrandName)
                            .miamFontStyle(style: MiamFontStyleProvider.sharedInstance.titleExtraSmallMediumStyle)
                            .foregroundColor(Color.miamColor(.secondaryText))
                            .padding(.leading, Dimension.sharedInstance.sPadding)
                            .padding(.top, Dimension.sharedInstance.borderWidth)
                        
                        Text(productDescription)
                            .miamFontStyle(style: MiamFontStyleProvider.sharedInstance.bodyBigLightStyle)
                            .foregroundColor(Color.miamColor(.secondaryText))
                            .padding(.leading, Dimension.sharedInstance.sPadding)
                            .padding(.top, Dimension.sharedInstance.borderWidth)
                        HStack {
                            Spacer()
                            HStack {
                                if(previewLine.inlineTag != nil ){
                                    HStack(){
                                        Text(previewLine.inlineTag!)
                                            .miamFontStyle(style: MiamFontStyleProvider.sharedInstance.bodyMediumStyle)
                                        Image.miamImage(icon: .look)
                                            .resizable()
                                            .scaledToFill()
                                            .frame(width:24, height:24)
                                    }.padding(.horizontal,16)
                                        .padding(.vertical,4)
                                        .background(Color.miamColor(.greyLighter))
                                        .cornerRadius(8)
                                }
                            }
                        }
                    }
                    
                    Spacer()
                    Button(action: {
                        removeProductAction()
                    }) {
                        Image.miamImage(icon: .bin)
                    }.frame(width: 30, height: 30, alignment: .topTrailing)
                }
                
                //Price
                VStack {
                    Text(formattedProductPrice)
                        .miamFontStyle(style: MiamFontStyleProvider.sharedInstance.titleStyle)
                        .foregroundColor(Color.miamColor(.primaryText))
                }.frame(maxWidth: .infinity, alignment: .trailing)
                    .padding(.top, Dimension.sharedInstance.borderWidth)
                
                //Ingredeient View
                HStack {
                    HStack {
                        if((previewLine.record as! BasketEntry).itemsCountOrZero > 1){
                            Button(action: {
                                replaceProductAction()
                            }) {
                                Image.miamImage(icon: .sync).resizable()
                                    .renderingMode(.original)
                                    .frame(width: 18, height: 18, alignment: .topTrailing)
                                Text(MiamText.sharedInstance.replaceIngredient)
                                    .miamFontStyle(style: MiamFontStyleProvider.sharedInstance.bodyMediumBoldStyle)
                                    .foregroundColor(Color.miamColor(.primaryText))
                            }.frame(width: 100, height: 18, alignment: .topTrailing)
                        }
                        Spacer().onAppear(perform: {
                            print((previewLine.record as! BasketEntry).relationships!.items!.data.description )
                        })
                        
                        // Plus Minus View
                        CounterView(
                            count: Int(previewLine.count),
                            lightMode: true,
                            onCounterChanged: { qty in
                            onQuantityChanged(value: qty)
                        }, isLoading: updatingBasketEntryId == previewLine.id,
                           isDisable: updatingBasketEntryId != nil)
                    }
                }
                
                Divider()
                    .background(Color.miamColor(.borderLight))
                    .padding(.top, Dimension.sharedInstance.mPadding)
            }
            .padding([.horizontal, .top], Dimension.sharedInstance.lPadding)
            .frame(alignment: .topLeading)
        }
    }
}

