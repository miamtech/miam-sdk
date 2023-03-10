//
//  MyMealButtonVM.swift
//  MiamIOSFramework
//
//  Created by Vincent Kergonna on 03/11/2022.
//  Copyright © 2022 Miam. All rights reserved.
//

import Foundation
import miamCore

@available(iOS 14, *)
public class MyMealButtonVM: MyMealButtonViewModel, ObservableObject {
   
    @Published public var state: MyMealButtonContractState?
    
    public var mealsCount: Int = 0
    
    public override init() {
        super.init()
        self.collect(flow: uiState) { data in
            guard let state = data as? MyMealButtonContractState else {
                return
            }

            self.state = state
            switch state.recipeCount {
            case let success as BasicUiStateSuccess<KotlinInt>:
                self.mealsCount = success.data?.intValue ?? 0
            default:
                break
            }
        }
    }
}
