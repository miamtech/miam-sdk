//
//  RecipeCardSucessView.swift
//  MiamIOSFramework
//
//  Created by Miam on 20/06/2022.
//

import SwiftUI
import shared


@available(iOS 14, *)
struct RecipeCardSuccessView: View {
    
    @ObservedObject var viewModel: RecipeCardVM
    
    public var recipeId: String?
    public var criteria: SuggestionsCriteria?
    
    @SwiftUI.State private var initialDialogScreen = RouterContent.recipeDetail
    @SwiftUI.State var showingPopup = false
    
    public init(viewModel: RecipeCardVM, recipeId: String) {
        self.recipeId = recipeId
        self.viewModel = viewModel
    }
    
    public init(viewModel: RecipeCardVM, criteria: SuggestionsCriteria) {
        self.criteria = criteria
        self.viewModel = viewModel
    }
    
    var body: some View {
        if (Template.sharedInstance.recipeCardTemplate != nil) {
            Template.sharedInstance.recipeCardTemplate!(
                viewModel,
                {
                    viewModel.goToDetail()
                    showingPopup = true
                },
                {}).popover(isPresented: $showingPopup) {
                    Dialog(
                        close: { showingPopup = false },
                        initialRoute : initialDialogScreen,
                        routerVm: viewModel.routerVM
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
                                HStack(){
                                    Image("ideerepas", bundle: Bundle(for: RecipeCardVM.self))
                                        .resizable()
                                        .scaledToFill()
                                        .frame(width:24, height:24)
                                    Text(RecipeCardText.sharedInstance.recipeFlag)
                                        .font(.system(size: 14.0, design: .default))
                                }.padding(.horizontal,16)
                                    .padding(.vertical,4)
                                    .background(MiamColor.sharedInstance.musterd)
                                    .cornerRadius(8).rotationEffect(Angle(degrees: -2.0))
                                Spacer()
                                if(viewModel.likeIsEnable()){
                                    LikeButton(recipeVm: viewModel)
                                }
                            }.padding([.leading,.trailing],8).padding(.top,16)
                        }.frame(height: 240)
                        Text(viewModel.recipe!.attributes?.title ?? "")
                            .lineLimit(2)
                            .foregroundColor(MiamColor.sharedInstance.black)
                            .font(.system(size: 13.0, weight: .bold, design: .default))
                            .padding(EdgeInsets(top: Dimension.sharedInstance.mlPadding,
                                                leading: Dimension.sharedInstance.lPadding,
                                                bottom: Dimension.sharedInstance.mlPadding,
                                                trailing: Dimension.sharedInstance.lPadding))

                        HStack(alignment: .center, spacing: Dimension.sharedInstance.lPadding) {
                            IconWithText(imageName: "clock", text: viewModel.recipe?.totalTime ?? "")
                            Divider()
                            IconWithText(imageName: "whisk", text: viewModel.recipe?.difficultyLabel ?? "")
                        }

                        Button {
                            if !viewModel.isInCart {
                                viewModel.routerVM.setEvent(event: RouterOutletContractEvent.GoToPreview(recipeId: viewModel.recipe?.id ?? "", vm: viewModel))
                            } else {
                                viewModel.goToDetail()
                            }
                            showingPopup = true
                        } label: {
                            if !viewModel.isInCart {
                                HStack {
                                    Text(MiamText.sharedInstance.checkBasketPreview)
                                    Image("cart", bundle: Bundle(for: RecipeCardVM.self))
                                }
                            } else {
                                HStack {
                                    Text(MiamText.sharedInstance.viewRecipeDetail)
                                    Image("Check", bundle: Bundle(for: RecipeCardVM.self))
                                }
                            }
                        }.foregroundColor(!viewModel.isInCart ? MiamColor.sharedInstance.white : MiamColor.sharedInstance.primaryText)
                            .frame(minHeight: 50.0, maxHeight: 50.0)
                            .padding(.horizontal, Dimension.sharedInstance.lPadding)
                            .background(!viewModel.isInCart ? MiamColor.sharedInstance.primaryText : Color.white)
                            .cornerRadius(25)
                            .font(.system(size: 14.0, weight: .bold, design: .default))
                            .overlay(Capsule().stroke(MiamColor.sharedInstance.primary, lineWidth: 1.0))
                            .padding(.bottom, Dimension.sharedInstance.lPadding)
                    }
                    
                    
                }.popover(isPresented: $showingPopup) {
                    Dialog(
                        close: { showingPopup = false },
                        initialRoute : initialDialogScreen,
                        routerVm: viewModel.routerVM
                    )
                }.cornerRadius(15).clipped().overlay(
                    RoundedRectangle(cornerRadius: 15)
                        .stroke(MiamColor.sharedInstance.borderColor, lineWidth: 1)
                )
            }.frame(height: 400)
        }
    }
}
