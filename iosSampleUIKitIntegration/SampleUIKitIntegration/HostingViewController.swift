//
//  HostingViewController.swift
//  SampleUIKitIntegration
//
//  Created by Vincent Kergonna on 13/06/2022.
//

import UIKit
import SwiftUI
import MiamIOSFramework

class HostingViewController: UIHostingController<RecipeCardView> {
    // Initialize our controller with RecipeCardView as a root view and show
    // recipe 1.
    required init?(coder aDecoder: NSCoder) {
        super.init(coder: aDecoder, rootView: RecipeCardView(recipeId: "1"))
    }
}
