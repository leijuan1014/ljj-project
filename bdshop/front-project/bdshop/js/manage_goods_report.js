define(["jquery","template","components","bootstrap","jqueryValidate","paginator","manageCommon"],function(e,t,a,s,o,i,n){function l(){var e=!1,t=!1,s=!1,o=!1,i=!1,n=!1;a.getMsg(apiUrl+"/admin/module/module/queryModules").done(function(a){if(1==a.res)for(var l=a.obj,r=0;r<l.length;r++){var d=l[r],c=d.moduleId;d.parentId;7==c&&($("#goodsList").css("display","block"),e=!0),8==c&&($("#goodsOrder").css("display","block"),e=!0),9==c&&($("#goodsCatory").css("display","block"),e=!0),10==c&&($("#memberManage").css("display","block"),t=!0),11==c&&($("#messageManage").css("display","block"),t=!0),12==c&&($("#articleManage").css("display","block"),s=!0),13==c&&($("#adManage").css("display","block"),o=!0),14==c&&($("#goodsReport").css("display","block"),i=!0),15==c&&($("#orderReport").css("display","block"),i=!0),16==c&&($("#adminManage").css("display","block"),n=!0),17==c&&($("#roleManage").css("display","block"),n=!0),e&&$("#div1").css("display","block"),t&&$("#div2").css("display","block"),s&&$("#div3").css("display","block"),o&&$("#div4").css("display","block"),i&&$("#div5").css("display","block"),n&&$("#div6").css("display","block"),(e||t||s&&o||i||n)&&$(".container-wrap").css("display","block")}})}function r(e){$("#cloading").removeClass("hidden");var t=10,s=$("#form-box").serialize();s=s+"&pageNo="+e+"&pageSize="+t;var o=listUrl;a.getMsg(o,s,"post").done(function(e){var i=e.res;if(1==i){e=e.obj;for(var n=0;n<e.dataList.length;n++)e.dataList[n].unitPrice=e.dataList[n].unitPrice/100,e.dataList[n].detailsAmount=e.dataList[n].detailsAmount/100;d(e);var l={bootstrapMajorVersion:3,currentPage:1,totalPages:1,itemTexts:function(e,t,a){switch(e){case"first":return"首页";case"prev":return"上一页";case"next":return"下一页";case"last":return"末页";case"page":return t}},onPageClicked:function(e,s,i,n){$("#cloading").removeClass("hidden");var l=$("#form-box").serialize();l=l+"&pageNo="+n+"&pageSize="+t,a.getMsg(o,l,"post").done(function(e){d(e.obj)})}};l.totalPages=e.pages,$(".page-sum").html('当前页面总数：<span class="text-333">'+e.pages+'</span> 当前总个数：<span class="text-333">'+e.total+"</span>"),$("#cloading").addClass("hidden"),$("#pagination").bootstrapPaginator(l),$("#pagination li").removeClass("active").eq(s.pageNo-1).addClass("active"),1==l.totalPages?$("#pagination").hide():$("#pagination").show()}else document.getElementById("table-list-tpl").innerHTML="没有数据",$("#cloading").addClass("hidden"),$(".page-sum").html(""),$("#pagination").hide(),document.getElementById("table-list-content").innerHTML="没有数据"})}function d(e){var a=t("table-list-tpl",e);document.getElementById("table-list-content").innerHTML=a,$("#table-list-content").on("click",".detailClass",function(){var e=$(this).attr("oid");g(e)})}function c(){var e=apiUrl+"/admin/order/orderAdmin/getTotalNumAmountByOrderDetailsAdminSearchVO",t=$("#form-box").serialize();a.getMsg(e,t,"post").done(function(e){1==e.res&&(document.getElementById("totalNumAmount").innerHTML=e.obj)});var s=apiUrl+"/admin/order/orderAdmin/getTotalDetailsAmountByOrderDetailsAdminSearchVO";a.getMsg(s,t,"post").done(function(e){1==e.res&&(document.getElementById("totalDetailsAmount").innerHTML=e.obj/100)})}function g(e){if(void 0==e||isNaN(parseInt(e)))return!1;var t=$("#createTime_le").val(),a=$("#createTime_ge").val();if(void 0!=t&&""!=t||(t=(new Date).Format("yyyy-MM-dd 23:59:59")),void 0==a||""==a){var s=new Date;s.setDate(s.getDate()-15),a=s.Format("yyyy-MM-dd hh:mm:ss")}window.location.href="/pages/bdshop/page/manage_goods_report_detail.html?goodsName="+e+"&createTime_le="+t+"&createTime_ge="+a+"&lid=7"}l(),listUrl=apiUrl+"/admin/order/orderAdmin/getPageByOrderDetailsAdminSearchVO",$("#submit-btn").on("click",function(){r(1),c()}),Date.prototype.Format=function(e){var t={"M+":this.getMonth()+1,"d+":this.getDate(),"h+":this.getHours(),"m+":this.getMinutes(),"s+":this.getSeconds(),"q+":Math.floor((this.getMonth()+3)/3),S:this.getMilliseconds()};/(y+)/.test(e)&&(e=e.replace(RegExp.$1,(this.getFullYear()+"").substr(4-RegExp.$1.length)));for(var a in t)new RegExp("("+a+")").test(e)&&(e=e.replace(RegExp.$1,1==RegExp.$1.length?t[a]:("00"+t[a]).substr((""+t[a]).length)));return e},c(),r(1)});