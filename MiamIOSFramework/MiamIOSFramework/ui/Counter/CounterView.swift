//
//  CounterView.swift
//  MiamIOSFramework
//
//  Created by Miam on 27/04/2022.
//

import SwiftUI

struct CounterView: View {
    
    public var count: Int
    public var isDisable: Bool
    public var increase:  () -> Void
    public var decrease:  () -> Void
    public var lightMode: Bool = false
    
    public init(
        count: Int,
        isDisable: Bool,
        increase: @escaping () -> Void,
        decrease: @escaping () -> Void
    ) {
        self.count = count
        self.isDisable = isDisable
        self.increase = increase
        self.decrease = decrease
    }
    
    public init(
        count: Int,
        isDisable: Bool,
        increase: @escaping () -> Void,
        decrease: @escaping () -> Void,
        lightMode: Bool
    ) {
        self.count = count
        self.isDisable = isDisable
        self.increase = increase
        self.decrease = decrease
        self.lightMode = lightMode
    }
    
    var body: some View {
        if(Template.sharedInstance.counterTemplate != nil) {
            Template.sharedInstance.counterTemplate!(
                count
               ,{increase()}
               ,{decrease()}
            )
        } else {
            HStack{
                Button(action: {
                    decrease()
                }) {
                    Image("minus")
                        .foregroundColor(MiamColor.sharedInstance.white)
                }.padding(.leading, Dimension.sharedInstance.mPadding)
                    .frame(width: 20.0, height: 20.0, alignment: .leading)
                Spacer()
                Text(String(count)+" pers.")
                    .foregroundColor(MiamColor.sharedInstance.white)
                    .font(.system(size: 13, weight: .bold, design: .default))
                Spacer()
                
                Button(action: {
                    increase()
                }) {
                    Image("plus")
                        .foregroundColor(MiamColor.sharedInstance.white)
                }.padding(.trailing, Dimension.sharedInstance.lPadding)
                    .frame(width: 20.0, height: 20.0, alignment: .trailing)
            }.frame(width: 130.0, height: 40.0, alignment: .center)
            .background(MiamColor.sharedInstance.primaryText)
            .cornerRadius(25.0).padding(.trailing, Dimension.sharedInstance.mPadding)
        }
    }
}

struct CounterView_Previews: PreviewProvider {
    static var previews: some View {
        CounterView(count: 12 ,
                    isDisable: false,
                    increase: {() -> () in  print("ok")},
                    decrease: {() -> () in  print("not ok")}
            )
    }
}