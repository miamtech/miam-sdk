//
//  MyMealLoadingView.swift
//  MiamIOSFramework
//
//  Created by Miam on 21/07/2022.
//

import SwiftUI

@available(iOS 14, *)
struct MyMealLoadingView: View {
    let loadingText: String
    
    var body: some View {
        VStack {
            Spacer()
            HStack{
                Spacer()
                VStack{
                    ProgressLoader(color: Color.miamColor(.primary))
                    Text(MiamText.sharedInstance.simmering)
                }
                Spacer()
                
            }
            Spacer()
        }.frame(
            minWidth: 0,
            maxWidth: .infinity,
            minHeight: 0,
            maxHeight: .infinity,
            alignment: .topLeading
        )
    }
}


