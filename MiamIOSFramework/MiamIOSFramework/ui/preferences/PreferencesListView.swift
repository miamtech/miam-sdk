//
//  PreferencesListView.swift
//  MiamIOSFramework
//
//  Created by Vincent Kergonna on 03/11/2022.
//  Copyright © 2022 Miam. All rights reserved.
//

import SwiftUI
import miamCore

@available(iOS 14, *)
struct PreferencesListView: View {
    let title: String
    let subtitle: String
    let preferences: [CheckableTag]
    let onToggleTag: (CheckableTag) -> Void
    
    var body: some View {
        if let template = Template.sharedInstance.preferencesListViewTemplate {
            template(title, subtitle, preferences, onToggleTag)
        } else {
            VStack(alignment: .leading) {
                Text(title).bold().padding(.top, Dimension.sharedInstance.mPadding)
                Text(subtitle).padding(.bottom, Dimension.sharedInstance.mPadding)
                
                VStack(alignment: .leading) {
                    ForEach(preferences, id: \.self) { tag in
                        PreferenceListItemView(tag: tag) { tag in
                            onToggleTag(tag)
                        }
                    }
                }.background(Color.miamColor(.white))
                    .padding([.bottom], 8.0)
                    .cornerRadius(8.0)
                    .padding([.bottom], -8.0)
            }.background(Color.miamColor(.lightGreyBackground))
        }
    }
}

@available(iOS 14, *)
public struct PreferenceListItemView: View {
    public let tag: CheckableTag
    public let onToggleTag: (CheckableTag) -> Void
    var tagName: String {
        get {
            if let name = tag.tag.attributes?.name {
                return name
            }
            return ""
        }
    }
    
    public var body: some View {
        if let template = Template.sharedInstance.preferenceListItemViewTemplate {
            template(tag, onToggleTag)
        } else {
            VStack(alignment: .leading) {
                HStack {
                    Button(action:{
                        onToggleTag(tag)
                    }) {
                        ZStack(alignment: .center) {
                            Rectangle()
                                .fill(.white)
                                .cornerRadius(4)
                                .border(Color.miamColor(.borderLight), width: 1)
                                .frame(width:20, height:20, alignment: .center)
                            
                            if(tag.isChecked) {
                                Image.miamImage(icon: .check)
                                    .renderingMode(.original).frame( alignment: .center)
                            }
                        }
                    }
                    .foregroundColor(Color.white)
                    Text(tagName)
                }.padding(Dimension.sharedInstance.lPadding)
                Divider()
            }
        }
    }
}
