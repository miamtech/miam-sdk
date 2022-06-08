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
    
    @SwiftUI.State private var initialDialogScreen = RouterContent.recipeDetail
    @SwiftUI.State var showingPopup = false
    @ObservedObject var viewModel: RecipeCardVM = RecipeCardVM()
   
    
    public init(recipeId: String) {
        self.recipeId = recipeId
    }
    
    public init(criteria: SuggestionsCriteria) {
        self.criteria = criteria
    }
    
    public var body: some View {
        if (Template.sharedInstance.recipeCardTemplate != nil) {
            Template.sharedInstance.recipeCardTemplate!(viewModel,{},{})
        } else {
            
            VStack {
                VStack() {
                    if(viewModel.recipe ?? nil != nil) {
                        
                        ZStack(alignment: .topLeading) {
                            AsyncImage(
                                url: URL(
                                    string: viewModel.recipe!.attributes?.mediaUrl ?? ""
                                )! ,
                                placeholder: { Text("loading ...")},
                                height : 245
                            ).frame(height: 245).onTapGesture {
                                initialDialogScreen = RouterContent.recipeDetail
                                showingPopup = true
                            }
                            HStack(alignment: .center) {
                                HStack(){
                                    Image("ideerepas")
                                        .resizable()
                                        .scaledToFill()
                                        .frame(width:24, height:24)
                                    Text(RecipeCardText.sharedInstance.recipeFlag)
                                        .font(.system(size: 14.0, design: .default))
                                }.padding(.horizontal,16)
                                    .padding(.vertical,4)
                                    .background(MiamColor.sharedInstance.musterd)
                                    .cornerRadius(8)
                                Spacer()
                                ZStack(){
                                    Circle().fill(MiamColor.sharedInstance.white)
                                        .frame(width: 32, height: 32)
                                    Image("Like")
                                        .frame(width: 24.0, height: 24.0, alignment: .center)
                                        .foregroundColor(MiamColor.sharedInstance.primaryText)
                                    
                                }
                            }.padding([.leading,.trailing],8).padding(.top,16)
                        }.frame(height: 245)
                        Text(viewModel.recipe!.attributes?.title ?? "")
                            .lineLimit(2)
                            .foregroundColor(MiamColor.sharedInstance.black)
                            .font(.system(size: 16.0, weight: .bold, design: .default))
                            .padding(Dimension.sharedInstance.lPadding)
                        Button(RecipeCardText.sharedInstance.addRecipe) {
                        action: do {
                            initialDialogScreen = RouterContent.recipeDetail
                            showingPopup = true
                        }
                        }.foregroundColor(MiamColor.sharedInstance.white)
                            .frame(minHeight: 50.0, maxHeight: 50.0)
                            .padding(.horizontal, Dimension.sharedInstance.lPadding)
                            .background(MiamColor.sharedInstance.primaryText)
                            .cornerRadius(25)
                            .font(.system(size: 16.0, weight: .bold, design: .default))
                            .padding(.bottom, Dimension.sharedInstance.lPadding)
                        
                    }
                   
                    
                }.popover(isPresented: $showingPopup) {
                    Dialog(
                        close: { showingPopup = false },
                        initialRoute : initialDialogScreen  ,
                        recipeVm: viewModel
                    )
                }.onAppear(perform: {
                    if(recipeId != nil){
                        viewModel.setEvent(
                            event: RecipeContractEvent.OnGetRecipe(idRecipe: self.recipeId!))
                    } else if (criteria != nil) {
                        viewModel.setEvent(
                            event: RecipeContractEvent.OnSetCriteria(crieria: self.criteria!))
                    }
                    
                }
                           
                                           
                ).cornerRadius(15).clipped().overlay(
                    RoundedRectangle(cornerRadius: 15)
                        .stroke(MiamColor.sharedInstance.borderColor, lineWidth: 1)
                ).padding(16)
               
            }
        }
    }
}


struct RecipeCardView_Previews: PreviewProvider {
    static var previews: some View {
        RecipeCardView(recipeId: "0")
    }
}

