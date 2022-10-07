//
//  StepRow.swift
//  MiamIOSFramework
//
//  Created by miam on 12/05/2022.
//

import SwiftUI
import miamCore

@available(iOS 14, *)
struct RecipeDetailsStepRow: View {
    
    private var index : Int
    private var step : RecipeStep
    private var onToogleCheckbox : () -> ()
    
    private var checkState: Bool
    
    init(index : Int, step : RecipeStep, isCheck: Bool, onToogleCheckbox : @escaping() -> () ){
        self.index = index
        self.step = step
        self.onToogleCheckbox = onToogleCheckbox
        checkState = isCheck
    }
    
    var body: some View {
        HStack() {
            ZStack {
                Circle()
                    .fill(Color.miamColor(.primaryText))
                    .frame(width: 35.0, height: 35.0)
                Text(String(index+1))
                    .foregroundColor(Color.miamColor(.white))
                    .frame( alignment: .center)
            }
            
            Text(step.attributes!.stepDescription ?? "")
                .foregroundColor(Color.miamColor(.black20))
                .font(.system(size: 16, weight: .regular, design: .default))
                .multilineTextAlignment(.leading)
                .padding(Dimension.sharedInstance.mPadding).fixedSize(horizontal: false, vertical: true)
            Spacer()
            Button(action:
                        {
                            onToogleCheckbox()
                        }) {
                        ZStack(alignment: .center) {
                               Rectangle()
                                .fill(.white)
                                .cornerRadius(5)
                                .border(.black, width: 1)
                                        .frame(width:20, height:20, alignment: .center)
                                        
                            if(self.checkState) {
                                Image.miamImage(icon: .check)
                                    .renderingMode(.original).frame( alignment: .center)
                            }
                            
                        }
                    }
                    .foregroundColor(Color.white)
        }
       
    }
}
