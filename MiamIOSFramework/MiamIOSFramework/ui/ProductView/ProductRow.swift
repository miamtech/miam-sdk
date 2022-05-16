//
//  ProductRow.swift
//  MiamIOSFramework
//
//  Created by John on 15/05/2022.
//

import SwiftUI

struct ProductRow: View {
    var body: some View {
        VStack(alignment: .leading) {
            HStack(alignment: .top) {
                Image("product").resizable().aspectRatio( contentMode: .fit).frame(width: 100, height: 100, alignment: .topLeading)
                
                VStack (alignment: .leading){
                    
                    Text("Bière blonde (40 cl)").foregroundColor(MiamColor.sharedInstance.black).font(.system(size: 16, weight: .bold, design: .default)).padding(.leading, Dimension.sharedInstance.sPadding)
                    
                    Text("3 MONTS").foregroundColor(MiamColor.sharedInstance.bodyText).font(.system(size: 13, weight: .medium, design: .default)).padding(.leading, Dimension.sharedInstance.sPadding).padding(.top, Dimension.sharedInstance.xsPadding)
                    
                    Text("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Lacus. 0.15 kg").foregroundColor(MiamColor.sharedInstance.bodyText).font(.system(size: 16, weight: .light, design: .default)).padding(.leading, Dimension.sharedInstance.sPadding).padding(.top, Dimension.sharedInstance.xsPadding)
                    
                    Text("Plus d’infos").foregroundColor(MiamColor.sharedInstance.black).font(.system(size: 13, weight: .medium, design: .default)).padding(.leading, Dimension.sharedInstance.sPadding).padding(.top, Dimension.sharedInstance.xsPadding).padding(.top, Dimension.sharedInstance.xsPadding)
                }
                
                Spacer()
                Button(action: {
                    
                }) {
                    Image("bin").resizable()
                        .renderingMode(.original)
                }.frame(width: 30, height: 30, alignment: .topTrailing)
            }
            
            //Price
            VStack {
                Text("1,59 €").foregroundColor(MiamColor.sharedInstance.primaryText).font(.system(size: 20, weight: .heavy, design: .default))
            }.frame(maxWidth: .infinity, alignment: .trailing).padding(.top, Dimension.sharedInstance.xsPadding)
            
            //Ingredeient View
            HStack {
                HStack {
                    Button(action: {
                        
                    }) {
                        Image("sync").resizable()
                            .renderingMode(.original)
                    }.frame(width: 30, height: 30, alignment: .topTrailing)
                    
                    Text("Remplacer").foregroundColor(MiamColor.sharedInstance.primaryText).font(.system(size: 20, weight: .heavy, design: .default))
                    
                    Spacer()
                    
                    // Plus Minus View
                    HStack {
                        Button(action: {
                            
                        }) {
                            Image(systemName: "minus").foregroundColor(MiamColor.sharedInstance.white)
                        }.padding(.leading, Dimension.sharedInstance.mPadding).frame(width: 20.0, height: 20.0, alignment: .leading)
                        
                        Text("1").foregroundColor(MiamColor.sharedInstance.white).font(.system(size: 13, weight: .medium, design: .default)).padding(Dimension.sharedInstance.lPadding)
                        
                        Button(action: {
                            
                        }) {
                            Image(systemName: "plus").foregroundColor(MiamColor.sharedInstance.white)
                        }.padding(.trailing, Dimension.sharedInstance.mPadding).frame(width: 20.0, height: 20.0, alignment: .trailing)
                    }.frame(width: 120.0, height: 50.0, alignment: .center).background(MiamColor.sharedInstance.primaryText).cornerRadius(25.0)
                }.frame(
                    minWidth: 0,
                    maxWidth: .infinity,
                    minHeight: 0,
                    maxHeight: .infinity,
                    alignment: .topLeading
                )
            }.frame(height: 50.0, alignment: .topLeading)
            
            //Divider
            HStack {
                HStack  {
                    
                }.frame(
                    minWidth: 0,
                    maxWidth: .infinity,
                    minHeight: 0,
                    maxHeight: .infinity,
                    alignment: .topLeading)
            }.background(MiamColor.sharedInstance.borderBottom).frame(height: 1.0, alignment: .leading).padding(.top, Dimension.sharedInstance.mPadding)
            
        }.padding([.horizontal, .top], Dimension.sharedInstance.lPadding).frame(alignment: .topLeading)
    }
}

struct ProductRow_Previews: PreviewProvider {
    static var previews: some View {
        ProductRow()
    }
}
