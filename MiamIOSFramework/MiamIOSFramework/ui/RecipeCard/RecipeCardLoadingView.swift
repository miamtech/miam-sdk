//
//  RecipeCardLoadingView.swift
//  MiamIOSFramework
//
//  Created by Miam on 20/06/2022.
//

import Foundation
import SwiftUI

public struct RecipeCardLoadingView: View {
    
    private struct Constants {
        static let duration: Double = 0.9
        static let minOpacity: Double = 0.25
        static let maxOpacity: Double = 1.0
        static let cornerRadius: CGFloat = 2.0
    }
    
    @State private var opacity: Double = Constants.minOpacity
    
    
    public var body: some View {
        
        VStack {
            VStack() {
                ZStack(alignment: .topLeading) {
                    Rectangle()
                        .fill(MiamColor.sharedInstance.borderColor)
                        .frame(width: .infinity,height: 245 )
                    
                }.frame(height: 245)
                Text( "")
                    .lineLimit(2)
                    .foregroundColor(MiamColor.sharedInstance.black)
                    .font(.system(size: 16.0, weight: .bold, design: .default))
                    .padding(Dimension.sharedInstance.lPadding)
                Rectangle()
                    .fill(MiamColor.sharedInstance.borderColor).opacity(0.1)
                    .frame(minHeight: 50.0, maxHeight: 50.0)
                    .frame(width: 180)
                    .padding(.horizontal, Dimension.sharedInstance.lPadding)
                    .background(MiamColor.sharedInstance.borderColor)
                    .cornerRadius(25)
                    .font(.system(size: 16.0, weight: .bold, design: .default))
                    .padding(.bottom, Dimension.sharedInstance.lPadding)
                
            }.redacted(reason: .placeholder).opacity(opacity)
                .transition(.opacity).onAppear {
                    let baseAnimation = Animation.easeInOut(duration: Constants.duration)
                    let repeated = baseAnimation.repeatForever(autoreverses: true)
                    withAnimation(repeated) {
                        self.opacity = Constants.maxOpacity
                    }
                }.cornerRadius(15)
                .clipped()
                .overlay(
                    RoundedRectangle(cornerRadius: 15)
                        .stroke(MiamColor.sharedInstance.borderColor, lineWidth: 1)
                ).padding(16)
            
        }
    }
    
}

