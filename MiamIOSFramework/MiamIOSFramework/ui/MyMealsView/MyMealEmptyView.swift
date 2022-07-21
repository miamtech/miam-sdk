//
//  MyMealEmptyView.swift
//  MiamIOSFramework
//
//  Created by Miam on 21/07/2022.
//

import SwiftUI

@available(iOS 14, *)
struct MyMealEmptyView: View {
    var body: some View {
        GeometryReader { metrics in
        VStack{
            HStack{
                Spacer()
                Text("Vous n'avez aucune idée repas dans votre panier.").frame(width: metrics.size.width / 2)
                    .fixedSize(horizontal: false, vertical: true)
                    .multilineTextAlignment(.center)
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
}
