//
//  PreferencesSearchView.swift
//  MiamIOSFramework
//
//  Created by Vincent Kergonna on 04/11/2022.
//  Copyright © 2022 Miam. All rights reserved.
//

import SwiftUI
import miamCore

@available(iOS 14, *)
public struct PreferencesSearchView: View {
    @ObservedObject private var preferencesSearchViewModel = PreferencesSearchVM()
    @SwiftUI.State var searchString: String = ""

    let close: () -> Void

    public init(close: @escaping () -> Void) {
        self.close = close
    }

    public var body: some View {
        if let template = Template.sharedInstance.preferencesSearchViewTemplate {
            template(preferencesSearchViewModel, close)
        } else {
            VStack(alignment: .leading) {
                HStack(spacing: 5.0) {
                    HStack(spacing: 5.0) {
                        Image.miamImage(icon: .search)
                            .foregroundColor(Color.miamColor(.black))
                            .padding(5)
                        TextField(MiamText.sharedInstance.searchTagPlaceholder, text: $searchString).frame(height: 45.0)
                            .disableAutocorrection(true)
                            .onChange(of: searchString) { value in
                                preferencesSearchViewModel.search(search: value)
                            }
                    }.padding([.leading], 15).frame(height: 45.0)
                        .overlay(Capsule().stroke(Color.gray, lineWidth: 1.0))
                }
                
                VStack(alignment: .leading) {
                    ForEach(preferencesSearchViewModel.tagsSuggestions, id: \.id) { tag in
                        // Tags without name will not be displayed in search results
                        if let name = tag.attributes?.name {
                            Button {
                                PreferencesVM.sharedInstance.addTag(tag)
                                close()
                            } label: {
                                Text(name)
                                    .miamFontStyle(style: MiamFontStyleProvider.sharedInstance.bodyBigStyle)
                                    .padding(8)
                            }
                        }
                    }
                }
                Spacer()
            }.padding([.leading, .trailing], Dimension.sharedInstance.lPadding)
                .padding([.top, .bottom], 24)
        }
    }
}
