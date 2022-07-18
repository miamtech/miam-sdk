//
//  PriceVM.swift
//  MiamIOSFramework
//
//  Created by Miam on 02/05/2022.
//

import miamCore

@available(iOS 14, *)
public class PriceVM : PricingViewModel, ObservableObject {
    @Published var price: Pricing?


    override init() {
        super.init()
        // TODO handle other states
        collect(flow: uiState, collect: { data in
            let state = data as! PricingContractState
            switch state.price {
                case let success as BasicUiStateSuccess<Pricing>:
                    self.price = success.data!
                default:
                    break
                }
            }
        )

    }
}
