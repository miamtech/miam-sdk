//
//  RecipeCardView.swift
//  MiamIOSFramework
//
//  Created by miam on 17/02/2022.
//

import SwiftUI
import shared

public struct RecipeCardView: View {

    public var recipeId: String

    @ObservedObject var viewModel: RecipeCardVM = RecipeCardVM()

    public init(recipeId: String) {
        self.recipeId = recipeId
    }
    

    public var body: some View {
        VStack {
            if(viewModel.recipe ?? nil != nil) {
                Text(viewModel.recipe!.attributes?.title ?? "")
                    .font(.title)
                    .bold()
                if(viewModel.recipe!.attributes?.mediaUrl ?? nil != nil ) {
                    AsyncImage(
                        url: URL(
                            string: viewModel.recipe!.attributes?.mediaUrl ?? ""
                        )! ,
                    placeholder: { Text("loading ...")}
                    )
                }
            }
          }
        .onAppear(perform: {
            viewModel.setEvent(
                event: RecipeContractEvent.OnGetRecipe(idRecipe: self.recipeId))
        })
    }
}

struct RecipeCardView_Previews: PreviewProvider {
    static var previews: some View {
        RecipeCardView(recipeId: "0")
    }
}

