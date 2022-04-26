//
//  RecipeCardView.swift
//  MiamIOSFramework
//
//  Created by miam on 17/02/2022.
//

import SwiftUI
import shared

public struct RecipeCardView: View {
    
    public var recipeId: String?
    public var criteria: SuggestionsCriteria?
    
    @ObservedObject var viewModel: RecipeCardVM = RecipeCardVM()
    
    public init(recipeId: String) {
        self.recipeId = recipeId
    }
    
    public init(criteria: SuggestionsCriteria) {
        self.criteria = criteria
    }
    
    public var body: some View {
        ZStack {
            VStack {
                if(viewModel.recipe ?? nil != nil) {
                    if(viewModel.recipe!.attributes?.mediaUrl ?? nil != nil ) {
                        ZStack {
                            AsyncImage(
                                url: URL(
                                    string: viewModel.recipe!.attributes?.mediaUrl ?? ""
                                )! ,
                                placeholder: { Text("loading ...")}
                            ).frame(height: 245)
                            
                            Text(viewModel.recipe!.attributes?.title ?? "")
                                .font(.title)
                                .padding(.horizontal,20).multilineTextAlignment(.center)
                                .foregroundColor(Color.white)
                            Button(action: {
                                
                                
                            }, label: {
                                Text("?")
                                    .font(.system(size: 16))
                                    .frame(width: 24, height: 24)
                                    .foregroundColor(Color.white)
                                
                                
                            })
                            .background(Color.gray)
                            .cornerRadius(38.5)
                            .padding()
                            .offset(x: -130, y: -100)
                            .shadow(color: Color.black.opacity(0.3),
                                    radius: 3,
                                    x: 3,
                                    y: 3)
                        }
                        
                    }
                    HStack{
                        HStack{
                            VStack{
                                Image(systemName: "clock").padding(.bottom,2)
                                Text(viewModel.recipe!.totalTime)
                            }
                            Divider()
                            VStack{
                                Image(systemName: "clock").padding(.bottom,2)
                                Text(viewModel.recipe!.difficultyLabel)
                            }
                            
                        }
                        Spacer()
                        Text("14€")
                    }.padding([.trailing,.leading],16)
                    HStack{
                        HStack{
                            Image(systemName: "person")
                            
                            Button(action: {
                                print("button pressed")
                            }) {
                                Image(systemName: "minus")
                            }
                            Text("0")
                            Button(action: {
                                print("button pressed")
                            }) {
                                Image(systemName: "plus")
                            }
                        }
                        Spacer()
                        Button(action: {
                            
                            
                        }){
                            Image(systemName: "cart") .frame(width: 30, height: 30)
                                .foregroundColor(Color.white)
                            
                        }
                        .background(Color.blue)
                        .cornerRadius(38.5)
                        .padding(.bottom,8)
                        .shadow(color: Color.black.opacity(0.3),
                                radius: 3,
                                x: 3,
                                y: 3)
                    }.padding([.trailing,.leading],16)
                }
            }.cornerRadius(10)
                .overlay(
                    RoundedRectangle(cornerRadius: 10)
                        .stroke(Color(.sRGB, red: 150/255, green: 150/255, blue: 150/255, opacity: 0.1), lineWidth: 1)
                )}
        .padding([.top, .horizontal],8).onAppear(perform: {
            if(recipeId != nil){
                viewModel.setEvent(
                    event: RecipeContractEvent.OnGetRecipe(idRecipe: self.recipeId!))
            } else if (criteria != nil) {
                viewModel.setEvent(
                    event: RecipeContractEvent.OnSetCriteria(crieria: self.criteria!)                )
            }
            
        })
    }
}


struct RecipeCardView_Previews: PreviewProvider {
    static var previews: some View {
        RecipeCardView(recipeId: "0")
    }
}

