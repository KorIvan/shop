<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Client</title>
<link href="<spring:url value="/css/menu.css"/>" rel="stylesheet">

</head>
<body>
	<table>
		<tr>
			<td><a href="<spring:url value="/cart.html"/>">Open cart</a></td>
			<td></td>
			<td><a href="<spring:url value="/catalog.html"/>">Catalog</a></td>
		</tr>
		<tr>
			<td><a href="<spring:url value="/editClientParams.html"/>">Edit
					personal information</a></td>
			<td></td>
			<td><a href="<spring:url value="/addAddress.html"/>">Add new
					address</a></td>
		</tr>
		<tr>
			<td><a href="<spring:url value="/makeOrder.html"/>">Make
					order</a></td>
			<td></td>
			<td><a href="<spring:url value="/logout.html"/>">Log out</a></td>
		</tr>
	</table>

</body>
</html>