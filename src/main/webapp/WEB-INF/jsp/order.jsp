<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="<spring:url value="/css/addNewProd.css"/>" rel="stylesheet">
<link href="<spring:url value="/css/menu.css"/>" rel="stylesheet">

<title>${title}</title>
</head>
<body>
	<div align="center">
		<h1>${title}</h1>
		<div class="message">${message}</div>
		<form:form commandName="order">
			<!-- 			onsubmit="return validateRegForm(this);"> -->
			<%-- 		<form:errors path="*" cssClass="errorblock" element="div" /> --%>
			<div>
				Order status:
				<form:input readonly="true" cssClass="springInput" path="status" />
			</div>

			<table>
				<tr>

				</tr>

				<thead>
				<thead>
					<th>Product</th>
					<th>Price</th>
					<th>Amount</th>
				</thead>
				<form:hidden path="orderItems" />
				<c:forEach items="${order.orderItems}" var="item" begin="0"
					varStatus="i">

					<tr>

						<td><form:input cssClass="springInput" readonly="true"
								path="orderItems[${i.index}].product.name" /></td>

						<td><form:input cssClass="springAmount" readonly="true"
								path="orderItems[${i.index}].price" /></td>

						<td><form:input cssClass="springAmount" readonly="true"
								path="orderItems[${i.index}].amount" /></td>

						<td><form:input cssClass="springInput" readonly="true"
								path="orderItems[${i.index}].id" /></td>
						<%-- 						<td><form:hidden path="orderItems[${i.index}].product.id" /> --%>
						<%-- 						<td><form:hidden path="orderItems[${i.index}].product.weight" /> --%>
						<%-- 						<td><form:hidden path="orderItems[${i.index}].product.bulk" /> --%>
						<%-- 						<td><form:input --%>
						<%-- 								path="orderItems[${i.index}].product.category" /> <form:hidden --%>
						<%-- 								cssClass="springInput" readonly="true" path="orderItems" /> --%>
						<%-- 						<td><form:hidden --%>
						<%-- 								path="orderItems[${i.index}].product.currentPrice" /> --%>
					</tr>

				</c:forEach>
				<tr>

					<td>Total cost:</td>
					<td><form:input cssClass="springAmount" readonly="true"
							path="cost" /></td>
				</tr>
				<tr>
					<td>Payment method:</td>
					<td><form:select cssClass="springSelect" path="payMethod">
							<form:option value="" label="*** Select Option ***" />
							<form:options items="${paymentMethod}" />
						</form:select></td>
				</tr>
				<tr>
					<td>Delivery method:</td>
					<td><form:select cssClass="springSelect" path="deliveryMethod">
							<form:option value="" label="*** Select Option ***" />
							<form:options items="${deliveryMethod}" />
						</form:select></td>
				</tr>
				<tr>
<%-- 					<td><form:hidden path="id" /> --%>
<%-- 					<td><form:hidden path="client.id" /> --%>
<%-- 					<td><form:hidden path="paid" />  --%>
					<%-- 					<td><form:hidden path="deliveryDate" /></td> --%>
						<%-- 					<td><form:hidden path="payMethod" /> --%> <%-- 					<td><form:hidden path="creationDate.value" /></td> --%>
<%-- 					<td><form:hidden path="address" /></td> --%>


				</tr>
				<tr>
					<td><input type="submit" id="cancelOrder" value="Cancel order" name="action" onclick="return areYouSure();"/></td>

					<td><input type="submit" value="Next" name ="action" class="input"></td>
				</tr>
			</table>
		</form:form>
	</div>
	<script type="text/javascript">
	function areYouSure(){
		alert("Do you really want to cancel order?");
	}
	</script>

</body>
</html>