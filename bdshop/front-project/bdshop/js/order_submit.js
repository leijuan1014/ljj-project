define(["jquery","components","bootstrap","common","timepicker","template","weui"],function(e,t,o,r,n,a,i){function d(){t.getMsg(apiUrl+"/front/receive/receive/queryDefaultReceive").done(function(e){var t=e.res;if(1==t){var o=a("order-address-tpl",e);document.querySelector("#order-address").innerHTML=o}})}function c(){t.getMsg(apiUrl+"/front/payment/payment/queryUserPayment").done(function(e){e.res})}function u(){t.getMsg(apiUrl+"/front/shoppingcart/shoppingcart/queryShoppingCartInfo").done(function(e){var t=e.res;if(1==t){var o=a("order-goods-tpl",e);document.querySelector("#order-goods").innerHTML=o,o=a("order-amount-tpl",e),document.querySelector("#order-amount").innerHTML=o,o=a("order-amount2-tpl",e),document.querySelector("#order-amount2").innerHTML=o,o=a("order-amount3-tpl",e),document.querySelector("#order-amount3").innerHTML=o}})}function l(){function e(e){var t=new Date;t.setDate(t.getDate()+e);var o=t.getFullYear(),r=t.getMonth()+1,n=t.getDate();return o+"-"+r+"-"+n}$("#addSendTime").on("click",function(){function t(e){var t=e.date.getFullYear().toString()+"-"+(e.date.getMonth()+1).toString()+"-"+e.date.getDate().toString();$("#dialogDetail").modal("hide"),$("#showSendTime").val(t),$("#sendTime").val(e.date);var o=$("body");document.title="提交订单";var r=$("");r.on("load",function(){setTimeout(function(){r.off("load").remove()},0)}).appendTo(o)}var o=$("body");document.title="配送时间";var r=$("");r.on("load",function(){setTimeout(function(){r.off("load").remove()},0)}).appendTo(o),$("#dialogDetail").modal({show:!0});var n=e(1);$("#datetimepicker").datetimepicker({format:"yyyy-mm-dd",autoclose:!0,minView:2,weekStart:0,startDate:n}).on("changeDate",t)})}var m=(t.GetQueryString("id"),!1);a.helper("formatPrice",function(e,t){if((e||0==e)&&(e/=100,"true"==t))return e=0==e?"包邮":"¥"+e}),d(),c(),u(),$("#order_submit").click(function(){var e=apiUrl+"/front/order/order/addOrder",o=($("#receiveId").val(),$("#invoiceTag").val());o&&(o="0");var r=$("#order-submit-box").serialize();$(".payStyle").each(function(){$(this).is(":checked")&&(m=!0)}),t.getMsg(e,r,"post").done(function(e){var t=e.res;1==t?window.location.href="/pages/bdshop/page/pay_check.html?orderNumber="+e.result+"&price="+$("#total-money").text():$.toast("出错啦！","text")})}),l()});