(function(e){function t(t){for(var r,a,c=t[0],u=t[1],s=t[2],l=0,d=[];l<c.length;l++)a=c[l],Object.prototype.hasOwnProperty.call(o,a)&&o[a]&&d.push(o[a][0]),o[a]=0;for(r in u)Object.prototype.hasOwnProperty.call(u,r)&&(e[r]=u[r]);p&&p(t);while(d.length)d.shift()();return i.push.apply(i,s||[]),n()}function n(){for(var e,t=0;t<i.length;t++){for(var n=i[t],r=!0,a=1;a<n.length;a++){var c=n[a];0!==o[c]&&(r=!1)}r&&(i.splice(t--,1),e=u(u.s=n[0]))}return e}var r={},a={app:0},o={app:0},i=[];function c(e){return u.p+"js/"+({about:"about"}[e]||e)+"."+{about:"5e8badb9"}[e]+".js"}function u(t){if(r[t])return r[t].exports;var n=r[t]={i:t,l:!1,exports:{}};return e[t].call(n.exports,n,n.exports,u),n.l=!0,n.exports}u.e=function(e){var t=[],n={about:1};a[e]?t.push(a[e]):0!==a[e]&&n[e]&&t.push(a[e]=new Promise(function(t,n){for(var r="css/"+({about:"about"}[e]||e)+"."+{about:"86478531"}[e]+".css",o=u.p+r,i=document.getElementsByTagName("link"),c=0;c<i.length;c++){var s=i[c],l=s.getAttribute("data-href")||s.getAttribute("href");if("stylesheet"===s.rel&&(l===r||l===o))return t()}var d=document.getElementsByTagName("style");for(c=0;c<d.length;c++){s=d[c],l=s.getAttribute("data-href");if(l===r||l===o)return t()}var p=document.createElement("link");p.rel="stylesheet",p.type="text/css",p.onload=t,p.onerror=function(t){var r=t&&t.target&&t.target.src||o,i=new Error("Loading CSS chunk "+e+" failed.\n("+r+")");i.code="CSS_CHUNK_LOAD_FAILED",i.request=r,delete a[e],p.parentNode.removeChild(p),n(i)},p.href=o;var f=document.getElementsByTagName("head")[0];f.appendChild(p)}).then(function(){a[e]=0}));var r=o[e];if(0!==r)if(r)t.push(r[2]);else{var i=new Promise(function(t,n){r=o[e]=[t,n]});t.push(r[2]=i);var s,l=document.createElement("script");l.charset="utf-8",l.timeout=120,u.nc&&l.setAttribute("nonce",u.nc),l.src=c(e);var d=new Error;s=function(t){l.onerror=l.onload=null,clearTimeout(p);var n=o[e];if(0!==n){if(n){var r=t&&("load"===t.type?"missing":t.type),a=t&&t.target&&t.target.src;d.message="Loading chunk "+e+" failed.\n("+r+": "+a+")",d.name="ChunkLoadError",d.type=r,d.request=a,n[1](d)}o[e]=void 0}};var p=setTimeout(function(){s({type:"timeout",target:l})},12e4);l.onerror=l.onload=s,document.head.appendChild(l)}return Promise.all(t)},u.m=e,u.c=r,u.d=function(e,t,n){u.o(e,t)||Object.defineProperty(e,t,{enumerable:!0,get:n})},u.r=function(e){"undefined"!==typeof Symbol&&Symbol.toStringTag&&Object.defineProperty(e,Symbol.toStringTag,{value:"Module"}),Object.defineProperty(e,"__esModule",{value:!0})},u.t=function(e,t){if(1&t&&(e=u(e)),8&t)return e;if(4&t&&"object"===typeof e&&e&&e.__esModule)return e;var n=Object.create(null);if(u.r(n),Object.defineProperty(n,"default",{enumerable:!0,value:e}),2&t&&"string"!=typeof e)for(var r in e)u.d(n,r,function(t){return e[t]}.bind(null,r));return n},u.n=function(e){var t=e&&e.__esModule?function(){return e["default"]}:function(){return e};return u.d(t,"a",t),t},u.o=function(e,t){return Object.prototype.hasOwnProperty.call(e,t)},u.p="",u.oe=function(e){throw console.error(e),e};var s=window["webpackJsonp"]=window["webpackJsonp"]||[],l=s.push.bind(s);s.push=t,s=s.slice();for(var d=0;d<s.length;d++)t(s[d]);var p=l;i.push([0,"chunk-vendors"]),n()})({0:function(e,t,n){e.exports=n("cd49")},"29cf":function(e,t,n){"use strict";var r=function(){var e=this,t=e.$createElement,n=e._self._c||t;return n("div",[n("div",{class:["zBottomButton",e.styleClass,{disableClick:e.disableClick}],on:{click:e.buttonClicked}},[e._v("\n    "+e._s(e.buttonText)+"\n  ")])])},a=[],o={name:"BottomButton",props:{buttonText:String,buttonClick:Function,styleClass:String,disableClick:{type:Boolean,default:!1}},methods:{buttonClicked:function(){this.disableClick||this.buttonClick()}}},i=o,c=(n("bbab"),n("2877")),u=Object(c["a"])(i,r,a,!1,null,"3037661c",null);t["a"]=u.exports},"3f7f":function(e,t,n){"use strict";n.d(t,"c",function(){return c}),n.d(t,"d",function(){return s}),n.d(t,"a",function(){return d}),n.d(t,"b",function(){return f});n("96cf");var r=n("3b8d"),a=(n("2160"),"");function o(e){return i.apply(this,arguments)}function i(){return i=Object(r["a"])(regeneratorRuntime.mark(function e(t){var n,r,a,o=arguments;return regeneratorRuntime.wrap(function(e){while(1)switch(e.prev=e.next){case 0:return n=o.length>1&&void 0!==o[1]?o[1]:{},e.prev=1,e.next=4,fetch(t,n);case 4:return r=e.sent,e.next=7,r.json();case 7:return a=e.sent,e.abrupt("return",a);case 11:return e.prev=11,e.t0=e["catch"](1),console.log(e.t0),e.abrupt("return",{error:"NETWORK_ERROR"});case 15:case"end":return e.stop()}},e,null,[[1,11]])})),i.apply(this,arguments)}function c(e){return u.apply(this,arguments)}function u(){return u=Object(r["a"])(regeneratorRuntime.mark(function e(t){var n,r;return regeneratorRuntime.wrap(function(e){while(1)switch(e.prev=e.next){case 0:return n=a+"/api/initdoc",r={method:"POST",mode:"cors",headers:{"Content-Type":"application/json"},body:JSON.stringify(t)},e.abrupt("return",o(n,r));case 3:case"end":return e.stop()}},e)})),u.apply(this,arguments)}function s(e){return l.apply(this,arguments)}function l(){return l=Object(r["a"])(regeneratorRuntime.mark(function e(t){var n,r;return regeneratorRuntime.wrap(function(e){while(1)switch(e.prev=e.next){case 0:return n=a+"/api/init",r={method:"POST",mode:"cors",headers:{"Content-Type":"application/json"},body:JSON.stringify(t)},e.abrupt("return",o(n,r));case 3:case"end":return e.stop()}},e)})),l.apply(this,arguments)}function d(e){return p.apply(this,arguments)}function p(){return p=Object(r["a"])(regeneratorRuntime.mark(function e(t){var n,r;return regeneratorRuntime.wrap(function(e){while(1)switch(e.prev=e.next){case 0:return n=a+"/api/checkdoc",r={method:"POST",mode:"cors",headers:{"Content-Type":"application/json"},body:JSON.stringify(t)},e.abrupt("return",o(n,r));case 3:case"end":return e.stop()}},e)})),p.apply(this,arguments)}function f(e){return h.apply(this,arguments)}function h(){return h=Object(r["a"])(regeneratorRuntime.mark(function e(t){var n,r;return regeneratorRuntime.wrap(function(e){while(1)switch(e.prev=e.next){case 0:return n=a+"/api/checkfinal",r={method:"POST",mode:"cors",headers:{"Content-Type":"application/json"},body:JSON.stringify(t)},e.abrupt("return",o(n,r));case 3:case"end":return e.stop()}},e)})),h.apply(this,arguments)}},"5c48":function(e,t,n){},"63df":function(e,t,n){e.exports=n.p+"img/docguide.edb512a0.png"},"641e":function(e,t,n){"use strict";var r=function(){var e=this,t=e.$createElement,n=e._self._c||t;return n("div",{staticClass:"zNavigationBar"},[e._v("\n  "+e._s(e.title)+"\n")])},a=[],o=n("d225"),i=n("308d"),c=n("6bb5"),u=n("4e2b"),s=n("9ab4"),l=n("60a3"),d=function(e){function t(){return Object(o["a"])(this,t),Object(i["a"])(this,Object(c["a"])(t).apply(this,arguments))}return Object(u["a"])(t,e),t}(l["c"]);s["a"]([Object(l["b"])({type:String,default:""})],d.prototype,"title",void 0),d=s["a"]([l["a"]],d);var p=d,f=p,h=(n("ee8b"),n("2877")),b=Object(h["a"])(f,r,a,!1,null,"d10a4228",null);t["a"]=b.exports},"7c55":function(e,t,n){"use strict";var r=n("5c48"),a=n.n(r);a.a},"88e7":function(e,t,n){"use strict";var r=n("e9f0"),a=n.n(r);a.a},bbab:function(e,t,n){"use strict";var r=n("be93"),a=n.n(r);a.a},be93:function(e,t,n){},bf2b:function(e,t,n){},c39d:function(e,t,n){},cd49:function(e,t,n){"use strict";n.r(t);n("cadf"),n("551c"),n("f751"),n("097d");var r=n("2b0e"),a=function(){var e=this,t=e.$createElement,n=e._self._c||t;return n("div",{attrs:{id:"app"}},[n("router-view")],1)},o=[],i=(n("7c55"),n("2877")),c={},u=Object(i["a"])(c,a,o,!1,null,null,null),s=u.exports,l=n("8c4f"),d=function(){var e=this,t=e.$createElement,r=e._self._c||t;return r("div",{staticClass:"routerView"},[r("NavigationBar",{attrs:{title:"Choose ID Type"}}),r("div",{staticClass:"mainContainer"},[r("SelectorCell",{ref:e.docSelectorModel.submit_key,staticClass:"cellContainer",attrs:{uniqueId:e.docSelectorModel.submit_key,cellData:e.docSelectorModel}}),r("div",{staticClass:"tipHeader"},[e._v("\n      Prepare to take your ID photo\n    ")]),r("div",{staticClass:"tipText",domProps:{innerHTML:e._s(e.tipText)}}),r("img",{staticClass:"docGuideImage",attrs:{src:n("63df")}})],1),r("div",{staticClass:"footer"},[r("BottomButton",{attrs:{buttonText:"Take ID Photo",buttonClick:e.takePhoto}})],1)],1)},p=[],f=(n("28a5"),n("96cf"),n("3b8d")),h=(n("ac4d"),n("8a81"),n("ac6a"),n("d225")),b=n("b0b4"),v=n("308d"),m=n("6bb5"),y=n("4e2b"),g=n("9ab4"),C=n("60a3"),w=n("29cf"),k=function(){var e=this,t=e.$createElement,n=e._self._c||t;return n("div",{directives:[{name:"show",rawName:"v-show",value:!e.cellComputedData.hideCell,expression:"!cellComputedData.hideCell"}],class:["zCellContainer",{zShowError:e.showError,zActive:e.isActive,zFilled:e.isFilled}],attrs:{id:e.uniqueId}},[n("div",{staticClass:"zContentContainer"},[n("select",{directives:[{name:"model",rawName:"v-model",value:e.cellData.content,expression:"cellData.content"}],ref:"selectDiv",staticClass:"zListContent zSelector",on:{change:[function(t){var n=Array.prototype.filter.call(t.target.options,function(e){return e.selected}).map(function(e){var t="_value"in e?e._value:e.value;return t});e.$set(e.cellData,"content",t.target.multiple?n:n[0])},e.handleChange],focus:e.handleFocus,blur:e.handleBlur}},[n("option",{attrs:{disabled:"",value:"-1"}},[e._v("Please select")]),e._l(e.cellData.options,function(t){return n("option",{key:t.key,domProps:{value:t.key}},[e._v("\n        "+e._s(t.value)+"\n      ")])})],2),n("div",{staticClass:"zListLabel"},[e._v("\n      "+e._s(e.cellData.label)+"\n    ")])]),n("div",{staticClass:"zSeperatorLine"}),n("div",{staticClass:"zHelperMsg"},[e._v("\n    "+e._s(e.helperMsg)+"\n  ")])])},O=[],_={name:"SelectorCell",props:{uniqueId:String,cellData:{type:Object,default:function(){return{}}},cellComputedData:{type:Object,default:function(){return{}}}},data:function(){return{showError:!1,isActive:!1,errorMsg:""}},computed:{helperMsg:function(){return this.showError?this.errorMsg:this.cellData.optional_label},isFilled:function(){return!!this.cellData.content}},methods:{handleChange:function(){this.cellComputedData.onChange&&this.cellComputedData.onChange(this.uniqueId),this.$refs.selectDiv.blur()},handleFocus:function(){this.showError=!1,this.cellComputedData.onFocus&&this.cellComputedData.onFocus(this.uniqueId)},handleBlur:function(){this.isActive=!1,this.cellComputedData.onBlur&&this.cellComputedData.onBlur(this.uniqueId)},validateRule:function(){var e=this.cellComputedData.validate_rule||this.cellData.validate_rule;return!!("isSelected"!=e||this.isFilled&&"-1"!=this.cellData.content)||(this.showError=!0,this.errorMsg=this.cellData.error_msg,!1)}}},j=_,S=(n("f910"),Object(i["a"])(j,k,O,!1,null,"0fabc160",null)),D=S.exports,x=n("641e"),T=n("3f7f"),E=function(e){function t(){var e;return Object(h["a"])(this,t),e=Object(v["a"])(this,Object(m["a"])(t).apply(this,arguments)),e.tipText="&nbsp;&nbsp;· Make sure the ID is right side up<br/>&nbsp;&nbsp;· Make sure ID is clear and readable<br/>&nbsp;&nbsp;· Avoid flash or glare",e.docSelectorModel={submit_key:"clientDocType",content:"",optional_label:"",label:"ID type",validate_rule:"isSelected",error_msg:"Field can't be blank",options:[{key:"00630000001",value:"UMID"},{key:"00630000002",value:"TIN ID"},{key:"00000001003",value:"Passport"},{key:"00630000024",value:"Philhealth Card"},{key:"00630000004",value:"Driver's License"},{key:"00630000020",value:"SSS ID"},{key:"00630000022",value:"Voter's ID"}]},e}return Object(y["a"])(t,e),Object(b["a"])(t,[{key:"mounted",value:function(){var e=document.getElementsByClassName("routerView"),t=!0,n=!1,r=void 0;try{for(var a,o=e[Symbol.iterator]();!(t=(a=o.next()).done);t=!0){var i=a.value;i.style.minHeight=window.innerHeight+"px"}}catch(c){n=!0,r=c}finally{try{t||null==o.return||o.return()}finally{if(n)throw r}}}},{key:"takePhoto",value:function(){var e=Object(f["a"])(regeneratorRuntime.mark(function e(){var t,n,r,a,o,i,c,u,s;return regeneratorRuntime.wrap(function(e){while(1)switch(e.prev=e.next){case 0:if(this.checkValidate()){e.next=2;break}return e.abrupt("return");case 2:return t=this.docSelectorModel.content,n={docType:t},e.next=6,Object(T["c"])(n);case 6:r=e.sent,console.log(r),"NETWORK_ERROR"===r.error?alert("Network Error"):r.success?(a="https://zasia.oss-cn-beijing.aliyuncs.com/dev/zoloz-doc-demo/index.html",o=r.bizId,i=r.clientCfg,c=window.location.href,u=c.split("#")[0],s=u+"#/faceguide",window.location.href="".concat(a,"?state=").concat(o,"&clientcfg=").concat(encodeURIComponent(i),"&callbackurl=").concat(encodeURIComponent(s))):alert("System Error");case 9:case"end":return e.stop()}},e,this)}));function t(){return e.apply(this,arguments)}return t}()},{key:"checkValidate",value:function(){var e=this.docSelectorModel,t=this.$refs[e.submit_key];if(t&&"function"===typeof t.validateRule){var n=t.validateRule();if(!n)return!1}return!0}}]),t}(C["c"]);E=g["a"]([Object(C["a"])({components:{BottomButton:w["a"],SelectorCell:D,NavigationBar:x["a"]}})],E);var R=E,P=R,I=(n("88e7"),Object(i["a"])(P,d,p,!1,null,"17d9cca6",null)),B=I.exports;r["a"].use(l["a"]);var M=new l["a"]({routes:[{path:"/",name:"home",component:B},{path:"/faceguide",name:"faceguide",component:function(){return n.e("about").then(n.bind(null,"0897"))}},{path:"/result",name:"result",component:function(){return n.e("about").then(n.bind(null,"eeac"))}}]}),N=n("2f62");r["a"].use(N["a"]);var z=new N["a"].Store({state:{},mutations:{},actions:{}});r["a"].config.productionTip=!1,new r["a"]({router:M,store:z,render:function(e){return e(s)}}).$mount("#app")},e9f0:function(e,t,n){},ee8b:function(e,t,n){"use strict";var r=n("c39d"),a=n.n(r);a.a},f910:function(e,t,n){"use strict";var r=n("bf2b"),a=n.n(r);a.a}});