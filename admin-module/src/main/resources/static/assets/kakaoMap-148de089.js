import{g,r as u,m as h,o as d,c as p,a as s,w as b,v as y,F as w,b as x,t as k,C as f}from"./index-306eb029.js";import{_ as L}from"./popupModule-4d058fbf.js";const M={class:"card h-100 border-0"},C={class:"card-header"},P={class:"input-group"},E=s("div",{class:"card-body p-0 h-100"},[s("div",{id:"map"})],-1),T={class:"card-footer overflow-auto px-0"},A={class:"px-1"},B={class:"text-start my-2 border"},K=["onClick"],V=["onClick"],F={__name:"kakaoMap",setup(D){var n=null,l=null;const i=g(""),c=u([]),o=u({lat:37.51082,lng:127.02928}),m=()=>{var a=document.getElementById("map"),e={center:new kakao.maps.LatLng(o.lat,o.lng),level:4};n=new kakao.maps.Map(a,e),l=new kakao.maps.Marker({position:n.getCenter()}),l.setMap(n),n.setDraggable(!0),kakao.maps.event.addListener(n,"click",t=>{var r=t.latLng;l.setPosition(r),o.lat=r.getLat(),o.lng=r.getLng()})},v=()=>{c.splice(0);var a=new kakao.maps.services.Places;a.keywordSearch(i.value,(e,t)=>{t===kakao.maps.services.Status.OK&&(console.log(e),L.merge(c,e))})},_=a=>{o.lat=a.y,o.lng=a.x;var e=new kakao.maps.LatLng(o.lat,o.lng);n.panTo(e),l.setPosition(e)};return h(()=>{if(window.kakao&&window.kakao.maps)m();else{const a=document.createElement("script");a.src=`//dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=${{}.VITE_APP_KAKAOMAP_KEY}&libraries=services`,a.addEventListener("load",()=>{kakao.maps.load(m)}),document.head.appendChild(a)}}),(a,e)=>(d(),p("div",M,[s("div",C,[s("div",P,[b(s("input",{type:"text",class:"form-control","onUpdate:modelValue":e[0]||(e[0]=t=>i.value=t)},null,512),[[y,i.value]]),s("button",{class:"btn btn-outline-secondary",type:"button",onClick:v},"검색")])]),E,s("div",T,[s("ul",A,[(d(!0),p(w,null,x(c,t=>(d(),p("li",B,[s("button",{class:"btn btn-outline-primary",onClick:r=>_(t)},k(t.place_name),9,K),f(" [ "+k(t.y)+" , "+k(t.x)+" ] ",1),s("button",{class:"btn btn-outline-primary",onClick:r=>_(t)},"등록",8,V)]))),256))])])]))}};export{F as default};
