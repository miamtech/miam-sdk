//
//  RecipeCardView.swift
//  MiamIOSFramework
//
//  Created by noe on 17/02/2022.
//

import SwiftUI
import shared

public struct RecipeCardView: View {

    public var recipeId: Int

    @ObservedObject var viewModel: RecipeCardVM = RecipeCardVM()

    public init(recipeId: Int) {
        self.recipeId = recipeId
    }
    

    public var body: some View {
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

