//
//  CounterView.swift
//  MiamIOSFramework
//
//  Created by Miam on 27/04/2022.
//

import SwiftUI

@available(iOS 14, *)
public struct CounterView: View {

    @State public var count: Int
    public var onCounterChanged: (Int) -> Void
    public var lightMode: Bool = false
    public var maxValue: Int?
    public var minValue: Int?
    public var isLoading: Bool = false
    public var isDisable: Bool = false

    public init(
        count: Int,
        onCounterChanged: @escaping (Int) -> Void
    ) {
        self.onCounterChanged = onCounterChanged
        self._count = State(initialValue: count)
    }

    public init(
        count: Int,
        lightMode: Bool,
        onCounterChanged: @escaping (Int) -> Void
    ) {

        self.lightMode = lightMode
        self.onCounterChanged = onCounterChanged
        self._count = State(initialValue: count)
    }

    public init(
        count: Int,
        lightMode: Bool,
        onCounterChanged: @escaping (Int) -> Void,
        isLoading: Bool = false,
        isDisable: Bool = false,
        minValue: Int? = nil,
        maxValue: Int? = nil
    ) {
        self.lightMode = lightMode
        self.onCounterChanged = onCounterChanged
        self.minValue = minValue ?? nil
        self.maxValue = maxValue ?? nil
        self.isLoading = isLoading
        self.isDisable = isDisable
        self._count = State(initialValue: count)
    }

    private func newValueBounded(newValue: Int) -> Bool {
        return (minValue == nil || newValue >= minValue!) && (maxValue == nil || newValue <= maxValue!)
    }

    private func increase() {
        if !newValueBounded(newValue: count + 1) { return }
        count += 1
        onCounterChanged(count)
    }

    private  func decrease() {
        if !newValueBounded(newValue: count - 1) { return }
        count -= 1
        onCounterChanged(count)
    }

    public var body: some View {
        if let template = Template.sharedInstance.counterViewTemplate {
            template(count, lightMode, {increase()}, {decrease()})
        } else {
            HStack {
                Button(action: {
                    decrease()
                }) {
                    Image.miamImage(icon: .minus)
                        .resizable()
                        .scaledToFill()
                        .frame(width: 18, height: 18)
                        .foregroundColor(Color.miamColor(.white))
                }
                .padding(.leading, Dimension.sharedInstance.lPadding)
                .frame(width: 20.0, height: 20.0, alignment: .leading)
                .disabled(self.isDisable)

                Spacer()
                if isLoading {
                    ProgressLoader(color: Color.miamColor(.white))
                        .scaleEffect(0.5)
                } else {
                    Text("\(count)" + " \( lightMode ? "" : MiamText.sharedInstance.persons)")
                        .miamFontStyle(style: MiamFontStyleProvider.sharedInstance.titleSmallStyle)
                        .foregroundColor(Color.miamColor(.white))
                    Spacer()
                }
                Button(action: {
                   increase()
                }) {
                    Image.miamImage(icon: .plus)
                        .resizable()
                        .scaledToFill()
                        .frame(width: 18, height: 18)
                        .foregroundColor(Color.miamColor(.white))
                }
                .padding(.trailing, Dimension.sharedInstance.lPadding)
                .frame(width: 20.0, height: 20.0, alignment: .trailing)
                .disabled(self.isDisable)

            }.frame(width: lightMode ? 90 : 130.0, height: 40.0, alignment: .center)
                .background(Color.miamColor(.primaryText))
                .cornerRadius(25.0).padding(.trailing, Dimension.sharedInstance.mPadding)
        }
    }
}

@available(iOS 14, *)
struct CounterView_Previews: PreviewProvider {
    static var previews: some View {
        CounterView(count: 12,
                    onCounterChanged: {_ in  print("ok")}
            )
    }
}
