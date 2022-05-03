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
                Image(uiImage: UIImage(named: "Peoples")!).resizable().scaledToFill().frame(width:16, height:16).padding(.bottom,2)
                Button(action: {
                    decrease()
                }) {
                    Image(systemName: "minus")
                        .foregroundColor(CounterColor.sharedInstance.lessIconColor)
                }.foregroundColor(CounterColor.sharedInstance.lessButtonBackgroundColor)
                Text(String(count)).font(.system(size: 16)).padding(4).overlay(
                    RoundedRectangle(cornerRadius: 4)
                        .stroke(MiamColor.sharedInstance.lightgrey)
                )
                Button(action: {
                    increase()
                }) {
                    Image(systemName: "plus")
                        .foregroundColor(CounterColor.sharedInstance.plusIconColor)
                }.foregroundColor(CounterColor.sharedInstance.plusButtonBackgroundColor)
            }.mainRowContainer()
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
