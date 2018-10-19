<%@page import="java.net.URLDecoder"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
    String path = request.getContextPath();
	String basePath = request.getScheme() + "://" 
			+ request.getServerName() + ":" 
	        + request.getServerPort() + path + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<title>Login</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<script type="text/javascript">
	function autoLogin() {
		document.getElementById("loginForm").submit;
	}
</script>
</head>
<body onload="autoLogin()">
	
	<font color="#F00">${URLEncoder.decode(param.error, 'utf-8')}</font>
	
	<form id="loginForm" action="loginServlet.do" method="post">
		UserName:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <input
			type="text" name="usrName"
			value="${URLEncoder.decode(cookie.nameCookie.value, 'utf-8')}" />
			<br /> 
	    UserPassword:&nbsp;&nbsp;<input type="password" name="usrPwd"
	        value="${cookie.pwdCookie.value }" />
	        <br />
	    <input type="checkbox" name="rembMe" value="yes" />记住我<br />
	    <input type="submit" value="登录" /> <br />
	    <p><font>没有账号?<a href="SignUp.jsp">>>立即注册</a></font></p>
	</form>
</body>
</html>
