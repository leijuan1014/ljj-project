define(["jquery","components","common","template","weui","swiper"],function(i,e,n,o,t,a){function r(){var i=apiUrl+"/front/goods/goods/getGoodsById?goodsId="+s;e.getMsg(i).done(function(i){if(i=i.obj,""!==i.image){i.image=i.image.split(",");for(var e=0;e<i.image.length;e++)i.image[e]=apiUrlPic+i.image[e]}i.price=i.price/100;var n=o("pro-box-tpl",i);document.getElementById("pro-box").innerHTML=n;new Swiper(".swiper-container",{autoplay:3e3});p()})}function p(){$(".minus").click(function(){var i=$(this).siblings("input"),e=parseInt(i.val());1!==e&&(e-=1,i.val(e))}),$(".plus").click(function(){var i=$(this).siblings("input"),e=parseInt(i.val());e+=1,i.val(e)})}var s=e.GetQueryString("id");o.config("escape",!1),r(),$("#buy-now-btn").attr("href","/pages/bdshop/page/submit_order.html?id="+s)});