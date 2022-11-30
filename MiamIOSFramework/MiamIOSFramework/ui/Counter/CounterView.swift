//
//  CounterView.swift
//  MiamIOSFramework
//
//  Created by Miam on 27/04/2022.
//

import SwiftUI

@available(iOS 14, *)
public struct CounterView: View {
    
    public var count: Int
    public var increase:  () -> Void
    public var decrease:  () -> Void
    public var lightMode: Bool = false
    
    public init(
        count: Int,
        increase: @escaping () -> Void,
        decrease: @escaping () -> Void
    ) {
        self.count = count
        self.increase = increase
        self.decrease = decrease
    }
    
    public init(
        count: Int,
        lightMode: Bool,
        increase: @escaping () -> Void,
        decrease: @escaping () -> Void
    ) {
        self.count = count
        self.lightMode = lightMode
        self.increase = increase
        self.decrease = decrease
    }
    
    public var body: some View {
        if let template = Template.sharedInstance.counterViewTemplate {
            template(count, lightMode, {increase()}, {decrease()})
        } else {
            HStack{
                Button(action: {
                    decrease()
                }) {
                    Image.miamImage(icon: .minus)
                        .resizable()
                        .scaledToFill()
                        .frame(width:18, height:18)
                        .foregroundColor(Color.miamColor(.white))
                }.padding(.leading, Dimension.sharedInstance.lPadding)
                    .frame(width: 20.0, height: 20.0, alignment: .leading)
                Spacer()
                Text(String(count) + " \( lightMode ? "" : MiamText.sharedInstance.persons)")
                    .foregroundColor(Color.miamColor(.white))
                    .font(.system(size: 13, weight: .bold, design: .default))
                    Spacer()
                Button(action: {
                    increase()
                }) {
                    Image.miamImage(icon: .plus)
                        .resizable()
                        .scaledToFill()
                        .frame(width: 18, height:18)
                        .foregroundColor(Color.miamColor(.white))
                }.padding(.trailing, Dimension.sharedInstance.lPadding)
                    .frame(width: 20.0, height: 20.0, alignment: .trailing)
            }.frame(width: lightMode ? 90 : 130.0, height: 40.0, alignment: .center)
                .background(Color.miamColor(.primaryText))
                .cornerRadius(25.0).padding(.trailing, Dimension.sharedInstance.mPadding)
        }
    }
}

@available(iOS 14, *)
struct CounterView_Previews: PreviewProvider {
    static var previews: some View {
        CounterView(count: 12 ,
                    increase: {() -> () in  print("ok")},
                    decrease: {() -> () in  print("not ok")}
            )
    }
}
