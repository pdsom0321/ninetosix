import{g as v,r as k,k as y,l as w,m as x,h as C,o as r,c as d,a as t,w as M,v as $,F as f,b,n as _,_ as D,t as s}from"./index-ae0c7fd9.js";import{a as P,_ as V}from"./lodash-ca30e636.js";import{p as B}from"./popupModule-e56241ba.js";const E={class:"card h-100"},L={class:"card-header"},T={class:"input-group"},z={class:"card-body h-100 overflow-auto p-0"},A={class:"table table-hover text-center align-middle"},F=t("colgroup",null,[t("col",{width:"*"}),t("col",{width:"40%"}),t("col",{width:"*"}),t("col",{width:"*"}),t("col",{width:"5%"}),t("col",{width:"5%"})],-1),I={colspan:"100"},R=t("thead",null,[t("tr",null,[t("th",null,"ID"),t("th",{class:"text-start"},"Title"),t("th",null,"Start"),t("th",null,"End"),t("th",null,"Use"),t("th",null,"Delete")])],-1),S=["onClick"],U={class:"text-start"},Y={class:"card-footer"},j={class:"pagination justify-content-center my-2"},N=["onClick"],K={__name:"index",setup(O){const o=v(0),c=v(0),p=v(""),h=k([]),g=y(),i=()=>{var a;h.splice(0),P.api({url:`/board/${(a=g.meta)==null?void 0:a.type}?keyword=${p.value}&page=${o.value}&size=10`,method:"get"}).then(l=>{c.value=l.totalPage,V.merge(h,l.list)})};w(o,(a,l)=>{i()});const m=a=>{var e;let l={type:(e=g.meta)==null?void 0:e.type};a&&(l.id=a),B.popup({component:D(()=>import("./detail-8b1a243a.js"),["assets/detail-8b1a243a.js","assets/index-ae0c7fd9.js","assets/index-00d070bc.css","assets/lodash-ca30e636.js","assets/detail-ff81ffc5.css"]),data:l}).ok(()=>{i()})};x(()=>{i()});const u=C(()=>{let a=[],l=Math.floor(o.value/10)*10+1,e=Math.min(c.value,l+9);for(var n=l;n<=e;n++)a.push(n);return a});return(a,l)=>(r(),d("div",E,[t("div",L,[t("div",T,[M(t("input",{type:"text",class:"form-control","onUpdate:modelValue":l[0]||(l[0]=e=>p.value=e)},null,512),[[$,p.value]]),t("button",{class:"btn btn-outline-secondary",onClick:i},"검색")])]),t("div",z,[t("table",A,[F,t("thead",null,[t("tr",null,[t("td",I,[t("button",{class:"btn btn-sm btn-primary float-end",onClick:l[1]||(l[1]=e=>m(null))}," 신규 ")])])]),R,t("tbody",null,[(r(!0),d(f,null,b(h,(e,n)=>(r(),d("tr",{onClick:q=>m(e.id),style:{cursor:"pointer",height:"60px"}},[t("td",null,s(e.id),1),t("td",U,s(e.title),1),t("td",null,s(e.startDate),1),t("td",null,s(e.endDate),1),t("td",null,s(e.useYn),1),t("td",null,s(e.deleteYn),1)],8,S))),256))])])]),t("div",Y,[t("ul",j,[t("li",{class:_(["page-item",u.value[0]===1?"disabled":""])},[t("a",{class:"page-link",href:"#",onClick:l[2]||(l[2]=e=>o.value=Math.floor((o.value-10)/10)*10)},"이전")],2),(r(!0),d(f,null,b(u.value,e=>(r(),d("li",{class:_(["page-item",o.value==e-1?"active":""])},[t("a",{class:"page-link",href:"#",onClick:n=>o.value=e-1},s(e),9,N)],2))),256)),t("li",{class:_(["page-item",u.value[u.value.length-1]==c.value?"disabled":""])},[t("a",{class:"page-link",href:"#",onClick:l[3]||(l[3]=e=>o.value=Math.floor((o.value+10)/10)*10)},"다음")],2)])])]))}};export{K as default};
