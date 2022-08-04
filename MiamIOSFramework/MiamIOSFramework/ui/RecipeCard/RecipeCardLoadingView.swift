//
//  RecipeCardLoadingView.swift
//  MiamIOSFramework
//
//  Created by Miam on 20/06/2022.
//

import Foundation
import SwiftUI

@available(iOS 14, *)
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
                        .fill(Color.miamColor(.border))
                        .frame(height: 245)
                    
                }.frame(height: 245)
                Text( "")
                    .lineLimit(2)
                    .foregroundColor(Color.miamColor(.black))
                    .font(.system(size: 16.0, weight: .bold, design: .default))
                    .padding(Dimension.sharedInstance.lPadding)
                Text( "")
                    .lineLimit(2)
                    .foregroundColor(Color.miamColor(.black))
                    .font(.system(size: 16.0, weight: .bold, design: .default))
                    .padding(Dimension.sharedInstance.sPadding)
               
                Rectangle()
                    .fill(Color.miamColor(.border)).opacity(0.1)
                    .frame(minHeight: 50.0, maxHeight: 50.0)
                    .frame(width: 180)
                    .padding(.horizontal, Dimension.sharedInstance.lPadding)
                    .background(Color.miamColor(.border))
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
                        .stroke(Color.miamColor(.border), lineWidth: 1)
                )
            
        }.frame(height: 430)
    }
    
}
