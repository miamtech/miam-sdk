//
//  PreferencesTagsListView.swift
//  MiamIOSFramework
//
//  Created by Vincent Kergonna on 03/11/2022.
//  Copyright © 2022 Miam. All rights reserved.
//

import SwiftUI
import miamCore

@available(iOS 14, *)
struct PreferencesTagsListView: View {
    let title: String
    let subtitle: String
    let tags: [CheckableTag]
    let geometry: GeometryProxy
    let onToggleTag: (CheckableTag) -> Void
    let onAddTagTapped: () -> Void
    var body: some View {
        VStack(alignment: .leading) {
            Text(title).bold().padding(.top, Dimension.sharedInstance.mPadding)
            Text(subtitle).padding(.bottom, Dimension.sharedInstance.mPadding)
            
            displayTags(in: geometry)
        }
    }
    
    private func displayTags(in g: GeometryProxy) -> some View {
        var width = CGFloat.zero
        var height = CGFloat.zero

        return ZStack(alignment: .topLeading) {
            ForEach(self.tags, id: \.tag.id) { tag in
                PreferenceTagView(tag: tag, onToggleTag: { tag in
                    onToggleTag(tag)
                })
                .padding([.horizontal, .vertical], 4)
                .alignmentGuide(.leading, computeValue: { d in
                    if (abs(width - d.width) > g.size.width)
                    {
                        width = 0
                        height -= d.height
                    }
                    let result = width
                    if tag == self.tags.first! {
                        width = 0 //last item
                    } else {
                        width -= d.width
                    }
                    return result
                })
                .alignmentGuide(.top, computeValue: {d in
                    let result = height
                    if tag == self.tags.first! {
                        height = 0 // last item
                    }
                    return result
                })
            }
            AddTagView(onTapped: {
                onAddTagTapped()
            })
            .padding([.horizontal, .vertical], 4)
            .alignmentGuide(.leading, computeValue: { d in
                if (abs(width - d.width) > g.size.width)
                {
                    width = 0
                    height -= d.height
                }
                let result = width
                width = 0
                return result
            })
            .alignmentGuide(.top, computeValue: {d in
                let result = height
                height = 0
                return result
            })
        }
    }
}

@available(iOS 14, *)
struct AddTagView: View {
    let onTapped: () -> Void
    
    var body: some View {
        Text(MiamText.sharedInstance.addTag)
            .padding(14.0)
            .frame(height: 40.0)
            .foregroundColor(Color.miamColor(.black))
            .background(Color.miamColor(.white))
            .clipShape(Capsule())
            .overlay(Capsule().stroke(Color.miamColor(.borderLight), lineWidth: 1.0))
            .onTapGesture {
                onTapped()
            }
    }
}

@available(iOS 14, *)
struct PreferenceTagView: View {
    let tag: CheckableTag
    let onToggleTag: (CheckableTag) -> Void
    var tagName: String {
        get {
            if let name = tag.tag.attributes?.name {
                return name
            }
            return ""
        }
    }
    
    var backgroundColor: Color {
        get {
            if tag.isChecked {
                return Color.miamColor(.black)
            } else {
                return Color.miamColor(.white)
            }
        }
    }
    
    var foregroundColor: Color {
        get {
            if tag.isChecked {
                return Color.miamColor(.white)
            } else {
                return Color.miamColor(.black)
            }
        }
    }
    
    
    var body: some View {
        Text(tagName)
            .padding(14.0)
            .frame(height: 40.0)
            .foregroundColor(foregroundColor)
            .background(backgroundColor)
            .clipShape(Capsule())
            .overlay(Capsule().stroke(Color.miamColor(.borderLight), lineWidth: 1.0))
            .onTapGesture {
                onToggleTag(tag)
            }
    }
}
