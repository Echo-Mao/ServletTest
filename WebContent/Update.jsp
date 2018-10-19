<%@page import="com.smg.pojo.News"%>
<%@page import="com.smg.service.implement.ImplementSON"%>
<%@page import="com.smg.service.ServiceOfNews"%>
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
<title>Update</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<script type="text/javascript"
	src="<%=basePath%>My97DatePicker/WdatePicker.js">	
</script>
<%-- <script type="text/javascript"
    src="<%=basePath%>ckeditor5-build-classic/ckeditor.js">
</script> --%>
</head>
<body>
	
	<font color="#F00">${error }</font>
	<form action="updateNewsServlet.do" method="post" enctype="multipart/form-data">
		<table>
		    <tr>
		        <td></td>
		        <td><input type="hidden" name="nId" value="${n.n_id }"></td>
		    </tr>
			<tr>
				<td>新闻编号:</td>
				<td>
				    <input type="text" name="newsId"
                            value="${n.newsId }" />
				</td>
			</tr>
			<tr>
				<td>新闻标题:</td>
				<td>
				    <input type="text" name="newsTitle"
				            value="${n.title }" />
				</td>
			</tr>
			<tr>
				<td>新闻内容:</td>
				<td>
				    <textarea rows="8" cols="20"
				        name="newsContext">${n.n_context }</textarea>
				</td>
			</tr>
			<tr>
				<td>新闻日期:</td>
				<td>
				    <input type="text" name="newsDate" class="Wdate"
					        onclick="WdatePicker()" readonly="readonly"
					        value="${n.news_date }" />
				</td>
			</tr>
			<tr>
				<td>新闻图片:</td>
				<td>
				    <img src=${n.news_img_path } /><br />
				    <input type="file" name="newsImgPath" />
				</td>
			</tr>
		</table>
		<input type="submit" value="确认修改" />
	</form>
</body>
</html>
