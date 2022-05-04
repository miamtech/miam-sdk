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
        if(Template.sharedInstance.recipeCardTemplate != nil) {
            Template.sharedInstance.recipeCardTemplate!(viewModel,{},{})
        } else {
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
                                Rectangle()
                                       .foregroundColor(.clear)
                                       .background(LinearGradient(
                                        gradient: Gradient(
                                            colors: [.clear, .black]),
                                            startPoint: .top,
                                            endPoint: .bottom
                                       ))
                                Text(viewModel.recipe!.attributes?.title ?? "")
                                    .font(.title).bold()
                                    .padding(.horizontal,20).multilineTextAlignment(.center)
                                    .foregroundColor(Color.white)
                                if(viewModel.currentState.isInCart) {
                                    HStack{
                                        Text(RecipeCardText.sharedInstance.alreadyInCart)
                                            .foregroundColor(Color.white)
                                            .padding(4)
                                            .background(RoundedRectangle(cornerRadius: 4)
                                                .fill(MiamColor.sharedInstance.danger)
                                                )
                                        Spacer()
                                    }.padding([.leading],8).offset(x:0 , y: -100)
                                        
                                } else {
    //                                Button(action: {
    //                                }, label: {
    //                                    Text("?")
    //                                        .font(.system(size: 16))
    //                                        .frame(width: 24, height: 24)
    //                                        .foregroundColor(Color.white)
    //                                })
    //                                .background(Color.gray)
    //                                .cornerRadius(38.5)
    //                                .padding()
    //                                .offset(x: -130, y: -100)
    //                                .shadow(color: Color.black.opacity(0.3),
    //                                        radius: 3,
    //                                        x: 3,
    //                                        y: 3)
                                }
                                HStack{
                                    HStack{
                                        Image(uiImage: UIImage(named: "CookHat")!).resizable().scaledToFill().frame(width:16, height:16)
                                        Text(MiamText.sharedInstance.recipeFlag)
                                            .foregroundColor(Color.white)
                                    }.padding(8).background(RoundedRectangle(cornerRadius: 4)
                                        .fill(MiamColor.sharedInstance.info)
                                        )
                                    Spacer()
                                }
                               .offset(x: 0 , y: 120)
                               
                            }.onTapGesture {
                                initialDialogScreen = RouterContent.recipeDetail
                                showingPopup = true
                             }
                            
                        }
                        HStack{
                            HStack{
                                VStack{
                                    Image(uiImage: UIImage(named: "Clock")!).resizable().scaledToFill().frame(width:16, height:16).padding(.bottom,2)
                                    Text(viewModel.recipe!.totalTime).font(.system(size: 12.0))
                                }
                                Divider()
                                VStack{
                                    Image(uiImage: UIImage(named: "Difficulty")!).resizable().scaledToFill().frame(width:16, height:16).padding(.bottom,2)
                                    Text(viewModel.recipe!.difficultyLabel).font(.system(size: 12.0))
                                }
                                
                            }
                            Spacer()
                            PriceView(recipeId: viewModel.recipe?.id ?? "", guestNumber: Int(viewModel.currentState.guest))
                        }.padding([.trailing,.leading,.top],16)
                        HStack{
                            CounterView(count: Int(viewModel.currentState.guest) ,
                                        isDisable: false,
                                        increase: {() -> () in
                                viewModel.setEvent(event: RecipeContractEvent.IncreaseGuest.shared)
                                                  },
                                        decrease: {() -> () in
                                viewModel.setEvent(event: RecipeContractEvent.DecreaseGuest.shared)
                                                    }
                                )
                            Spacer()
                            Button(action: {
                                self.initialDialogScreen = RouterContent.basketPreview
                                showingPopup = true
                                viewModel.setEvent(event:
                                                    RecipeContractEvent.OnAddRecipe.shared)
                            }){
                                if(viewModel.currentState.isInCart){
                                    Image(systemName: "doc.text.magnifyingglass").frame(width: 30, height: 30)
                                        .foregroundColor(Color.white)
                                } else {
                                    Image(systemName: "cart").frame(width: 30, height: 30)
                                        .foregroundColor(Color.white)
                                }
                            }
                            .background(MiamColor.sharedInstance.primary)
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
            .padding([.top, .horizontal],8).popover(isPresented: $showingPopup) {
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
                
            })
        }
        
       
    }
}


struct RecipeCardView_Previews: PreviewProvider {
    static var previews: some View {
        RecipeCardView(recipeId: "0")
    }
}

