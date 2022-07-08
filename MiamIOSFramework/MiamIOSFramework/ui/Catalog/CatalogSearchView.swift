//
//  CatalogSearchView.swift
//  MiamIOSFramework
//
//  Created by Vincent Kergonna on 24/06/2022.
//

import SwiftUI
import shared

@available(iOS 14, *)
struct CatalogSearchView: View {
    @SwiftUI.State var searchString: String = ""
    var catalog: CatalogVM
    
    var body: some View {
        VStack {
            HStack {
                Button {
                    catalog.setEvent(event: CatalogContractEvent.ToggleSearch())
                } label: {
                    Image("Arrow", bundle: Bundle(for: RecipeListPageVM.self))
                }
                Spacer()
            }.frame(height: 44).padding(Dimension.sharedInstance.mlPadding)
            HStack {
                HStack {
                    TextField("Rechercher", text: $searchString).frame(height: 45.0)
                    Button {
                        catalog.setEvent(event: CatalogContractEvent.OnSearchLaunch(searchString: searchString))
                    } label: {
                        Image("search", bundle: Bundle(for: CatalogVM.self))
                            .padding(10)
                            .background(MiamColor.sharedInstance.primary).clipShape(Circle())
                    }
                }.padding([.leading], 15).frame(height: 45.0)
                    .overlay(Capsule().stroke(Color.gray, lineWidth: 1.0))
            }.padding(10)
            Spacer()
        }
    }
}

@available(iOS 14, *)
struct CatalogSearchView_Previews: PreviewProvider {
    static var previews: some View {
        VStack {
            CatalogSearchView(catalog: CatalogVM())
        }
    }
}
