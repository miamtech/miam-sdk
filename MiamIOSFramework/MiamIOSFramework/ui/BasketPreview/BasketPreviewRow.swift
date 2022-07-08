//
//  ProductRow.swift
//  MiamIOSFramework
//
//  Created by John on 15/05/2022.
//

import SwiftUI
import shared

@available(iOS 14, *)
struct BasketPreviewRow: View {
    
    @SwiftUI.State var count: Int = 1
   
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
    
    
    private let moreInformationButtonTitle = "Plus d'infos"
    private let replaceIngredientButtonTitle = "Remplacer"
    
    
    func increaseQty(){
       count+=1
       viewModel.setEvent(event: BasketPreviewContractEvent.UpdateBasketEntry(entry: previewLine.record as! BasketEntry, finalQty: Int32(count)))
   }
   
    func decreaseQty(){
       if(previewLine.count  == 1 ){
           viewModel.setEvent(event: BasketPreviewContractEvent.RemoveEntry(entry: previewLine.record as! BasketEntry))
       }else{
           count-=1
           viewModel.setEvent(event: BasketPreviewContractEvent.UpdateBasketEntry(entry: previewLine.record as! BasketEntry, finalQty: Int32(count)))
       }
   }
    
    var body: some View {
        
         
        if (Template.sharedInstance.basketPreviewRowTemplate != nil) {
            Template.sharedInstance.basketPreviewRowTemplate!(productName, productPictureURL, productBrandName, productName, productPrice, removeProductAction, replaceProductAction)
        } else {
            VStack(alignment: .leading) {
                HStack(alignment: .top) {
                    if hasPicture {
                        AsyncImage(url: productPictureURL!,
                                   placeholder: { ProgressView() }, height: productImageDimensions.height).frame(width: productImageDimensions.width, height: productImageDimensions.height, alignment: .topLeading)
                    }
                    VStack (alignment: .leading) {
                        
                        Text(productName)
                            .foregroundColor(MiamColor.sharedInstance.black)
                            .font(.system(size: 16, weight: .bold, design: .default))
                            .padding(.leading, Dimension.sharedInstance.sPadding)
                        
                        Text(productBrandName)
                            .foregroundColor(MiamColor.sharedInstance.bodyText)
                            .font(.system(size: 13, weight: .medium, design: .default))
                            .padding(.leading, Dimension.sharedInstance.sPadding)
                            .padding(.top, Dimension.sharedInstance.borderWidth)
                        
                        Text(productDescription)
                            .foregroundColor(MiamColor.sharedInstance.bodyText)
                            .font(.system(size: 16, weight: .light, design: .default))
                            .padding(.leading, Dimension.sharedInstance.sPadding)
                            .padding(.top, Dimension.sharedInstance.borderWidth)
                    }
                    
                    Spacer()
                    Button(action: {
                        removeProductAction()
                    }) {
                        Image("Bin", bundle: Bundle(for: BasketPreviewVM.self))
                    }.frame(width: 30, height: 30, alignment: .topTrailing)
                }
                
                //Price
                VStack {
                    Text("\(productPrice)€")
                        .foregroundColor(MiamColor.sharedInstance.primaryText)
                        .font(.system(size: 20, weight: .heavy, design: .default))
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
                                Image("sync", bundle: Bundle(for: BasketPreviewVM.self)).resizable()
                                    .renderingMode(.original)
                                    .frame(width: 30, height: 30, alignment: .topTrailing)
                                Text(replaceIngredientButtonTitle)
                                    .foregroundColor(MiamColor.sharedInstance.primaryText).font(.system(size: 20, weight: .semibold, design: .default))
                            }.frame(width: 145, height: 30, alignment: .topTrailing)
                        }
                        Spacer().onAppear(perform: {
                            print((previewLine.record as! BasketEntry).relationships!.items!.data.description )
                        })
                        
                        // Plus Minus View
                        CounterView(count: count, isDisable: false, lightMode: true) {
                            increaseQty()
                        } decrease: {
                            decreaseQty()
                        }.onAppear(perform: {
                            count = Int(previewLine.count)
                        })
                    }
                }
                
                Divider()
                    .background(MiamColor.sharedInstance.borderBottom)
                    .padding(.top, Dimension.sharedInstance.mPadding)
            }
            .padding([.horizontal, .top], Dimension.sharedInstance.lPadding)
            .frame(alignment: .topLeading)
        }
    }
}

