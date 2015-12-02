<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Manager</title>
<link href="<spring:url value="/css/menu.css"/>" rel="stylesheet">

</head>
<body>
	<table>
		<tr>
			<td><a href="<spring:url value="/manager/category.html"/>">Create
					product</a></td>
			<td><a href="<spring:url value="/manager/addCategory.html"/>">Create
					Category</a></td>
		</tr>
		<tr>
			<td><a href="<spring:url value="/manager/orders.html"/>">View
					orders</a></td>
			<td><a href="<spring:url value="/manager/logout.html"/>">Log
					out</a></td>
		</tr>


	</table>

	<div>${message}</div>
</body>
</html>