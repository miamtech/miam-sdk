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
    
    private var removeProductAction: () -> Void = {}
    private var replaceProductAction: () -> Void = {}
    
    private var hasPicture: Bool {
        productPictureURL != nil
    }
    
    public init(viewModel : BasketPreviewVM,
        previewLine: BasketPreviewLine,
        removeProductAction: @escaping() -> Void,
        replaceProductAction: @escaping() -> Void){
        
        self.viewModel = viewModel
        self.previewLine = previewLine
        self.productName = previewLine.title
        self.productPictureURL = URL(string: previewLine.picture)
        self.productBrandName =  previewLine.productBrand
        self.productDescription = previewLine.productDescription
        if let price = Double(previewLine.price) {
            productPrice = String(format: "%.2f", price)
        } else {
            productPrice = "N/A"
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
            Template.sharedInstance.basketPreviewRowTemplate!(productName, productPictureURL, productBrandName, productName, productPrice, removeProductAction, replaceProductAction, onQuantityChanged)
        } else {
            VStack(alignment: .leading) {
                HStack(alignment: .top) {
                    if hasPicture {
                        AsyncImage(url: productPictureURL!, height: productImageDimensions.height).frame(width: productImageDimensions.width, height: productImageDimensions.height, alignment: .topLeading)
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
                    Text("\(productPrice)€")
                        .miamFontStyle(style: MiamFontStyleProvider.sharedInstance.titleStyle)
                        .foregroundColor(Color.miamColor(.primaryText))
                }.frame(maxWidth: .infinity, alignment: .trailing)
                    .padding(.top, Dimension.sharedInstance.borderWidth)
                
                //Ingredeient View
                HStack {
                    HStack {
                        if(!((previewLine.record as! BasketEntry).relationships?.items?.data.isEmpty ?? true ||
                             (previewLine.record as! BasketEntry).relationships!.items!.data.count == 1 ) ){
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
                        CounterView(count: Int(previewLine.count), lightMode: true) { qty in
                            onQuantityChanged(value: qty)
                        }
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

