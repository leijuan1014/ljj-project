define(["jquery","components","timepicker","bootstrap","common","weui","","template"],function(t,e,a,n,r,o,i){function g(t){var e=t.date.getFullYear().toString()+"-"+(t.date.getMonth()+1).toString()+"-"+t.date.getDate().toString();c(u,e)}function c(t,e){history.go(-1)}function m(t){var e=new Date;e.setDate(e.getDate()+t);var a=e.getFullYear(),n=e.getMonth()+1,r=e.getDate();return a+"-"+n+"-"+r}var u=e.GetQueryString("id"),d=m(1);$("#datetimepicker").datetimepicker({format:"yyyy-mm-dd",autoclose:!0,minView:2,weekStart:0,startDate:d}).on("changeDate",g)});