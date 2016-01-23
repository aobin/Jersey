<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>查询商品列表</title>
</head>
<body>
	<form action="${pageContext.request.contextPath}/items/getUser">
		<table>
			<tr>
				<td>user_name:<input type="text" name="username"></td>
				<td>memcacheName: <input type="text" value="${memcacheName}" />
				</td>
			</tr>
			<tr>
				<td><input type="submit" value="获取用户信息" /></td>
			</tr>
		</table>
	</form>

	<form action="${pageContext.request.contextPath}/items/getAllItems"
		method="post">
		查询条件：
		<table width="100%" border=1>
			<tr>
				<td><input type="text" value="${resultNumber}"></td>
				<td><input type="submit" value="查询所有商品" /></td>
			</tr>
		</table>
	</form>



</body>

</html>