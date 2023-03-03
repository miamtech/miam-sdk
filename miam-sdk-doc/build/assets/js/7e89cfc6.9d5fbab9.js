"use strict";(self.webpackChunkmiam_sdk_doc=self.webpackChunkmiam_sdk_doc||[]).push([[501],{3905:(e,t,n)=>{n.d(t,{Zo:()=>p,kt:()=>f});var r=n(7294);function a(e,t,n){return t in e?Object.defineProperty(e,t,{value:n,enumerable:!0,configurable:!0,writable:!0}):e[t]=n,e}function i(e,t){var n=Object.keys(e);if(Object.getOwnPropertySymbols){var r=Object.getOwnPropertySymbols(e);t&&(r=r.filter((function(t){return Object.getOwnPropertyDescriptor(e,t).enumerable}))),n.push.apply(n,r)}return n}function o(e){for(var t=1;t<arguments.length;t++){var n=null!=arguments[t]?arguments[t]:{};t%2?i(Object(n),!0).forEach((function(t){a(e,t,n[t])})):Object.getOwnPropertyDescriptors?Object.defineProperties(e,Object.getOwnPropertyDescriptors(n)):i(Object(n)).forEach((function(t){Object.defineProperty(e,t,Object.getOwnPropertyDescriptor(n,t))}))}return e}function s(e,t){if(null==e)return{};var n,r,a=function(e,t){if(null==e)return{};var n,r,a={},i=Object.keys(e);for(r=0;r<i.length;r++)n=i[r],t.indexOf(n)>=0||(a[n]=e[n]);return a}(e,t);if(Object.getOwnPropertySymbols){var i=Object.getOwnPropertySymbols(e);for(r=0;r<i.length;r++)n=i[r],t.indexOf(n)>=0||Object.prototype.propertyIsEnumerable.call(e,n)&&(a[n]=e[n])}return a}var l=r.createContext({}),c=function(e){var t=r.useContext(l),n=t;return e&&(n="function"==typeof e?e(t):o(o({},t),e)),n},p=function(e){var t=c(e.components);return r.createElement(l.Provider,{value:t},e.children)},u="mdxType",m={inlineCode:"code",wrapper:function(e){var t=e.children;return r.createElement(r.Fragment,{},t)}},d=r.forwardRef((function(e,t){var n=e.components,a=e.mdxType,i=e.originalType,l=e.parentName,p=s(e,["components","mdxType","originalType","parentName"]),u=c(n),d=a,f=u["".concat(l,".").concat(d)]||u[d]||m[d]||i;return n?r.createElement(f,o(o({ref:t},p),{},{components:n})):r.createElement(f,o({ref:t},p))}));function f(e,t){var n=arguments,a=t&&t.mdxType;if("string"==typeof e||a){var i=n.length,o=new Array(i);o[0]=d;var s={};for(var l in t)hasOwnProperty.call(t,l)&&(s[l]=t[l]);s.originalType=e,s[u]="string"==typeof e?e:a,o[1]=s;for(var c=2;c<i;c++)o[c]=n[c];return r.createElement.apply(null,o)}return r.createElement.apply(null,n)}d.displayName="MDXCreateElement"},1386:(e,t,n)=>{n.r(t),n.d(t,{assets:()=>l,contentTitle:()=>o,default:()=>m,frontMatter:()=>i,metadata:()=>s,toc:()=>c});var r=n(7462),a=(n(7294),n(3905));const i={},o="User customisation",s={unversionedId:"ios/advanced/user-configuration",id:"ios/advanced/user-configuration",title:"User customisation",description:"Miam can provide a personalized experience for customers. To achive this purpose we propose two solution :",source:"@site/docs/ios/advanced/user-configuration.md",sourceDirName:"ios/advanced",slug:"/ios/advanced/user-configuration",permalink:"/docs/ios/advanced/user-configuration",draft:!1,tags:[],version:"current",frontMatter:{},sidebar:"iosSidebar",previous:{title:"Notifications",permalink:"/docs/ios/advanced/notification"}},l={},c=[{value:"Preferences",id:"preferences",level:2},{value:"Profiling",id:"profiling",level:2},{value:"Like recipe",id:"like-recipe",level:2}],p={toc:c},u="wrapper";function m(e){let{components:t,...n}=e;return(0,a.kt)(u,(0,r.Z)({},p,n,{components:t,mdxType:"MDXLayout"}),(0,a.kt)("h1",{id:"user-customisation"},"User customisation"),(0,a.kt)("p",null," Miam can provide a personalized experience for customers. To achive this purpose we propose two solution :"),(0,a.kt)("ul",null,(0,a.kt)("li",{parentName:"ul"},"Preferences: Localy stored ",(0,a.kt)("em",{parentName:"li"}," Activable without customer consent by the retailer")),(0,a.kt)("li",{parentName:"ul"},"Profiling : Back hosted tastes ",(0,a.kt)("em",{parentName:"li"},"Can be disable by the customer"))),(0,a.kt)("admonition",{type:"note"},(0,a.kt)("p",{parentName:"admonition"},"Those two solutions are GDPR compliant")),(0,a.kt)("h2",{id:"preferences"},"Preferences"),(0,a.kt)("p",null,"The preferences use the native IOS preference to make them pesitente. To enable this feature\nyou need to provide a ",(0,a.kt)("strong",{parentName:"p"},"context")," and change the Miam preference's configuration flag to ",(0,a.kt)("inlineCode",{parentName:"p"},"true"),":"),(0,a.kt)("p",null," example with ",(0,a.kt)("strong",{parentName:"p"},"Swift ui")," :"),(0,a.kt)("pre",null,(0,a.kt)("code",{parentName:"pre",className:"language-swift"},"// file Miam.swift\nimport miamCore\n\nclass Miam {\n   // CODE\n  private let basketHandler: BasketHandler\n\n  private init() {\n   ContextHandlerInstance.shared.instance.setContext(context: MiamPreferencesContext())\n    // CODE\n  }\n\n}\n")),(0,a.kt)("pre",null,(0,a.kt)("code",{parentName:"pre",className:"language-swift"},"import MiamIOSFramework\n\nstruct CatalogTabView: View {\n   var body: some View {\n       VStack {\n           ZStack(alignment: .bottom) {\n               CatalogView(usesPreferences: true, recipesListColumns: 2)\n               MyMealButtonView({})\n           }\n       }\n   }\n}\n\n")),(0,a.kt)("h2",{id:"profiling"},"Profiling"),(0,a.kt)("p",null,"Miam offers to your customer a fully personalized experience based on their tastes.\nOur IA will learn from the customer's choices and suggest more and more specific recipes and product."),(0,a.kt)("p",null,"This feature can be disable by customer if they wish to."),(0,a.kt)("p",null,"To do this, we expose this function:"),(0,a.kt)("pre",null,(0,a.kt)("code",{parentName:"pre",className:"language-swift"},"import miamCore\n\npublic class Miam {\n  // CODE\n\n  private init() {\n    // CODE\n\n      // allowance is a boolean, true by default\n      UserHandler.shared.setProfilingAllowed(allowance: USER_PREF_IN_HOST_APP)\n  }\n\n  // CODE\n}\n")),(0,a.kt)("h2",{id:"like-recipe"},"Like recipe"),(0,a.kt)("p",null,"You can disable Like feature with UserHandler"),(0,a.kt)("pre",null,(0,a.kt)("code",{parentName:"pre",className:"language-swift"},"// file Miam.swift\nimport miamCore\n\npublic class Miam {\n  // CODE\n\n  private init() {\n    // CODE\n      UserHandler.shared.setEnableLike(isEnable: false)\n  }\n\n  // CODE\n}\n\n")),(0,a.kt)("admonition",{type:"note"},(0,a.kt)("p",{parentName:"admonition"},"If not setted like feature will be enable by default")))}m.isMDXComponent=!0}}]);