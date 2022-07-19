//
//  AsyncImage.swift
//  MiamIOSFramework
//
//  Created by miam on 17/02/2022.
//

import Foundation
import SwiftUI
import Combine


@available(iOS 14, *)
class ImageLoader : ObservableObject {
    @Published var image: UIImage?
    private let url: URL
    private var cancellable:  AnyCancellable?
    
    init(url: URL){
        self.url = url
    }
    
    func load() {
        cancellable = URLSession.shared.dataTaskPublisher(for: url)
            .map { UIImage(data: $0.data)}
            .replaceError(with: nil)
            .receive(on: DispatchQueue.main)
            .sink { [weak self] in self?.image = $0  }
            }
    
    func cancel() {
        cancellable?.cancel()
    }
}

@available(iOS 14, *)
struct AsyncImage<Placeholder:View> : View {
    @StateObject private var loader: ImageLoader
    private let placeholder: Placeholder
    private let height: CGFloat
    
    init(url:URL, @ViewBuilder placeholder: () -> Placeholder, height: CGFloat ){
        self.placeholder = placeholder()
        self.height = height
        _loader = StateObject(wrappedValue: ImageLoader(url: url))
        
    }
    
    var body: some View {
        HStack {
            if (loader.image != nil) {
                GeometryReader { geo in
                    Image(uiImage: loader.image!)
                        .resizable()
                        .scaledToFill()
                        .frame(width: geo.size.width * 1, height: height)
                        .clipped()
                }
            } else {
                placeholder
            }
        }.onAppear(perform: loader.load)
    }
}
