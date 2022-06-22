//
//  BasketPreviewLoadingView.swift
//  MiamIOSFramework
//
//  Created by Miam on 21/06/2022.
//

import SwiftUI

struct BasketPreviewLoadingView: View {
    var body: some View {
        VStack(){
            Text("Préparation du repas ...")
            ProgressLoader(color: MiamColor.sharedInstance.primaryText)
        }
    }
}


