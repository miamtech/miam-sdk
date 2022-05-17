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
                        Image(systemName: "heart").resizable().aspectRatio(contentMode: .fit)
                            .background(MiamColor.sharedInstance.musterd).frame(width: 50, height: 50, alignment: .center).padding(.leading, Dimension.sharedInstance.lPadding)
                        Spacer()
                        Button(action: {
                            
                        }) {
                            Image(systemName: "heart")
                                .renderingMode(.original)
                        }.frame(width: 50, height: 50, alignment: .center).background(MiamColor.sharedInstance.musterd).padding(.trailing, Dimension.sharedInstance.lPadding)
                    }.frame(height: 50.0, alignment: .topLeading)
                    Image(systemName: "heart").frame(
                        minWidth: 0,
                        maxWidth: .infinity,
                        minHeight: 0,
                        maxHeight: .infinity,
                        alignment: .topLeading
                    ).aspectRatio(contentMode: .fit).padding()
                        .background(MiamColor.sharedInstance.musterd)
                    
                    HStack {
                        HStack {
                            Button(action: {
                                
                            }) {
                                Image(systemName: "heart")
                                    .renderingMode(.original)
                            }
                            .padding(.leading).frame(width: 50.0, height: 50.0, alignment: .leading).background(Color.green)
                            
                            Button(action: {
                                
                            }) {
                                Image(systemName: "heart")
                                    .renderingMode(.original)
                            }
                            .padding(.leading).frame(width: 50.0, height: 50.0, alignment: .leading).background(Color.green)
                            
                            Spacer()
                            
                            Button(action: {
                                
                            }) {
                                Image(systemName: "heart")
                                    .renderingMode(.original)
                            }
                            .padding(.trailing).frame(width: 50.0, height: 50.0, alignment: .trailing).background(Color.green)
                        }.frame(
                            minWidth: 0,
                            maxWidth: .infinity,
                            minHeight: 0,
                            maxHeight: .infinity,
                            alignment: .topLeading
                        )
                    }.frame(height: 50.0, alignment: .topLeading).padding(.horizontal, Dimension.sharedInstance.lPadding)
                    
                    Spacer()
                    
                    HStack {
                        Text(MiamText.sharedInstance.recpieDetailTitle).foregroundColor(MiamColor.sharedInstance.black).font(.system(size: 20, weight: .heavy, design: .default)).padding(.horizontal, Dimension.sharedInstance.lPadding).frame(
                            minWidth: 0,
                            maxWidth: .infinity,
                            minHeight: 0,
                            maxHeight: .infinity,
                            alignment: .topLeading
                        )
                    }.frame(
                        minWidth: 0,
                        maxWidth: .infinity,
                        minHeight: 0,
                        maxHeight: .infinity,
                        alignment: .topLeading
                    )
                    
                    HStack {
                        Text(MiamText.sharedInstance.recpeitDetailsSubtitle).foregroundColor(MiamColor.sharedInstance.bodyText).padding(.horizontal, Dimension.sharedInstance.lPadding).font(.system(size: 16.0, weight: .regular, design: .default)).frame(
                            minWidth: 0,
                            maxWidth: .infinity,
                            minHeight: 0,
                            maxHeight: .infinity,
                            alignment: .topLeading)
                    }.frame(
                        minWidth: 0,
                        maxWidth: .infinity,
                        minHeight: 0,
                        maxHeight: .infinity,
                        alignment: .topLeading
                    )
                    
                    
                    HStack {
                        Spacer()
                        VStack {
                            HStack {
                                Image(systemName: "pencil").padding(.trailing).frame(width: 40.0, height: 40.0, alignment: .trailing).background(Color.green)
                                Image(systemName: "pencil").padding(.trailing).frame(width: 40.0, height: 40.0, alignment: .trailing).background(Color.green).opacity(0.7)
                                Image(systemName: "pencil").padding(.trailing).frame(width: 40.0, height: 40.0, alignment: .trailing).background(Color.green).opacity(0.7)
                            }
                            Text("Chef débutant").foregroundColor(MiamColor.sharedInstance.bodyText).font(.system(size: 13.0, weight: .regular, design: .default))
                        }
                        
                        VStack {
                            
                        }.frame(width: 1.0, height: 20.0, alignment: .center).background(MiamColor.sharedInstance.borderColor).padding(.horizontal, Dimension.sharedInstance.lPadding).padding(.bottom, 5.0)
                        
                        VStack {
                            HStack {
                                Spacer()
                                Image(systemName: "alarm").padding(.trailing).frame(width: 40.0, height: 40.0, alignment: .trailing).background(Color.green).opacity(0.7)
                                Spacer()
                            }
                            Text("25 min").foregroundColor(MiamColor.sharedInstance.bodyText).font(.system(size: 13.0, weight: .regular, design: .default))
                        }
                        Spacer()
                    }.padding(.horizontal, Dimension.sharedInstance.lPadding*2)
                }
                
                //Expand/Collapse Heading
                HStack {
                    Text(MiamText.sharedInstance.recpeitDetailsInfo).foregroundColor(MiamColor.sharedInstance.grey).font(.system(size: 13, weight: .bold, design: .default)).padding(Dimension.sharedInstance.mPadding).padding(.horizontal, Dimension.sharedInstance.lPadding)
                    
                    Button(action: {
                        
                    }) {
                        Image(systemName: "heart")
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
                                Image(systemName: "minus").foregroundColor(MiamColor.sharedInstance.white)
                            }.padding(.leading, Dimension.sharedInstance.lPadding).frame(width: 20.0, height: 20.0, alignment: .leading)
                            
                            
                            Text("4 pers.").foregroundColor(MiamColor.sharedInstance.white).font(.system(size: 13, weight: .bold, design: .default)).padding(Dimension.sharedInstance.lPadding)
                            
                            Button(action: {
                                
                            }) {
                                Image(systemName: "plus").foregroundColor(MiamColor.sharedInstance.white)
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
                    Image(systemName: "heart").aspectRatio(contentMode: .fit)
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

