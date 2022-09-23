//
//  RecipeStepsView.swift
//  MiamIOSFramework
//
//  Created by Vincent Kergonna on 21/09/2022.
//

import SwiftUI
import miamCore

@available(iOS 14, *)
struct RecipeStepsView: View {
    let steps: [RecipeStep]
    @SwiftUI.State private var activeStep = -1
    
    var body: some View {
        HStack {
            Text(MiamText.sharedInstance.steps).foregroundColor(Color.miamColor(.black))
                .font(.system(size: 20, weight: .heavy, design: .default))
                .padding(Dimension.sharedInstance.lPadding)
            Spacer()
        }.frame(height: 60.0, alignment: .topLeading)
            .padding(.top, Dimension.sharedInstance.lPadding)
        
        //Steps
        Divider().background(Color.miamColor(.borderLight))
            .padding(.horizontal, Dimension.sharedInstance.lPadding)
        
        //Steps ListView
        VStack {
            VStack {
                ForEach(steps, id: \.self) { step in
                    let index = Int(step.attributes!.stepNumber)
                    let isChecked = activeStep > index
                    StepRow(
                        index: index,
                        step: step,
                        isCheck: isChecked,
                        onToogleCheckbox: {
                            activeStep = index + 1
                        }
                    )
                }
            }.padding(.vertical, Dimension.sharedInstance.lPadding)
        }.padding( .horizontal, Dimension.sharedInstance.lPadding)
    }
}
