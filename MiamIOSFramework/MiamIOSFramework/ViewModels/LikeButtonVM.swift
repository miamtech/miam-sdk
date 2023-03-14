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
    @Published public var isLiked: Bool = false
    @Published public var state: RecipeLikeContractState?
    
    public override init() {
        super.init()
        collect(flow: uiState) { data in
            self.state = data as? RecipeLikeContractState
            switch self.state?.isLiked {
                case let success as BasicUiStateSuccess<KotlinBoolean>:
                self.isLiked = Bool(truncating: success.data!)
                default:
                    break
            }
        }
    }
}
