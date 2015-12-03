<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Client's addresses</title>
<link href="<spring:url value="/css/bootstrap.css"/>" rel="stylesheet"
	integrity="sha384-1q8mTJOASx8j1Au+a5WDVnPi2lkFfwwEAa8hDDdjZlpLegxhjVME1fgjWPGmkzs7"
	crossorigin="anonymous">
<link href="<spring:url value="/css/menu.css"/>" rel="stylesheet">
</head>
<body>
	<%@ include file="clientNavbar.jsp"%>
	<div align="center">
	<form action="">
		<table>
			<thead>
			<th>№</th>
				<th>Zip code</th>
				<th>Country</th>
				<th>City</th>
				<th>Street</th>
				<th>Building</th>
				<th>Apartment</th>

			</thead>
			<tbody>
				<tr>
					<td>${message}</td>
				</tr>
				<c:forEach items="${addresses}" var="address" begin="0"
					varStatus="i">

					<tr>
						<td><label>${i.index+1}</label>
						<td><input readonly="true" value="${address.zip}" /></td>
						<td><input readOnly="true" value="${address.country}" /></td>
						<td><input readonly="true" value="${address.city}" /></td>
						<td><input readonly="true" value="${address.street}" /></td>
						<td><input readonly="true" value="${address.building}" /></td>
						<td><input readonly="true" value="${address.apartment}"/></td>
					</tr>




				</c:forEach>
			</tbody>
		</table>
		</form>
	</div>
	<script type="text/javascript"
		src='<spring:url value="js/jquery-2.1.4.js"/>'></script>
	<script src="<spring:url value="js/bootstrap.min.js"/>"
		integrity="sha384-0mSbJDEHialfmuBBQP6A4Qrprq5OVfW37PRR3j5ELqxss1yVqOtnepnHVP9aJ7xS"
		crossorigin="anonymous"></script>

</body>
</html>