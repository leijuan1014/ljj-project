define(["jquery","components","bootstrap","common","template","updown"],function(e,t,o,n,s,a){function i(){$("#search_goods_list").empty(),b.show(),w.hide(),E.val("")}function d(){i(),v.removeClass("weui-search-bar_focusing"),B.show()}function c(e){var t={secondCategoryDataList:[{categoryId:"水蜜桃",categoryName:"水蜜桃"},{categoryId:"小青芒",categoryName:"小青芒"},{categoryId:"黄金鲽鱼",categoryName:"黄金鲽鱼"},{categoryId:"火龙果",categoryName:"火龙果"}]},o=s("productSearch-tpl",t);$("#show_second_category").html(o),$("#show_second_category li[data-type]").click(function(){$("#search_goods_list").empty(),b.hide(),w.show(),L=0,P.categoryId=$(this).attr("data-type"),P.unlock(),P.noData(!1),P.$domDown.html(P.opts.domDown.domLoad),P.loading=!0,P.opts.loadDownFn(P)})}function r(e,o,n){if(1!=I){I=!0;var s="one_edit_"+o,a="one_sub_"+o;1==n&&(s="edit_"+o,a="sub_"+o);var i=$("#"+s).val();i=parseInt(i),(isNaN(i)||i<0)&&(i=0),1==e?i=parseInt(i)+parseInt(1):2==e&&(i=parseInt(i)-parseInt(1)),i>1e4&&(i=1e4);var d=apiUrl+"/front/shoppingcart/shoppingcart/editNumeberToShoppingCart?priceId="+o+"&quantity="+i;t.getMsg(d).done(function(e){I=!1,y();var t=e.res;1==t?(0==i?(document.getElementById(a).style.display="none",document.getElementById(s).style.display="none"):(document.getElementById(a).style.display="block",document.getElementById(s).style.display="block"),$("#"+s).val(i)):alert("直接修改数量操作失败！")})}}function l(e,t){r(e,t,0)}function g(e){r(2,e,1)}function u(e){r(1,e,1)}function m(e){r(3,e,1)}function p(e){var o="show_goods_price_list_"+e,n=document.getElementById(o).style.display;if("none"==n){var a=apiUrl+"/front/goodsPrice/goodsPrice/getGoodsPriceListByGoodsId?goodsId="+e;t.getMsg(a).done(function(t){var n=t.res;if(1==n){for(var a=t.obj,i=0;i<a.length;i++)a[i].retailPrice=a[i].retailPrice/100;t.goodspriceDataList=a;var d=s("show_goods_price_list-tpl",t);$("#"+o).html(d);var c="goodsId_"+e;document.getElementById(c).innerHTML="收起",document.getElementById(o).style.display="";for(var r in a){var l=new Object;l=a[r].priceId,$("#edit_"+l).val(a[r].buyPrice),$("#sub_"+l).click(function(){g(this.id.substring(4))}),$("#add_"+l).click(function(){u(this.id.substring(4))}),$("#edit_"+l).change(function(){m(this.id.substring(5))}),0==a[r].buyPrice&&(document.getElementById("sub_"+l).style.display="none",document.getElementById("edit_"+l).style.display="none")}}else alert("获取产品规格失败！")})}else{document.getElementById(o).style.display="none";var i="goodsId_"+e;document.getElementById(i).innerHTML="选择规格"}}function y(){var e=apiUrl+"/front/shoppingcart/shoppingcart/queryCurrentUserTotalPriceAndCategory";t.getMsg(e).done(function(e){var t=e.res;if(1==t){var o=e.obj;document.getElementById("totalNumber").innerHTML=o.totalPrice/100*1,document.getElementById("totalCategory").innerHTML=o.totalCategory,_()}else alert("获取总价失败！")})}function _(){var e=document.getElementById("totalNumber").innerHTML,t=(100*e-100*settleAccountsPrice)/100;t=parseFloat(t.toFixed(2)),parseInt(-t)==parseInt(settleAccountsPrice)?(document.getElementById("settle_accounts").innerHTML=settleAccountsPrice+"起送",document.getElementById("settle_accounts").removeAttribute("href"),document.getElementById("settle_accounts").style.backgroundColor="#CCC"):t>=0?(document.getElementById("settle_accounts").innerHTML="结算",document.getElementById("settle_accounts").style.backgroundColor="#2CC27B",document.getElementById("settle_accounts").setAttribute("href","/pages/bdshop/page/order_submit.html")):(document.getElementById("settle_accounts").innerHTML="还差"+t*-1+"配送",document.getElementById("settle_accounts").removeAttribute("href"),document.getElementById("settle_accounts").style.backgroundColor="#CCC")}var h=apiUrlPic,I=!1;$(".search_Top").css("height",document.documentElement.clientHeight-44-50),$(".page__bd").css("height",document.documentElement.clientHeight-44);var f,v=$("#searchBar"),b=$("#searchResult"),B=$("#searchText"),E=$("#searchInput"),C=$("#searchClear"),D=$("#searchCancel"),w=$(".search_Top");B.on("click",function(){v.addClass("weui-search-bar_focusing"),E.focus()}),C.on("click",function(){i(),E.focus()}),D.on("click",function(){d(),E.blur()}),$(".title_btn").click(function(){}),E.on("blur",function(){this.value.length||d()}).on("input",function(e){$("#search_goods_list").empty(),this.value.length?(b.hide(),w.show(),$this=$(this),f=e.timeStamp,setTimeout(function(){if(f-e.timeStamp===0){var t=$this.val();L=0,P.categoryId=t,P.unlock(),P.noData(!1),P.$domDown.html(P.opts.domDown.domLoad),P.loading=!0,P.opts.loadDownFn(P)}},1500)):(b.show(),w.hide())}),c("5");var L=0,k=5,P=$(".search_Top").dropload({size:5,categoryId:"0",autoLoad:!1,domDown:{domNoData:'<div class="dropload-noData">没有了~</div>'},loadDownFn:function(e){L++;var t="";$.ajax({type:"GET",url:apiUrl+"/front/goods/goods/getPageFrontVOByGoodsName",data:"pageNo="+L+"&pageSize="+k+"&goodsName="+encodeURIComponent(e.categoryId),dataType:"json",xhrFields:{withCredentials:!0},crossDomain:!0,success:function(o){var n=o.res,a=o.obj.dataList;if(o.goodsDataList=a,1===n&&a.length>0){for(var i=0;i<o.goodsDataList.length;i++){if(""!==o.goodsDataList[i].image){o.goodsDataList[i].image=o.goodsDataList[i].image.split(",");for(var d=0;d<o.goodsDataList[i].image.length;d++)o.goodsDataList[i].image[d]=h+o.goodsDataList[i].image[d]}o.goodsDataList[i].vo_retailPrice=o.goodsDataList[i].vo_retailPrice/100}t=s("search_goods_list_tp1",o)}else e.lock();$("#search_goods_list").append(t),e.resetload();for(var c in a){var r=new Object;r="goodsId_"+a[c].goodsId,$("#"+r).click(function(){p(this.id.substring(8))}),$("#one_edit_"+a[c].vo_priceId).val(a[c].vo_shoppingCartNum),$("#one_sub_"+a[c].vo_priceId).click(function(){l(2,this.id.substring(8))}),$("#one_add_"+a[c].vo_priceId).click(function(){l(1,this.id.substring(8))}),$("#one_edit_"+a[c].vo_priceId).change(function(){l(3,this.id.substring(9))}),1==a[c].vo_countGoodsPrice&&(document.getElementById("goodsId_"+a[c].goodsId).style.display="none",0==a[c].vo_shoppingCartNum?document.getElementById("one_add_"+a[c].vo_priceId).style.display="block":(document.getElementById("one_sub_"+a[c].vo_priceId).style.display="block",document.getElementById("one_edit_"+a[c].vo_priceId).style.display="block",document.getElementById("one_add_"+a[c].vo_priceId).style.display="block"))}},error:function(t,o){e.resetload()}})}});$("#shoppingCartDetail").on("click",function(e){e.stopPropagation(),window.location.href="/pages/bdshop/page/myShoppingCart.html"}),y()});