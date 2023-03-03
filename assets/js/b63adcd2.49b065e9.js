"use strict";(self.webpackChunkmiam_sdk_doc=self.webpackChunkmiam_sdk_doc||[]).push([[679],{3905:(e,n,t)=>{t.d(n,{Zo:()=>d,kt:()=>f});var r=t(7294);function i(e,n,t){return n in e?Object.defineProperty(e,n,{value:t,enumerable:!0,configurable:!0,writable:!0}):e[n]=t,e}function a(e,n){var t=Object.keys(e);if(Object.getOwnPropertySymbols){var r=Object.getOwnPropertySymbols(e);n&&(r=r.filter((function(n){return Object.getOwnPropertyDescriptor(e,n).enumerable}))),t.push.apply(t,r)}return t}function o(e){for(var n=1;n<arguments.length;n++){var t=null!=arguments[n]?arguments[n]:{};n%2?a(Object(t),!0).forEach((function(n){i(e,n,t[n])})):Object.getOwnPropertyDescriptors?Object.defineProperties(e,Object.getOwnPropertyDescriptors(t)):a(Object(t)).forEach((function(n){Object.defineProperty(e,n,Object.getOwnPropertyDescriptor(t,n))}))}return e}function s(e,n){if(null==e)return{};var t,r,i=function(e,n){if(null==e)return{};var t,r,i={},a=Object.keys(e);for(r=0;r<a.length;r++)t=a[r],n.indexOf(t)>=0||(i[t]=e[t]);return i}(e,n);if(Object.getOwnPropertySymbols){var a=Object.getOwnPropertySymbols(e);for(r=0;r<a.length;r++)t=a[r],n.indexOf(t)>=0||Object.prototype.propertyIsEnumerable.call(e,t)&&(i[t]=e[t])}return i}var l=r.createContext({}),c=function(e){var n=r.useContext(l),t=n;return e&&(t="function"==typeof e?e(n):o(o({},n),e)),t},d=function(e){var n=c(e.components);return r.createElement(l.Provider,{value:n},e.children)},p="mdxType",u={inlineCode:"code",wrapper:function(e){var n=e.children;return r.createElement(r.Fragment,{},n)}},m=r.forwardRef((function(e,n){var t=e.components,i=e.mdxType,a=e.originalType,l=e.parentName,d=s(e,["components","mdxType","originalType","parentName"]),p=c(t),m=i,f=p["".concat(l,".").concat(m)]||p[m]||u[m]||a;return t?r.createElement(f,o(o({ref:n},d),{},{components:t})):r.createElement(f,o({ref:n},d))}));function f(e,n){var t=arguments,i=n&&n.mdxType;if("string"==typeof e||i){var a=t.length,o=new Array(a);o[0]=m;var s={};for(var l in n)hasOwnProperty.call(n,l)&&(s[l]=n[l]);s.originalType=e,s[p]="string"==typeof e?e:i,o[1]=s;for(var c=2;c<a;c++)o[c]=t[c];return r.createElement.apply(null,o)}return r.createElement.apply(null,t)}m.displayName="MDXCreateElement"},7089:(e,n,t)=>{t.r(n),t.d(n,{assets:()=>l,contentTitle:()=>o,default:()=>u,frontMatter:()=>a,metadata:()=>s,toc:()=>c});var r=t(7462),i=(t(7294),t(3905));const a={},o="Internationalisation - I18n",s={unversionedId:"ios/advanced/i18n",id:"ios/advanced/i18n",title:"Internationalisation - I18n",description:"This section is about how we can define localised content in the context of the Miam SDK.",source:"@site/docs/ios/advanced/i18n.md",sourceDirName:"ios/advanced",slug:"/ios/advanced/i18n",permalink:"/kmm-miam-sdk/docs/ios/advanced/i18n",draft:!1,tags:[],version:"current",frontMatter:{},sidebar:"iosSidebar",previous:{title:"Groceries list",permalink:"/kmm-miam-sdk/docs/ios/advanced/groceries-list-handler"},next:{title:"Notifications",permalink:"/kmm-miam-sdk/docs/ios/advanced/notification"}},l={},c=[{value:"Override Miam SDK values",id:"override-miam-sdk-values",level:3}],d={toc:c},p="wrapper";function u(e){let{components:n,...t}=e;return(0,i.kt)(p,(0,r.Z)({},d,t,{components:n,mdxType:"MDXLayout"}),(0,i.kt)("h1",{id:"internationalisation---i18n"},"Internationalisation - I18n"),(0,i.kt)("p",null,"This section is about how we can define localised content in the context of the Miam SDK."),(0,i.kt)("admonition",{type:"info"},(0,i.kt)("p",{parentName:"admonition"},"Actualy Miam support two languages English and French")),(0,i.kt)("h3",{id:"override-miam-sdk-values"},"Override Miam SDK values"),(0,i.kt)("p",null,"Overriding localisation on ios is a little big trickier.\nFirst thing is to create a new entry in your ",(0,i.kt)("inlineCode",{parentName:"p"},"Localizable.strings")," file, to provide your localisation:"),(0,i.kt)("pre",null,(0,i.kt)("code",{parentName:"pre",className:"language-text"},'// Localizable.strings\n"com_miam_i18n_recipe_add" = "Add recipe to basket"\n')),(0,i.kt)("p",null,"Then, as Moko is reading localisations from a Bundle, that by default is the Miam SDK's bundle, you need to register your app's bundle on the ",(0,i.kt)("inlineCode",{parentName:"p"},"I18nResolver")," before consuming any override resources."),(0,i.kt)("pre",null,(0,i.kt)("code",{parentName:"pre",className:"language-swift"},"I18nResolver.shared.registerAppBundle(Bundle.main)\n")),(0,i.kt)("p",null,"After that, consuming ",(0,i.kt)("inlineCode",{parentName:"p"},"com_miam_i18n_recipe_add")," on iOS, should display ",(0,i.kt)("strong",{parentName:"p"},"Add recipe to basket")," instead of ",(0,i.kt)("strong",{parentName:"p"},"Add ingredients"),"."))}u.isMDXComponent=!0}}]);