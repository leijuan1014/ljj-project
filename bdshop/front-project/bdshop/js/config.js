var apiUrl="http://192.168.1.103:8080/xiss",listUrl="",apiUrlPic="http://192.168.1.103:90/img/bdshop",settleAccountsPrice=.01;("Microsoft Internet Explorer"==navigator.appName&&"MSIE6.0"==navigator.appVersion.split(";")[1].replace(/[ ]/g,"")||"Microsoft Internet Explorer"==navigator.appName&&"MSIE7.0"==navigator.appVersion.split(";")[1].replace(/[ ]/g,"")||"Microsoft Internet Explorer"==navigator.appName&&"MSIE8.0"==navigator.appVersion.split(";")[1].replace(/[ ]/g,"")||"Microsoft Internet Explorer"==navigator.appName&&"MSIE9.0"==navigator.appVersion.split(";")[1].replace(/[ ]/g,""))&&(alert("您的浏览器版本过低，请下载IE9以上版本或使用其他浏览器"),window.close()),requirejs.config({paths:{common:"/pages/bdshop/js/common",membersCommon:"/pages/bdshop/js/members_common",manageCommon:"/pages/bdshop/js/manage_common",components:"/pages/bdshop/js/components",jquery:"/pages/bdshop/libs/jquery-1.11.1.min",jqueryCookie:"/pages/bdshop/libs/jquery.cookie",bootstrap:"/pages/bdshop/libs/bootstrap-3.3.5-dist/js/bootstrap.min",cityselect:"/pages/bdshop/libs/jquery.cityselect",paginator:"/pages/bdshop/libs/bootstrap-paginator.min",ZeroClipboard:"/pages/bdshop/libs/utf8-jsp/third-party/zeroclipboard/ZeroClipboard",ueditorConfig:"/pages/bdshop/libs/utf8-jsp/ueditor.config",ueditorAll:"/pages/bdshop/libs/utf8-jsp/uemy",bdlang:"/pages/bdshop/libs/utf8-jsp/lang/zh-cn/zh-cn",template:"/pages/bdshop/libs/template",jqueryValidate:"/pages/bdshop/libs/jquery.validate.min",timepicker:"/pages/bdshop/libs/bootstrap-datetimepicker.min",swiper:"/pages/bdshop/libs/swiper.jquery.min",easings:"/pages/bdshop/libs/jquery.easings.min",scrolloverflow:"/pages/bdshop/libs/scrolloverflow.min",fullPage:"/pages/bdshop/libs/jquery.fullPage.min",weui:"/pages/bdshop/libs/jquery-weui.min",cityPicker:"/pages/bdshop/libs/city-picker.min",zepto:"/pages/bdshop/libs/zepto.min",event:"/pages/bdshop/libs/event",touch:"/pages/bdshop/libs/touch",fastclick:"/pages/bdshop/libs/fastclick",highcharts:"/pages/bdshop/libs/highcharts",hammer:"/pages/bdshop/libs/hammer.min",updown:"/pages/bdshop/libs/updown",lazyImg:"/pages/bdshop/libs/lazyimg"},shim:{paginator:["jquery"],common:["jquery"],components:["jquery"],bootstrap:["jquery"],swiper:["jquery"],weui:["jquery"],easings:["jquery"],fullPage:["jquery"],event:["zepto"],touch:["zepto"]}}),define(["jquery"],function(e){var s=$("#script").attr("require-module");void 0!==s&&""!==s&&require([s])});