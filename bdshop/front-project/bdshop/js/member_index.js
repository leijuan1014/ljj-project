define(["jquery","components","common","template","weui"],function(e,i,t,a,o){function s(){i.getMsg(apiUrl+"/front/user/user/getUserById").done(function(e){var i=e.res;if(1==i){e=e.obj;var t=a("user-info-tpl",e);$("#user-info").html(t)}})}s(),$("#index-menu li").eq(2).addClass("active");var d=$("#order-box"),r=$("#weui-loadmore"),n=$("#weui-none"),g={page:0,size:10,cid:"",isLoading:!1,getMore:function(e){g.isLoading||(e?(g.page=1,n.hide(),$("#newListBox").html("")):g.page+=1,r.show(),g.isLoading=!0,setTimeout(g.d(g.page,g.size),2e3))},d:function(e,t){var o;o={pageNo:e,pageSize:t},i.getMsg(apiUrl+"/front/order/order/getOrderByPage?pageSize=4",o).done(function(e){var i=e.res;if(0!==i){e=e.obj;for(var t=0;t<e.dataList.length;t++)e.dataList[t].goodsName=e.dataList[t].orderDetailsList[0].goodsName,e.dataList[t].orderDetailsList[0].image=e.dataList[t].orderDetailsList[0].image.split(","),e.dataList[t].image=apiUrlPic+e.dataList[t].orderDetailsList[0].image[0],e.dataList[t].totalAmount=e.dataList[t].totalAmount/100;var o=e.dataList,s=a("order-box-tpl",e);o&&o.length>0?(g.isLoading=!1,n.hide()):(g.isLoading=!0,n.show()),g.page>1?d.append(s):d.html(s),r.hide()}else n.show(),r.hide()})}};$(window).scroll(function(){$(document).scrollTop()+$(window).height()+100>=$(document).height()&&g.getMore(!1)}),g.getMore(!0)});