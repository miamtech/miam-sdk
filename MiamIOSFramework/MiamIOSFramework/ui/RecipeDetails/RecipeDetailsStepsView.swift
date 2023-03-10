//
//  RecipeStepsView.swift
//  MiamIOSFramework
//
//  Created by Vincent Kergonna on 21/09/2022.
//

import SwiftUI
import miamCore

@available(iOS 14, *)
public struct RecipeDetailsStepsView: View {
    let steps: [RecipeStep]
    @SwiftUI.State private var activeStep = -1

    public var body: some View {
        if let template = Template.sharedInstance.recipeDetailStepsViewTemplate {
            template(steps)
        } else {
            HStack {
                Text(MiamText.sharedInstance.steps)
                    .miamFontStyle(style: MiamFontStyleProvider.sharedInstance.titleStyle)
                    .foregroundColor(Color.miamColor(.black))
                    .padding(Dimension.sharedInstance.lPadding)
                Spacer()
            }.frame(height: 60.0, alignment: .topLeading)
                .padding(.top, Dimension.sharedInstance.lPadding)

            // Steps
            Divider().background(Color.miamColor(.borderLight))
                .padding(.horizontal, Dimension.sharedInstance.lPadding)

            // Steps ListView
            VStack {
                VStack {
                    ForEach(steps, id: \.self) { step in
                        let stepNumber = step.attributes?.stepNumber ?? 0
                        let index = Int(stepNumber)
                        let isChecked = activeStep > index
                        RecipeDetailsStepRow(
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
}
