define(["jquery","components","bootstrap","manageCommon"],function(s,l,a,i){function o(){var s=!1,a=!1,i=!1,o=!1,d=!1,e=!1;l.getMsg(apiUrl+"/admin/module/module/queryModules").done(function(l){if(1==l.res)for(var c=l.obj,t=0;t<c.length;t++){var n=c[t],r=n.moduleId;n.parentId;7==r&&($("#goodsList").css("display","block"),s=!0),8==r&&($("#goodsOrder").css("display","block"),s=!0),9==r&&($("#goodsCatory").css("display","block"),s=!0),10==r&&($("#memberManage").css("display","block"),a=!0),11==r&&($("#messageManage").css("display","block"),a=!0),12==r&&($("#articleManage").css("display","block"),i=!0),13==r&&($("#adManage").css("display","block"),o=!0),14==r&&($("#goodsReport").css("display","block"),d=!0),15==r&&($("#orderReport").css("display","block"),d=!0),16==r&&($("#adminManage").css("display","block"),e=!0),17==r&&($("#roleManage").css("display","block"),e=!0),s&&$("#div1").css("display","block"),a&&$("#div2").css("display","block"),i&&$("#div3").css("display","block"),o&&$("#div4").css("display","block"),d&&$("#div5").css("display","block"),e&&$("#div6").css("display","block"),(s||a||i&&o||d||e)&&$(".container-wrap").css("display","block")}})}function d(){$("#table-list-content").on("click",".del-btn",function(){var s=apiUrl+"/admin/article/articleAdmin/delArticleById?articleId="+$(this).attr("god");$("#del-modal").modal("show"),$("#del-modal #name").html('确定是否删除： "'+$($(this).parent("td").siblings()[1]).text().trim()+'" 文章'),$("#submit-btn").click(function(){l.getMsg(s).done(function(s){1==s.res?(l.Alert("success","删除成功"),location.reload(),$("#del-modal").modal("hide")):l.Alert("","删除失败")})})})}o(),listUrl=apiUrl+"/admin/article/articleAdmin/getArticleByPage",require(["./table"]),d()});