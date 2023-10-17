import{r as g,o as n,c as s,a as t,F as k,b as f,n as V,u as v,t as M,w as m,v as h,d as T,e as L,_ as D,f as I}from"./index-1fb661e4.js";import{u as U}from"./company-ca5e79dd.js";import{a as $,_ as w}from"./lodash-cea45741.js";import{p as N}from"./popupModule-1eabdad5.js";const E={class:"table table-bordered table-hover text-center align-middle"},P=t("colgroup",null,[t("col"),t("col",{width:"90%"})],-1),A={class:"border-0"},B={class:"text-end border-0",colspan:"100"},F=t("tr",null,[t("th",null,"ID"),t("th",null,"회사명")],-1),S=["onClick"],z={class:"bg-transparent"},O={class:"bg-transparent"},R={class:"input-group"},j=["role","onUpdate:modelValue","readonly"],q=["onClick"],G=["onClick"],H=["onClick"],J=t("td",{class:"opacity-75"},"auto",-1),K={class:"input-group"},Q=["onUpdate:modelValue"],W=["onClick"],X={__name:"company",setup(C){const i=U(),a=g([]),u=()=>{a.splice(0),$.api({url:"/company",method:"get"}).then(o=>{w.merge(a,o)})},_=g([]),p=o=>{$.api({url:"/company",method:"post",data:{name:_[o].name}}).then(()=>{_.splice(o,1),u()})},y=o=>{$.api({url:`/company/${a[o].id}`,method:"put",data:{name:a[o].name}}).then(()=>{u()})},r=o=>{a[o].editMode=!0},b=o=>{confirm("정말 삭제하시겠습니까?")&&$.api({url:`/company/${a[o].id}`,method:"delete"}).then(()=>{u()})};return u(),(o,l)=>(n(),s("table",E,[P,t("thead",null,[t("tr",A,[t("th",B,[t("button",{class:"btn btn-sm btn-outline-secondary",onClick:l[0]||(l[0]=e=>_.push({}))},"추가")])]),F]),t("tbody",null,[(n(!0),s(k,null,f(a,(e,c)=>(n(),s("tr",{class:V(v(i).compId==e.id?"bg-primary-subtle":""),onClick:d=>v(i).compId=e.id},[t("td",z,M(e.id),1),t("td",O,[t("div",R,[m(t("input",{type:"text",role:e.editMode?"":"button",class:V(["form-control cursor-pointer",e.editMode?"":"bg-transparent border-0"]),"onUpdate:modelValue":d=>e.name=d,readonly:!e.editMode},null,10,j),[[h,e.name]]),e.editMode?T("",!0):(n(),s("button",{key:0,class:"btn btn-sm btn-outline-secondary rounded-0",onClick:d=>r(c)}," 수정 ",8,q)),e.editMode?(n(),s("button",{key:2,class:"btn btn-sm btn-outline-success",onClick:d=>y(c)}," 저장 ",8,H)):(n(),s("button",{key:1,class:"btn btn-sm btn-outline-danger rounded-0",onClick:d=>b(c)}," 삭제 ",8,G))])])],10,S))),256)),(n(!0),s(k,null,f(_,(e,c)=>(n(),s("tr",null,[J,t("td",null,[t("div",K,[m(t("input",{type:"text",class:"form-control","onUpdate:modelValue":d=>e.name=d},null,8,Q),[[h,e.name]]),t("button",{class:"btn btn-sm btn-outline-success rounded-0",onClick:d=>p(c)}," 저장 ",8,W)])])]))),256))])]))}},Y={class:"table table-bordered table-hover text-center align-middle"},Z=t("colgroup",null,[t("col"),t("col",{width:"90%"})],-1),tt={class:"border-0"},et={class:"text-end border-0",colspan:"100"},ot=["disabled"],nt=t("tr",null,[t("th",null,"ID"),t("th",null,"팀명")],-1),st={key:0},at={class:"input-group"},lt=["onUpdate:modelValue","disabled"],dt=["onClick"],ct=["onClick"],it=["onClick"],rt=t("td",{class:"opacity-75"},"auto",-1),ut={class:"input-group"},_t=["onUpdate:modelValue"],bt=["onClick"],pt={key:1},mt=t("tr",null,[t("td",{colspan:"100"},"회사를 선택해주세요")],-1),ht=[mt],$t=t("tbody",null,null,-1),vt={__name:"team",setup(C){const i=U(),a=g([]),u=g([]),_=({id:o,method:l,data:e},c)=>{const d={url:o?`/team/${o}`:"/team",method:l};e&&(d.data=e);try{$.api(d).then(x=>{c(x)})}catch(x){console.log(x)}},p=()=>{a.splice(0);const o={id:i.compId,method:"get"};_(o,l=>{w.merge(a,l)})};L(()=>{i.compId&&(a.splice(0),u.splice(0),p())});const y=o=>{const l={method:"post",data:{name:u[o].name,companyId:i.compId}};_(l,()=>{u.splice(o,1),p()})},r=o=>{const l={id:a[o].id,method:"put",data:{name:a[o].name}};_(l,()=>{p()})},b=o=>{if(confirm("정말 삭제하시겠습니까?")){const l={id:a[o].id,method:"delete"};_(l,()=>{p()})}};return(o,l)=>(n(),s("table",Y,[Z,t("thead",null,[t("tr",tt,[t("th",et,[t("button",{class:"btn btn-sm btn-outline-secondary",onClick:l[0]||(l[0]=e=>u.push({})),disabled:!v(i).compId}," 추가 ",8,ot)])]),nt]),v(i).compId?(n(),s("tbody",st,[(n(!0),s(k,null,f(a,(e,c)=>(n(),s("tr",null,[t("td",null,M(e.id),1),t("td",null,[t("div",at,[m(t("input",{type:"text",class:V(["form-control",e.editMode?"":"bg-transparent border-0"]),"onUpdate:modelValue":d=>e.name=d,disabled:!e.editMode},null,10,lt),[[h,e.name]]),e.editMode?T("",!0):(n(),s("button",{key:0,class:"btn btn-sm btn-outline-secondary rounded-0",onClick:d=>e.editMode=!0}," 수정 ",8,dt)),e.editMode?(n(),s("button",{key:2,class:"btn btn-sm btn-outline-success",onClick:d=>r(c)}," 저장 ",8,it)):(n(),s("button",{key:1,class:"btn btn-sm btn-outline-danger rounded-0",onClick:d=>b(c)}," 삭제 ",8,ct))])])]))),256)),(n(!0),s(k,null,f(u,(e,c)=>(n(),s("tr",null,[rt,t("td",null,[t("div",ut,[m(t("input",{type:"text",class:"form-control","onUpdate:modelValue":d=>e.name=d},null,8,_t),[[h,e.name]]),t("button",{class:"btn btn-sm btn-outline-success rounded-0",onClick:d=>y(c)}," 저장 ",8,bt)])])]))),256))])):(n(),s("tbody",pt,ht)),$t]))}};const yt={class:"table table-bordered table-hover text-center align-middle"},gt=t("colgroup",null,[t("col"),t("col",{width:"90%"})],-1),kt={class:"border-0"},ft={class:"text-end border-0",colspan:"100"},Ct=["disabled"],xt=t("tr",null,[t("th",null,"ID"),t("th",null,"위치명 / 위도 / 경도")],-1),It={key:0},Vt={class:"input-group"},Mt=["onUpdate:modelValue"],Ut=["onUpdate:modelValue"],wt=["onUpdate:modelValue"],Tt=["onClick"],Lt=["onClick"],Dt={key:1},Nt=t("tr",null,[t("td",{colspan:"100"},"회사를 선택해주세요")],-1),Et=[Nt],Pt={__name:"company-locations",setup(C){const i=U(),a=g([]),u=({id:r,method:b,data:o},l)=>{const e={url:r?`/company-location/${r}`:"/company-location",method:b};o&&(e.data=o);try{$.api(e).then(c=>{l(c)})}catch(c){console.log(c)}},_=()=>{a.splice(0);const r={id:i.compId,method:"get"};u(r,b=>{w.merge(a,b)})};L(()=>{i.compId&&_()});const p=r=>{if(confirm("정말 삭제하시겠습니까?")){const b={id:a[r].id,method:"delete"};u(b,o=>{_()})}},y=()=>{N.popup({component:D(()=>import("./locations-511d2120.js"),["assets/locations-511d2120.js","assets/index-1fb661e4.js","assets/index-00d070bc.css","assets/lodash-cea45741.js","assets/company-ca5e79dd.js"]),data:{isModal:!0}}).ok(r=>{console.log(r)})};return(r,b)=>(n(),s("table",yt,[gt,t("thead",null,[t("tr",kt,[t("th",ft,[t("button",{class:"btn btn-sm btn-outline-secondary",onClick:y,disabled:!v(i).compId}," 추가 ",8,Ct)])]),xt]),v(i).compId?(n(),s("tbody",It,[(n(!0),s(k,null,f(a,(o,l)=>(n(),s("tr",null,[t("td",null,M(o.id),1),t("td",null,[t("div",Vt,[m(t("input",{type:"text",class:"form-control text-center border-0 bg-transparent","onUpdate:modelValue":e=>o.locationName=e,readonly:""},null,8,Mt),[[h,o.locationName]]),m(t("input",{type:"text",class:"form-control text-center border-0 bg-transparent","onUpdate:modelValue":e=>o.latitude=e,readonly:""},null,8,Ut),[[h,o.latitude]]),m(t("input",{type:"text",class:"form-control text-center border-0 bg-transparent","onUpdate:modelValue":e=>o.longitude=e,readonly:""},null,8,wt),[[h,o.longitude]]),o.editMode?(n(),s("button",{key:1,class:"btn btn-sm btn-outline-success",onClick:e=>r.saveLocation(l)}," 저장 ",8,Lt)):(n(),s("button",{key:0,class:"btn btn-sm btn-outline-danger rounded-0",onClick:e=>p(l)}," 삭제 ",8,Tt))])])]))),256))])):(n(),s("tbody",Dt,Et))]))}},At={class:"row m-0 h-100"},Bt={class:"col-6 h-100 overflow-auto"},Ft=t("ul",{class:"nav nav-tabs",id:"mainTab",role:"tablist"},[t("li",{class:"nav-item",role:"presentation"},[t("button",{class:"nav-link active",id:"comp-tab","data-bs-toggle":"tab","data-bs-target":"#comp-tab-pane",type:"button",role:"tab","aria-controls":"comp-tab-pane","aria-selected":"true"}," 회사 ")])],-1),St={class:"tab-content",id:"mainTabContent"},zt={class:"tab-pane fade show active",id:"comp-tab-pane",role:"tabpanel","aria-labelledby":"comp-tab",tabindex:"0"},Ot={class:"col-6"},Rt=t("ul",{class:"nav nav-tabs",id:"subTab",role:"tablist"},[t("li",{class:"nav-item",role:"presentation"},[t("button",{class:"nav-link active",id:"team-tab","data-bs-toggle":"tab","data-bs-target":"#team-tab-pane",type:"button",role:"tab","aria-controls":"team-tab-pane","aria-selected":"true"}," 팀 ")]),t("li",{class:"nav-item",role:"presentation"},[t("button",{class:"nav-link",id:"location-tab","data-bs-toggle":"tab","data-bs-target":"#location-tab-pane",type:"button",role:"tab","aria-controls":"location-tab-pane","aria-selected":"false"}," 위치 ")])],-1),jt={class:"tab-content",id:"subTabContent"},qt={class:"tab-pane fade show active",id:"team-tab-pane",role:"tabpanel","aria-labelledby":"team-tab",tabindex:"0"},Gt={class:"tab-pane fade",id:"location-tab-pane",role:"tabpanel","aria-labelledby":"location-tab",tabindex:"0"},Wt={__name:"index",setup(C){return(i,a)=>(n(),s("div",At,[t("div",Bt,[Ft,t("div",St,[t("div",zt,[I(X)])])]),t("div",Ot,[Rt,t("div",jt,[t("div",qt,[I(vt)]),t("div",Gt,[I(Pt)])])])]))}};export{Wt as default};
