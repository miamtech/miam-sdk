//
//  MyMealsView.swift
//  MiamIOSFramework
//
//  Created by John on 16/05/2022.
//

import SwiftUI

public struct MyMealsView: View {
    @ObservedObject var myMealsViewModel: MyMealVM = MyMealVM()

    public init() {
        UINavigationBar.appearance().backgroundColor = UIColor(cgColor: MiamColor.sharedInstance.primaryText.cgColor!)
        
        UINavigationBar.appearance().tintColor = UIColor(cgColor: MiamColor.sharedInstance.white.cgColor!)
        
        UINavigationBar.appearance().largeTitleTextAttributes = [.foregroundColor : UIColor(cgColor: MiamColor.sharedInstance.white.cgColor!)]
        
        UINavigationBar.appearance().titleTextAttributes = [.foregroundColor : UIColor(cgColor: MiamColor.sharedInstance.white.cgColor!)]
    }
    
    public var body: some View {
        NavigationView {
            ScrollView {
                
                ForEach(myMealsViewModel.meals) { meal in
                    MyMealRow(myMealViewModel: myMealsViewModel, meal: meal).animation(.default, value: 1).padding(.bottom, Dimension.sharedInstance.mPadding)
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

