//
//  RecipeCardSucessView.swift
//  MiamIOSFramework
//
//  Created by Miam on 20/06/2022.
//

import SwiftUI
import miamCore


@available(iOS 14, *)
struct RecipeCardSuccessView: View {
    
    @ObservedObject var viewModel: RecipeCardVM
    
    public var recipeId: String?
    public var criteria: SuggestionsCriteria?

    private let showMealIdeaTag: Bool
    @SwiftUI.State private var initialDialogScreen = RouterContent.recipeDetail
    @SwiftUI.State var showingPopup = false
    
    public init(viewModel: RecipeCardVM, recipeId: String, showMealIdeaTag: Bool = true) {
        self.recipeId = recipeId
        self.viewModel = viewModel
        self.showMealIdeaTag = showMealIdeaTag
    }
    
    public init(viewModel: RecipeCardVM, criteria: SuggestionsCriteria, showMealIdeaTag: Bool = true) {
        self.criteria = criteria
        self.viewModel = viewModel
        self.showMealIdeaTag = showMealIdeaTag
    }
    
    var body: some View {
        if (Template.sharedInstance.recipeCardTemplate != nil) {
            Template.sharedInstance.recipeCardTemplate!(
                viewModel,
                {
                    viewModel.goToDetail()
                    showingPopup = true
                },
                {}).sheet(isPresented: $showingPopup) {
                    Dialog(
                        close: { showingPopup = false },
                        initialRoute : initialDialogScreen,
                        routerVm: viewModel.routerVM
                    )
                }.onAppear(perform: {
                    if(recipeId != nil){
                        viewModel.setEvent(
                            event: RecipeContractEvent.OnFetchRecipe(idRecipe: self.recipeId!))
                    } else if (criteria != nil) {
                        viewModel.setEvent(
                            event: RecipeContractEvent.OnSetCriteria(crieria: self.criteria!))
                    }
                }
                )
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
                                height : 240
                            ).frame(height: 240).onTapGesture {
                                viewModel.goToDetail()
                                showingPopup = true
                            }
                            HStack(alignment: .center) {
                                if (showMealIdeaTag) {
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
                                }
                                Spacer()
                                if(viewModel.likeIsEnable()){
                                    LikeButton(recipeVm: viewModel)
                                }
                            }.padding([.leading,.trailing],8).padding(.top,16)
                        }.frame(height: 240)
                        Text(viewModel.recipe!.attributes?.title ?? "")
                            .lineLimit(2)
                            .foregroundColor(Color.miamColor(.black))
                            .font(.system(size: 13.0, weight: .bold, design: .default))
                            .padding(EdgeInsets(top: Dimension.sharedInstance.mlPadding,
                                                leading: Dimension.sharedInstance.lPadding,
                                                bottom: Dimension.sharedInstance.mlPadding,
                                                trailing: Dimension.sharedInstance.lPadding))

                        HStack(alignment: .center, spacing: Dimension.sharedInstance.lPadding) {
                            IconWithText(icon: .clock, text: viewModel.recipe?.totalTime ?? "")
                            Divider()
                            IconWithText(icon: .whisk, text: viewModel.recipe?.difficultyLabel ?? "")
                        }

                        Button {
                            if !viewModel.isInCart {
                                viewModel.setEvent(event: RecipeContractEvent.OnAddRecipe())
                                viewModel.routerVM.setEvent(event: RouterOutletContractEvent.GoToPreview(recipeId: viewModel.recipe?.id ?? "", vm: viewModel))
                            } else {
                                viewModel.goToDetail()
                            }
                            showingPopup = true
                        } label: {
                            if !viewModel.isInCart {
                                HStack {
                                    Text(MiamText.sharedInstance.checkBasketPreview)
                                    Image.miamImage(icon: .cart)
                                }
                            } else {
                                HStack {
                                    Text(MiamText.sharedInstance.viewRecipeDetail)
                                    Image.miamImage(icon: .check)
                                }
                            }
                        }.foregroundColor(!viewModel.isInCart ? Color.miamColor(.white) : Color.miamColor(.primary))
                            .frame(minHeight: 50.0, maxHeight: 50.0)
                            .padding(.horizontal, Dimension.sharedInstance.lPadding)
                            .background(!viewModel.isInCart ? Color.miamColor(.primaryText) : Color.miamColor(.white))
                            .cornerRadius(25)
                            .font(.system(size: 14.0, weight: .bold, design: .default))
                            .overlay(Capsule().stroke(Color.miamColor(.primary), lineWidth: 1.0))
                            .padding(.bottom, Dimension.sharedInstance.lPadding)
                    }
                    
                    
                }.sheet(isPresented: $showingPopup) {
                    Dialog(
                        close: { showingPopup = false },
                        initialRoute : initialDialogScreen,
                        routerVm: viewModel.routerVM
                    )
                }.cornerRadius(15).clipped().overlay(
                    RoundedRectangle(cornerRadius: 15)
                        .stroke(Color.miamColor(.border), lineWidth: 1)
                )
            }.frame(height: 400)
        }
    }
}
