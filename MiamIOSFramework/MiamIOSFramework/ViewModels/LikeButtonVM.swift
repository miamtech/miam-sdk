//
//  LikeButtonVM.swift
//  MiamIOSFramework
//
//  Created by Miam on 17/11/2022.
//  Copyright © 2022 Miam. All rights reserved.
//

import Foundation
import miamCore


@available(iOS 14, *)
public class LikeButtonVM: LikeButtonViewModel, ObservableObject {
    @Published var isLiked: Bool = false
    
    override public init() {
        super.init()
        collect(flow: uiState) { data in
            let state = data as! RecipeLikeContractState
            switch state.isLiked {
                case let success as BasicUiStateSuccess<KotlinBoolean>:
                self.isLiked = Bool(success.data!)
                default:
                    break
            }
        }
    }
}
