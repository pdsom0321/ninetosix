import{r as u,g,e as y,i as x,o as m,c as i,f as v,u as b,a as e,F as I,b as w,t as l,_ as V}from"./index-a4006179.js";import{a as p,_}from"./lodash-8d52f98d.js";import{p as k}from"./popupModule-c2aa091e.js";import{u as C}from"./company-73a2d023.js";const E={class:"row"},M={style:{"max-height":"700px"},class:"overflow-auto"},N={class:"table table-hover table-bordered h-75 text-center"},B=e("colgroup",null,[e("col",{width:"*"}),e("col",{width:"*"}),e("col",{width:"*"}),e("col",{width:"*"}),e("col",{width:"5%"}),e("col",{width:"5%"})],-1),D=e("thead",{class:"sticky-top"},[e("tr",null,[e("th",null,"ID"),e("th",{class:"text-start"},"email"),e("th",null,"이름"),e("th",null,"소속"),e("th",null,"use"),e("th",null,"delete")])],-1),$={class:"text-start"},z=["onClick"],A={__name:"customer",setup(F){const a=C(),h=u([]),r=u([]),c=u([]),n=g(null);y(()=>{a.compId&&(r.splice(0),p.api({url:`/team/${a.compId}`,method:"get"}).then(o=>{_.merge(r,o)})),n.value&&(c.splice(0),p.api({url:`/member/${n.value}`,method:"get"}).then(o=>{_.merge(c,o)}))});const f=(o,s)=>{k.popup({component:V(()=>import("./customerDtl-83209ec6.js"),["assets/customerDtl-83209ec6.js","assets/index-a4006179.js","assets/index-00d070bc.css","assets/lodash-8d52f98d.js"]),data:{isModal:!0,teamId:o,memberId:s}}).ok(d=>{console.log(d)})};return a.compId=null,p.api({url:"/company",method:"get"}).then(o=>{_.merge(h,o)}),(o,s)=>{const d=x("v-select");return m(),i("div",E,[v(d,{class:"my-1",style:{"z-index":"1040"},options:h,reduce:t=>t.id,label:"name",modelValue:b(a).compId,"onUpdate:modelValue":s[0]||(s[0]=t=>b(a).compId=t)},null,8,["options","reduce","modelValue"]),v(d,{class:"my-1",style:{"z-index":"1030"},options:r,reduce:t=>t.id,label:"name",modelValue:n.value,"onUpdate:modelValue":s[1]||(s[1]=t=>n.value=t)},null,8,["options","reduce","modelValue"]),e("div",M,[e("table",N,[B,D,e("tbody",null,[(m(!0),i(I,null,w(c,t=>(m(),i("tr",null,[e("td",null,l(t.id),1),e("td",$,l(t.email),1),e("td",null,[e("button",{class:"btn border-0 bg-transparent text-primary",onClick:L=>f(n.value,t.id)},l(t.name),9,z)]),e("td",null,l(t.companyName)+" ["+l(t.teamName)+"]",1),e("td",null,l(t.useYn),1),e("td",null,l(t.deleteYn),1)]))),256))])])])])}}};export{A as default};
