//
//  CharacterView.swift
//  iosApp
//
//  Created by Miam on 15/9/21.
//  Copyright © 2021 orgName. All rights reserved.
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
            Text(viewModel.recipe.attributes.name)
                .font(.title)
                .bold()
            AsyncImage(url: URL(string: viewModel.recipe!.attributes.mediaUrl )!,
                                      placeholder: { Text("loading ...")})

          }
        .onAppear(perform: {
            viewModel.setEvent(event: RecipeCardContractEvent.GetRecipe(idRecipe: Int32(recipeId)))
        })
    }
}

struct CharacterDetailView_Previews: PreviewProvider {
    static var previews: some View {
        RecipeCardView(recipeId: 0)
    }
}
