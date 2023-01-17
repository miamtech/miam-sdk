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
    @StateObject var recipeViewModel: RecipeCardVM = RecipeCardVM(routerVM: RouterOutletViewModel())
    @SwiftUI.State var showBasketPreview: Bool = false
    @SwiftUI.State var showItemSelector: Bool = false
    let recipeId: String
    let close: () -> Void
    
    init(recipeId: String, showBasketPreview: Bool = false, close: @escaping () -> Void) {
        self.showBasketPreview = showBasketPreview
        self.recipeId = recipeId
        self.close = close
    }
    
    var body: some View {
        NavigationView {
            VStack {
                RecipeDetailsView(vmRecipe: recipeViewModel, showFooter: true, close: {
                    close()
                }, navigateToPreview: {
                    self.showBasketPreview = true
                }, buy: {
                    recipeViewModel.setEvent(event: RecipeContractEvent.OnAddRecipe())
                })
                .frame(maxHeight: .infinity)
                
                NavigationLink("Basket Preview", isActive: $showBasketPreview) {
                    VStack  {
                        BasketPreviewView(recipeId: recipeId, recipeVm: recipeViewModel) { _, _ in
                            self.showBasketPreview = false
                        } close: {
                            close()
                        } goToItemSelector: {
                            self.showItemSelector = true
                        }
                        
                        NavigationLink("Item Selector", isActive: $showItemSelector) {
                            ItemSelector {
                                self.showItemSelector = false
                            }
                        }.hidden()
                    }.frame(maxHeight: .infinity)
                }.hidden()
                
                
            }
            .frame(maxHeight: .infinity)
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
            .navigationBarTitleDisplayMode(.inline)
        }.onAppear {
            recipeViewModel.fetchRecipe(recipeId: recipeId)
        }
    }
    
    func navigateToBasketPreview() {
        self.showBasketPreview.toggle()
    }
}
