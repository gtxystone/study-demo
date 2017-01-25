<%@ page pageEncoding="UTF-8" %>
<link href="${ctx}/static/bui/css/${skin}/bs3/dpl-min.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/static/bui/css/${skin}/bs3/bui-min.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/static/bui/css/${skin}/page-min.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/static/iconfont/css/iconfont.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${ctx}/static/common/js/jquery-1.8.1.min.js"></script>
<script type="text/javascript" src="${ctx}/static/bui/bui-min.js"></script>

<script>
	var ctx = '${pageContext.request.contextPath}';
	BUI.actions = {};
	BUI.setDebug(<spring:eval expression="@webConf['bui.debug']" />);
	//BUI.setDebug(true);
</script>
<script type="text/javascript" src="${ctx}/static/bui/ux/crudgrid.js"></script>
<script type="text/javascript" src="${ctx}/static/bui/ux/savedialog.js"></script>

<style type="text/css">
body{
    background: none repeat scroll 0 0 #F9F9F9 ;  
}
.form-horizontal .control-label {
	width: 85px;
}

.bui-bar .bui-grid-button-bar {
	padding-bottom: 15px;
	/*padding-left: 25px;*/
}

.panel-header {
	background-color: #FBFBFB;
}

.bui-select-list .bui-list-item {
	height: 25px;
	margin: 1px;
	border-bottom: 1px solid #dddddd;
}

/*search tool bar start*/
.search-form{
	background-color: #f5f5f5;
	padding: 12px 0px 0px 0px;
	margin: 0px 0px;
	border: 1px solid #ddd !important;
	margin-bottom: 10px; 
	border-radius: 3px;
	
}
.span_width{
	width:auto;
	}
.puff-left{
	float:left;
	}	
.height_auto{
	}	
/*search tool bar end*/
</style>

<script type="text/javascript">

 $.ajaxSetup({
	complete: function(xhr,status){
		if(xhr.status==403){
			BUI.Message.Alert('您没有权限进行此操作或登录超时！',function(){
				window.location.href='${ctx}/<spring:eval expression="@webConf['admin.login']" />';
			},'error'); 
		}
	}
 });

</script>
