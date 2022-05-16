//
//  MealView.swift
//  MiamIOSFramework
//
//  Created by John on 13/05/2022.
//

import SwiftUI

struct MealView: View {
    var body: some View {
        ScrollView {
            ForEach(["","",""], id: \.self) { ingr in
                MealRow()
            }
        }
    }
}

struct MealView_Previews: PreviewProvider {
    static var previews: some View {
        MealView().previewDevice("iPhone 8")
    }
}
