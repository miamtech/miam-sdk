//
//  RecpieDetailsView.swift
//  MiamIOSFramework
//
//  Created by Miam on 11/05/2022.
//

import SwiftUI
import shared

public struct RecpieDetailsView: View {
    
    public var recipeId: String?
    public var close: () -> ()
    
    @ObservedObject var viewModel: RecipeCardVM
    
    public init(recipeId: String, close: @escaping () -> ()) {
        self.recipeId = recipeId
        self.close = close
        viewModel = RecipeCardVM(routerVM: RouterOutletViewModel())
    }
    
    public init(vmRecipe: RecipeCardVM, close: @escaping () -> ()) {
        self.close = close
        self.viewModel = vmRecipe
    }
    
    public var body: some View {
        VStack {
            ScrollView {
                ZStack {
                    VStack{
                        if(viewModel.recipe != nil ){
                            if(Template.sharedInstance.recipeDetailInfosTemplate != nil){
                                Template.sharedInstance.recipeDetailInfosTemplate!(
                                    viewModel.recipe!
                                )
                            }
                            else{
                                VStack {
                                    AsyncImage(
                                        url: URL(
                                            string: viewModel.recipe!.attributes?.mediaUrl ?? ""
                                        )! ,
                                        placeholder: { Text("loading ...")},
                                        height: 250
                                    ).frame(height: 250).padding(.top,50)
                                    HStack {
                                        //TODO Put logic into like button component
                                        Button(action: {
                                            
                                        }) {
                                            Image("Like")
                                                .renderingMode(.original)
                                        }
                                        .padding(.leading).frame(width: 40, height: 40, alignment: .leading)
                                        Spacer()
                                        
                                        Button(action: {
                                            
                                        }) {
                                            Image("Help")
                                                .renderingMode(.original)
                                        }
                                        .frame(width: 40.0, height: 40.0, alignment: .center).background(MiamColor.sharedInstance.greySurface).cornerRadius(25)
                                    }.frame(height: 50.0, alignment: .topLeading).padding(.horizontal, Dimension.sharedInstance.lPadding)
                                    Text(viewModel.recipe?.attributes?.title ?? "")
                                        .foregroundColor(MiamColor.sharedInstance.black)
                                        .font(.system(size: 20, weight: .heavy, design: .default))
                                        .padding(.horizontal, Dimension.sharedInstance.lPadding)
                                        .frame( alignment: .topLeading)
                                    
                                    ZStack {
                                        HStack(alignment: .center) {
                                            HStack {
                                                Spacer()
                                                RecipeDetailsDifficulty(difficulty: Int(viewModel.recipe?.attributes?.difficulty ?? 1))
                                                Spacer()
                                            }
                                            Spacer()
                                            Divider().frame(height: 20)
                                            Spacer()
                                            HStack {
                                                Spacer()
                                                VStack(alignment: .center) {
                                                    Image( "Clock").frame(width: 25, height:25, alignment: .center)
                                                    Text(viewModel.recipe?.totalTime ?? "10 min").foregroundColor(MiamColor.sharedInstance.bodyText).font(.system(size: 13.0, weight: .regular, design: .default))
                                                }
                                                Spacer()
                                            }
                                        }.padding(.top, Dimension.sharedInstance.lPadding)
                                    }
                                }
                                RecipeDetailsMoreInfo(recipe: viewModel.recipe!)
                            }
                            
                            if(Template.sharedInstance.recipeDetailIngredientsTemplate != nil){
                                Template.sharedInstance.recipeDetailIngredientsTemplate!(
                                    viewModel.recipe!.relationships?.ingredients!.data ?? [],
                                    viewModel,
                                    Int(viewModel.currentState.guest),
                                    { viewModel.setEvent(event: RecipeContractEvent.IncreaseGuest()) },
                                    { viewModel.setEvent(event: RecipeContractEvent.DecreaseGuest()) }
                                )
                            }
                            else{
                                //Ingredients Heading
                                HStack {
                                    HStack {
                                        Text(String((viewModel.recipe!.relationships?.ingredients!.data.count)!) + " ingrédients")
                                            .foregroundColor(MiamColor.sharedInstance.primaryText)
                                            .font(.system(size: 20, weight: .heavy, design: .default))
                                            .padding(Dimension.sharedInstance.lPadding)
                                        Spacer()
                                        CounterView(
                                            count: Int(viewModel.currentState.guest),
                                            isDisable: false,
                                            increase: { viewModel.setEvent(event: RecipeContractEvent.IncreaseGuest()) },
                                            decrease: { viewModel.setEvent(event: RecipeContractEvent.DecreaseGuest()) }
                                        )
                                    }
                                }.frame(height: 60.0, alignment: .topLeading)
                                Divider()
                                    .background(MiamColor.sharedInstance.borderBottom)
                                    .padding(.horizontal, Dimension.sharedInstance.lPadding)
                                
                                //Ingredeients ListView
                                VStack {
                                    VStack {
                                        ForEach(viewModel.recipe!.relationships?.ingredients!.data ?? [], id: \.self) { ingr in
                                            IngredientRow(
                                                ingredientName: ingr.attributes!.name!,
                                                qty: viewModel.readableFloatNumber(value:
                                                                                    viewModel.realQuantities(
                                                                                        quantity: ingr.attributes!.quantity!,
                                                                                        currentGuest: viewModel.currentState.guest,
                                                                                        recipeGuest: viewModel.recipe!.attributes!.numberOfGuests
                                                                                    ),
                                                                                   unit: ingr.attributes!.unit))
                                        }
                                    }.padding(.vertical, Dimension.sharedInstance.lPadding)
                                }.background(MiamColor.sharedInstance.lightPrimaryBg).cornerRadius(15.0).padding( .horizontal, Dimension.sharedInstance.lPadding)
                            }
                            
                            //Étapes Heading
                            
                            if(Template.sharedInstance.recipeDetailStepsTemplate != nil){
                                Template.sharedInstance.recipeDetailStepsTemplate!(
                                    viewModel.recipe!.relationships?.recipeSteps!.data ?? [RecipeStep](),
                                    viewModel
                                )
                            }
                            else{
                                HStack {
                                    Text("Étapes").foregroundColor(MiamColor.sharedInstance.primaryText).font(.system(size: 20, weight: .heavy, design: .default)).padding(Dimension.sharedInstance.lPadding)
                                    Spacer()
                                }.frame(height: 60.0, alignment: .topLeading)
                                    .padding(.top, Dimension.sharedInstance.lPadding)
                                
                                //Steps
                                Divider().background(MiamColor.sharedInstance.borderBottom)
                                    .padding(.horizontal, Dimension.sharedInstance.lPadding)
                                
                                //Steps ListView
                                VStack {
                                    VStack {
                                        ForEach(0..<(viewModel.recipe!.relationships?.recipeSteps!.data ?? []).count, id: \.self) { i in
                                            StepRow(
                                                index: i,
                                                step: viewModel.recipe!.relationships!.recipeSteps!.data[i],
                                                isCheck: viewModel.currentState.activeStep <= i,
                                                onToogleCheckbox:  {
                                                    viewModel.setEvent(event: RecipeContractEvent.SetActiveStep(stepIndex: Int32(i)))
                                                }
                                            )
                                        }
                                    }.padding(.vertical, Dimension.sharedInstance.lPadding)
                                }.padding( .horizontal, Dimension.sharedInstance.lPadding)
                            }
                        }
                    }.padding(.bottom, 100.0)
                    if(viewModel.recipe != nil ){
                        RecipeDetailsHeader(recipe: viewModel.recipe!, closeDetail: { close() })
                    }
                }.onAppear(perform: {
                    if(recipeId != nil){
                        viewModel.setEvent(
                            event: RecipeContractEvent.OnGetRecipe(idRecipe: self.recipeId!))
                    }
                }
                )
            }
            RecipeDetailsFooter(recipeVM: viewModel)
        }
    }
}

