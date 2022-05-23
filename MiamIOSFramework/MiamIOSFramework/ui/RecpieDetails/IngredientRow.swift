//
//  IngredientRow.swift
//  MiamIOSFramework
//
//  Created by John on 12/05/2022.
//

import SwiftUI

struct IngredientRow: View {
    var body: some View {
        HStack {
            Text("Bière blonde")
                .foregroundColor(MiamColor.sharedInstance.black)
                .font(.system(size: 16, weight: .regular, design: .default))
                .padding(Dimension.sharedInstance.mPadding)
            
            Spacer()
            
            Text("40 cL")
                .foregroundColor(MiamColor.sharedInstance.black)
                .font(.system(size: 16, weight: .bold, design: .default))
                .cornerRadius(25.0).padding(Dimension.sharedInstance.mPadding)
        }
    }
}

struct IngredientRow_Previews: PreviewProvider {
    static var previews: some View {
        IngredientRow()
    }
}
