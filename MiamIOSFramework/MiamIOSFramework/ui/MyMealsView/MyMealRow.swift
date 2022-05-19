//
//  MyMealRow.swift
//  MiamIOSFramework
//
//  Created by John on 16/05/2022.
//

import SwiftUI

struct MyMealRow: View {
    @State private var isExpanded: Bool = false
    
    var body: some View {
        content
            .frame(maxWidth: .infinity)
    }
    
    private var content: some View {
        VStack(alignment: .leading) {
            header
            if isExpanded {
                Group {
                    ForEach(0 ..< 5) { _ in
                        MyMealItemRow()
                    }
                    HStack {
                        Text(MiamText.sharedInstance.mealRowAlready).font(.system(size: 16.0, weight: .bold, design: .default)).foregroundColor(MiamColor.sharedInstance.bodyText)
                        
                        Spacer()
                        
                        Image("right").resizable().aspectRatio( contentMode: .fit)
                    }.padding(Dimension.sharedInstance.lPadding).background(MiamColor.sharedInstance.primaryLight).cornerRadius(10).padding([.horizontal, .top], Dimension.sharedInstance.lPadding)
                    
                    HStack {
                        Text(MiamText.sharedInstance.mealRowAlready).font(.system(size: 16.0, weight: .bold, design: .default)).foregroundColor(MiamColor.sharedInstance.bodyText)
                        
                        Spacer()
                        
                        Image("right").resizable().aspectRatio( contentMode: .fit)
                    }.padding(Dimension.sharedInstance.lPadding).background(MiamColor.sharedInstance.borderBottom).cornerRadius(10).padding([.horizontal, .top], Dimension.sharedInstance.lPadding)
                }
            }
        }
    }
    
    private var header: some View {
        VStack {
            //Product View
            VStack(alignment: .leading) {
                HStack {
                    WebView(url: URL(string: "https://dev.w3.org/SVG/tools/svgweb/samples/svg-files/410.svg")!)
                        .frame(width: 120, height: 120, alignment: .topLeading)
                    
                    VStack (alignment: .leading){
                        
                        Text("Welsh royal à la 3 Monts").foregroundColor(MiamColor.sharedInstance.black).font(.system(size: 16, weight: .heavy, design: .default)).padding(.leading, Dimension.sharedInstance.sPadding)
                        
                        Text("6 articles").foregroundColor(MiamColor.sharedInstance.bodyText).font(.system(size: 16, weight: .light, design: .default)).padding(.leading, Dimension.sharedInstance.sPadding).padding(.top, Dimension.sharedInstance.borderWidth)
                        
                        Text("4,93 € par personne").foregroundColor(MiamColor.sharedInstance.bodyText).font(.system(size: 16, weight: .light, design: .default)).padding(.leading, Dimension.sharedInstance.sPadding)
                    }
                }
                
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
                            }.padding(.leading, Dimension.sharedInstance.lPadding)
                            
                            Text("4 pers.").foregroundColor(MiamColor.sharedInstance.white).font(.system(size: 13, weight: .bold, design: .default)).padding(Dimension.sharedInstance.lPadding)
                            
                            Button(action: {
                                
                            }) {
                                Image(systemName: "plus").foregroundColor(MiamColor.sharedInstance.white)
                            }.padding(.trailing, Dimension.sharedInstance.lPadding)
                        }
                        .background(MiamColor.sharedInstance.primaryText)
                        .cornerRadius(25.0)
                        
                        if isExpanded {
                            Image("down").resizable()
                                .background(MiamColor.sharedInstance.white).padding(.vertical, Dimension.sharedInstance.lPadding+4).rotationEffect(.degrees(180))
                        }else{
                            Image("down").resizable()
                                .frame(width: 20.0, height: 12.0, alignment: .center)
                                .background(MiamColor.sharedInstance.white).padding(.vertical, Dimension.sharedInstance.lPadding+4).rotationEffect(.degrees(0))
                        }
                    }
                }.padding(.top, Dimension.sharedInstance.mPadding)
            }.padding().background(MiamColor.sharedInstance.lightPrimaryBg).padding(.top, -8)
            
            //Divider
            Divider().background(MiamColor.sharedInstance.borderBottom).padding(.top, -8)
        }
        .onTapGesture {
            withAnimation { isExpanded.toggle() }
        }
    }
}

struct MyMealRow_Previews: PreviewProvider {
    static var previews: some View {
        MyMealRow()
    }
}
