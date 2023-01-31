//
//  ManagementResourceState.swift
//  MiamIOSFramework
//
//  Created by Miam on 20/06/2022.
//

import Foundation
import SwiftUI
import miamCore

@available(iOS 14, *)
struct ManagementResourceState<T: AnyObject, SuccessView: View, LoadingView: View, EmptyView: View>: View {

    private let resourceState: BasicUiState<T>?
    private let successView: SuccessView
    private let loadingView: LoadingView
    private let emptyView: EmptyView

    init(
        resourceState: BasicUiState<T>?,
        successView: SuccessView,
        loadingView: LoadingView,
        emptyView: EmptyView
    ) {
        self.resourceState = resourceState
        self.successView = successView
        self.loadingView = loadingView
        self.emptyView = emptyView
    }

    init(
        resourceState: BasicUiState<T>,
        successView: SuccessView,
        loadingView: LoadingView,
        emptyView: EmptyView
    ) {
        self.resourceState = resourceState
        self.successView = successView
        self.loadingView = loadingView
        self.emptyView = emptyView
    }

    var body: some View {
        switch self.resourceState {
        case is BasicUiStateEmpty:
            emptyView
        case is BasicUiStateError:
            HStack {} // TODO handle error state
        case is BasicUiStateSuccess<T>:
            successView
        case is BasicUiStateLoading:
            loadingView
        default:
            emptyView
        }
    }
}
