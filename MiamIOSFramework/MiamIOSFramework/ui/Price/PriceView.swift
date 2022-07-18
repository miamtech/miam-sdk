//
//  PriceView.swift
//  MiamIOSFramework
//
//  Created by Miam on 02/05/2022.
//

import SwiftUI
import miamCore

@available(iOS 14, *)
struct PriceView: View {
    
    public var recipeId: String?
    public var guestNumber: Int?
    public var isTotalPrice: Bool = false
    
    @ObservedObject var viewModel: PriceVM = PriceVM()
    
    public init(
        recipeId: String,
        guestNumber: Int
    ) {
        self.recipeId = recipeId
        self.guestNumber = guestNumber
        viewModel.setEvent(event: PricingContractEvent.OnSetRecipe(idRecipe: recipeId, guestNumber: Int32(Int(guestNumber))))
        viewModel.setEvent(event:PricingContractEvent.OnPriceUpdate.shared)
    }
    
    public init(price: Double){
        self.isTotalPrice = true
        viewModel.setEvent(event:PricingContractEvent.SetDirectPrice(price:price))
        viewModel.setEvent(event:PricingContractEvent.OnPriceUpdate.shared)
    }
    
    var body: some View {
        if(Template.sharedInstance.priceTemplate != nil) {
            Template.sharedInstance.priceTemplate!(
                Int(viewModel.currentState.integerPart)!,
                Int(viewModel.currentState.decimalPart)!
            )
        } else {
        VStack{
            HStack(alignment: .top, spacing: 2){
                Text(viewModel.currentState.integerPart+"," ).font(.system(size: 14,weight: .bold))
                Text(viewModel.currentState.decimalPart + "" + MiamText.sharedInstance.currency).font(.system(size: 14,weight: .bold))
            }
            if(!isTotalPrice){
                Text(MiamText.sharedInstance.preGuests).foregroundColor(MiamColor.sharedInstance.grey).font(.system(size: 12))
            }
        }
        }
        
    }
}

@available(iOS 14, *)
struct PriceView_Previews: PreviewProvider {
    static var previews: some View {
        PriceView(recipeId:String(1),guestNumber: 4)
    }
}
