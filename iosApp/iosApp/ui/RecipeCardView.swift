//
//  RecipeCardView.swift
//  iosApp
//
//  Created by Miam on 12/01/2022.
//  Copyright © 2022 orgName. All rights reserved.
//

import SwiftUI
import shared

struct RecipeCardView: View {

    var recipeId: Int

    @ObservedObject var viewModel: RecipeCardVM = RecipeCardVM()

    init(recipeId: Int) {
        self.recipeId = recipeId
    }
    

    var body: some View {
        VStack {
            Text("" + viewModel.recipe.attributes.title)
                .font(.title)
                .bold()
            if(viewModel.recipe.attributes.mediaUrl != nil ) { AsyncImage(url: URL(string: "" + (viewModel.recipe.attributes.mediaUrl ?? ""))! ,
                                                                  placeholder: { Text("loading ...")})}
           

          }
        .onAppear(perform: {
            viewModel.setEvent(event: RecipeContractEvent.OnGetRecipe(idRecipe: Int32(self.recipeId)))
        })
    }
}

struct CharacterDetailView_Previews: PreviewProvider {
    static var previews: some View {
        RecipeCardView(recipeId: 0)
    }
}
