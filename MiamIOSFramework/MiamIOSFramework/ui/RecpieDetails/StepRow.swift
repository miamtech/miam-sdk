//
//  StepRow.swift
//  MiamIOSFramework
//
//  Created by miam on 12/05/2022.
//

import SwiftUI
import shared

struct StepRow: View {
    
    private var index : Int
    private var step : RecipeStep
    private var onToogleCheckbox : () -> ()
    
    @SwiftUI.State var checkState:Bool = false
    
    init(index : Int, step : RecipeStep, isCheck: Bool = false){
        self.index = index
        self.step = step
        self.onToogleCheckbox = {}
        checkState = isCheck
    }
    
    var body: some View {
        HStack(spacing: 10.0) {
            ZStack {
                Circle()
                    .fill(MiamColor.sharedInstance.primaryText)
                    .frame(width: 35.0, height: 35.0)
                Text(String(index))
                    .foregroundColor(MiamColor.sharedInstance.white)
                    .frame( alignment: .center)
            }
            Text(step.attributes!.description.description)
                    .foregroundColor(MiamColor.sharedInstance.black20)
                    .font(.system(size: 16, weight: .regular, design: .default))
                    .multilineTextAlignment(.leading)
                    .padding(Dimension.sharedInstance.mPadding)
            
            Button(action:
                        {
                            self.checkState = !self.checkState
                    
                        }) {
                        ZStack(alignment: .center) {
                               Rectangle()
                                        .fill(self.checkState ? Color.green : Color.red)
                                        .frame(width:20, height:20, alignment: .center)
                                        .cornerRadius(5)
                        }
                    }
                    .foregroundColor(Color.white)
        }
       
    }
}
