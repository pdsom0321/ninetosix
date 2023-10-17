import{g as R,r as V,h as X,o as d,c as a,a as t,d as k,F as b,b as y,t as f,w as p,v as h,n as v,u as Y}from"./index-d7ee057e.js";import{a as I,_ as Q}from"./lodash-474b4435.js";import{u as N}from"./company-5fc40497.js";const D="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABAAAAAQCAYAAAAf8/9hAAAABHNCSVQICAgIfAhkiAAAAAlwSFlzAAAAdgAAAHYBTnsmCAAAABl0RVh0U29mdHdhcmUAd3d3Lmlua3NjYXBlLm9yZ5vuPBoAAAHoSURBVDiNlZM/aFNRGMV/9yYv4ntQa5cKQiwERWjRRReLg9qKJETaQoJ0UqroIDi0g4VILa5KIB0E0U2K5NVKoVqo7SCpTgoOYnGR4p9GtI8umshL3/scTMozJMWe6R7u+R2+y+VT1Ckx3d8lyEWF9CB0AKBYEdSCQt1/OvDkXTCvaodUPhUpGW4W4Qqg64ur8hB11/SMYTttu5sFqXwqUg5X5gQ52QSs16K5EYnbadvVACXDzW4DBjhVMtzbACox3d8F/lsgtBVxYPd+ju45wuTyIwQB8LTvHw4r/EvyH/Ct7nEsw6TiV8h/mAII+VoPaYGeYDisQw3gm1iGiVN2WPr6Knjdq4FozbXu2EXuRJbBg+cAiLXGGD82hmVYrP9eJ/NyjNWfq8GCfWH4+yCAs7Ek0ZYogy1R2na2cXxvN5Zh4ZQdRpdu1MMAooHPNfdweZIXXwoAnOk4jWVYrJUdrhcyjWCAT1pQz2vOF587r7ObJWtlh9FChuKvYiMYQc03/MawDnGh8zyzH581hQHP1xxSAInHfRMoudos2UgKcrMDM9c0gOkZw8DiNuiF4o/2EagujZ22XXMjEkcxAXhboJ6C3Lfv7fE3l+9VqpP8q+RUstPXegjoheo6w4qg5kXLg7m+mffB/B94Za7eE9WUdgAAAABJRU5ErkJggg==",W={class:"text-center h-100"},S={class:"card h-100 rounded-0"},T={class:"card-header"},j={class:"card-body h-100 overflow-auto"},q={class:"table table-hover align-middle"},F={class:"text-end"},G={colspan:"100"},H=t("th",null,"ID",-1),K=t("th",null,"위치명",-1),P=t("th",null,"위도",-1),J=t("th",null,"경도",-1),z={key:0},O=t("th",{colspan:"100"},null,-1),Z=["readonly","onUpdate:modelValue"],$=["readonly","onUpdate:modelValue"],tt=["readonly","onUpdate:modelValue"],et={class:"dropdown"},ot={class:"text-primary border-0 bg-transparent",type:"button","data-bs-toggle":"dropdown","aria-expanded":"false"},nt={class:"dropdown-menu"},st={class:"dropdown-item",href:"#"},dt={key:0},at={key:0,class:"btn-group"},lt=["onClick"],ct=["onClick"],it=["onClick"],rt={key:1},ut={key:0},mt=t("img",{src:D},null,-1),pt=[mt],ht=["onClick"],_t=t("span",{class:"fw-bold"},"+",-1),At=[_t],gt=t("td",null,"auto",-1),bt=["onUpdate:modelValue"],yt=["onUpdate:modelValue"],ft=["onUpdate:modelValue"],vt={key:0},Ct={colspan:"100"},Vt=["onClick"],Ut={__name:"locations",props:{data:{type:Object}},setup(M){const g=M,u=N(),C=R(""),i=V([]),m=V([]),A=({id:n,method:l,data:r},e)=>{const c={url:n?`/location/${n}`:"/location",method:l};r&&(c.data=r),I.api(c).then(s=>{console.log(s),e(s)})},_=()=>{i.splice(0),A({method:"get"},l=>{let r=l.map(s=>({...s,companies:s.companyList.length})),e=r.filter(s=>s.companyList.findIndex(o=>o.id===u.compId)>-1).sort((s,o)=>s.name<o.name?-1:s.name>o.name?1:0),c=r.filter(s=>s.companyList.findIndex(o=>o.id===u.compId)==-1).sort((s,o)=>s.name<o.name?-1:s.name>o.name?1:0);Q.merge(i,[...e,...c])})},U=X(()=>i.filter(n=>n.name.indexOf(C.value)>-1)),w=n=>{const l={method:"post",data:{name:m[n].name,latitude:m[n].latitude,longitude:m[n].longitude}};A(l,()=>{m.splice(n,1),_()})},L=n=>{const l={id:i[n].id,method:"put",data:{name:i[n].name,latitude:i[n].latitude,longitude:i[n].longitude}};A(l,()=>{_()})},B=n=>{if(confirm("정말 삭제하시겠습니까?")){const l={id:i[n].id,method:"delete"};A(l,()=>{_()})}},x=n=>{I.api({url:"company-location",data:{companyId:u.compId,locationId:i[n].id}}).then(()=>{_();let l=u.compId;u.compId=0,u.compId=l})},E=n=>{C.value=n.target.value};return _(),(n,l)=>{var r;return d(),a("div",W,[t("div",S,[t("div",T,[t("input",{type:"text",class:"form-control",placeholder:"위치 검색",onInput:E},null,32)]),t("div",j,[t("table",q,[t("thead",F,[t("tr",null,[t("th",G,[t("button",{class:"btn btn-sm btn-outline-secondary",onClick:l[0]||(l[0]=e=>m.push({}))}," 신규 ")])])]),t("thead",null,[t("tr",null,[H,K,P,J,(r=g.data)!=null&&r.isModal?k("",!0):(d(),a("th",z,"매핑된 회사 수")),O])]),t("tbody",null,[(d(!0),a(b,null,y(U.value,(e,c)=>{var s;return d(),a("tr",null,[t("td",null,f(e.id),1),t("td",null,[p(t("input",{class:v(["form-control text-center",e.editMode?"":"border-0 bg-transparent"]),type:"text",readonly:!e.editMode,"onUpdate:modelValue":o=>e.name=o},null,10,Z),[[h,e.name]])]),t("td",null,[p(t("input",{class:v(["form-control text-center",e.editMode?"":"border-0 bg-transparent"]),type:"text",readonly:!e.editMode,"onUpdate:modelValue":o=>e.latitude=o},null,10,$),[[h,e.latitude]])]),t("td",null,[p(t("input",{class:v(["form-control text-center",e.editMode?"":"border-0 bg-transparent"]),type:"text",readonly:!e.editMode,"onUpdate:modelValue":o=>e.longitude=o},null,10,tt),[[h,e.longitude]])]),t("td",null,[t("div",et,[t("button",ot,f(e.companies),1),t("ul",nt,[(d(!0),a(b,null,y(e.companyList,o=>(d(),a("li",null,[t("a",st,f(o.name),1)]))),256))])])]),(s=g.data)!=null&&s.isModal?(d(),a("td",rt,[e.companyList.findIndex(o=>o.id===Y(u).compId)>-1?(d(),a("span",ut,pt)):(d(),a("button",{key:1,onClick:o=>x(c),class:"btn btn-sm btn-outline-secondary fw-bold"},At,8,ht))])):(d(),a("td",dt,[e.editMode?(d(),a("button",{key:1,class:"btn btn-sm btn-outline-success",onClick:o=>L(c)}," 저장 ",8,it)):(d(),a("div",at,[t("button",{class:"btn btn-sm btn-outline-secondary",onClick:o=>e.editMode=!0}," 수정 ",8,lt),t("button",{class:"btn btn-sm btn-outline-danger",onClick:o=>B(c)}," 삭제 ",8,ct)]))]))])}),256)),(d(!0),a(b,null,y(m,(e,c)=>{var s;return d(),a("tr",null,[gt,t("td",null,[p(t("input",{class:"form-control",type:"text","onUpdate:modelValue":o=>e.name=o},null,8,bt),[[h,e.name]])]),t("td",null,[p(t("input",{class:"form-control",type:"text","onUpdate:modelValue":o=>e.latitude=o},null,8,yt),[[h,e.latitude]])]),t("td",null,[p(t("input",{class:"form-control",type:"text","onUpdate:modelValue":o=>e.longitude=o},null,8,ft),[[h,e.longitude]])]),(s=g.data)!=null&&s.isModal?k("",!0):(d(),a("td",vt,"-")),t("td",Ct,[t("button",{class:"btn btn-sm btn-outline-success",onClick:o=>w(c)}," 저장 ",8,Vt)])])}),256))])])])])])}}};export{Ut as default};
