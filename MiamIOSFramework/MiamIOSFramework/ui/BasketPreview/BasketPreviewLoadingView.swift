//
//  BasketPreviewLoadingView.swift
//  MiamIOSFramework
//
//  Created by Miam on 21/06/2022.
//

import SwiftUI

@available(iOS 14, *)
struct BasketPreviewLoadingView: View {
    var body: some View {
        VStack(){
            Text("Préparation du repas ...")
            ProgressLoader(color: Color.miamColor(.primaryText))
        }
    }
}


