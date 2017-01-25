<%@ page pageEncoding="UTF-8" %>
<%@ page
	import="org.apache.shiro.web.filter.authc.FormAuthenticationFilter"%>
<%@ page import="org.apache.shiro.authc.ExcessiveAttemptsException"%>
<%@ page import="org.apache.shiro.authc.IncorrectCredentialsException"%>
<%@ page import="cn.gzjp.framwork.core.web.MediaTypes"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
	String accept = request.getHeader("Accept");
	String contentType = MediaTypes.TEXT_HTML_UTF_8;
	String msg = "您没有权限进行此操作或登录超时！";
	response.setStatus(403);
	if (accept.indexOf("application/json") != -1) {
        
		response.setHeader("Content-Type", MediaTypes.JSON_UTF_8);
		String json = "{\"success\":false,\"msg\":\""+msg+
			"\",\"hasError\":true,\"error\":\""+msg+"\"}";
		out.print(json);
	} else {
		response.setHeader("Content-Type", MediaTypes.TEXT_HTML_UTF_8);
%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>您没有权限进行此操作或登录超时</title>
</head>

<body>
	<h2>您没有权限进行此操作或登录超时</h2>
	<p>
		<a href="${ctx}/<spring:eval expression="@webConf['admin.login']"/>">返回登录页</a>
	</p>
    <p>将在 <span id="mes">5</span> 秒钟后返回首页！</p> 
<script  type="text/javascript"> 
var i = 5; 
var intervalid; 
intervalid = setInterval("fun()", 1000); 
function fun() { 
	if (i == 0) { 
		window.location.href = "${ctx}/<spring:eval expression="@webConf['admin.login']" />"; 
		clearInterval(intervalid); 
	} 
	document.getElementById("mes").innerHTML = i; 
	i--; 
} 
</script> 
</body>

</html>
<%
	}
%>






