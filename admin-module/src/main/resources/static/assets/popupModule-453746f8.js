import{r as l,m,o as i,c as _,a,u as d,p as f,q as h,d as b,n as w,s as k,x as v,y}from"./index-6d99b222.js";import"./lodash-9cc57d9a.js";const g={class:"card popup-component border-0"},x=a("span",{class:"text-start text-light float-start ms-2 p-2"},"-",-1),C={class:"card-body p-0 overflow-auto"},O={__name:"popup",props:{settings:{type:Object,default:()=>({})}},setup(e,{emit:s}){const o=e,n=l({show:!1,showing:!1});m(()=>{n.show=!0,requestAnimationFrame(()=>{n.showing=!0})});const p=k({loader:()=>o.settings.component});l({callbackData:{}});const r=c=>{o.settings._ok&&o.settings._ok(c),u()},t=c=>{o.settings._close&&o.settings._close(c),u()},u=()=>{n.showing=!1,setTimeout(()=>{n.show=!1,o.settings.$unmount()},300)};return(c,A)=>(i(),_("div",{class:w(["popup-container text-center",{show:n.show,showing:n.showing}])},[a("div",g,[a("div",{class:"card-header text-end p-0 bg-secondary border-0"},[x,a("button",{class:"px-3 py-2 btn btn-sm btn-outline-secondary text-light close-btn fw-bold",onClick:t}," X ")]),a("div",C,[d(p)?(i(),f(h(d(p)),{key:0,data:o.settings.data,onOk:r,onClose:t},null,40,["data"])):b("",!0)])])],2))}},B={popup(e){if(!e.popup&&!e.component){console.warn("popup의 component가 정의되지 않았습니다.");return}const s=new Promise(t=>{e._popupFinal=()=>{t()}});Object.assign(s,{ok(t){return e._ok=t,s},close(t){return e._ok=t,s},final(t){return e._final=t,s}}),Object.assign(e,{$promise(){return s}});const o=document.createElement("div");document.body.append(o);const n=e.popup??O,p=v({render:()=>y(n,{settings:e})}),r=p.mount(o);return Object.assign(e,{$app(){return p},$vueApp(){return r},$unmount(){p.unmount(),o.remove()}}),s}};export{B as p};
