<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="<spring:url value="/css/bootstrap.css"/>" rel="stylesheet"
	integrity="sha384-1q8mTJOASx8j1Au+a5WDVnPi2lkFfwwEAa8hDDdjZlpLegxhjVME1fgjWPGmkzs7"
	crossorigin="anonymous">
	<link href="<spring:url value="/css/menu.css"/>" rel="stylesheet">
	<style type="text/css">
	body{background: red;
	}
	</style>
<title>Access denied</title>
</head>
<body>
	<%@ include file="clientNavbar.jsp"%>
<div align="center">
<h1>Access denied!</h1></div>
	<script type="text/javascript"
		src='<spring:url value="js/jquery-2.1.4.js"/>'></script>
	<script src="<spring:url value="js/bootstrap.min.js"/>"
		integrity="sha384-0mSbJDEHialfmuBBQP6A4Qrprq5OVfW37PRR3j5ELqxss1yVqOtnepnHVP9aJ7xS"
		crossorigin="anonymous"></script>
</body>
</html>