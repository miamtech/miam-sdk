//
//  CatalogFiltersView.swift
//  MiamIOSFramework
//
//  Created by Vincent Kergonna on 23/06/2022.
//

import SwiftUI
import miamCore

@available(iOS 14, *)
struct CatalogFiltersView: View {
    @SwiftUI.State var resultCount: Int = 0
    @ObservedObject var catalogFilters: CatalogFilterVM

    let mainTitle = "Affiner ma sélection"
    let difficultySectionTitle = "Difficulté"
    let costSectionTitle = "Coût par personne"
    let preparationTimeSectionTitle = "Temps de préparation"
    let removeFiltersButtonTitle = "Retirer les filtres"

    let closeFilters: () -> Void
    let applyFilters: () -> Void

    init(catalogFiltersModel: CatalogFilterVM, apply: @escaping () -> Void, close: @escaping () -> Void) {
        catalogFilters = catalogFiltersModel
        applyFilters = apply
        closeFilters = close
    }

    var body: some View {
        if (Template.sharedInstance.catalogFiltersViewTemplate != nil) {
            Template.sharedInstance.catalogFiltersViewTemplate!
        } else {
            VStack() {
                // Title and close button
                HStack {
                    Text(mainTitle).fontWeight(.bold)
                    Spacer()
                    Button {
                        closeFilters()
                    } label: {
                        Image("cross", bundle: Bundle(for: RecipeCardVM.self))
                    }
                }.padding([.top], 20)

                // Filters
                CatalogFilterSection(title: difficultySectionTitle, filters: catalogFilters.difficulty) { option in
                    catalogFilters.model.setEvent(event: CatalogFilterContractEvent.OnDifficultyChanged(difficulty: option))
                }
                Divider()
                CatalogFilterSection(title: costSectionTitle, filters: catalogFilters.cost) { option in
                    catalogFilters.model.setEvent(event: CatalogFilterContractEvent.OnCostFilterChanged(costFilter: option))
                }
                Divider()
                CatalogFilterSection(title: preparationTimeSectionTitle, filters: catalogFilters.time) { option in
                    catalogFilters.model.setEvent(event: CatalogFilterContractEvent.OnTimeFilterChanged(timeFilter: option))
                }

                Spacer()

                Button {
                    catalogFilters.model.clearFilter()
                } label: {
                    Text(removeFiltersButtonTitle).foregroundColor(Color.miamColor(.primaryText))
                }.padding(EdgeInsets(top: 9, leading: 20, bottom: 9, trailing: 20))
                Divider().padding([.bottom, .top], 10)
                Button {
                    applyFilters()
                } label: {
                    Text("Voir les \(catalogFilters.numberOfRecipes) idées repas")
                        .padding(EdgeInsets(top: 9, leading: 20, bottom: 9, trailing: 20))
                        .foregroundColor(.white)
                        .background(Color.miamColor(.primary))
                        .clipShape(Capsule())
                }
            }.padding(Dimension.sharedInstance.lPadding)
        }
    }
}

@available(iOS 14, *)
internal struct CatalogFilterSection: View {
    let title: String
    let filters: Array<CatalogFilterOptions>
    let filterSelected: (CatalogFilterOptions) -> Void
    var body: some View {
        if (Template.sharedInstance.catalogFiltersSectionTemplate != nil) {
            Template.sharedInstance.catalogFiltersSectionTemplate!(title, filters, filterSelected)
        } else {
            VStack(alignment: .leading) {
                Text(title).bold().fontWeight(.bold)
                ForEach(filters, id: \.self) { filter in
                    CatalogFilterRow(filter: filter) { option in
                        filterSelected(option)
                    }
                }
            }.padding([.top, .bottom], 25.0)
        }
    }
}

@available(iOS 14, *)
internal struct CatalogFilterRow: View {
    let filter: CatalogFilterOptions
    let filterSelected: (CatalogFilterOptions) -> Void
    var imageName: String {
        filter.isSelected ? "Check" : "cross"
    }
    var body: some View {
        if Template.sharedInstance.catalogFilterRowTemplate != nil {
            Template.sharedInstance.catalogFilterRowTemplate!(filter, filterSelected)
        } else {
            HStack {
                Button {
                    filterSelected(filter)
                } label: {
                    if (filter.isSelected) {
                        Image(imageName, bundle: Bundle(for: CatalogVM.self))
                    } else {
                        Rectangle().foregroundColor(.clear)
                    }
                }.frame(width: 22, height: 22)
                    .overlay(RoundedRectangle(cornerRadius: 4.0).stroke(Color.miamColor(.primary), lineWidth: 1.0))
                Text(filter.uiLabel).fontWeight(Font.Weight.regular)
                Spacer()
            }
        }
    }
}
