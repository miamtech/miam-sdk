"use strict";(self.webpackChunkmiam_sdk_doc=self.webpackChunkmiam_sdk_doc||[]).push([[585],{3905:(e,n,t)=>{t.d(n,{Zo:()=>l,kt:()=>f});var r=t(7294);function a(e,n,t){return n in e?Object.defineProperty(e,n,{value:t,enumerable:!0,configurable:!0,writable:!0}):e[n]=t,e}function i(e,n){var t=Object.keys(e);if(Object.getOwnPropertySymbols){var r=Object.getOwnPropertySymbols(e);n&&(r=r.filter((function(n){return Object.getOwnPropertyDescriptor(e,n).enumerable}))),t.push.apply(t,r)}return t}function o(e){for(var n=1;n<arguments.length;n++){var t=null!=arguments[n]?arguments[n]:{};n%2?i(Object(t),!0).forEach((function(n){a(e,n,t[n])})):Object.getOwnPropertyDescriptors?Object.defineProperties(e,Object.getOwnPropertyDescriptors(t)):i(Object(t)).forEach((function(n){Object.defineProperty(e,n,Object.getOwnPropertyDescriptor(t,n))}))}return e}function c(e,n){if(null==e)return{};var t,r,a=function(e,n){if(null==e)return{};var t,r,a={},i=Object.keys(e);for(r=0;r<i.length;r++)t=i[r],n.indexOf(t)>=0||(a[t]=e[t]);return a}(e,n);if(Object.getOwnPropertySymbols){var i=Object.getOwnPropertySymbols(e);for(r=0;r<i.length;r++)t=i[r],n.indexOf(t)>=0||Object.prototype.propertyIsEnumerable.call(e,t)&&(a[t]=e[t])}return a}var d=r.createContext({}),s=function(e){var n=r.useContext(d),t=n;return e&&(t="function"==typeof e?e(n):o(o({},n),e)),t},l=function(e){var n=s(e.components);return r.createElement(d.Provider,{value:n},e.children)},p="mdxType",u={inlineCode:"code",wrapper:function(e){var n=e.children;return r.createElement(r.Fragment,{},n)}},m=r.forwardRef((function(e,n){var t=e.components,a=e.mdxType,i=e.originalType,d=e.parentName,l=c(e,["components","mdxType","originalType","parentName"]),p=s(t),m=a,f=p["".concat(d,".").concat(m)]||p[m]||u[m]||i;return t?r.createElement(f,o(o({ref:n},l),{},{components:t})):r.createElement(f,o({ref:n},l))}));function f(e,n){var t=arguments,a=n&&n.mdxType;if("string"==typeof e||a){var i=t.length,o=new Array(i);o[0]=m;var c={};for(var d in n)hasOwnProperty.call(n,d)&&(c[d]=n[d]);c.originalType=e,c[p]="string"==typeof e?e:a,o[1]=c;for(var s=2;s<i;s++)o[s]=t[s];return r.createElement.apply(null,o)}return r.createElement.apply(null,t)}m.displayName="MDXCreateElement"},7826:(e,n,t)=>{t.r(n),t.d(n,{assets:()=>d,contentTitle:()=>o,default:()=>u,frontMatter:()=>i,metadata:()=>c,toc:()=>s});var r=t(7462),a=(t(7294),t(3905));const i={},o="Internationalisation - I18n",c={unversionedId:"android/advanced/i18n",id:"android/advanced/i18n",title:"Internationalisation - I18n",description:"This section is about how we can define localised content in the context of the Miam SDK.",source:"@site/docs/android/advanced/i18n.md",sourceDirName:"android/advanced",slug:"/android/advanced/i18n",permalink:"/docs/android/advanced/i18n",draft:!1,tags:[],version:"current",frontMatter:{},sidebar:"androidSidebar",previous:{title:"Groceries list",permalink:"/docs/android/advanced/groceries-list-handler"},next:{title:"Notifications",permalink:"/docs/android/advanced/notification"}},d={},s=[{value:"Override Miam SDK values",id:"override-miam-sdk-values",level:3}],l={toc:s},p="wrapper";function u(e){let{components:n,...t}=e;return(0,a.kt)(p,(0,r.Z)({},l,t,{components:n,mdxType:"MDXLayout"}),(0,a.kt)("h1",{id:"internationalisation---i18n"},"Internationalisation - I18n"),(0,a.kt)("p",null,"This section is about how we can define localised content in the context of the Miam SDK."),(0,a.kt)("admonition",{type:"info"},(0,a.kt)("p",{parentName:"admonition"},"Actualy Miam support two languages English and French")),(0,a.kt)("h3",{id:"override-miam-sdk-values"},"Override Miam SDK values"),(0,a.kt)("p",null,"You can override Miam SDK's localised content. However, the mechanism is different for each platform. "),(0,a.kt)("p",null,"Let's assume that the Miam SDK embed the following localisation string:"),(0,a.kt)("pre",null,(0,a.kt)("code",{parentName:"pre",className:"language-xml"},'<resources>\n    <string name="com_miam_i18n_recipe_add">Add ingredients</string>\n</resources>\n')),(0,a.kt)("p",null,"On Android, it is fairly simple to override resources that come from a library. Just declare values with the same identifier, and it's done. "),(0,a.kt)("p",null,"In your application, just add the following string declaration inside a ",(0,a.kt)("inlineCode",{parentName:"p"},"res/values(-LANG)/<FILENAME>.xml")," (the filename doesn't matter) to override it:"),(0,a.kt)("pre",null,(0,a.kt)("code",{parentName:"pre",className:"language-xml"},'<resources>\n    <string name="com_miam_i18n_recipe_add">Add recipe to basket</string>\n</resources>\n')),(0,a.kt)("p",null,"When consuming ",(0,a.kt)("inlineCode",{parentName:"p"},"com_miam_i18n_recipe_add"),", you should see ",(0,a.kt)("strong",{parentName:"p"},"Add recipe to basket")," instead of ",(0,a.kt)("strong",{parentName:"p"},"Add ingredients"),"."))}u.isMDXComponent=!0}}]);