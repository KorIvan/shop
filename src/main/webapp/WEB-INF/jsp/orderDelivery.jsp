<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Order purchasing</title>
<link href="<spring:url value="/css/menu.css"/>" rel="stylesheet">

</head>
<body>

	<div align="center">
		<h1>${title}</h1>
		<div>${message}</div>
		<form:form commandName="order" onsubmit="return validateForm(this);">
			<table>
				<%-- 				<c:forEach items="${order.orderItems}" var="item" begin="0" --%>
				<%-- 					varStatus="i"> --%>
				<!-- 					<tr> -->
				<%-- 						<td><form:hidden cssClass="springInput" readonly="true" --%>
				<%-- 								path="orderItems[${i.index}].product.name" /></td> --%>
				<%-- 						<td><form:hidden cssClass="springAmount" readonly="true" --%>
				<%-- 								path="orderItems[${i.index}].price" /></td> --%>
				<%-- 						<td><form:hidden cssClass="springAmount" readonly="true" --%>
				<%-- 								path="orderItems[${i.index}].amount" /></td> --%>
				<%-- 						<td><form:hidden path="orderItems[${i.index}].product" /> --%>
				<%-- 						<td><form:hidden path="orderItems[${i.index}].product.weight" /> --%>
				<%-- 						<td><form:hidden path="orderItems[${i.index}].product.bulk" /> --%>
				<%-- 						<td><form:hidden --%>
				<%-- 								path="orderItems[${i.index}].product.category" /> --%>
				<%-- 						<td><form:hidden --%>
				<%-- 								path="orderItems[${i.index}].product.currentPrice" /> --%>
				<!-- 					</tr> -->

				<%-- 				</c:forEach> --%>
				<tr>
					<td>Order status:</td>
					<td><form:input readonly="true" cssClass="springInput"
							path="status" /></td>
				</tr>
				<tr>

<%-- 					<td><form:hidden cssClass="springAmount" readonly="true" --%>
<%-- 							path="cost" /></td> --%>
				</tr>
				<tr>
					<td>Payment method:</td>
					<td><form:select cssClass="springSelect" path="payMethod">
							<form:option value="" label="*** Select Option ***" />
							<form:options items="${paymentMethod}" />
						</form:select></td>
				</tr>
				<tr>
					<td></td>
					<td class="errorblock"><label for="payMethod" id="payError"
						class="error"></label></td>
					<form:errors path="payMethod" cssClass="error" />

				</tr>
				<tr>
					<td>Delivery method:</td>
					<td><form:input readonly="true" cssClass="springInput"
							path="deliveryMethod" />
							 <%-- 					<form:select cssClass="springSelect" path="deliveryMethod"> --%>
						<%-- 							<form:option value="" label="*** Select Option ***" /> --%>
						<%-- 							<form:options items="${deliveryMethod}" /> --%> <%-- 						</form:select></td> --%>
				</tr>

				<tr>
<%-- 					<td><form:hidden path="id" /> --%>
<%-- 					<td><form:hidden path="client" /> --%>
<%-- 					<td><form:hidden path="paid" /> --%>
<%-- 					<td><form:hidden path="creationDate" /></td> --%>


				</tr>
				<tr>
					<td>Delivery date</td>
					<td><form:input cssClass="springInput" path="deliveryDate" /></td>
				</tr>
				<tr>
					<td></td>
					<td class="errorblock"><label for="deliveryDate"
						id="deliveryDateError" class="error"></label></td>
					<form:errors path="deliveryDate" cssClass="error" />

				</tr>

				<tr>
					<td></td>
					<td><form:select cssClass="springSelect" path="address">
							<option value="0">Previous addresses</option>

							<c:forEach items="${addresses}" var="address" begin="0"
								varStatus="i">
								<form:option value="${address.id}">${address.zip},
									${address.country}, ${address.city}, ${address.street} str, bld
									${address.building}, apt ${address.apartment}</form:option>
							</c:forEach>
						</form:select></td>
				</tr>
				<tr>
					<td></td>
					<td class="errorblock"><label for="address" id="addressError"
						class="error"></label></td>
					<form:errors path="address" cssClass="error" />

				</tr>
				<tr>
					<td><input type="submit" id="cancelOrder" value="Cancel order"
						name="action" onclick="return areYouSure();" /></td>

					<td><input type="submit" value="Next" name="action"
						class="input"></td>
				</tr>

			</table>
		</form:form>
		<div></div>
	</div>
	<script type="text/javascript">
		function validateForm(form) {
			var date = form.deliveryDate.value;
			var datePattern = /^\d{2}\/\d{2}\/\d{4}$/;
			var dateBool = false;
			document.getElementById("deliveryDateError").innerHTML = "";
			if (date == null || !date.match(datePattern)) {
				document.getElementById("deliveryDateError").innerHTML = "Select delivery date as mm/dd/yyyy!";
			} else {
				dateBool = true;
			}

			var pay = form.payMethod.value;
			var payBool = false;
			document.getElementById("payError").innerHTML = "";
			if (pay == "UNKNOWN") {
				document.getElementById("payError").innerHTML = "You can't choose UNKNOWN now!";
			} else {
				payBool = true;
			}

			var address = Number(form.address.value);
			var addressBool = false;
			console.log(address);
			document.getElementById("addressError").innerHTML = "";
			if (address === 0) {
				document.getElementById("addressError").innerHTML = "Select select address or create new one.";
			} else {
				addressBool = true;
			}
			console.log("addressbool=" + addressBool);

			if (dateBool && addressBool && payBool)
				return true;
			else
				return false;
		}
	</script>
</body>
</html>