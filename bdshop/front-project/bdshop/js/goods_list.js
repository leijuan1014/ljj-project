define(["jquery","components","common","template"],function(a,e,i,t){$("#index-menu li").eq(1).addClass("active");var o="http://localhost:8090/hongbao",d=$("#product-list"),s=$("#noRec"),n=$("#cloading"),g={page:0,size:10,isLoading:!1,url:o+"/front/goods/goods/getGoodsByPage",getMore:function(a){g.isLoading||(a?(g.page=1,$("#noRec").hide(),d.html("")):g.page+=1,$("#cloading").show(),g.isLoading=!0,setTimeout(g.d(g.page,g.size),1e3))},d:function(a,e){$.ajax({url:g.url,data:"pageNo="+a+"&pageSize="+e,type:"GET",xhrFields:{withCredentials:!0},crossDomain:!0,dataType:"json"}).done(function(a){var e=a.res;if(0!==e){var i=a.obj.dataList;a=a.obj;for(var l=0;l<a.dataList.length;l++){if(""!==a.dataList[l].image){a.dataList[l].image=a.dataList[l].image.split(",");for(var r=0;r<a.dataList[l].image.length;r++)a.dataList[l].image[r]=o+a.dataList[l].image[r]}a.dataList[l].price=a.dataList[l].price/100}i=a.dataList;var c=t("product-list-tpl",a);i&&i.length>0?(g.isLoading=!1,s.hide()):(g.isLoading=!0,s.show()),g.page>1?d.append(c):d.html(c),n.hide()}else s.show(),n.hide()})}};$(window).scroll(function(){$(document).scrollTop()+$(window).height()+100>=$(document).height()&&g.getMore(!1)}),g.getMore(!0)});