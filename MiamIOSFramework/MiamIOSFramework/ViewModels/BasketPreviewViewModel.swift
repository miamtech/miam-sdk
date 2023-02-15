//
//  BasketPreviewViewModel.swift
//  MiamIOSFramework
//
//  Created by Vincent Kergonna on 06/06/2022.
//

import Foundation
import Combine
import miamCore


@available(iOS 14, *)
public class BasketPreviewVM: BasketPreviewViewModel, ObservableObject {
    private let currencyCode = "EUR"
    @Published public var basketPreviewLine: BasketPreviewLine?
    @Published public var state : BasketPreviewContractState?

    private lazy var numberFormatter: NumberFormatter = {
        let numberFormatter = NumberFormatter()
        numberFormatter.currencyCode = currencyCode
        numberFormatter.numberStyle = .currency
        numberFormatter.maximumFractionDigits = 2

        return numberFormatter
    }()

    public override init(recipeId: String?) {
        super.init(recipeId: recipeId)
        collect(flow: uiState) { data in
            let state = data as! BasketPreviewContractState
            self.state = state
            switch state.line {
            case let success as BasicUiStateSuccess<BasketPreviewLine>:
                self.basketPreviewLine = success.data
            default:
                break
            }
        }
    }

    public var pictureURL: URL? {
        guard let basket = basketPreviewLine else {
            return nil
        }

        return URL(string: basket.picture)
    }

    public var basketTitle: String {
        return basketPreviewLine?.title ?? ""
    }

    public var basketDescription: String {
        return basketPreviewLine?.bplDescription[0] ?? ""
    }

    public var pricePerGuest: String {
        guard let basket = basketPreviewLine else {
            return ""
        }

        guard let parsedPrice = Double(basket.price) else {
            return ""
        }
        let basketCount = basket.count > 0 ? basket.count : 1
        let price = parsedPrice * 100 / Double(basketCount) / 100
        guard let formattedPrice = numberFormatter.string(from: NSNumber(floatLiteral: price)) else {
            return ""
        }

        return "\(formattedPrice) /personne"
    }

    public var numberOfGuests: Int {
        return Int(basketPreviewLine?.count ?? 0)
    }

    public var price: String {
        guard let price = basketPreviewLine?.price else {
            return ""
        }

        guard let doublePrice = Double(price) else {
            return ""
        }

        guard let formattedPrice = numberFormatter.string(from: NSNumber(floatLiteral: doublePrice)) else {
            return ""
        }

        return formattedPrice
    }

    public var  numberOfproductsInBasket: Int {
        return basketPreviewLine?.entries?.found.count ?? 0
    }

    public var productsInBasket: Array<BasketEntry> {
        // Avoid error "Generic parameter 'C' could not be inferred" when accessing entries
        guard let entries = basketPreviewLine?.entries?.found as? Array<BasketEntry> else {
            return []
        }

        return entries
    }

    public var productsNotFound: Array<BasketEntry> {
        // Avoid error "Generic parameter 'C' could not be inferred" when accessing entries
        guard let entries = basketPreviewLine?.entries?.notFound as? Array<BasketEntry> else {
            return []
        }

        return entries
    }

    public var productsOftenDeleted: Array<BasketEntry> {
        // Avoid error "Generic parameter 'C' could not be inferred" when accessing entries
        guard let entries = basketPreviewLine?.entries?.oftenDeleted as? Array<BasketEntry> else {
            return []
        }

        return entries
    }

    public var productsRemoved: Array<BasketEntry> {
        // Avoid error "Generic parameter 'C' could not be inferred" when accessing entries
        guard let entries = basketPreviewLine?.entries?.removed as? Array<BasketEntry> else {
            return []
        }

        return entries
    }
}
