//
//  ProgressLoader.swift
//  MiamIOSFramework
//
//  Created by Miam on 21/06/2022.
//

import SwiftUI

struct ProgressLoader: View {
    
    @State private var isAnimating = false
    @State private var showProgress = false
    private var color : Color
    
    var foreverAnimation: Animation {
            Animation.linear(duration: 0.5)
                .repeatForever(autoreverses: false)
        }
    init(color: Color){
        self.color = color
    }
    
    var body: some View {
        ZStack {
            Circle()
                .stroke(lineWidth: 8.0)
                .opacity(0.3)
                .foregroundColor(color)
            
            Circle()
                .trim(from: 0.0, to: 0.3)
                .stroke(style: StrokeStyle(lineWidth: 8.0, lineCap: .round, lineJoin: .round))
                .foregroundColor(color).rotationEffect(Angle(degrees: self.isAnimating ? 360 : 0.0))
                .animation(self.isAnimating ? foreverAnimation : .default)
                .onAppear { self.isAnimating = true }
                .onDisappear { self.isAnimating = false }
        }.frame(width: 60, height: 60)
    }
}

