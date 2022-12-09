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
    @ObservedObject var catalogFilters = CatalogFilterViewModel.sharedInstance
    
    let closeFilters: () -> Void
    let applyFilters: () -> Void
    
    init(apply: @escaping () -> Void, close: @escaping () -> Void) {
        applyFilters = apply
        closeFilters = close
    }
    
    var body: some View {
        if (Template.sharedInstance.catalogFiltersViewTemplate != nil) {
            Template.sharedInstance.catalogFiltersViewTemplate!
        } else {
            ScrollView {
                VStack() {
                    // Title and close button
                    HStack {
                        Text(MiamText.sharedInstance.filtersTitle)
                            .miamFontStyle(style: MiamFontStyleProvider.sharedInstance.titleMediumStyle)
                        Spacer()
                        Button {
                            closeFilters()
                        } label: {
                            Image.miamImage(icon: .cross)
                        }
                    }.padding([.top], 20)
                    
                    // Filters
                    CatalogFilterSection(title: MiamText.sharedInstance.filtersDifficultySectionTitle, filters: catalogFilters.difficulty) { option in
                        catalogFilters.setEvent(event: SingletonFilterContractEvent.OnDifficultyChanged(difficulty: option))
                    }
                    Divider()
                    CatalogFilterSection(title: MiamText.sharedInstance.filterCostSectionTitle, filters: catalogFilters.cost) { option in
                        catalogFilters.setEvent(event: SingletonFilterContractEvent.OnCostFilterChanged(costFilter: option))
                    }
                    Divider()
                    CatalogFilterSection(title: MiamText.sharedInstance.filterPreparationTimeSectionTitle, filters: catalogFilters.time) { option in
                        catalogFilters.setEvent(event: SingletonFilterContractEvent.OnTimeFilterChanged(timeFilter: option))
                    }
                    
                    Spacer()
                    
                    Button {
                        catalogFilters.clear()
                    } label: {
                        Text(MiamText.sharedInstance.removeFiltersButtonTitle)
                            .miamFontStyle(style: MiamFontStyleProvider.sharedInstance.bodyBigStyle)
                            .foregroundColor(Color.miamColor(.primaryText))
                    }.padding(EdgeInsets(top: 9, leading: 20, bottom: 9, trailing: 20))
                    Divider().padding([.bottom, .top], 10)
                    Button {
                        applyFilters()
                    } label: {
                        Text("Voir les \(catalogFilters.numberOfRecipes) idées repas")
                            .padding(EdgeInsets(top: 9, leading: 20, bottom: 9, trailing: 20))
                            .miamFontStyle(style: MiamFontStyleProvider.sharedInstance.bodyBigStyle)
                            .foregroundColor(.white)
                            .background(Color.miamColor(.primary))
                            .clipShape(Capsule())
                    }
                }.padding(Dimension.sharedInstance.lPadding)
            }
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
                Text(title)
                    .miamFontStyle(style: MiamFontStyleProvider.sharedInstance.titleMediumStyle)
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
    var icon: MiamIcon {
        filter.isSelected ? .check : .cross
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
                        Image.miamImage(icon: icon)
                    } else {
                        Rectangle().foregroundColor(.clear)
                    }
                }.frame(width: 22, height: 22)
                    .overlay(RoundedRectangle(cornerRadius: 4.0).stroke(Color.miamColor(.primary), lineWidth: 1.0))
                Text(filter.uiLabel)
                    .miamFontStyle(style: MiamFontStyleProvider.sharedInstance.bodyBigStyle)
                Spacer()
            }
        }
    }
}
