//
//  RecpieDetailsView.swift
//  MiamIOSFramework
//
//  Created by John on 11/05/2022.
//

import SwiftUI

struct RecpieDetailsView: View {
    var body: some View {
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
                    VStack {
                        HStack {
                            Image(systemName: "pencil").padding(.trailing).frame(width: 50.0, height: 50.0, alignment: .trailing).background(Color.green)
                            Image(systemName: "pencil").padding(.trailing).frame(width: 50.0, height: 50.0, alignment: .trailing).background(Color.green).opacity(0.7)
                            Image(systemName: "pencil").padding(.trailing).frame(width: 50.0, height: 50.0, alignment: .trailing).background(Color.green).opacity(0.7)
                        }
                        Text("Chef débutant").foregroundColor(MiamColor.sharedInstance.bodyText).font(.system(size: 13.0, weight: .regular, design: .default))
                    }
                    
                    Spacer()
                    VStack {
                        HStack {
                            Spacer()
                            Image(systemName: "alarm").padding(.trailing).frame(width: 50.0, height: 50.0, alignment: .trailing).background(Color.green).opacity(0.7)
                            Spacer()
                        }
                        Text("25 min").foregroundColor(MiamColor.sharedInstance.bodyText).font(.system(size: 13.0, weight: .regular, design: .default))
                    }
                }.padding(.horizontal, Dimension.sharedInstance.lPadding)
                
                Spacer()
            }
        }.frame(
            minWidth: 0,
            maxWidth: .infinity,
            minHeight: 0,
            maxHeight: .infinity,
            alignment: .topLeading
        )
    }
}

struct RecpieDetailsView_Previews: PreviewProvider {
    static var previews: some View {
        RecpieDetailsView().previewDevice("iPhone 8")
    }
}
