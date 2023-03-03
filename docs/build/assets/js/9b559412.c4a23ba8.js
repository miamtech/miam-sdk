"use strict";(self.webpackChunkmiam_sdk_doc=self.webpackChunkmiam_sdk_doc||[]).push([[73],{3905:(e,n,t)=>{t.d(n,{Zo:()=>p,kt:()=>f});var r=t(7294);function a(e,n,t){return n in e?Object.defineProperty(e,n,{value:t,enumerable:!0,configurable:!0,writable:!0}):e[n]=t,e}function o(e,n){var t=Object.keys(e);if(Object.getOwnPropertySymbols){var r=Object.getOwnPropertySymbols(e);n&&(r=r.filter((function(n){return Object.getOwnPropertyDescriptor(e,n).enumerable}))),t.push.apply(t,r)}return t}function i(e){for(var n=1;n<arguments.length;n++){var t=null!=arguments[n]?arguments[n]:{};n%2?o(Object(t),!0).forEach((function(n){a(e,n,t[n])})):Object.getOwnPropertyDescriptors?Object.defineProperties(e,Object.getOwnPropertyDescriptors(t)):o(Object(t)).forEach((function(n){Object.defineProperty(e,n,Object.getOwnPropertyDescriptor(t,n))}))}return e}function c(e,n){if(null==e)return{};var t,r,a=function(e,n){if(null==e)return{};var t,r,a={},o=Object.keys(e);for(r=0;r<o.length;r++)t=o[r],n.indexOf(t)>=0||(a[t]=e[t]);return a}(e,n);if(Object.getOwnPropertySymbols){var o=Object.getOwnPropertySymbols(e);for(r=0;r<o.length;r++)t=o[r],n.indexOf(t)>=0||Object.prototype.propertyIsEnumerable.call(e,t)&&(a[t]=e[t])}return a}var l=r.createContext({}),s=function(e){var n=r.useContext(l),t=n;return e&&(t="function"==typeof e?e(n):i(i({},n),e)),t},p=function(e){var n=s(e.components);return r.createElement(l.Provider,{value:n},e.children)},u="mdxType",d={inlineCode:"code",wrapper:function(e){var n=e.children;return r.createElement(r.Fragment,{},n)}},m=r.forwardRef((function(e,n){var t=e.components,a=e.mdxType,o=e.originalType,l=e.parentName,p=c(e,["components","mdxType","originalType","parentName"]),u=s(t),m=a,f=u["".concat(l,".").concat(m)]||u[m]||d[m]||o;return t?r.createElement(f,i(i({ref:n},p),{},{components:t})):r.createElement(f,i({ref:n},p))}));function f(e,n){var t=arguments,a=n&&n.mdxType;if("string"==typeof e||a){var o=t.length,i=new Array(o);i[0]=m;var c={};for(var l in n)hasOwnProperty.call(n,l)&&(c[l]=n[l]);c.originalType=e,c[u]="string"==typeof e?e:a,i[1]=c;for(var s=2;s<o;s++)i[s]=t[s];return r.createElement.apply(null,i)}return r.createElement.apply(null,t)}m.displayName="MDXCreateElement"},9619:(e,n,t)=>{t.r(n),t.d(n,{assets:()=>l,contentTitle:()=>i,default:()=>d,frontMatter:()=>o,metadata:()=>c,toc:()=>s});var r=t(7462),a=(t(7294),t(3905));const o={},i="Groceries list",c={unversionedId:"android/advanced/groceries-list-handler",id:"android/advanced/groceries-list-handler",title:"Groceries list",description:"Some Miam internal informations an function are available they can be accesed",source:"@site/docs/android/advanced/groceries-list-handler.md",sourceDirName:"android/advanced",slug:"/android/advanced/groceries-list-handler",permalink:"/kmm-miam-sdk/docs/android/advanced/groceries-list-handler",draft:!1,tags:[],version:"current",frontMatter:{},sidebar:"androidSidebar",previous:{title:"Link",permalink:"/kmm-miam-sdk/docs/android/advanced/deepLink"},next:{title:"Internationalisation - I18n",permalink:"/kmm-miam-sdk/docs/android/advanced/i18n"}},l={},s=[{value:"Recipes count",id:"recipes-count",level:2},{value:"Reset groceries list",id:"reset-groceries-list",level:2}],p={toc:s},u="wrapper";function d(e){let{components:n,...t}=e;return(0,a.kt)(u,(0,r.Z)({},p,t,{components:n,mdxType:"MDXLayout"}),(0,a.kt)("h1",{id:"groceries-list"},"Groceries list"),(0,a.kt)("p",null,"Some Miam internal informations an function are available they can be accesed\nwith ",(0,a.kt)("inlineCode",{parentName:"p"},"GroceriesListHandler")),(0,a.kt)("h2",{id:"recipes-count"},"Recipes count"),(0,a.kt)("p",null,"You can get the count of recipes added to cart by the user, for this you have two approche: "),(0,a.kt)("ul",null,(0,a.kt)("li",{parentName:"ul"},"you can subscribe to the observable :")),(0,a.kt)("pre",null,(0,a.kt)("code",{parentName:"pre",className:"language-kotlin"},'import com.miam.kmmMiamCore.handler.GroceriesListHandler\n\nprivate var recipeCount = 0\n\nlaunch {\n    GroceriesListHandler.getRecipeCountChangeFlow().collect {\n        recipeCount = it.newRecipeCount\n        println("recipes count by flow : $recipeCount ")\n    }\n}\n')),(0,a.kt)("ul",null,(0,a.kt)("li",{parentName:"ul"},"or neither provide use a call back to use when it change")),(0,a.kt)("pre",null,(0,a.kt)("code",{parentName:"pre",className:"language-kotlin"},'import com.miam.kmmMiamCore.handler.GroceriesListHandler\n\nprivate var recipeCount = 0\n\n// RecipeCountChanged -> {newRecipeCount :Int}\nfun customCallBack(gle: RecipeCountChanged){\n    recipeCount = gle.newRecipeCount\n    println("recipes count by callback : $recipeCount ")\n}\n\nGroceriesListHandler.onRecipeCountChange(customCallBack)\n')),(0,a.kt)("admonition",{type:"note"},(0,a.kt)("p",{parentName:"admonition"},"Using ",(0,a.kt)("inlineCode",{parentName:"p"},"getRecipeCountChangeFlow")," will only emit change witch occure after you start the observation. ",(0,a.kt)("br",null),"\nUsing ",(0,a.kt)("inlineCode",{parentName:"p"},"onRecipeCountChange")," will provide a first value and from then after each change.")),(0,a.kt)("h2",{id:"reset-groceries-list"},"Reset groceries list"),(0,a.kt)("p",null,"You can provide a new groceries list to your customer :"),(0,a.kt)("pre",null,(0,a.kt)("code",{parentName:"pre",className:"language-kotlin"},"import com.miam.kmmMiamCore.handler.GroceriesListHandler\n\nGroceriesListHandler.resetGroceriesList()\n")),(0,a.kt)("admonition",{type:"tip"},(0,a.kt)("p",{parentName:"admonition"},"It depend on the user experience you want, but you can link this reset to your customer cart reset,\nor use it only for development purpose to avoid weird test behavours.")))}d.isMDXComponent=!0}}]);