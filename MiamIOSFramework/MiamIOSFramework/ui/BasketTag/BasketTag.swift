//
//  BasketTag.swift
//  MiamIOSFramework
//
//  Created by Miam on 07/07/2022.
//

import SwiftUI
import shared

@available(iOS 14, *)
public struct BasketTag: View {
    
    @SwiftUI.State private var initialDialogScreen = RouterContent.recipeDetail
    @SwiftUI.State var showingPopup = false
    @SwiftUI.State var showingListModal = false
    
    public init(){}
    
    
    public var body: some View {
        ZStack(){
            HStack(){
                Text(/*@START_MENU_TOKEN@*/"Hello, World!"/*@END_MENU_TOKEN@*/).padding(.horizontal,8).padding(.vertical,4)
                    .overlay(
                        RoundedRectangle(cornerRadius: 50)
                            .stroke(.gray, lineWidth: 1)
                    ).onTapGesture {
                        showingListModal = true
                    }
                ZStack(){
                    Circle()
                        .strokeBorder(Color.blue,lineWidth: 1)
                        .frame(width: 30, height: 30)
                    Text("+12")
                }
            }.sheet(isPresented: $showingListModal)
                    { ListModal(showingListModal: $showingListModal  ) }
            
           
        }
    }
}

@available(iOS 14, *)
internal struct ListModal: View {
  
  @Binding var showingListModal:Bool
    

  var body: some View {
  
        
       
            HStack {
                VStack(spacing: 10) {
                    Text("Ce produit est utilisé pour 3 recettes :").bold()
                    HStack {
                        VStack{ Text(/*@START_MENU_TOKEN@*/"Hello, World!"/*@END_MENU_TOKEN@*/).underline().foregroundColor(.blue).bold().padding(.horizontal,8).padding(.vertical,4)
                            Text("Hello, World!").underline().foregroundColor(.blue).bold().padding(.horizontal,8).padding(.vertical,4)
                            Text(/*@START_MENU_TOKEN@*/"Hello, World!"/*@END_MENU_TOKEN@*/).underline().foregroundColor(.blue).bold().padding(.horizontal,8).padding(.vertical,4)
                            
                        }
                   
                        Spacer()
                    }
                    Divider()
                    HStack{
                        Spacer()
                        Image("cross", bundle: Bundle(for: RecipeCardVM.self))
                            .renderingMode(.original)
                            .frame(
                                width: 24,
                                height: 24,
                                alignment: .center
                            )
                        Spacer()
                    }
                }
                Spacer()
            }.padding([.horizontal,.vertical],8)
            .frame( alignment: .top)
            .background(RoundedRectangle(cornerRadius: 27).fill(Color.white.opacity(1)))
    
  }
}

@available(iOS 14, *)
extension View {

    public func popup<PopupContent: View>(
        isPresented: Binding<Bool>,
        view: @escaping () -> PopupContent) -> some View {
        self.modifier(
            Popup(
                isPresented: isPresented,
                view: view)
        )
    }
}

@available(iOS 14, *)
public struct Popup<PopupContent>: ViewModifier where PopupContent: View {
    
    init(isPresented: Binding<Bool>,
         view: @escaping () -> PopupContent) {
        self._isPresented = isPresented
        self.view = view
    }

    /// Controls if the sheet should be presented or not
    @Binding var isPresented: Bool

    /// The content to present
    var view: () -> PopupContent
    /// The rect of the hosting controller
    @SwiftUI.State private var presenterContentRect: CGRect = .zero

    /// The rect of popup content
    @SwiftUI.State private var sheetContentRect: CGRect = .zero

    /// The offset when the popup is displayed
    private var displayedOffset: CGFloat {
        -presenterContentRect.midY + screenHeight/2
    }

    /// The offset when the popup is hidden
    private var hiddenOffset: CGFloat {
        if presenterContentRect.isEmpty {
            return 1000
        }
        return screenHeight - presenterContentRect.midY + sheetContentRect.height/2 + 5
    }

    /// The current offset, based on the "presented" property
    private var currentOffset: CGFloat {
        return isPresented ? displayedOffset : hiddenOffset
    }
    private var screenWidth: CGFloat {
        UIScreen.main.bounds.size.width
    }

    private var screenHeight: CGFloat {
        UIScreen.main.bounds.size.height
    }
    
    public func body(content: Content) -> some View {
        ZStack {
            content
              .frameGetter($presenterContentRect)
        }
        .overlay(sheet())
    }

    func sheet() -> some View {
        ZStack {
            self.view()
              .simultaneousGesture(
                  TapGesture().onEnded {
                      dismiss()
              })
              .frameGetter($sheetContentRect)
              .frame(width: screenWidth)
              .offset(x: 0, y: currentOffset)
              .animation(Animation.easeOut(duration: 0.3), value: currentOffset)
        }
    }

    private func dismiss() {
        isPresented = false
    }
}

@available(iOS 14, *)
extension View {
    func frameGetter(_ frame: Binding<CGRect>) -> some View {
        modifier(FrameGetter(frame: frame))
    }
}

@available(iOS 14, *)
struct FrameGetter: ViewModifier {
  
    @Binding var frame: CGRect
    
    func body(content: Content) -> some View {
        content
            .background(
                GeometryReader { proxy -> AnyView in
                    let rect = proxy.frame(in: .global)
                    // This avoids an infinite layout loop
                    if rect.integral != self.frame.integral {
                        DispatchQueue.main.async {
                            self.frame = rect
                        }
                    }
                return AnyView(EmptyView())
            })
    }
}
