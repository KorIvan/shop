<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Client</title>
</head>
<body>
<a href="<spring:url value="/cart.html"/>">Open cart</a>
<a href="<spring:url value="/editClientParams.html"/>">Edit personal information</a>
<a href="<spring:url value="/addAddress.html"/>">Add new address</a>
<a href="<spring:url value="/makeOrder.html"/>">Make order</a>

<a href="<spring:url value="/logout.html"/>">Log out</a>

</body>
</html>