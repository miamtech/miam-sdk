//
//  MyMealButtonView.swift
//  MiamIOSFramework
//
//  Created by Vincent Kergonna on 03/11/2022.
//  Copyright © 2022 Miam. All rights reserved.
//

import SwiftUI
import miamCore

@available(iOS 14, *)
public struct MyMealButtonView: View {
    @ObservedObject private var myMealButtonViewModel = MyMealButtonVM()
    
    let onButtonTapped: () -> Void
    
    public init(_ onButtonTapped: @escaping () -> Void) {
        self.onButtonTapped = onButtonTapped
    }
    
    public var body: some View {
        if let currentState = myMealButtonViewModel.state {
            ManagementResourceState<KotlinInt, MyMealButtonSuccessView, EmptyView, MyMealButtonEmptyView>(
                resourceState: currentState.recipeCount,
                successView: MyMealButtonSuccessView(mealsCount: myMealButtonViewModel.mealsCount,
                                                     onButtonTapped: { onButtonTapped() }),
                loadingView: EmptyView(),
                emptyView: MyMealButtonEmptyView()
            )
        }
    }
}
