//
//  StepRow.swift
//  MiamIOSFramework
//
//  Created by miam on 12/05/2022.
//

import SwiftUI
import miamCore

@available(iOS 14, *)
struct StepRow: View {
    
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
                    .fill(MiamColor.sharedInstance.primaryText)
                    .frame(width: 35.0, height: 35.0)
                Text(String(index+1))
                    .foregroundColor(MiamColor.sharedInstance.white)
                    .frame( alignment: .center)
            }
            
            Text(step.attributes!.stepDescription ?? "")
                    .foregroundColor(MiamColor.sharedInstance.black20)
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
                                        
                            if(self.checkState){
                                Image("Check", bundle: Bundle(for: RecipeCardVM.self))
                                    .renderingMode(.original).frame( alignment: .center)
                            }
                            
                        }
                    }
                    .foregroundColor(Color.white)
        }
       
    }
}
