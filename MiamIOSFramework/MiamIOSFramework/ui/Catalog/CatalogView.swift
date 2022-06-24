//
//  CatalogView.swift
//  MiamIOSFramework
//
//  Created by Vincent Kergonna on 23/06/2022.
//

import Foundation
import SwiftUI


public struct CatalogView: View {

    @State private var showingFilters = false

    public init() {

    }

    public var body: some View {
        VStack {
            CatalogViewHeader()
            CatalogViewToolbar {
                showingFilters = true
            }
            Spacer()
        }.popover(isPresented: $showingFilters) {
            CatalogFiltersView()
        }
    }
}

internal struct CatalogViewHeader: View {
    var body: some View {
        HStack {
            Image("ideerepas", bundle: Bundle(for: RecipeCardVM.self))
                .renderingMode(.template)
                .foregroundColor(.white)
                .frame(width: 30, height: 30)

            VStack (alignment: .leading) {
                Spacer()
                Text("Idées repas en 1 clic")
                    .foregroundColor(.white)
                    .font(.system(size: 20)).bold()
                Image("yellow_underline", bundle: Bundle(for: RecipeCardVM.self))
                    .position(x: 145.0, y: -12.0)
            }
            Spacer()
        }
        .padding(EdgeInsets(top: 17, leading: 11, bottom: 17, trailing: 11))
        .frame(maxWidth: .infinity, maxHeight: 80.0)
        .background(MiamColor.sharedInstance.backgroundDark)
    }
}

internal struct CatalogViewToolbar: View {
    let myIdeas = "Mes idées repas"
    let filtersTapped: () -> Void

    var body: some View {
        HStack {
            Spacer()
            Button {
                // TODO: open search
            } label: {
                Image("search", bundle: Bundle(for: RecipeCardVM.self))
            }.frame(width: 40, height: 40).background(MiamColor.sharedInstance.primary).clipShape(Circle())
            Spacer()
            Button {
                filtersTapped()
            } label: {
                Image("filters", bundle: Bundle(for: RecipeCardVM.self))
            }.frame(width: 40, height: 40).background(MiamColor.sharedInstance.primary).clipShape(Circle())
            Spacer()
            Button {
                // TODO: open favorites
            } label: {
                Image("Like", bundle: Bundle(for: RecipeCardVM.self))
                Text(myIdeas).foregroundColor(MiamColor.sharedInstance.primary)
            }
            .padding(EdgeInsets(top: 9, leading: 20, bottom: 9, trailing: 20))
            .border(MiamColor.sharedInstance.primary, width: 1.0)
            Spacer()
        }.padding(10)
    }
}

struct CatalogView_Preview: PreviewProvider {
    static var previews: some View {
        CatalogView()
    }
}
