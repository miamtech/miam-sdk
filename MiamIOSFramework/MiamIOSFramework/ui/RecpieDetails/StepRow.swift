//
//  StepRow.swift
//  MiamIOSFramework
//
//  Created by John on 12/05/2022.
//

import SwiftUI

struct StepRow: View {
    var body: some View {
        HStack(spacing: 10.0) {
            ZStack {
                Text("1").foregroundColor(MiamColor.sharedInstance.white)
            }.frame(width: 35.0, height: 35.0, alignment: .center).background(MiamColor.sharedInstance.primaryText).cornerRadius(17.5)
            VStack {
                Text("40 scLkj haskdkjahsdsdasasdd").foregroundColor(MiamColor.sharedInstance.black20).font(.system(size: 16, weight: .regular, design: .default)).multilineTextAlignment(.leading).padding(Dimension.sharedInstance.mPadding).frame(maxWidth: .infinity)
            }.frame(maxWidth: .infinity)
            HStack {
                
            }.frame(width: 30.0, height: 30.0, alignment: .leading).border(MiamColor.sharedInstance.black, width: 1.0)
        }.frame(maxWidth: .infinity)
    }
}

struct StepRow_Previews: PreviewProvider {
    static var previews: some View {
        StepRow()
    }
}
