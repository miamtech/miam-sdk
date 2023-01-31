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
public struct PreferencesTagsListView: View {
    let title: String
    let subtitle: String
    let tags: [CheckableTag]
    let geometry: GeometryProxy
    let onToggleTag: (CheckableTag) -> Void
    let onAddTagTapped: () -> Void

    public init(title: String, subtitle: String, tags: [CheckableTag], geometry: GeometryProxy, onToggleTag: @escaping (CheckableTag) -> Void, onAddTagTapped: @escaping () -> Void) {
        self.title = title
        self.subtitle = subtitle
        self.tags = tags
        self.geometry = geometry
        self.onToggleTag = onToggleTag
        self.onAddTagTapped = onAddTagTapped
    }

    public var body: some View {
        if let template = Template.sharedInstance.preferencesTagsListViewTemplate {
            template(title, subtitle, tags, geometry, onToggleTag, onAddTagTapped)
        } else {
            VStack(alignment: .leading) {
                Text(title)
                    .miamFontStyle(style: MiamFontStyleProvider.sharedInstance.titleMediumStyle)
                    .padding(.top, Dimension.sharedInstance.mPadding)
                Text(subtitle)
                    .miamFontStyle(style: MiamFontStyleProvider.sharedInstance.bodyStyle)
                    .padding(.bottom, Dimension.sharedInstance.mPadding)

                displayTags(in: geometry)
            }
        }
    }

    public func displayTags(in geometry: GeometryProxy) -> some View {
        var width = CGFloat.zero
        var height = CGFloat.zero

        return ZStack(alignment: .topLeading) {
            ForEach(self.tags, id: \.tag.id) { tag in
                PreferenceTagView(tag: tag, onToggleTag: { tag in
                    onToggleTag(tag)
                })
                .padding([.horizontal, .vertical], 4)
                .alignmentGuide(.leading, computeValue: { dimension in
                    if abs(width - dimension.width) > geometry.size.width {
                        width = 0
                        height -= dimension.height
                    }
                    let result = width
                    if tag == self.tags.first! {
                        width = 0 // last item
                    } else {
                        width -= dimension.width
                    }
                    return result
                })
                .alignmentGuide(.top, computeValue: {_ in
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
            .alignmentGuide(.leading, computeValue: { dimension in
                if abs(width - dimension.width) > geometry.size.width {
                    width = 0
                    height -= dimension.height
                }
                let result = width
                width = 0
                return result
            })
            .alignmentGuide(.top, computeValue: {_ in
                let result = height
                height = 0
                return result
            })
        }
    }
}

@available(iOS 14, *)
public struct AddTagView: View {
    public let onTapped: () -> Void

    public init(onTapped: @escaping () -> Void) {
        self.onTapped = onTapped
    }

    public var body: some View {
        if let template = Template.sharedInstance.addTagViewTemplate {
            template(onTapped)
        } else {
            Text(MiamText.sharedInstance.addTag)
                .miamFontStyle(style: MiamFontStyleProvider.sharedInstance.bodyBigStyle)
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
}

@available(iOS 14, *)
public struct PreferenceTagView: View {
    public let tag: CheckableTag
    public let onToggleTag: (CheckableTag) -> Void
    public var tagName: String {
        get {
            if let name = tag.tag.attributes?.name {
                return name
            }
            return ""
        }
    }

    public var backgroundColor: Color {
        get {
            if tag.isChecked {
                return Color.miamColor(.black)
            } else {
                return Color.miamColor(.white)
            }
        }
    }

    public var foregroundColor: Color {
        get {
            if tag.isChecked {
                return Color.miamColor(.white)
            } else {
                return Color.miamColor(.black)
            }
        }
    }

    public init(tag: CheckableTag, onToggleTag: @escaping (CheckableTag) -> Void) {
        self.tag = tag
        self.onToggleTag = onToggleTag
    }

    public var body: some View {
        if let template = Template.sharedInstance.preferencesTagViewTemplate {
            template(tag, onToggleTag)
        } else {
            Text(tagName)
                .miamFontStyle(style: MiamFontStyleProvider.sharedInstance.bodyBigStyle)
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
}
