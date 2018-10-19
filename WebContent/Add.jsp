<%@page import="com.smg.pojo.UserInfo"%>
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
<title>AddNews</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<script type="text/javascript"
	src="<%=basePath%>My97DatePicker/WdatePicker.js">
</script>
</head>
<body>
	
	<p>当前用户为<font size="4" color="#00F">${loginInfo.usrName }</font></p>
	<form action="addNewsServlet.do" method="post" enctype="multipart/form-data">
		<table>
			<tr>
				<td>新闻编号:</td>
				<td><input type="text" name="newsId" /></td>
			</tr>
			<tr>
				<td>新闻标题:</td>
				<td><input type="text" name="newsTitle" /></td>
			</tr>
			<tr>
				<td>新闻内容:</td>
				<td>
				    <textarea rows="8" cols="20" name="newsContext"></textarea>
				</td>
			</tr>
			<tr>
				<td>新闻日期:</td>
				<td>
				    <input type="text" name="newsDate" class="Wdate"
				        onclick="WdatePicker()" readonly="readonly" />
				</td>
			</tr>
			<tr>
				<td>新闻图片:</td>
				<td><input type="file" name="newsImgPath" value="" /></td>
			</tr>
		</table>
		<input type="submit" value="提交" /> <input type="reset" value="重置" />
	</form>
</body>
</html>