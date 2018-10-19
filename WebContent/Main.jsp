<%@page import="com.smg.pojo.Paging"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.smg.pojo.Search"%>
<%@page import="com.smg.service.implement.ImplementSON"%>
<%@page import="com.smg.service.ServiceOfNews"%>
<%@page import="com.smg.pojo.UserInfo"%>
<%@page import="com.smg.pojo.News"%>
<%@page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String path = request.getContextPath();
			String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
					+ path + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<title>Main</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<script type="text/javascript">
	function checkAllBox(obj) {
		var subChecks = document.getElementsByName("subCheck");
		for (var i = 0; i < subChecks.length; i++) {
			subChecks[i].checked = obj.checked;
		}
	}
</script>
<script type="text/javascript"
	src="<%=basePath%>My97DatePicker/WdatePicker.js">
	
</script>
</head>
<body>

	<div align="center">
		<font size="4" color="#00F">${loginInfo.usrName }</font>,欢迎登录系统!
	</div>
	<!-- 条件搜索栏 -->
	<div align="center">
		<form action="toNewsListServlet.do" method="post">
			标题:<input type="text" name="sTitle" value="${paging.search.sTitle }" />
			开始时间:<input type="text" name="sStartDate" class="Wdate"
				onclick="WdatePicker()" readonly="readonly"
				value="<fmt:formatDate value='${paging.search.sStartDate }' pattern='yyyy-MM-dd'/>" />
			结束时间:<input type="text" name="sEndDate" class="Wdate"
				onclick="WdatePicker()" readonly="readonly"
				value="<fmt:formatDate value='${paging.search.sEndDate }' pattern='yyyy-MM-dd'/>" />
			<input type="submit" value="搜索" />
		</form>
	</div>
	<!-- 显示主表 -->
	<table align="center" cellpadding="5px" cellspacing="0" border="1px">
		<tr align="center">
			<td><input type="checkbox" id="allCheck"
				onclick="checkAllBox(this)" />全选</td>
			<td>新闻编号</td>
			<td>新闻标题</td>
			<td>新闻内容</td>
			<td>新闻作者</td>
			<td>新闻日期</td>
			<td>新闻图片</td>
			<td>删除新闻</td>
			<td>修改新闻</td>
		</tr>
		<c:forEach items="${news }" var="n">
			<tr align="center">
				<td><input type="checkbox" name="subCheck"
					value="${n.n_id }" /></td>
				<td>${n.news_id }</td>
				<td>${n.title }</td>
				<td>${n.n_context }</td>
				<td>${n.author }</td>
				<td>${n.news_date }</td>
				<td>${n.news_img_path }</td>
				<td><a href="delNewsServlet.do?n_id=${n.n_id }"
					onclick="return confirm('即将删除该条新闻,您确定吗?')">删除</a></td>
				<td><a href="toUpdateNewsServlet.do?n_id=${n.n_id }">修改</a></td>
			</tr>
		</c:forEach>
	</table>
    <!-- URL -->
    <c:url value="toNewsListServlet.do" var="newsURL" scope="request">
        <c:param name="sTitle"></c:param>
        <c:param name="sStartDate">
           <fmt:formatDate value="${paging.search.sStartDate }" pattern="yyyy-MM-dd" />
        </c:param>
        <c:param name="sEndDate">
           <fmt:formatDate value="${paging.search.sEndDate }" pattern="yyyy-MM-dd" />
        </c:param>
    </c:url>
    <!-- 当前页 -->
	<div align="center">
		<a href="${newsURL }&pg=1">首页</a>
		<c:choose>
		  <c:when test="${paging.page==1 }">上一页</c:when>
		  <c:otherwise>
		      <a href="${newsURL }&pg=${paging.page-1 }">上一页</a>
		  </c:otherwise>
		</c:choose>
		<c:choose>
          <c:when test="${paging.page==paging.pagesCount }">下一页</c:when>
          <c:otherwise>
              <a href="${newsURL }&pg=${paging.page+1 }">下一页</a>
          </c:otherwise>
        </c:choose>
        <a href="${newsURL }&pg=${pageing.pagesCount }"></a>
        <span>第${paging.page }/${paging.pagesCount }页</span>
	</div>
	<div>&nbsp;</div>
	<div align="center">
		<a href="Add.jsp"><input type="button" value="添加新闻" /></a>
		<button name="deleteAll">批量删除</button>
	</div>
</body>
</html>
