<!DOCTYPE html>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html>
<head>
<%@include file="./common/import-tags.jsp"%>
<title><spring:eval expression="@webConf['admin.title']" /></title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%@include file="./common/import-static.jsp"%>
<style type="text/css">
body {
	background: #eee
}

#loginForm {
	
}

.loginBox {
	background: -moz-linear-gradient(center top, #FFFFFF, #EFEFEF 8%) repeat
		scroll 0 0 rgba(0, 0, 0, 0);
	border: 1px solid #FFFFFF;
	border-radius: 8px;
	box-shadow: 0 0 15px #222222;
	color: #000000;
	font: 11.5px/1.5em 'Microsoft YaHei';
	height: 280px;
	left: 50%;
	margin-left: -210px;
	margin-top: -165px;
	padding: 0 0px;
	position: absolute;
	top: 50%;
	width: 420px;
	background: #ffffff;
	border-color: #acd5ff;
	/* 	background-color: #e6f2ff; */
}



#submitCode {
	width: 40px;
}

#loginForm {
	padding-left: 24px;
}


</style>
</head>
<body>
	<div class="loginBox ">
		<fieldset>
			<legend style="text-align: center">用户登录</legend>
			<form class="form-horizontal"
				id="loginForm" method="post" action="${ctx}/admin/loginForm">
				<div class="row">
					<div class="control-group ">
						<label class="control-label">用户名：</label>
						<div class="controls">
							<input type="text" 
								data-rules="{required : true}" value="${loginName}"
								data-messages="{required:'用户名不能为空'}" name="username" data-tip="{text:'请输入用户名'}">
						</div>
					</div>
				</div>
				<div class="row">
					<div class="control-group ">
						<label class="control-label">密 码：</label>
						<div class="controls">
							<input type="password"
								data-rules="{required : true}"
								data-messages="{required:'密码不能为空'}" name="password" data-tip="{text:'请输入密码'}">
						</div>
					</div>
				</div>
				<div class="row">
					<div class="control-group">
						<label class="control-label">验证码：</label>
						<div class="controls">
							<input type="text" class="span2"
								data-rules="{required : true}"
								data-messages="{required:'请输入验证码'}" name="code" data-tip="{text:'验证码'}">
							<img src="${ctx}/kaptcha" style="width:100px;height:36px;" id="code" alt="看不清楚？换一张" title="看不清楚？换一张"/>
						</div>
					</div>
				</div>
				<div class="row"> 
					<div class="control-group">
                        <label class="control-label">&nbsp;</label>
						<div class="controls" >
                          <span id="rememberMe">
							<input name="rememberMe"
								type="checkbox" value="true"/>七天内免登录</span>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="offset3 ">
						<button class="button button-primary" id="loginBtn" type='submit'>登录</button>
						&nbsp;
						<button class="button" type="reset">重置</button>
					</div>
				</div>
				<div id="errorDiv" style="display: none">
					<span class="tips tips-warning tips-small"></span>
				</div>
			</form>
		</fieldset>

	</div>
	<script type="text/javascript">
		BUI.use([ 'bui/form', 'bui/tooltip' ], function(Form, Tooltip) {
			var loginForm = new Form.Form({
				srcNode : '#loginForm',
				submitType : 'ajax',
				action : ctx + '/admin/loginForm',
				method : 'post',
				callback : function(data) {
					if (data.success) {
						location.href = ctx + '/admin/home';
					} else {
						$('#errorDiv span').text(data.msg);
						$('#errorDiv').show();
						changeCode();
						hide();
					}
				}
			}).render();
			
			$('#code').click(function(){
				changeCode();
			});
			
			function changeCode(){
				var time = new Date().getTime();
				$('#code').attr('src','${ctx}/kaptcha?_t='+time);
			}
			
		      //使用模板右边显示
		      new Tooltip.Tip({
		        trigger : '#rememberMe',
		        alignType : 'right',
		        offset : 10,
		        title : '右边的提示信息',
		        elCls : 'tips tips-warning', 
		        titleTpl : '<span class="x-icon x-icon-small x-icon-error"><i class="icon icon-white icon-bell"></i></span>\
		        <div class="tips-content">为了您的信息安全，请不要在网吧或公用电脑上使用此功能！</div>'
		      }).render();
		      
		});
		function hide() {
			setTimeout(function() {
				$('#errorDiv span').text('');
				$('#errorDiv').hide();
			}, 3000);
		}
		
		if (window != top) {
			top.location.href = location.href;
		}
	</script>
</body>
</html>
