//
//  MyMealsView.swift
//  MiamIOSFramework
//
//  Created by John on 16/05/2022.
//

import SwiftUI

struct MyMealsView: View {
    
    init() {
        UINavigationBar.appearance().backgroundColor = UIColor(cgColor: MiamColor.sharedInstance.primaryText.cgColor!)
        
        UINavigationBar.appearance().tintColor = UIColor(cgColor: MiamColor.sharedInstance.white.cgColor!)
        
        UINavigationBar.appearance().largeTitleTextAttributes = [.foregroundColor : UIColor(cgColor: MiamColor.sharedInstance.white.cgColor!)]
        
        UINavigationBar.appearance().titleTextAttributes = [.foregroundColor : UIColor(cgColor: MiamColor.sharedInstance.white.cgColor!)]
    }
    
    var body: some View {
        NavigationView {
            ScrollView {
                
                ForEach(0 ..< 2) { task in
                    MyMealRow().animation(.default, value: 1).padding(.bottom, Dimension.sharedInstance.mPadding)
                }
                .navigationTitle("Mes repas (2)")
            }
        }.navigationBarBackButtonHidden(false)
    }
}

struct MyMealsView_Previews: PreviewProvider {
    static var previews: some View {
        MyMealsView()
    }
}

