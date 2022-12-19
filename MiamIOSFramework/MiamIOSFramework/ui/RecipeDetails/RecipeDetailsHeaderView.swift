//
//  RecipeDetailsHeaderView.swift
//  MiamIOSFramework
//
//  Created by Vincent Kergonna on 21/09/2022.
//

import SwiftUI

@available(iOS 14, *)
public struct RecipeDetailsHeaderView: View {
    let mediaURL: String?
    let title: String
    let difficulty: Int
    let totalTime: String
    @Binding var showTitleInHeader: Bool
    let isLikeEnabled: Bool
    let recipeId: String?
    let imageHeight = 280.0
    
    public init(mediaURL: String?, title: String, difficulty: Int, totalTime: String, showTitleInHeader: Bool, isLikeEnabled: Bool, recipeId: String?) {
        self.mediaURL = mediaURL
        self.title = title
        self.difficulty = difficulty
        self.totalTime = totalTime
        self.showTitleInHeader = showTitleInHeader
        self.isLikeEnabled = isLikeEnabled
        self.recipeId = recipeId
    }
    
    public var body: some View {
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
                    .miamFontStyle(style: MiamFontStyleProvider.sharedInstance.titleStyle)
                    .foregroundColor(Color.miamColor(.black))
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
                        Text(totalTime)
                            .miamFontStyle(style: MiamFontStyleProvider.sharedInstance.bodySmallStyle)
                            .foregroundColor(Color.miamColor(.secondaryText))
                    }
                }
                Spacer()
            }.padding(.vertical, Dimension.sharedInstance.lPadding)
                .padding(.horizontal, Dimension.sharedInstance.lPadding)
        }
    }
}
