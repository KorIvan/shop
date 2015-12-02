<%@ include file="client.jsp"%>
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
<link href="<spring:url value="/css/registration.css"/>" rel="stylesheet">

</head>
<body>
	<div align="center"><h1>${title}</h1>
	<form:form commandName="address">
<%-- 		onsubmit="return validateAddressForm(this);"> --%>
<%-- 		<form:errors path="*" cssClass="errorblock" element="div" /> --%>
		<table>
			<tr>
				<td>${message}</td>
			</tr>
			<tr>
				<td><form:input path="zip" placeholder="Enter zip-code" />
				</td>
			</tr>
			<tr>
				<td class="errorblock"><label for="zip"
					id="zipError" class="error"></label></td>
				<form:errors path="zip" cssClass="error" />

			</tr>
			<tr>
				<td><form:input path="country" placeholder="Enter country" />
				</td>
			</tr>
			<tr>
				<td class="errorblock"><label for="country" id="countryError"
					class="error"></label></td>
				<form:errors path="country" cssClass="error" />

			</tr>

			<tr>
				<td><form:input path="city" placeholder="Enter city" />
				</td>
			</tr>
			<tr>
				<td class="errorblock"><label for="city"
					id="cityError" class="error"></label></td>
				<form:errors path="city" cssClass="error" />

			</tr>

			<tr>
				<td><form:input path="street" placeholder="Enter street" /></td>
			</tr>
			<tr>
				<td class="errorblock"><label for="street" id="streetError"
					class="error"></label></td>
				<form:errors path="street" cssClass="error" />

			</tr>

			<tr>
				<td><form:input path="building" placeholder="Enter building" /></td>
			</tr>
			<tr>
				<td class="errorblock"><label for="building" id="buildingError"
					class="error"></label></td>
				<form:errors path="building" cssClass="error" />

			</tr>
			<tr>
				<td><form:input path="apartment" placeholder="Enter apartment" /></td>
			</tr>
			<tr>
				<td class="errorblock"><label for="apartment"
					id="apartmentError" class="error"></label></td>

			</tr>

			<tr>
				<td><input type="submit" value="Submit" class="input"></td>
			</tr>
		</table>
	</form:form>
</div>
	<script src="<spring:url value="js/validateAddressForm.js"/>"
		type="text/javascript" /></script>

</body>
</html>