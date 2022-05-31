//
//  RecipeCard.swift
//  MiamIOSFramework
//
//  Created by John on 13/05/2022.
//

import SwiftUI

struct RecipeCard: View {
    var body: some View {
        VStack {
            //Product View
            ZStack(alignment: .center) {
                    Text("Product")
                    .foregroundColor(MiamColor.sharedInstance.black)
                    .font(.system(size: 16.0, weight: .bold, design: .default))
            }.frame(minWidth: 0, maxWidth: .infinity, minHeight: 250.0, maxHeight: 250.0, alignment: .center)
                .background(MiamColor.sharedInstance.primaryLight)
                .cornerRadius(12.0).padding([.horizontal, .bottom] ,Dimension.sharedInstance.lPadding)
            
            //Product Detail
            VStack {
                
                ZStack(alignment: .topLeading) {
                    WebView(url: URL(string: "https://dev.w3.org/SVG/tools/svgweb/samples/svg-files/410.svg")!).frame( maxWidth: .infinity, minHeight: 250, maxHeight: 250, alignment: .center)
                    
                    VStack(alignment: .trailing){
                        HStack {
                            Image("Like")
                                .frame(width: 55.0, height: 55.0, alignment: .center)
                                .background(MiamColor.sharedInstance.white).foregroundColor(MiamColor.sharedInstance.primaryText)
                        }
                        .background(MiamColor.sharedInstance.white)
                        .cornerRadius(55/2)
                        .padding(.trailing, Dimension.sharedInstance.lPadding)
                    }.frame(minWidth: 0, maxWidth: .infinity,minHeight: 0, maxHeight: 55.0, alignment: .trailing)
                        .padding(.top, Dimension.sharedInstance.xlPadding)
                    
                    VStack(alignment: .leading) {
                        Text("Idée repas")
                            .font(.system(size: 20.0, weight: .heavy, design: .default))
                            .padding(.leading, 110)
                            .padding(.trailing, Dimension.sharedInstance.lPadding+6)
                    }.frame(minWidth: 0, minHeight: 0, maxHeight: 55.0, alignment: .center)
                        .background(MiamColor.sharedInstance.white)
                        .cornerRadius(27.5).padding(.top, Dimension.sharedInstance.xlPadding)
                        .padding(.leading, -20)
                    
                    HStack(alignment: .center) {
                        HStack(alignment: .center) {
                            Image("bucket")
                                .resizable()
                                .aspectRatio( contentMode: .fit)
                                .padding(.trailing, 2)
                                .frame( maxWidth: .infinity, minHeight: 0, maxHeight: .infinity, alignment: .topLeading)
                                .padding(Dimension.sharedInstance.mlPadding)
                        }.background(MiamColor.sharedInstance.musterd)
                            .frame(width: 70, height: 70, alignment: .center)
                            .cornerRadius(35)
                    }.frame(width: 75, height: 75, alignment: .center).background(MiamColor.sharedInstance.white)
                        .cornerRadius(37.5)
                        .padding(.top,  Dimension.sharedInstance.lPadding + 8)
                        .padding(.leading, Dimension.sharedInstance.mPadding)
                }.background(MiamColor.sharedInstance.greySurface)
                
                VStack {
                    Text("Poêlée de pates dindes et épinards sur deux grandes grandes lignes asdf asdf asdf asdf asdf asdf sadf asdf sdf asfd")
                        .lineLimit(2)
                        .foregroundColor(MiamColor.sharedInstance.black)
                        .font(.system(size: 16.0, weight: .bold, design: .default))
                        .padding(Dimension.sharedInstance.lPadding)
                }
                
                Button("Découvrir la recette") {
                    
                }.foregroundColor(MiamColor.sharedInstance.white)
                    .frame(minHeight: 50.0, maxHeight: 50.0)
                    .padding(.horizontal, Dimension.sharedInstance.lPadding)
                    .background(MiamColor.sharedInstance.primaryText)
                    .cornerRadius(25)
                    .font(.system(size: 16.0, weight: .bold, design: .default))
                    .padding(.bottom, Dimension.sharedInstance.lPadding)
                
            }.background(MiamColor.sharedInstance.lightPrimaryBg)
                .cornerRadius(12.0)
                .padding(.horizontal ,Dimension.sharedInstance.lPadding)
                .padding(.bottom, Dimension.sharedInstance.lPadding)
        }
    }
}

struct MealRow_Previews: PreviewProvider {
    static var previews: some View {
        RecipeCard()
    }
}

