define(["zepto","components","common","weui","touch","template"],function(e,i,t,n,d,s){function o(){i.getMsg(apiUrl+"/front/receive/receive/getReceiveByPage").done(function(e){var i=e.res;if(1==i){var t=s("address-list-tpl",e);document.getElementById("address-list").innerHTML=t,r()}else document.getElementById("address-list").innerHTML='<div class="weui-loadmore weui-loadmore_line"><span class="weui-loadmore__tips">暂无数据</span></div>'})}function a(){$("#address-list").on("click",".delete",function(e){var t=$(this).attr("rid");$.confirm({text:"确定要删除此地址吗？",onOK:function(){i.getMsg(apiUrl+"/front/receive/receive/delReceiveById?receiveId="+t).done(function(e){var i=e.res;1==i&&($.toast("删除成功","text"),o())})}})})}function r(){$("#address-list").on("swipeLeft","li",function(){var e=$(this);e.find(".edit").addClass("hide"),e.find(".delete").removeClass("hide"),event.preventDefault()}),$("#address-list").on("swipeRight","li",function(){var e=$(this);e.find(".edit").removeClass("hide"),e.find(".delete").addClass("hide"),event.preventDefault()}),a()}o(),$("#edit").click(function(){$(".addres").css("display","none"),$(".delete").css("display","none"),$("#address-list").on("click","li",function(){var e=$(this).attr("rid");window.location.href="/pages/bdshop/page/add_address.html?id="+e})})});