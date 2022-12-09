//
//  RecipeDetailsDifficulty.swift
//  MiamIOSFramework
//
//  Created by Miam on 02/06/2022.
//

import SwiftUI


@available(iOS 14, *)
struct RecipeDetailsDifficulty: View {
    
    private var difficulty : Int
    
    init(difficulty : Int){
        self.difficulty = difficulty
    }
    
    var body: some View {
        VStack {
            switch difficulty {
            case 1 :
                HStack{
                    Image.miamImage(icon: .difficultyLow).frame( height: 24.0, alignment: .center)
                   
                }
                Text(MiamText.sharedInstance.difficultyEasy)
                    .miamFontStyle(style: MiamFontStyleProvider.sharedInstance.bodyMediumStyle)
                    .foregroundColor(Color.miamColor(.secondaryText))
            case 2 :
                HStack{
                    Image.miamImage(icon: .difficultyMedium).frame( height: 24.0, alignment: .center)
                }
                Text(MiamText.sharedInstance.difficultyMid)
                    .miamFontStyle(style: MiamFontStyleProvider.sharedInstance.bodyMediumStyle)
                    .foregroundColor(Color.miamColor(.secondaryText))

            case 3 :
                HStack{
                    Image.miamImage(icon: .difficultyHigh).frame(width: 24.0, height: 24.0, alignment: .center)
                }
                Text(MiamText.sharedInstance.difficultyHard)
                    .miamFontStyle(style: MiamFontStyleProvider.sharedInstance.bodyMediumStyle)
                    .foregroundColor(Color.miamColor(.secondaryText))

            default :
                HStack{
                    Image.miamImage(icon: .difficultyLow).frame( height: 24.0, alignment: .center)
                   
                }
                Text(MiamText.sharedInstance.difficultyEasy)
                    .miamFontStyle(style: MiamFontStyleProvider.sharedInstance.bodyMediumStyle) // TODO: check font size in mockups
                    .foregroundColor(Color.miamColor(.secondaryText))
            }
            
        }
    }
}


