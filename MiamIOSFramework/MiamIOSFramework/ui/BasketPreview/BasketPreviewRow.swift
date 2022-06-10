//
//  ProductRow.swift
//  MiamIOSFramework
//
//  Created by John on 15/05/2022.
//

import SwiftUI

struct BasketPreviewRow: View {
    @State var stepperValue: Float = 0.0

    private let productImageDimensions = CGSize(width: 90, height: 90)

    var body: some View {
        VStack(alignment: .leading) {
            HStack(alignment: .top) {
                AsyncImage(url: URL(string: "https://dev.w3.org/SVG/tools/svgweb/samples/svg-files/410.svg")!,
                           placeholder: { ProgressView() }, height: 100.0).frame(width: productImageDimensions.width, height: productImageDimensions.height, alignment: .topLeading)
                
                VStack (alignment: .leading) {
                    
                    Text("Bière blonde (40 cl)")
                        .foregroundColor(MiamColor.sharedInstance.black)
                        .font(.system(size: 16, weight: .bold, design: .default))
                        .padding(.leading, Dimension.sharedInstance.sPadding)
                    
                    Text("3 MONTS")
                        .foregroundColor(MiamColor.sharedInstance.bodyText)
                        .font(.system(size: 13, weight: .medium, design: .default))
                        .padding(.leading, Dimension.sharedInstance.sPadding)
                        .padding(.top, Dimension.sharedInstance.borderWidth)
                    
                    Text("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Lacus. 0.15 kg")
                        .foregroundColor(MiamColor.sharedInstance.bodyText)
                        .font(.system(size: 16, weight: .light, design: .default))
                        .padding(.leading, Dimension.sharedInstance.sPadding)
                        .padding(.top, Dimension.sharedInstance.borderWidth)
                    
                    Text("Plus d’infos").foregroundColor(MiamColor.sharedInstance.black).font(.system(size: 13, weight: .medium, design: .default))
                        .padding(.leading, Dimension.sharedInstance.sPadding)
                        .padding(.top, Dimension.sharedInstance.borderWidth)
                        .padding(.top, Dimension.sharedInstance.borderWidth)
                }
                
                Spacer()
                Button(action: {
                    
                }) {
                    Image("Bin")
                }.frame(width: 30, height: 30, alignment: .topTrailing)
            }
            
            //Price
            VStack {
                Text("1,59 €")
                    .foregroundColor(MiamColor.sharedInstance.primaryText)
                    .font(.system(size: 20, weight: .heavy, design: .default))
            }.frame(maxWidth: .infinity, alignment: .trailing)
                .padding(.top, Dimension.sharedInstance.borderWidth)
            
            //Ingredeient View
            HStack {
                HStack {
                    Button(action: {
                        
                    }) {
                        Image("sync").resizable()
                            .renderingMode(.original)
                            .frame(width: 30, height: 30, alignment: .topTrailing)
                        Text("Remplacer")
                            .foregroundColor(MiamColor.sharedInstance.primaryText).font(.system(size: 20, weight: .heavy, design: .default))
                    }.frame(width: 145, height: 30, alignment: .topTrailing)
                    
                    Spacer()
                    
                    // Plus Minus View
                    CounterView(count: 1, isDisable: false) {
                        ()
                    } decrease: {
                        ()
                    }
                }
            }
            
            //Divider
            Divider()
                .background(MiamColor.sharedInstance.borderBottom)
                .padding(.top, Dimension.sharedInstance.mPadding)
        }
        .padding([.horizontal, .top], Dimension.sharedInstance.lPadding)
        .frame(alignment: .topLeading)
    }
}

struct ProductRow_Previews: PreviewProvider {
    static var previews: some View {
        BasketPreviewRow()
    }
}
