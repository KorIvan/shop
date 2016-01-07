<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${title}</title>
<link href="<spring:url value="/css/bootstrap.css"/>" rel="stylesheet"
	integrity="sha384-1q8mTJOASx8j1Au+a5WDVnPi2lkFfwwEAa8hDDdjZlpLegxhjVME1fgjWPGmkzs7"
	crossorigin="anonymous">
<link href="<spring:url value="/css/menu.css"/>" rel="stylesheet">
		<script src="<spring:url value="js/bootstrap.min.js"/>" integrity="sha384-0mSbJDEHialfmuBBQP6A4Qrprq5OVfW37PRR3j5ELqxss1yVqOtnepnHVP9aJ7xS" crossorigin="anonymous"></script>

</head>
<body>
<%@ include file="header.jsp" %>
<div align="center"><h1>${title}</h1>

	<form:form commandName="product">
		<table>
			<tr>
				<td>${message}</td>
			</tr>
			<tr>
				<td><form:select path="category" id="categories" >
						<form:option value="">Select Category</form:option>
						<form:options items="${categories}" itemValue="id"
							itemLabel="name" />
					</form:select></td>
			</tr>

			<tr>
				<td><input type="submit" value="Submit" id="submit"/></td>
			</tr>
		</table>


	</form:form>
	</div>
	
</body>
</html>