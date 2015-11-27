<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><c:out value="${title}" /></title>
<link href="<spring:url value="css/registration.css"/>" rel="stylesheet">

</head>
<body>
	<h1>${title}</h1>
	<form:form commandName="person"
		onsubmit="return validateRegForm(this);">
<%-- 		<form:errors path="*" cssClass="errorblock" element="div" /> --%>
		<table>
			<tr>
				<td>${message}</td>
			</tr>
			<tr>
				<td><form:input path="firstName" placeholder="Enter first name" />
				</td>
			</tr>
			<tr>
				<td class="errorblock"><label for="firstName"
					id="firstNameError" class="error"></label></td>
				<form:errors path="firstName" cssClass="error" />

			</tr>
			<tr>
				<td><form:input path="lastName" placeholder="Enter last name" />
				</td>
			</tr>
			<tr>
				<td class="errorblock"><label for="lastName" id="lastNameError"
					class="error"></label></td>
				<form:errors path="lastName" cssClass="error" />

			</tr>

			<tr>
				<td><form:input path="birthdate" placeholder="Enter birthdate" />
				</td>
			</tr>
			<tr>
				<td class="errorblock"><label for="birthdate"
					id="birthdateError" class="error"></label></td>
				<form:errors path="birthdate" cssClass="error" />

			</tr>

			<tr>
				<td><form:input path="email" placeholder="Enter email" /></td>
			</tr>
			<tr>
				<td class="errorblock"><label for="email" id="emailError"
					class="error"></label></td>
				<form:errors path="email" cssClass="error" />

			</tr>

			<tr>
				<td><form:input path="password" placeholder="Enter password" /></td>
			</tr>
			<tr>
				<td class="errorblock"><label for="password" id="passwordError"
					class="error"></label></td>
				<form:errors path="password" cssClass="error" />

			</tr>
			<tr>
				<td><input id="passwordRepeat" placeholder="Repeat password" /></td>
			</tr>

			<tr>
				<td class="errorblock"><label for="passwordRepeat"
					id="passwordRepeatError" class="error"></label></td>

			</tr>

			<!-- 			<tr> -->
			<%-- 				<td><form:errors path="email" cssClass="error" /></td> --%>

			<!-- 			</tr> -->

			<tr>
				<td><input type="submit" value="Submit" class="input"></td>
			</tr>
		</table>
	</form:form>

	<script src="<spring:url value="js/validateRegForm.js"/>"
		type="text/javascript" /></script>

</body>
</html>