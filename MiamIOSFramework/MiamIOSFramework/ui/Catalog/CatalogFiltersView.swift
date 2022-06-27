//
//  CatalogFiltersView.swift
//  MiamIOSFramework
//
//  Created by Vincent Kergonna on 23/06/2022.
//

import SwiftUI
import shared

struct CatalogFiltersView: View {

    @SwiftUI.State var resultCount: Int = 0
    let catalogFilters: CatalogFilter = CatalogFilter()

    var body: some View {
        VStack() {
            HStack {
                Text("Affiner ma sélection").fontWeight(.bold)
                Spacer()
                Button {
                    // TODO: Close filters window
                } label: {
                    Image("cross", bundle: Bundle(for: RecipeCardVM.self))
                }
            }.padding([.top], 20)
            // TODO: Should be a loop on filters object
            CatalogSection(title: "Difficulté", filters: catalogFilters.difficulty)
            Divider()
            CatalogSection(title: "Coût par personne", filters: catalogFilters.cost)
            Divider()
            CatalogSection(title: "Temps de préparation", filters: catalogFilters.time)

            Spacer()

            Button {
                catalogFilters.clearFilter()
            } label: {
                Text("Retirer les filtres").foregroundColor(MiamColor.sharedInstance.primaryText)
            }.padding(EdgeInsets(top: 9, leading: 20, bottom: 9, trailing: 20))
                .overlay(Capsule().stroke(MiamColor.sharedInstance.primary, lineWidth: 1.0))


            Divider().padding([.bottom, .top], 10)
            Button {
                // TODO: display results
            } label: {
                Text("Voir les \(resultCount) idées repas")
                    .padding(EdgeInsets(top: 9, leading: 20, bottom: 9, trailing: 20))
                    .foregroundColor(.white)
                    .background(MiamColor.sharedInstance.primary)
                    .clipShape(Capsule())
            }
        }.padding(Dimension.sharedInstance.lPadding)
    }
}

internal struct CatalogSection: View {
    let title: String
    let filters: Array<CatalogFilterOptions>
    var body: some View {
        VStack(alignment: .leading) {
            Text(title).bold().fontWeight(.bold)
            ForEach(filters, id: \.self) { filter in
                CatalogFilterRow(filterName: filter.uiLabel)
            }
        }.padding([.top, .bottom], 25.0)
    }
}

internal struct CatalogFilterRow: View {
    let filterName: String
    var body: some View {
        HStack {
            Button {

            } label: {
                RoundedRectangle(cornerRadius: 4.0).frame(width: 22, height: 22).border(MiamColor.sharedInstance.primary).foregroundColor(.clear)
            }
            Text(filterName).fontWeight(Font.Weight.regular)
            Spacer()

        }
    }
}

struct CatalogFiltersView_Previews: PreviewProvider {
    static var previews: some View {
        CatalogFiltersView()
    }
}
