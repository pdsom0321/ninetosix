import{a as o,_ as m}from"./lodash-474b4435.js";import{r as s,t as n}from"./index-d7ee057e.js";const d={__name:"customerDtl",props:{data:{type:String}},setup(r){const e=r,a=s({});return o.api({url:`/member/${e.data.teamId}/${e.data.memberId}`,method:"get"}).then(t=>{console.log(t),m.merge(a,t)}),(t,p)=>n(a)}};export{d as default};