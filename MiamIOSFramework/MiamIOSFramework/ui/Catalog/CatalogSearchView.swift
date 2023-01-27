//
//  CatalogSearchView.swift
//  MiamIOSFramework
//
//  Created by Vincent Kergonna on 24/06/2022.
//

import SwiftUI
import miamCore

@available(iOS 14, *)
public struct CatalogSearchView: View {
    @SwiftUI.State var searchString: String = ""
    let close: () -> Void
    let search: () -> Void
    let filterVM = FilterViewModelInstance.shared.instance
    
    public init(close: @escaping () -> Void, search: @escaping () -> Void) {
        self.close = close
        self.search = search
    }
    
    public var body: some View {
        if let template = Template.sharedInstance.catalogSearchViewTemplate {
//            template(close, search)
            EmptyView()
        } else {
            VStack(spacing: 10.0) {
                HStack {
                    Button {
                        close()
                    } label: {
                        Image.miamImage(icon: .arrow)
                    }
                    Spacer()
                }.frame(height: 44).padding(Dimension.sharedInstance.mlPadding)
                
                HStack(spacing: 10.0) {
                    HStack(spacing: 10.0) {
                        TextField(MiamText.sharedInstance.search, text: $searchString).frame(height: 45.0)
                            .disableAutocorrection(true)
                            .onChange(of: searchString) { value in
                                filterVM.setSearchString(searchString: value)
                            }
                        Button {
                            search()
                        } label: {
                            Image.miamImage(icon: .search)
                                .padding(10)
                                .background(Color.miamColor(.primary)).clipShape(Circle())
                        }
                    }.padding([.leading], 15).frame(height: 45.0)
                        .overlay(Capsule().stroke(Color.gray, lineWidth: 1.0))
                }.padding(10)
                Spacer()
            }
        }
    }
}

@available(iOS 14, *)
struct CatalogSearchView_Previews: PreviewProvider {
    static var previews: some View {
        VStack {
            CatalogSearchView(close: {}, search: {})
        }
    }
}
