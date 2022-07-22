//
//  CatalogSearchView.swift
//  MiamIOSFramework
//
//  Created by Vincent Kergonna on 24/06/2022.
//

import SwiftUI
import miamCore

@available(iOS 14, *)
struct CatalogSearchView: View {
    @SwiftUI.State var searchString: String = ""
    var catalog: CatalogVM
    
    var body: some View {
        VStack(spacing: 10.0) {
            HStack {
                Button {
                    catalog.setEvent(event: CatalogContractEvent.ToggleSearch())
                } label: {
                    Image("Arrow", bundle: Bundle(for: RecipeListPageVM.self))
                }
                Spacer()
            }.frame(height: 44).padding(Dimension.sharedInstance.mlPadding)
            HStack(spacing: 10.0) {
                HStack(spacing: 10.0) {
                    TextField("Rechercher", text: $searchString).frame(height: 45.0)
                        .disableAutocorrection(true)
                        .onChange(of: searchString) { value in
                        if let filtersViewModel = catalog.filtersViewModel {
                            filtersViewModel.setEvent(event: CatalogFilterContractEvent.SetSearchString(searchString: value))
                        }
                    }
                    Button {
                        catalog.setEvent(event: CatalogContractEvent.GoToRecipeList())
                    } label: {
                        Image("search", bundle: Bundle(for: CatalogVM.self))
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

@available(iOS 14, *)
struct CatalogSearchView_Previews: PreviewProvider {
    static var previews: some View {
        VStack {
            CatalogSearchView(catalog: CatalogVM())
        }
    }
}
