<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Manager</title>
</head>
<body>
<a href="<spring:url value="/manager/category.html"/>">Create product</a>
<a href="<spring:url value="/manager/addCategory.html"/>">Create Category</a>
<a href="<spring:url value="/manager/logout.html"/>">Log out</a>

<div>${message}</div>
</body>
</html>