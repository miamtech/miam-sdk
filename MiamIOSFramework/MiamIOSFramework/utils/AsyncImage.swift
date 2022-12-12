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
public struct AsyncImage : View {
    @StateObject private var loader: ImageLoader

    private let height: CGFloat
    
    public init(url:URL, height: CGFloat ){
        
        self.height = height
        _loader = StateObject(wrappedValue: ImageLoader(url: url))
        
    }
    
    public var body: some View {
        HStack {
            GeometryReader { geo in
               if let imageUrl = loader.image {
                   Image(uiImage: imageUrl)
                       .resizable()
                       .scaledToFill()
                       .frame(width: geo.size.width * 1, height: height)
                       .clipped()
                } else {
                    if let template = Template.sharedInstance.asyncImageLoadingTemplate {
                        template(geo.size.width * 1,height)
                    } else {
                        Rectangle()
                            .fill( Color.miamColor(.borderLight))
                            .frame(width: geo.size.width * 1, height: height)
                            .clipped()
                    }
                }
            }
        }.onAppear(perform: loader.load)
    }
}
