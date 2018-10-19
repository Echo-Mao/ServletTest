<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
    String path = request.getContextPath();
			String basePath = request.getScheme() + "://" 
			        + request.getServerName() + ":" + request.getServerPort()
					+ path + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<title>注册</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<script type="text/javascript"
	src="<%=basePath%>jquery-3.3.1/jquery-3.3.1.js"></script>
<script type="text/javascript">
	$(function() {
		$("#registerName").blur(function(){
			//发送AJAX请求
			$.ajax({
				//目的资源地址
				url:"registerName.do",
				//请求发出方式
				type:"post",
				//预计收到的返回的数据格式
				dataType:"json",
				//发送的数据
				data:{
					"registerName":$("#registerName").val()
				},
				//如果请求成功,此处data为服务器返回的数据,并非上文的data
				success:function(data){
					if(data.message){
						//未发现重复记录
						$("#checkMessage").css("color","#006400").html("用户名可用!");
					}else{
						//发现重复记录
						$("#checkMessage").css("color","#FF0000").html("用户名已被注册!");
					}
				},
				error:function(data){
					alter("请求失败!请联系网站管理员.");
				}
			})
		});
	});
</script>
</head>
<body>
	<p>
		<font size="6">注册</font>
	</p>
	<form action="" method="post" id="registerInfo">
		请输入用户名:&nbsp;&nbsp;<input type="text" id="registerName" name="registerName" />
		<font id="checkMessage" size="2"></font> <br />
		请输入密码:&nbsp;&nbsp;&nbsp;&nbsp;<input type="password" id="pressPwd" name="pressPwd" /> <br />
		请再次输入密码:&nbsp;<input type="password" id="repressPwd" name="repressPwd" /> <br />
		<input type="submit" value="确定提交" />
	</form>
</body>
</html>
