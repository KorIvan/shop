<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form:form commandName="orders">
	<table>
		<tr>
			<td>${message}</td>
		</tr>
		<tr>
			<c:forEach items="${orders.orderItems}" var="order" begin="0" varStatus="i">
				<tr>
					<td><input value="orderItems[${i.index}].orderItem.amount" /></td>
				</tr>

			</c:forEach>
		</tr>
		</table>
	</form:form>
</body>
</html>