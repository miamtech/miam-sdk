//
//  ProductView.swift
//  MiamIOSFramework
//
//  Created by John on 15/05/2022.
//

import SwiftUI

public struct BasketPreviewView: View {

    public init() {
        
    }

    public var body: some View {
        VStack {
            // Top Bar
            HStack {
                Image("Arrow")
                    .resizable()
                    .aspectRatio(contentMode: .fit)
                    .frame(width: 30, height: 30, alignment: .center)
                    .padding(.leading, Dimension.sharedInstance.lPadding)
                
                Text("6 produits ajoutés à votre panier")
                    .font(.system(size: 16.0, weight: .bold, design: .default))
                    .padding(.leading, Dimension.sharedInstance.lPadding)
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
                    Image("recipe").resizable()
                        .frame(width: 150, height: 150, alignment: .topLeading)
                    
                    VStack (alignment: .leading){
                        
                        Text("Welsh royal à la 3 Monts")
                            .foregroundColor(MiamColor.sharedInstance.black)
                            .font(.system(size: 16, weight: .heavy, design: .default))
                            .padding(.leading, Dimension.sharedInstance.sPadding)
                        
                        Text("6 articles")
                            .foregroundColor(MiamColor.sharedInstance.bodyText)
                            .font(.system(size: 16, weight: .light, design: .default))
                            .padding(.leading, Dimension.sharedInstance.sPadding)
                            .padding(.top, Dimension.sharedInstance.borderWidth)
                        
                        Text("4,93 € par personne")
                            .foregroundColor(MiamColor.sharedInstance.bodyText)
                            .font(.system(size: 16, weight: .light, design: .default))
                            .padding(.leading, Dimension.sharedInstance.sPadding)
                    }.frame(width: .infinity, height: 150, alignment: .topLeading)
                }.frame(width: .infinity, height: 150, alignment: .topLeading)
                
                //Ingredeient View
                HStack {
                    HStack {
                        Text("19,72 €")
                            .foregroundColor(MiamColor.sharedInstance.primaryText)
                            .font(.system(size: 20, weight: .heavy, design: .default))
                        
                        Spacer()
                        
                        // Plus Minus View
                        CounterView(count: 4, isDisable: false) {
                            // TODO: increase. Use CountChange event?
                        } decrease: {
                            // TODO: decrease
                        }

                    }.frame(
                        minWidth: 0,
                        maxWidth: .infinity,
                        minHeight: 0,
                        maxHeight: .infinity,
                        alignment: .topLeading
                    )
                }.frame(height: 50.0, alignment: .topLeading).padding(.top, Dimension.sharedInstance.mPadding)
            }.padding().background(MiamColor.sharedInstance.lightPrimaryBg)
                .padding(.top, -8)
            
            //Divider
            HStack {
                HStack  {
                    
                }.frame(
                    minWidth: 0,
                    maxWidth: .infinity,
                    minHeight: 0,
                    maxHeight: .infinity,
                    alignment: .topLeading)
            }.background(MiamColor.sharedInstance.borderBottom)
                .frame(height: 1.0, alignment: .leading)
                .padding(.top, -8)
            
            //List
            ScrollView {
                ForEach([""], id: \.self) { ingr in
                    BasketPreviewRow()
                }
                HStack {
                    Text(MiamText.sharedInstance.mealRowAlready)
                        .font(.system(size: 16.0, weight: .bold, design: .default))
                        .foregroundColor(MiamColor.sharedInstance.bodyText)
                    
                    Spacer()
                    
                    Image("Caret")
                        .resizable()
                        .aspectRatio( contentMode: .fit)
                        .frame(width: 30, height: 30, alignment: .center)
                }
                .padding(Dimension.sharedInstance.lPadding)
                .background(MiamColor.sharedInstance.primaryLight)
                .cornerRadius(10).padding(.all, Dimension.sharedInstance.lPadding)
                
                HStack {
                    Text("Oeufs")
                        .font(.system(size: 16.0, weight: .bold, design: .default))
                        .foregroundColor(MiamColor.sharedInstance.bodyText)
                    
                    Spacer()
                    
                    Image("PlusGreen")
                        .resizable()
                        .aspectRatio( contentMode: .fit)
                        .frame(width: 30, height: 30, alignment: .center)
                        .foregroundColor(MiamColor.sharedInstance.primary)
                    
                    Text("Ajouter")
                        .font(.system(size: 16.0, weight: .bold, design: .default))
                        .foregroundColor(MiamColor.sharedInstance.primary)
                }.padding(.horizontal, Dimension.sharedInstance.lPadding)
                    .padding(.bottom, Dimension.sharedInstance.lPadding)
            }
            
            //Bottom View
            HStack {
                ZStack(alignment: .center) {
                    Text("Retirer du panier")
                        .foregroundColor(MiamColor.sharedInstance.bodyText)
                        .font(.system(size: 16.0, weight: .regular, design: .default))
                }.padding(.horizontal, Dimension.sharedInstance.mPadding)
                
                ZStack(alignment: .center) {
                    Text("Continuer mes courses")
                        .foregroundColor(MiamColor.sharedInstance.white
                        ).padding(.horizontal, Dimension.sharedInstance.sPadding)
                        .font(.system(size: 16.0, weight: .bold, design: .default))
                }.frame(maxWidth: .infinity)
                    .frame(height: 100.0)
                    .background(MiamColor.sharedInstance.primaryText)
            }.frame(maxWidth: .infinity, alignment: .bottom).background(MiamColor.sharedInstance.white)
        }
    }
}

struct BasketPreviewView_Previews: PreviewProvider {
    static var previews: some View {
        BasketPreviewView()
    }
}

