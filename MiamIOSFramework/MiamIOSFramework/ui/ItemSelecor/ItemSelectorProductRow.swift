//
//  ItemSelectorProductRow.swift
//  MiamIOSFramework
//
//  Created by Miam on 08/06/2022.
//

import SwiftUI
import shared

@available(iOS 14, *)
struct ItemSelectorProductRow: View {
    
    private var isSelected : Bool
    private var product :  BasketPreviewLine
    
    init(product : BasketPreviewLine,  isSelected : Bool = false){
        self.isSelected = isSelected
        self.product = product
        
    }
    
    var body: some View {
        VStack{
            HStack{
                AsyncImage(
                    url: URL(
                        string: product.picture
                    )! ,
                    placeholder: { Text("loading ...")},
                    height: 90
                ).frame(width: 90, height: 90)
                VStack(alignment: .leading){
                    Text(product.productBrand )
                        .font(.system(size: 13))
                        .padding(.bottom,8)
                    Text(product.productDescription )
                        .font(.system(size: 13))
                        .padding(.bottom,32)
                        .foregroundColor(MiamColor.sharedInstance.grey)
                    HStack{
                        Text(product.price + "€")
                            .font(.system(size: 16, weight: .heavy))
                        Spacer()
                        Text(isSelected ? "Déjà au panier" : ItemSelectorText.sharedInstance.select )
                            .font(.system(size: 13, weight: .heavy))
                            .padding(.horizontal,16)
                            .padding(.vertical,4)
                            .foregroundColor(MiamColor.sharedInstance.white)
                            .background( isSelected ? MiamColor.sharedInstance.grey : MiamColor.sharedInstance.primary )
                            .cornerRadius(1000)
                    }
                }.padding(16)
            }
            Divider()
        }
    }
}
