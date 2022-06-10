//
//  RecipeDetailsDifficulty.swift
//  MiamIOSFramework
//
//  Created by Miam on 02/06/2022.
//

import SwiftUI

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
                    Image( "Easy").frame( height: 24.0, alignment: .center)
                   
                }
                Text(MiamText.sharedInstance.difficultyEasy)
                    .foregroundColor(MiamColor.sharedInstance.bodyText).font(.system(size: 13.0, weight: .regular, design: .default))
            case 2 :
                HStack{
                    Image( "Mid").frame( height: 24.0, alignment: .center)
                }
                Text(MiamText.sharedInstance.difficultyMid)
                    .foregroundColor(MiamColor.sharedInstance.bodyText).font(.system(size: 13.0, weight: .regular, design: .default))
            case 3 :
                HStack{
                    Image( "Hard").frame(width: 24.0, height: 24.0, alignment: .center)
                }
                Text(MiamText.sharedInstance.difficultyHard)
                    .foregroundColor(MiamColor.sharedInstance.bodyText).font(.system(size: 13.0, weight: .regular, design: .default))
            default :
                HStack{
                    Image( "Easy").frame( height: 24.0, alignment: .center)
                   
                }
                Text(MiamText.sharedInstance.difficultyEasy)
                    .foregroundColor(MiamColor.sharedInstance.bodyText).font(.system(size: 13.0, weight: .regular, design: .default))
                
            }
            
        }
    }
}


