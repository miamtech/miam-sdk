//
//  RecipeDetailsHeader.swift
//  MiamIOSFramework
//
//  Created by Miam on 01/06/2022.
//

import SwiftUI
import shared

struct RecipeDetailsHeader: View {
    
    private var recipe: Recipe
    private var closeDetail: () -> ()
    
    
    init(recipe: Recipe, closeDetail: @escaping () -> ()){
        self.recipe = recipe
        self.closeDetail = closeDetail
    }
    
    
    var body: some View {
        GeometryReader { gr in
            if(Template.sharedInstance.recipeDetailHeaderTemplate != nil) {
                Template.sharedInstance.recipeDetailHeaderTemplate!(
                    {closeDetail()},
                    recipe
                )
            } else {
                VStack{
                    HStack {
                        Button(
                            action: {
                                closeDetail()
                            }
                        ) {
                            Image("Caret", bundle: Bundle(for: RecipeCardVM.self))
                                .renderingMode(.original)
                                .frame(
                                    width: 24,
                                    height: 24,
                                    alignment: .center
                                )
                        }
                        .frame( alignment: .center)
                        .rotationEffect(.degrees(180))
                        
                        if(gr.frame(in: .global).origin.y < -300){
                            Text(recipe.attributes!.title).lineLimit(1)
                        }
                        
                        Spacer()
                        
                        ZStack(){
                            Circle()
                                .fill(MiamColor.sharedInstance.musterd)
                                .frame(
                                    width: 40,
                                    height: 40
                                )
                            Image( "bucket", bundle: Bundle(for: RecipeCardVM.self))
                                .resizable()
                                .aspectRatio(contentMode: .fit)
                                .frame(
                                    width: 28,
                                    height: 28,
                                    alignment: .center
                                )
                        }
                    }.frame(
                        height: 50.0,
                        alignment: .topLeading
                    )
                    .padding(.top,50)
                    .padding(.horizontal,8)
                    .background(MiamColor.sharedInstance.white)
                    .offset(y: gr.frame(in: .global).origin.y < 0 // Is it going up?
                            ? abs(gr.frame(in: .global).origin.y) // Push it down!
                            : -gr.frame(in: .global).origin.y) // Push it up!

                }
            }
            
        }
    }
}

