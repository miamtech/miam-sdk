//
//  PriceView.swift
//  MiamIOSFramework
//
//  Created by Miam on 02/05/2022.
//

import SwiftUI
import shared

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
                Text(viewModel.currentState.integerPart+"," ).foregroundColor(MiamColor.sharedInstance.primary)
                Text(viewModel.currentState.decimalPart + "" + MiamText.sharedInstance.currency).foregroundColor(MiamColor.sharedInstance.primary).font(.system(size: 12))
            }
            if(!isTotalPrice){
                Text(MiamText.sharedInstance.preGuests).foregroundColor(MiamColor.sharedInstance.grey).font(.system(size: 12))
            }
        }
        }
        
    }
}

struct PriceView_Previews: PreviewProvider {
    static var previews: some View {
        PriceView(recipeId:String(1),guestNumber: 4)
    }
}
