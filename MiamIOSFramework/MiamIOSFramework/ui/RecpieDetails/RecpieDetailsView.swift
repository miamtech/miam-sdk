//
//  RecpieDetailsView.swift
//  MiamIOSFramework
//
//  Created by John on 11/05/2022.
//

import SwiftUI

struct RecpieDetailsView: View {
    let ingredients: [String] = ["","","","","",""]
    
    var body: some View {
        ZStack(alignment: .bottom) {
            
            ScrollView {
                VStack {
                    HStack {
                        Image( "bucket").resizable().aspectRatio(contentMode: .fit)
                            .padding(Dimension.sharedInstance.sPadding).padding([.bottom], 5).padding(.trailing, 2).frame(width: 40, height: 40, alignment: .center).background(MiamColor.sharedInstance.musterd).cornerRadius(25).padding([.leading], Dimension.sharedInstance.lPadding)
                        Spacer()
                        Button(action: {
                            
                        }) {
                            Image("cross")
                                .renderingMode(.original).frame(width: 14, height: 14, alignment: .center)
                        }.frame(width: 40, height: 40, alignment: .center)
                    }.frame(height: 50.0, alignment: .topLeading)
                    Divider().background(MiamColor.sharedInstance.borderBottom)
                    WebView(url: URL(string: "https://dev.w3.org/SVG/tools/svgweb/samples/svg-files/410.svg")!).frame(
                        minWidth: 0,
                        maxWidth: .infinity,
                        minHeight: 0,
                        maxHeight: .infinity,
                        alignment: .topLeading
                    ).aspectRatio(contentMode: .fit).padding()
                    HStack {
                        HStack {
                            Button(action: {
                                
                            }) {
                                Image("Like")
                                    .renderingMode(.original)
                            }
                            .padding(.leading).frame(width: 40, height: 40, alignment: .leading)
                            
                            Button(action: {
                                
                            }) {
                                Image("Print")
                                    .renderingMode(.original)
                            }
                            .padding(.leading).frame(width: 40, height: 40, alignment: .leading)
                            
                            Spacer()
                            
                            Button(action: {
                                
                            }) {
                                Image("Help")
                                    .renderingMode(.original)
                            }
                            .frame(width: 40.0, height: 40.0, alignment: .center).background(MiamColor.sharedInstance.greySurface).cornerRadius(25)
                        }.frame(
                            minWidth: 0,
                            maxWidth: .infinity,
                            minHeight: 0,
                            maxHeight: .infinity,
                            alignment: .topLeading
                        )
                    }.frame(height: 50.0, alignment: .topLeading).padding(.horizontal, Dimension.sharedInstance.lPadding)
                    
                    Spacer()
                    
                    Text(MiamText.sharedInstance.recpieDetailTitle).foregroundColor(MiamColor.sharedInstance.black).font(.system(size: 20, weight: .heavy, design: .default)).padding(.horizontal, Dimension.sharedInstance.lPadding).frame(
                        minWidth: 0,
                        maxWidth: .infinity,
                        minHeight: 0,
                        maxHeight: .infinity,
                        alignment: .topLeading
                    )
                    
                    Text(MiamText.sharedInstance.recpeitDetailsSubtitle).foregroundColor(MiamColor.sharedInstance.bodyText).padding(.horizontal, Dimension.sharedInstance.lPadding).font(.system(size: 16.0, weight: .regular, design: .default)).frame(
                        minWidth: 0,
                        maxWidth: .infinity,
                        minHeight: 0,
                        maxHeight: .infinity,
                        alignment: .topLeading)
                    
                    ZStack {
                        HStack(alignment: .center) {
                            HStack {
                                Spacer()
                                VStack {
                                    HStack{
                                        Image( "Difficulty").frame(width: 24.0, height: 24.0, alignment: .center)
                                        Image( "Difficulty").frame(width: 24.0, height: 24.0, alignment: .center).opacity(0.7)
                                        Image( "Difficulty").frame(width: 24.0, height: 24.0, alignment: .center).opacity(0.7)
                                    }
                                    Text("Chef débutant").foregroundColor(MiamColor.sharedInstance.bodyText).font(.system(size: 13.0, weight: .regular, design: .default))
                                }
                                Spacer()
                            }
                            Spacer()
                            HStack {
                                Spacer()
                                VStack(alignment: .center) {
                                    Image( "Clock").frame(width: 25, height:25, alignment: .center)
                                    Text("25 min").foregroundColor(MiamColor.sharedInstance.bodyText).font(.system(size: 13.0, weight: .regular, design: .default))
                                }
                                Spacer()
                            }
                        }
                        
                        HStack {
                            
                        }.frame(width: 1, height: 15, alignment: .center).background(MiamColor.sharedInstance.borderBottom)
                        
                    }.padding(.top, Dimension.sharedInstance.lPadding)
                }
                
                //Expand/Collapse Heading
                HStack {
                    Text(MiamText.sharedInstance.recpeitDetailsInfo).foregroundColor(MiamColor.sharedInstance.grey).font(.system(size: 13, weight: .bold, design: .default)).padding(Dimension.sharedInstance.mPadding).padding(.leading, Dimension.sharedInstance.lPadding)
                    
                    Button(action: {
                        
                    }) {
                        Image("chevron-down-grey")
                            .renderingMode(.original)
                    }.padding(.trailing, Dimension.sharedInstance.lPadding).frame(width: 20.0, height: 20.0, alignment: .trailing)
                }
                .background(MiamColor.sharedInstance.greySurface).cornerRadius(15.0).padding()
                
                //Ingredients Heading
                HStack {
                    HStack {
                        Text("6 ingrédients").foregroundColor(MiamColor.sharedInstance.primaryText).font(.system(size: 20, weight: .heavy, design: .default)).padding(Dimension.sharedInstance.lPadding)
                        
                        Spacer()
                        
                        HStack {
                            Button(action: {
                                
                            }) {
                                Image("Minus").foregroundColor(MiamColor.sharedInstance.white)
                            }.padding(.leading, Dimension.sharedInstance.lPadding).frame(width: 20.0, height: 20.0, alignment: .leading)
                            
                            
                            Text("4 pers.").foregroundColor(MiamColor.sharedInstance.white).font(.system(size: 13, weight: .bold, design: .default)).padding(Dimension.sharedInstance.lPadding)
                            
                            Button(action: {
                                
                            }) {
                                Image("Plus").foregroundColor(MiamColor.sharedInstance.white)
                            }.padding(.trailing, Dimension.sharedInstance.lPadding).frame(width: 20.0, height: 20.0, alignment: .trailing)
                        }.frame(width: 140.0, height: 50.0, alignment: .center).background(MiamColor.sharedInstance.primaryText).cornerRadius(25.0).padding(.trailing, Dimension.sharedInstance.lPadding)
                    }.frame(
                        minWidth: 0,
                        maxWidth: .infinity,
                        minHeight: 0,
                        maxHeight: .infinity,
                        alignment: .topLeading
                    )
                }.frame(height: 60.0, alignment: .topLeading)
                
                //Divider
                Divider().background(MiamColor.sharedInstance.borderBottom).padding(.horizontal, Dimension.sharedInstance.lPadding)
                
                //Ingredeients ListView
                VStack {
                    VStack {
                        ForEach(ingredients, id: \.self) { ingr in
                            IngredientRow()
                        }
                    }.padding(.vertical, Dimension.sharedInstance.lPadding)
                }.background(MiamColor.sharedInstance.lightPrimaryBg).cornerRadius(15.0).padding( .horizontal, Dimension.sharedInstance.lPadding)
                
                //Étapes Heading
                HStack {
                    HStack {
                        Text("Étapes").foregroundColor(MiamColor.sharedInstance.primaryText).font(.system(size: 20, weight: .heavy, design: .default)).padding(Dimension.sharedInstance.lPadding)
                        
                        Spacer()
                    }.frame(
                        minWidth: 0,
                        maxWidth: .infinity,
                        minHeight: 0,
                        maxHeight: .infinity,
                        alignment: .topLeading
                    )
                }.frame(height: 60.0, alignment: .topLeading).padding(.top, Dimension.sharedInstance.lPadding)
                
                //Steps
                Divider().background(MiamColor.sharedInstance.borderBottom).padding(.horizontal, Dimension.sharedInstance.lPadding)
                
                //Steps ListView
                VStack {
                    VStack {
                        ForEach(ingredients, id: \.self) { ingr in
                            StepRow()
                        }
                    }.padding(.vertical, Dimension.sharedInstance.lPadding)
                }.padding( .horizontal, Dimension.sharedInstance.lPadding)
            }.frame(
                minWidth: 0,
                maxWidth: .infinity,
                minHeight: 0,
                maxHeight: .infinity,
                alignment: .topLeading
            ).padding(.bottom, 100.0)
            
            HStack {
                ZStack(alignment: .leading) {
                    VStack {
                        Text("4,93 €").foregroundColor(MiamColor.sharedInstance.black).font(.system(size: 16.0, weight: .bold, design: .default))
                        Text("par personne").foregroundColor(MiamColor.sharedInstance.black).font(.system(size: 16.0, weight: .regular, design: .default))
                    }
                }.padding(.horizontal, Dimension.sharedInstance.mPadding)
                
                HStack {
                    Text("Ajouter les ingrédients").foregroundColor(MiamColor.sharedInstance.white).padding(.horizontal, Dimension.sharedInstance.sPadding).font(.system(size: 16.0, weight: .bold, design: .default))
                    Image("Cart").aspectRatio(contentMode: .fit)
                        .frame(width: 40, height: 40, alignment: .center).foregroundColor(Color.white)
                }.frame(maxWidth: .infinity).frame(height: 100.0).background(MiamColor.sharedInstance.primaryText)
            }.frame(maxWidth: .infinity, alignment: .bottom).background(MiamColor.sharedInstance.white)
        }
    }
}

struct RecpieDetailsView_Previews: PreviewProvider {
    static var previews: some View {
        RecpieDetailsView().previewDevice("iPhone 8")
    }
}


