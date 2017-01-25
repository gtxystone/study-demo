<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="cn.gzjp.weixin.auth.dao.UserInfoDao"%>
<%
UserInfoDao dao = new UserInfoDao();
String openid = request.getParameter("openid"); 
String phone = dao.queryPhone(openid);
String backUrl = request.getParameter("backUrl");
System.out.println(backUrl);
if(backUrl==null){
	backUrl = "";
}
if(phone!=null){
	response.sendRedirect("./success.jsp?ifAuth=true&backUrl="+backUrl);
}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0" name="viewport" />
<title>用户认证</title>
<link href="css/main.css" type="text/css" rel="stylesheet"/>
<link href="css/style.css" type="text/css" rel="stylesheet" />
<script src="js/jquery-1.9.1.min.js" type="text/javascript"></script>
</head>

<body>
<div class="banner"><img src="img/banner1.jpg" /></div>
<div class="main">
<div class="message"><p>请输入您的联通手机号码进行绑定，绑定成功后，返回主界面即可体验中国联通客服官方微信的各种有趣活动啦。</p></div>
<div class="message"><input type="text" class="in_text" value="请输入您的手机号码"  id="phone"/></div>
<div class="message"><a id="stime">点击发送验证码</a><a id="timer" style="display:none; background-color:#09C;">60秒后重试</a>
<input id="smsCode" type="text" class="in_text" value="请输入短信验证码"  id="validation" style="float:left; width:40%; margin-left:2%;"/>
</div>
<div class="message_bth">
<div style="display:none;" id="msg"><p></p></div>
<div class="bth_all"><a id="submit">确认绑定</a></div>
</div>

</div>
<script type="text/javascript">
//倒计时函数
var timer;
var maxtime = 59; // 指定倒计时时间，单位为秒
function CountDown() {
	times = Math.floor(maxtime / 3600);
	minutes = Math.floor((maxtime - times * 3600) / 60);
	seconds = Math.floor((maxtime - times * 3600 - minutes * 60) % 60);
	if (String(minutes).length < 2)
		minutes = "0" + minutes;
	if (String(seconds).length < 2)
		seconds = "0" + seconds;
	msg = /* minutes+":"+ */seconds + "" + "秒后重试";
	document.getElementById("timer").innerHTML = msg;
	if (maxtime == 0) {
		clearInterval(timer);
		$("#stime").show();
		$("#timer").hide();
		return false;
	}
	maxtime--;
}
//

// 记录输入的文字
function words_deal() {
	var curLength = $("#TextArea1").val().length;
	if (curLength > 800) {
		var num = $("#TextArea1").val().substr(0, 5);
		$("#TextArea1").val(num);
		alert("超过字数限制，多出的字将被截断！");
	} else {
		$("#textCount").text(800 - $("#TextArea1").val().length);
	}
}

//验证手机号码
function checkMobile(phone){ 
    if(!(/^1(3[0-2]|5[56]|8[56]|45)\d{8}$/.test(phone))){
        return false; 
    }else{
    	return true;
    }
}
//添加一个获取URL里的参数的方法

function getQueryString(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
    var r = window.location.search.substr(1).match(reg);
    if (r != null) {
        return unescape(r[2]);
    }
    return null;
};

var openid = getQueryString("openid");

$(document).ready(function() {
	
	// 点击引导按钮之后回复透明，同时开始倒计时游戏
	$("#stime").click(function() {
		$('#msg').hide();
		var phone = $('#phone').val();
		if(!checkMobile(phone)){
			$('#msg').html('<p>请输入正确的联通手机号码</p>');
			$('#msg').show();
			return false;
		}
		$("#stime").hide();
		$("#timer").show();
		maxtime = 59;
		
		$.getJSON('./sendSms',{phone:phone,openid:openid},function(data){
			if(data.success){
				$('#msg').html('<p>验证码短信已发送。</p>');
				$('#msg').show();
			}else{
				$('#msg').html('<p>'+data.msg+'</p>');
				$('#msg').show();
			}
		});
		timer = setInterval('CountDown()', 1000);
	});
	// 显示隐藏输入框文字
	$('.in_text').bind({
		focus : function() {
			if (this.value == this.defaultValue) {
				this.value = "";
			}
		},
		blur : function() {
			if (this.value == "") {
				this.value = this.defaultValue;
			}
		}
	});
	$("#submit").click(function() {
		var smsCode = $("#smsCode").val();
		if (!smsCode || smsCode.length!=4) {
			$('#msg').html('<p>请输入正确的短信验证码。</p>');
			$('#msg').show();
			return false;
		} else {
			$.getJSON('./checkSms',{smsCode:smsCode,openid:openid},function(data){
				if(data.success){
					location.href = 'success.jsp?backUrl=<%=backUrl%>';
				}else{
					$('#msg').html('<p>'+data.msg+'</p>');
					$('#msg').show();
				}
			});
			return true;
		}
	});
});
</script>
</body>
</html>