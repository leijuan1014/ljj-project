define(["jquery","components","common","template"],function(e,i,o,t){var a=$("#article-list"),n=$("#noRec"),d=$("#cloading"),r={page:0,size:15,isLoading:!1,url:apiUrl+"/front/article/article/getArticleByPage",getMore:function(e){r.isLoading||(e?(r.page=1,$("#noRec").hide(),a.html("")):r.page+=1,d.show(),r.isLoading=!0,setTimeout(r.d(r.page,r.size),1e3))},d:function(e,i){$.ajax({url:r.url,data:"pageNo="+e+"&pageSize="+i,type:"GET",xhrFields:{withCredentials:!0},crossDomain:!0,dataType:"json"}).done(function(e){var i=e.res;if(0!==i){var o=e.obj.dataList;e=e.obj,o=e.dataList;var s=t("product-list-tpl",e);o&&o.length>0?(r.isLoading=!1,n.hide()):(r.isLoading=!0,n.show()),r.page>1?a.append(s):a.html(s),d.hide()}else n.show(),d.hide()})}};$(window).scroll(function(){var e=$(document).scrollTop()+$(window).height()+100,i=$(document).height();e>=i&&r.getMore(!1)}),r.getMore(!0)});