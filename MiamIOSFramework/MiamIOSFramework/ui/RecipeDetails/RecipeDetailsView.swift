//
//  RecpieDetailsView.swift
//  MiamIOSFramework
//
//  Created by Miam on 11/05/2022.
//

import SwiftUI
import miamCore


@available(iOS 14, *)
public struct RecipeDetailsView: View {
    
    public var recipeId: String?
    public var close: () -> ()
    public var showFooter = true
    @SwiftUI.State var lulu = false
    @ObservedObject var viewModel: RecipeCardVM
    
    public init(recipeId: String, close: @escaping () -> (), showFooter: Bool = true) {
        self.recipeId = recipeId
        self.close = close
        viewModel = RecipeCardVM(routerVM: RouterOutletViewModel())
        self.showFooter = showFooter
    }
    
    public init(vmRecipe: RecipeCardVM, close: @escaping () -> (), showFooter: Bool = true) {
        self.close = close
        self.viewModel = vmRecipe
        self.showFooter = showFooter
    }
    
    public var body: some View {
        VStack {
            TitleBarView(showBackButton: true, backAction: close, titleView: AnyView(
                HStack(){
                    Image.miamImage(icon: .ideeRepas)
                        .resizable()
                        .scaledToFill()
                        .frame(width:24, height:24)
                    Text(RecipeCardText.sharedInstance.recipeFlag)
                        .font(.system(size: 14.0, design: .default))
                }.padding(.horizontal,16)
                    .padding(.vertical,4)
                    .background(Color.miamColor(.musterd))
                    .cornerRadius(8).rotationEffect(Angle(degrees: -2.0))
                
            ))
            
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
                                        height: 280
                                    ).frame(height: 280)
                                    if(viewModel.likeIsEnable()){
                                        HStack {
                                            LikeButton(recipeVm: viewModel)
                                            
                                            Spacer()
                                            
                                            Button(action: {
                                                
                                            }) {
                                                Image.miamImage(icon: .help)
                                                    .renderingMode(.original)
                                            }
                                            .frame(width: 40.0, height: 40.0, alignment: .center).background(Color.miamColor(.greySurface)).cornerRadius(25)
                                        }.frame(height: 50.0, alignment: .topLeading).padding(.horizontal, Dimension.sharedInstance.lPadding)
                                    }
                                    HStack() {
                                        Text(viewModel.recipe?.attributes?.title ?? "")
                                            .foregroundColor(Color.miamColor(.black))
                                            .font(.system(size: 20, weight: .heavy, design: .default))
                                            .padding(.horizontal, Dimension.sharedInstance.lPadding)
                                            .frame( alignment: .topLeading)
                                        Spacer()
                                    }
                                    
                                    HStack(alignment: .center) {
                                        HStack {
                                            RecipeDetailsDifficulty(difficulty: Int(truncating: viewModel.recipe?.attributes?.difficulty ?? 1))
                                        }
                                        Divider().frame(height: 20).padding(.horizontal, Dimension.sharedInstance.lPadding)
                                        HStack {
                                            VStack(alignment: .center) {
                                                Image.miamImage(icon: .clock).frame(width: 25, height:25, alignment: .center)
                                                Text(viewModel.recipe?.totalTime ?? "10 min").foregroundColor(Color.miamColor(.secondaryText)).font(.system(size: 13.0, weight: .regular, design: .default))
                                            }
                                        }
                                        Spacer()
                                    }.padding(.vertical, Dimension.sharedInstance.lPadding)
                                        .padding(.horizontal, Dimension.sharedInstance.lPadding)
                                    
                                }
                                HStack {
                                    if(viewModel.recipe!.preparationTimeIos != "0") {
                                        HStack{
                                            Text(RecipeDetailsText.sharedInstance.preparationTime + " :").foregroundColor(Color.miamColor(.secondaryText)).font(.system(size: 13.0, weight: .regular, design: .default))
                                            Text(viewModel.recipe!.preparationTimeIos).foregroundColor(Color.miamColor(.secondaryText)).font(.system(size: 13.0, weight: .heavy, design: .default))
                                        }
                                    }
                                    if(viewModel.recipe!.cookingTimeIos != "0") {
                                        HStack{
                                            Text(RecipeDetailsText.sharedInstance.cookingTime + " :").foregroundColor(Color.miamColor(.secondaryText)).font(.system(size: 13.0, weight: .regular, design: .default))
                                            Text(viewModel.recipe!.cookingTimeIos).foregroundColor(Color.miamColor(.secondaryText)).font(.system(size: 13.0, weight: .heavy, design: .default))
                                        }
                                    }
                                    if(viewModel.recipe!.restingTimeIos != "0") {
                                        HStack{
                                            Text(RecipeDetailsText.sharedInstance.restingTime + " :").foregroundColor(Color.miamColor(.secondaryText)).font(.system(size: 13.0, weight: .regular, design: .default))
                                            Text(viewModel.recipe!.restingTimeIos).foregroundColor(Color.miamColor(.secondaryText)).font(.system(size: 13.0, weight: .heavy, design: .default))
                                        }
                                    }
                                    Spacer()
                                }.padding(.horizontal, Dimension.sharedInstance.lPadding)
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
                                            .foregroundColor(Color.miamColor(.primaryText))
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
                                    .background(Color.miamColor(.borderLight))
                                    .padding(.horizontal, Dimension.sharedInstance.lPadding)
                                
                                //Ingredients ListView
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
                                }.background(Color.miamColor(.primaryLighter)).cornerRadius(15.0).padding( .horizontal, Dimension.sharedInstance.lPadding)
                            }
                            
                            //Étapes Heading
                            
                            if(Template.sharedInstance.recipeDetailStepsTemplate != nil){
                                Template.sharedInstance.recipeDetailStepsTemplate!(
                                    viewModel.recipe?.sortedStep ?? [RecipeStep](),
                                    viewModel
                                )
                            }
                            else{
                                HStack {
                                    Text("Étapes").foregroundColor(Color.miamColor(.primaryText)).font(.system(size: 20, weight: .heavy, design: .default)).padding(Dimension.sharedInstance.lPadding)
                                    Spacer()
                                }.frame(height: 60.0, alignment: .topLeading)
                                    .padding(.top, Dimension.sharedInstance.lPadding)
                                
                                //Steps
                                Divider().background(Color.miamColor(.borderLight))
                                    .padding(.horizontal, Dimension.sharedInstance.lPadding)
                                
                                //Steps ListView
                                VStack {
                                    VStack {
                                        ForEach(viewModel.sortedSteps, id: \.self) { step in
                                            let index = Int(step.attributes!.stepNumber)
                                            let isChecked = viewModel.currentState.activeStep > index
                                            StepRow(
                                                index: index,
                                                step: step,
                                                isCheck: isChecked,
                                                onToogleCheckbox:  {
                                                    viewModel.setEvent(event: RecipeContractEvent.SetActiveStep(stepIndex: Int32(index + 1)))
                                                }
                                            )
                                        }
                                    }.padding(.vertical, Dimension.sharedInstance.lPadding)
                                }.padding( .horizontal, Dimension.sharedInstance.lPadding)
                            }
                        }
                    }.padding(.bottom, 100.0)
                }.onAppear(perform: {
                    if(recipeId != nil){
                        viewModel.setEvent(
                            event: RecipeContractEvent.OnFetchRecipe(idRecipe: self.recipeId!))
                    }
                }
                )
            }
            
            if (showFooter) {
                RecipeDetailsFooter(recipeVM: viewModel)
            }
        }
    }
}

