//
//  ProductView.swift
//  MiamIOSFramework
//
//  Created by John on 15/05/2022.
//

import SwiftUI

struct ProductView: View {
    var body: some View {
        VStack {
            // Top Bar
            HStack {
                Image(systemName: "arrow.left").resizable().aspectRatio(contentMode: .fit)
                    .frame(width: 30, height: 30, alignment: .center).padding(.leading, Dimension.sharedInstance.lPadding)
                
                Text("6 produits ajoutés à votre panier").font(.system(size: 16.0, weight: .bold, design: .default)).padding(.leading, Dimension.sharedInstance.lPadding)
                Spacer()
            }.frame(width: .infinity, height: 50, alignment: .leading)
            
            //Divider
            HStack {
                HStack  {
                    
                }.frame(
                    minWidth: 0,
                    maxWidth: .infinity,
                    minHeight: 0,
                    maxHeight: .infinity,
                    alignment: .topLeading)
            }.background(MiamColor.sharedInstance.borderBottom).frame(height: 1.0, alignment: .leading)
            
            //Product View
            VStack(alignment: .leading) {
                HStack {
                    Image("recipe").resizable().frame(width: 150, height: 150, alignment: .topLeading)
                    
                    VStack (alignment: .leading){
                        
                        Text("Welsh royal à la 3 Monts").foregroundColor(MiamColor.sharedInstance.black).font(.system(size: 16, weight: .heavy, design: .default)).padding(.leading, Dimension.sharedInstance.sPadding)
                        
                        Text("6 articles").foregroundColor(MiamColor.sharedInstance.bodyText).font(.system(size: 16, weight: .light, design: .default)).padding(.leading, Dimension.sharedInstance.sPadding).padding(.top, Dimension.sharedInstance.xsPadding)
                        
                        Text("4,93 € par personne").foregroundColor(MiamColor.sharedInstance.bodyText).font(.system(size: 16, weight: .light, design: .default)).padding(.leading, Dimension.sharedInstance.sPadding)
                    }.frame(width: .infinity, height: 150, alignment: .topLeading)
                }.frame(width: .infinity, height: 150, alignment: .topLeading)
                
                //Ingredeient View
                HStack {
                    HStack {
                        Text("19,72 €").foregroundColor(MiamColor.sharedInstance.primaryText).font(.system(size: 20, weight: .heavy, design: .default))
                        
                        Spacer()
                        
                        // Plus Minus View
                        HStack {
                            Button(action: {
                                
                            }) {
                                Image(systemName: "minus").foregroundColor(MiamColor.sharedInstance.white)
                            }.padding(.leading, Dimension.sharedInstance.lPadding).frame(width: 20.0, height: 20.0, alignment: .leading)
                            
                            Text("4 pers.").foregroundColor(MiamColor.sharedInstance.white).font(.system(size: 13, weight: .bold, design: .default)).padding(Dimension.sharedInstance.lPadding)
                            
                            Button(action: {
                                
                            }) {
                                Image(systemName: "plus").foregroundColor(MiamColor.sharedInstance.white)
                            }.padding(.trailing, Dimension.sharedInstance.lPadding).frame(width: 20.0, height: 20.0, alignment: .trailing)
                        }.frame(width: 140.0, height: 50.0, alignment: .center).background(MiamColor.sharedInstance.primaryText).cornerRadius(25.0)
                    }.frame(
                        minWidth: 0,
                        maxWidth: .infinity,
                        minHeight: 0,
                        maxHeight: .infinity,
                        alignment: .topLeading
                    )
                }.frame(height: 50.0, alignment: .topLeading).padding(.top, Dimension.sharedInstance.mPadding)
            }.padding().background(MiamColor.sharedInstance.lightPrimaryBg).padding(.top, -8)
            
            //Divider
            HStack {
                HStack  {
                    
                }.frame(
                    minWidth: 0,
                    maxWidth: .infinity,
                    minHeight: 0,
                    maxHeight: .infinity,
                    alignment: .topLeading)
            }.background(MiamColor.sharedInstance.borderBottom).frame(height: 1.0, alignment: .leading).padding(.top, -8)
            
            //List
            ScrollView {
                ForEach(["","","","",""], id: \.self) { ingr in
                    ProductRow()
                }
                HStack {
                    HStack {
                        Text("Déjà dans vos placards").font(.system(size: 16.0, weight: .bold, design: .default)).foregroundColor(MiamColor.sharedInstance.bodyText)
                        
                        Spacer()
                        
                        Image("right").resizable().aspectRatio( contentMode: .fit).frame(width: 20, height: 20, alignment: .center)
                    }.padding(Dimension.sharedInstance.lPadding+7)
                }.background(MiamColor.sharedInstance.primaryLight).cornerRadius(10).frame(minWidth: 0, maxWidth: .infinity, alignment: .topLeading).padding(Dimension.sharedInstance.lPadding)
                
                HStack {
                    HStack {
                        Text("Oeufs").font(.system(size: 16.0, weight: .bold, design: .default)).foregroundColor(MiamColor.sharedInstance.bodyText)
                        
                        Spacer()
                        
                        Image(systemName: "plus").resizable().aspectRatio( contentMode: .fit).frame(width: 20, height: 20, alignment: .center).foregroundColor(MiamColor.sharedInstance.primary)
                        
                        Text("Ajouter").font(.system(size: 16.0, weight: .bold, design: .default)).foregroundColor(MiamColor.sharedInstance.primary)
                    }
                }.cornerRadius(10).frame(minWidth: 0, maxWidth: .infinity, alignment: .topLeading).padding(.horizontal, Dimension.sharedInstance.lPadding).padding(.bottom, Dimension.sharedInstance.lPadding)
            }
            
            //Bottom View
            HStack {
                ZStack(alignment: .center) {
                    Text("Retirer du panier").foregroundColor(MiamColor.sharedInstance.bodyText).font(.system(size: 16.0, weight: .regular, design: .default))
                }.padding(.horizontal, Dimension.sharedInstance.mPadding)
                
                ZStack(alignment: .center) {
                    Text("Continuer mes courses").foregroundColor(MiamColor.sharedInstance.white).padding(.horizontal, Dimension.sharedInstance.sPadding).font(.system(size: 16.0, weight: .bold, design: .default))
                }.frame(maxWidth: .infinity).frame(height: 100.0).background(MiamColor.sharedInstance.primaryText)
            }.frame(maxWidth: .infinity, alignment: .bottom).background(MiamColor.sharedInstance.white)
        }
    }
}

struct ProductView_Previews: PreviewProvider {
    static var previews: some View {
        ProductView()
    }
}
