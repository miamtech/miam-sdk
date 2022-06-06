//
//  BasketPreviewViewModel.swift
//  MiamIOSFramework
//
//  Created by Vincent Kergonna on 06/06/2022.
//

import Foundation
import Combine
import shared



public class BasketPreviewVM: BasketPreviewViewModel, ObservableObject {
    @Published var basketPreviewLine: BasketPreviewLine?

    override init(recipeId: String?) {
        super.init(recipeId: recipeId)
        collect(flow: uiState) { data in
            let state = data as! BasketPreviewContractState
            switch state.line {
            case let success as BasicUiStateSuccess<BasketPreviewLine>:
                self.basketPreviewLine = success.data
            default:
                break
            }
        }
    }

    public var basketTitle: String {
        return basketPreviewLine?.title ?? ""
    }

    public var basketDescription: String {
        return basketPreviewLine?.description_[0] ?? ""
    }

    public var pricePerGuest: String {
        guard let basket = basketPreviewLine else {
            return ""
        }

        guard let parsedPrice = Double(basket.price) else {
            return ""
        }

        let price = parsedPrice * 100 / Double(basket.count) / 100
        return "\(price)€ /personne"
    }

    public var numberOfGuests: Int {
        return Int(basketPreviewLine?.count ?? 0)
    }

    public var price: String {
        return basketPreviewLine?.price ?? ""
    }
}
