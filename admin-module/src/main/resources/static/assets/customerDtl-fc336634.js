import{r as p,o as u,c as a,a as t,w as n,v as l}from"./index-14c6b9c1.js";import"./lodash-ed81fcb9.js";const i={class:"p-3"},d={class:"input-group input-group-sm mb-1"},r=t("span",{class:"input-group-text w-25 fw-bold"},"ID",-1),m={class:"input-group input-group-sm mb-1"},c=t("span",{class:"input-group-text w-25 fw-bold"},"email",-1),g={class:"input-group input-group-sm mb-1"},_=t("span",{class:"input-group-text w-25 fw-bold"},"이름",-1),f={class:"input-group input-group-sm mb-1"},y=t("span",{class:"input-group-text w-25 fw-bold"},"회사",-1),w={class:"input-group input-group-sm mb-1"},b=t("span",{class:"input-group-text w-25 fw-bold"},"팀",-1),x={class:"input-group input-group-sm mb-1"},v=t("span",{class:"input-group-text w-25 fw-bold"},"연락처",-1),U={class:"input-group input-group-sm mb-1"},V=t("span",{class:"input-group-text w-25 fw-bold"},"Use YN",-1),Y={class:"input-group input-group-sm mb-1"},D=t("span",{class:"input-group-text w-25 fw-bold"},"Delete YN",-1),N={class:"input-group input-group-sm mb-1"},h=t("span",{class:"input-group-text w-25 fw-bold"},"Push 동의여부",-1),T={class:"input-group input-group-sm mb-1"},A=t("span",{class:"input-group-text w-25 fw-bold"},"등록일",-1),B={class:"input-group input-group-sm mb-1"},I=t("span",{class:"input-group-text w-25 fw-bold"},"수정일",-1),P={__name:"customerDtl",setup(k){const o=p({id:12,email:"장대현",name:"test2",contact:"1",useYn:null,deleteYn:"N",pushAgreeYn:"Y",insertDate:"2023-09-26T14:54:09",updateDate:"2023-09-26T14:54:20",teamName:"모바일팀",companyName:"GS ITM"});return(M,s)=>(u(),a("div",i,[t("div",d,[r,n(t("input",{type:"text",class:"form-control","onUpdate:modelValue":s[0]||(s[0]=e=>o.id=e),readonly:""},null,512),[[l,o.id]])]),t("div",m,[c,n(t("input",{type:"text",class:"form-control","onUpdate:modelValue":s[1]||(s[1]=e=>o.email=e),readonly:""},null,512),[[l,o.email]])]),t("div",g,[_,n(t("input",{type:"text",class:"form-control","onUpdate:modelValue":s[2]||(s[2]=e=>o.name=e),readonly:""},null,512),[[l,o.name]])]),t("div",f,[y,n(t("input",{type:"datetime",class:"form-control","onUpdate:modelValue":s[3]||(s[3]=e=>o.companyName=e),readonly:""},null,512),[[l,o.companyName]])]),t("div",w,[b,n(t("input",{type:"datetime",class:"form-control","onUpdate:modelValue":s[4]||(s[4]=e=>o.teamName=e),readonly:""},null,512),[[l,o.teamName]])]),t("div",x,[v,n(t("input",{type:"text",class:"form-control","onUpdate:modelValue":s[5]||(s[5]=e=>o.contact=e),readonly:""},null,512),[[l,o.contact]])]),t("div",U,[V,n(t("input",{type:"text",class:"form-control","onUpdate:modelValue":s[6]||(s[6]=e=>o.useYn=e),readonly:""},null,512),[[l,o.useYn]])]),t("div",Y,[D,n(t("input",{type:"text",class:"form-control","onUpdate:modelValue":s[7]||(s[7]=e=>o.deleteYn=e),readonly:""},null,512),[[l,o.deleteYn]])]),t("div",N,[h,n(t("input",{type:"text",class:"form-control","onUpdate:modelValue":s[8]||(s[8]=e=>o.pushAgreeYn=e),readonly:""},null,512),[[l,o.pushAgreeYn]])]),t("div",T,[A,n(t("input",{type:"datetime",class:"form-control","onUpdate:modelValue":s[9]||(s[9]=e=>o.insertDate=e),readonly:""},null,512),[[l,o.insertDate]])]),t("div",B,[I,n(t("input",{type:"datetime",class:"form-control","onUpdate:modelValue":s[10]||(s[10]=e=>o.updateDate=e),readonly:""},null,512),[[l,o.updateDate]])])]))}};export{P as default};