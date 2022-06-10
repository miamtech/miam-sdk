//
//  ProductView.swift
//  MiamIOSFramework
//
//  Created by John on 15/05/2022.
//

import SwiftUI
import shared

public struct BasketPreviewView: View {
    @ObservedObject private var viewModel: BasketPreviewVM

    public init(recipeId: String) {
        viewModel = BasketPreviewVM(recipeId: recipeId)
    }

    func increaseGuestsCount() {
        
    }

    func decreaseGuestsCount() {

    }

    public var body: some View {
        VStack {
            // Top Bar
            HStack {
                Image("Arrow")
                    .resizable()
                    .aspectRatio(contentMode: .fit)
                    .frame(width: 30, height: 30, alignment: .center)
                    .padding(.leading, Dimension.sharedInstance.lPadding)
                
                Text("XX produits ajoutés à votre panier")
                    .font(.system(size: 16.0, weight: .bold, design: .default))
                    .padding(.leading, Dimension.sharedInstance.lPadding)
                Spacer()
            }.frame(width: .infinity, height: 50, alignment: .leading)

            ScrollView {
                BasketPreviewHeader(basketTitle: viewModel.basketTitle, basketDescription: viewModel.basketDescription, pricePerGuest: viewModel.pricePerGuest, numberOfGuests: Int(viewModel.numberOfGuests), price: viewModel.price, pictureURL: viewModel.pictureURL!, descreaseGuestsCount: {}, increaseGuestsCount: {})

                //Product View
//                VStack(alignment: .leading) {
//
//
//                    //Ingredeient View
//
//                }.padding().background(MiamColor.sharedInstance.lightPrimaryBg)
//                    .padding(.top, -8)

                Divider().background(MiamColor.sharedInstance.borderBottom)

                //List
//                if let found = viewModel.$basketPreviewLine?.entries?.found {
//                    ForEach(found, id: \.self) { entry in
//                            BasketPreviewRow()
//                    }
//                }

                    HStack {
                        Text(MiamText.sharedInstance.mealRowAlready)
                            .font(.system(size: 16.0, weight: .bold, design: .default))
                            .foregroundColor(MiamColor.sharedInstance.bodyText)

                        Spacer()

                        Image("Caret")
                            .resizable()
                            .aspectRatio( contentMode: .fit)
                            .frame(width: 30, height: 30, alignment: .center)
                    }
                    .padding(Dimension.sharedInstance.lPadding)
                    .background(MiamColor.sharedInstance.primaryLight)
                    .cornerRadius(10).padding(.all, Dimension.sharedInstance.lPadding)

                    HStack {
                        Text("Oeufs")
                            .font(.system(size: 16.0, weight: .bold, design: .default))
                            .foregroundColor(MiamColor.sharedInstance.bodyText)

                        Spacer()

                        Image("PlusGreen")
                            .resizable()
                            .aspectRatio( contentMode: .fit)
                            .frame(width: 30, height: 30, alignment: .center)
                            .foregroundColor(MiamColor.sharedInstance.primary)

                        Text("Ajouter")
                            .font(.system(size: 16.0, weight: .bold, design: .default))
                            .foregroundColor(MiamColor.sharedInstance.primary)
                    }.padding(.horizontal, Dimension.sharedInstance.lPadding)
                        .padding(.bottom, Dimension.sharedInstance.lPadding)
            }

            BasketPreviewBottomView {

            } continueShoppingAction: {

            }
        }
    }
}

fileprivate struct HeaderView: View {
    @ObservedObject var model: BasketPreviewVM

    private let headerHeight = 150.0

    var body: some View {
        HStack {
            AsyncImage(url: model.pictureURL!, placeholder: {
                    ProgressView()
                }, height: 150.0).frame(width: 150, height: 150, alignment: .topLeading)
            EmptyView().frame(width: 150, height: 150, alignment: .topLeading)


            VStack (alignment: .leading) {
                Text(model.basketTitle)
                    .foregroundColor(MiamColor.sharedInstance.black)
                    .font(.system(size: 16, weight: .heavy, design: .default))
                    .padding(.leading, Dimension.sharedInstance.sPadding)

                Text(model.basketDescription)
                    .foregroundColor(MiamColor.sharedInstance.bodyText)
                    .font(.system(size: 16, weight: .light, design: .default))
                    .padding(.leading, Dimension.sharedInstance.sPadding)
                    .padding(.top, Dimension.sharedInstance.borderWidth)

                Text(model.pricePerGuest)
                    .foregroundColor(MiamColor.sharedInstance.bodyText)
                    .font(.system(size: 16, weight: .light, design: .default))
                    .padding(.leading, Dimension.sharedInstance.sPadding)
            }.frame(width: .infinity, height: .infinity, alignment: .topLeading)
        }.frame(width: .infinity, height: headerHeight, alignment: .topLeading)
        HStack {
            Text("\(model.price) €").foregroundColor(MiamColor.sharedInstance.primary).fontWeight(.bold).padding([.leading], Dimension.sharedInstance.lPadding)
            Spacer()
            
            CounterView(count: Int(model.numberOfGuests), isDisable: false) {

            } decrease: {

            }

        }
        Divider().background(MiamColor.sharedInstance.borderBottom).frame(height: 1.0, alignment: .leading)
    }
}

fileprivate struct ContentView: View {
    //let basketPreviewLine = BasketPreviewLine(id: "1", record: Recipe(id: "1", attributes: nil, json_relationships: nil, includedRecords: []), isRecipe: true, inlineTag: "tag", title: "La soupe aux choux", picture: "", description: ["6 personnes"], price: "4.55", count: 4, entries: nil, _displayMode: true)
    var body: some View {
        VStack {
//            ScrollView {
//                HeaderView(line: basketPreviewLine)
//            }.frame(width: .infinity, height: .infinity, alignment: .topLeading)
//            BasketPreviewBottomView {
//
//            } continueShoppingAction: {
//
//            }
        }
    }
}

struct BasketPreviewView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView()
    }
}

