<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns:th="http://www.thymeleaf.org" xmlns:tiles="http://www.thymeleaf.org">
<head>
<meta charset="utf-8">
<link href="<spring:url value="/css/bootstrap.css"/>" rel="stylesheet"
	integrity="sha384-1q8mTJOASx8j1Au+a5WDVnPi2lkFfwwEAa8hDDdjZlpLegxhjVME1fgjWPGmkzs7"
	crossorigin="anonymous">

<link href="<spring:url value="/css/registration.css"/>"
	rel="stylesheet">

<title>Authorization</title>
<link type="text/css" rel="stylesheet" href="css/mycss.css">
</head>
<body>
<%@ include file="clientNavbar.jsp" %>

	<div align="center">
<h1>Authentication</h1>
<div class="message" align="center">

	${message}</div>

<div align="center">
		<div tiles:fragment="content">
        <form name="f" th:action="@{/login}" method="post">               
            <fieldset>
                <legend>Please Login</legend>
                <div th:if="${param.error}" class="alert alert-error">    
                    Invalid username and password.
                </div>
                <div th:if="${param.logout}" class="alert alert-success"> 
                    You have been logged out.
                </div>
                <label for="username">Username</label>
                <input type="text" id="username" name="username"/>        
                <label for="password">Password</label>
                <input type="password" id="password" name="password"/>    
                <div class="form-actions">
                    <button type="submit" class="btn">Log in</button>
                </div>
            </fieldset>
        </form>
    </div>
	</div>
		<script	src='<spring:url value="js/jquery-2.1.4.js"/>'></script>
	
	<script src="<spring:url value="js/bootstrap.min.js"/>" integrity="sha384-0mSbJDEHialfmuBBQP6A4Qrprq5OVfW37PRR3j5ELqxss1yVqOtnepnHVP9aJ7xS" crossorigin="anonymous"></script>
	
	<script type="text/javascript">
		function validate(form) {
			var email = form.email.value;

			var password = form.password.value;
			if (email == null || email == "" || password == null
					|| password == "") {
				alert("Fields must be filled out");
				return false;
			}
			var pattern = /^\w+@[a-zA-Z_]+?\.[a-zA-Z]{2,3}$/;
			if (!email.match(pattern)) {
				alert("Login does not match pattern!");
			}
			return true;
		}
	</script>

	<script type="text/javascript"
		src="js-plugin/jquery/1.8.3/jquery.min.js"></script>
	<script type="text/javascript"
		src="js-plugin/jquery-ui/jquery-ui-1.8.23.custom.min.js"></script>
	<!-- third party plugins  -->
	<script type="text/javascript" src="bootstrap/js/bootstrap.js"></script>
	<script type="text/javascript"
		src="js-plugin/easing/jquery.easing.1.3.js"></script>


	<!-- form -->
	<script type="text/javascript"
		src="js-plugin/neko-contact-ajax-plugin/js/jquery.form.js"></script>
	<script type="text/javascript"
		src="js-plugin/neko-contact-ajax-plugin/js/jquery.validate.min.js"></script>

	<!-- Custom  -->
	<script type="text/javascript" src="js/custom.js"></script>

	<!-- 	<script type="text/javascript" src="js/admin.js"></script> -->


</body>
</html>

</html>