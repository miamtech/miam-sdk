//
//  MyMealsVM.swift
//  MiamIOSFramework
//
//  Created by Vincent Kergonna on 16/06/2022.
//

import Foundation
import miamCore

@available(iOS 14, *)
public class MyMeal: Identifiable {
    public init(basketPreviewLine: BasketPreviewLine) {
        self.basketPreviewLine = basketPreviewLine
    }

    public let basketPreviewLine: BasketPreviewLine
    public var id: String {
        guard let basketId = basketPreviewLine.id else {
            return UUID.init().uuidString
        }

        return basketId
    }
}

@available(iOS 14, *)
public class MyMealVM: MyMealViewModel, ObservableObject {
    @Published public var meals: [MyMeal] = []
    @Published public var state: MyMealContractState?

    public override init() {
        super.init()
        collect(flow: uiState) { data in
            let state = data as? MyMealContractState
            self.state = state
            switch state?.lines {
            case let success as BasicUiStateSuccess<NSArray>: // Must use an object, thus NSArray
                if let basketPreviewLines = success.data as? [BasketPreviewLine] {
                    self.meals = basketPreviewLines.map { line in
                        MyMeal(basketPreviewLine: line)
                    }
                }
            default:
                break
            }
        }
    }

}
