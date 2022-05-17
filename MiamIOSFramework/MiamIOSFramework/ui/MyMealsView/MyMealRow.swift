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
                        HStack {
                            Text(MiamText.sharedInstance.mealRowAlready).font(.system(size: 16.0, weight: .bold, design: .default)).foregroundColor(MiamColor.sharedInstance.bodyText)
                            
                            Spacer()
                            
                            Image("right").resizable().aspectRatio( contentMode: .fit).frame(width: 20, height: 20, alignment: .center)
                        }.padding(Dimension.sharedInstance.lPadding)
                    }.background(MiamColor.sharedInstance.primaryLight).cornerRadius(10).frame(minWidth: 0, maxWidth: .infinity, alignment: .topLeading).padding(Dimension.sharedInstance.lPadding)
                    
                    HStack {
                        HStack {
                            Text(MiamText.sharedInstance.mealRowAlready).font(.system(size: 16.0, weight: .bold, design: .default)).foregroundColor(MiamColor.sharedInstance.bodyText)
                            
                            Spacer()
                            
                            Image("right").resizable().aspectRatio( contentMode: .fit).frame(width: 20, height: 20, alignment: .center)
                        }.padding(Dimension.sharedInstance.lPadding+7)
                    }.background(MiamColor.sharedInstance.borderBottom).cornerRadius(10).frame(minWidth: 0, maxWidth: .infinity, alignment: .topLeading).padding([.bottom, .horizontal],Dimension.sharedInstance.lPadding)
                }
            }
        }
    }
    
    private var header: some View {
        VStack {
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
                        
                        if isExpanded {
                            Image("up").resizable().frame(width: 20.0, height: 12.0, alignment: .center).background(MiamColor.sharedInstance.white).padding(.vertical, Dimension.sharedInstance.lPadding+4).padding(.leading, Dimension.sharedInstance.lPadding)
                        }else{
                            Image("down").resizable().frame(width: 20.0, height: 12.0, alignment: .center).background(MiamColor.sharedInstance.white).padding(.vertical, Dimension.sharedInstance.lPadding+4).padding(.leading, Dimension.sharedInstance.lPadding)
                        }
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
