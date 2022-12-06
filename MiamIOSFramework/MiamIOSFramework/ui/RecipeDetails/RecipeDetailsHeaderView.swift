//
//  RecipeDetailsHeaderView.swift
//  MiamIOSFramework
//
//  Created by Vincent Kergonna on 21/09/2022.
//

import SwiftUI

@available(iOS 14, *)
struct RecipeDetailsHeaderView: View {
    let mediaURL: String?
    let title: String
    let difficulty: Int
    let totalTime: String
    @Binding var showTitleInHeader: Bool
    let isLikeEnabled: Bool
    let recipeId: String?
    let imageHeight = 280.0
    
    var body: some View {
        if let template = Template.sharedInstance.recipeDetailsHeaderTemplate {
            template(mediaURL,
                     title,
                     difficulty,
                     totalTime,
                     $showTitleInHeader,
                     isLikeEnabled)
        } else {
            AsyncImage(
                url: URL(
                    string: mediaURL ?? ""
                )! ,
                height: imageHeight
            ).frame(height: imageHeight)
            
            if (isLikeEnabled) {
                HStack {
                    LikeButton(recipeId: recipeId!)
                    
                    Spacer()
                    
                    Button(action: {
                        
                    }) {
                        Image.miamImage(icon: .help).renderingMode(.original)
                    }
                    .frame(width: 40.0, height: 40.0, alignment: .center).background(Color.miamColor(.greySurface)).cornerRadius(25)
                }.frame(height: 50.0, alignment: .topLeading).padding(.horizontal, Dimension.sharedInstance.lPadding)
            }
            
            HStack() {
                Text(title)
                    .foregroundColor(Color.miamColor(.black))
                    .font(.system(size: 20, weight: .heavy, design: .default))
                    .padding(.horizontal, Dimension.sharedInstance.lPadding)
                    .frame( alignment: .topLeading)
                    .background(GeometryReader {
                        Color.clear.preference(key: ViewOffsetKey.self,
                                               value: -$0.frame(in: .named("scroll")).origin.y)
                    })
                    .onPreferenceChange(ViewOffsetKey.self) {
                        if($0 > 24){
                            showTitleInHeader = true
                        }else {
                            showTitleInHeader = false
                        }
                    }
                Spacer()
            }
            
            HStack(alignment: .center) {
                HStack {
                    RecipeDetailsDifficulty(difficulty: difficulty)
                }
                Divider().frame(height: 20).padding(.horizontal, Dimension.sharedInstance.lPadding)
                HStack {
                    VStack(alignment: .center) {
                        Image.miamImage(icon: .clock).frame(width: 25, height:25, alignment: .center)
                        Text(totalTime).foregroundColor(Color.miamColor(.secondaryText)).font(.system(size: 13.0, weight: .regular, design: .default))
                    }
                }
                Spacer()
            }.padding(.vertical, Dimension.sharedInstance.lPadding)
                .padding(.horizontal, Dimension.sharedInstance.lPadding)
        }
    }
}
