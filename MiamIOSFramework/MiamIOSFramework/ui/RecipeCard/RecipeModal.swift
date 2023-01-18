//
//  RecipeCardModal.swift
//  MiamIOSFramework
//
//  Created by Vincent Kergonna on 11/01/2023.
//  Copyright © 2023 Miam. All rights reserved.
//

import SwiftUI
import miamCore

@available(iOS 14, *)
struct RecipeModal: View {
    @StateObject private var recipeViewModel: RecipeCardVM = RecipeCardVM(routerVM: RouterOutletViewModel())
    @SwiftUI.State private var showBasketPreview: Bool = false
    @SwiftUI.State private var showItemSelector: Bool = false
    let recipeId: String
    let close: () -> Void
    
    init(recipeId: String, showBasketPreview: Bool = false, close: @escaping () -> Void) {
        self.showBasketPreview = showBasketPreview
        self.recipeId = recipeId
        self.close = close
    }
    
    var body: some View {
        if let template = Template.sharedInstance.recipeModalTemplate {
            template(recipeId, recipeViewModel, close)
        } else {
            NavigationView {
                VStack {
                    RecipeDetailsView(vmRecipe: recipeViewModel, showFooter: true, close: {
                        close()
                    }, navigateToPreview: {
                        self.showBasketPreview = true
                    }, buy: {
                        recipeViewModel.setEvent(event: RecipeContractEvent.OnAddRecipe())
                    })
                    .navigationTitle(recipeViewModel.recipe?.attributes?.title ?? "")
                    
                    NavigationLink("Produits dans votre panier", isActive: $showBasketPreview) {
                        VStack  {
                            BasketPreviewView(recipeId: recipeId, recipeVm: recipeViewModel) { _, _ in
                                self.showBasketPreview = false
                            } close: {
                                close()
                            } goToItemSelector: {
                                self.showItemSelector = true
                            }
                            
                            NavigationLink(ItemSelectorText.sharedInstance.swapProduct, isActive: $showItemSelector) {
                                ItemSelector {
                                    self.showItemSelector = false
                                }
                            }.hidden()
                        }.frame(maxHeight: .infinity)
                    }.hidden()
                }
                .navigationBarTitleDisplayMode(.inline)
                .toolbar {
                    ToolbarItem(placement: .navigationBarLeading) {
                        Button {
                            close()
                        } label: {
                            Image.miamImage(icon: MiamIcon.back)
                                .renderingMode(.template)
                                .foregroundColor(Color.miamColor(.primary))
                        }
                    }
                }
                .frame(maxHeight: .infinity)
            }
            .accentColor(Color.miamColor(.primary))
            .frame(maxHeight: .infinity)
            .onAppear {
                recipeViewModel.fetchRecipe(recipeId: recipeId)
            }
        }
    }
        
    func navigateToBasketPreview() {
        self.showBasketPreview.toggle()
    }
}
