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
                    .foregroundColor(Color.miamColor(.secondaryText)).font(.system(size: 13.0, weight: .regular, design: .default))
            case 2 :
                HStack{
                    Image.miamImage(icon: .difficultyMedium).frame( height: 24.0, alignment: .center)
                }
                Text(MiamText.sharedInstance.difficultyMid)
                    .foregroundColor(Color.miamColor(.secondaryText)).font(.system(size: 13.0, weight: .regular, design: .default))
            case 3 :
                HStack{
                    Image.miamImage(icon: .difficultyHigh).frame(width: 24.0, height: 24.0, alignment: .center)
                }
                Text(MiamText.sharedInstance.difficultyHard)
                    .foregroundColor(Color.miamColor(.secondaryText)).font(.system(size: 13.0, weight: .regular, design: .default))
            default :
                HStack{
                    Image.miamImage(icon: .difficultyLow).frame( height: 24.0, alignment: .center)
                   
                }
                Text(MiamText.sharedInstance.difficultyEasy)
                    .foregroundColor(Color.miamColor(.secondaryText)).font(.system(size: 13.0, weight: .regular, design: .default))
                
            }
            
        }
    }
}


