//
//  ManagementResourceState.swift
//  MiamIOSFramework
//
//  Created by Miam on 20/06/2022.
//

import Foundation
import SwiftUI
import shared

@available(iOS 14, *)
struct ManagementResourceState<T : AnyObject, SuccessView : View, LoadingView : View>: View {
    
    private let resourceState: BasicUiState<T>
    private let successView:  SuccessView
    private let loadingView: LoadingView
    
    init(
        resourceState: BasicUiState<T>,
        successView:  SuccessView,
        loadingView:  LoadingView
    ) {
        self.resourceState = resourceState
        self.successView = successView
        self.loadingView = loadingView
    }
    
    var body: some View {
        switch self.resourceState {
        case is BasicUiStateEmpty:
            HStack{} // TODO handle empty state
        case is BasicUiStateError:
            HStack{} // TODO handle error state
        case is BasicUiStateSuccess<T> :
            successView
        case is BasicUiStateLoading:
            loadingView
        default:
            Text("You shouldn't be seeing this message")
        }
    }
}
