
<%@ include file="header.jsp"%>

<div align="center">
	<h1>${title}</h1>
</div>
<div align="center">
	<%-- 	<form:form commandName="order"> --%>
	<form:form method="POST" commandName="order">
		<input type="hidden" name="${_csrf.parameterName}"
			value="${_csrf.token}" />
		<div class="message" align="center">${message}</div>

		<!-- 			onsubmit="return validateRegForm(this);"> -->
		<%-- 		<form:errors path="*" cssClass="errorblock" element="div" /> --%>
		<div>
			Order status:
						<form:input readonly="true" cssClass="springInput" path="status" />
		</div>
		<%-- 		<form:hidden path="address" /> --%>
		<%-- 		<form:hidden path="client" /> --%>
		<%-- 		<form:hidden path="commentsForOrder" /> --%>
		<%-- 		<form:hidden path="cost" /> --%>
		<%-- 		<form:hidden path="creationDate" /> --%>
		<%-- 		<form:hidden path="deliveryDate" /> --%>
		<%-- 		<form:hidden path="deliveryMethod" /> --%>
		<%-- 		<form:hidden path="id" /> --%>
		<%-- 		<form:hidden path="orderItems" /> --%>
		<%-- 		<form:hidden path="paid" /> --%>
		<%-- 		<form:hidden path="payMethod" /> --%>
		<%-- 		<form:hidden path="status" /> --%>

		<table>
			<tr>

			</tr>

			<thead>
			<thead>
				<th>Product</th>
				<th>Price</th>
				<th>Amount</th>
			</thead>
			<c:forEach items="${order.orderItems}" var="item">
				<tr>
					<td>${item.product.name}</td>

					<td>${item.price}</td>
					<td>${item.amount}</td>

				</tr>

			</c:forEach>
			<tr>
				<td>Total cost: <%-- <form:hidden path="cost"/> --%>
				</td>
				<td>${order.cost}</td>
			</tr>
			<tr>
					<td>Payment method:</td>
					<td><form:select cssClass="springSelect" path="payMethod">
							<form:option value="" label="*** Select Option ***" />
							<form:options items="${payMethod}" />
						</form:select></td>
				</tr>
				<tr>
					<td>Delivery method:</td>
					<td><form:select cssClass="springSelect" path="deliveryMethod">
							<form:option value="" label="*** Select Option ***" />
							<form:options items="${deliveryMethod}" />
						</form:select></td>
				</tr>
			
			
<!-- 			<tr> -->
<!-- 				<td>Payment method:</td> -->
<!-- 				<td><select name="payMethod"> -->
<%-- 						<c:forEach items="${paymentMethod}" var="method"> --%>
<%-- 							<option value="${method}">${method}</option> --%>
<%-- 						</c:forEach> --%>
<!-- 				</select></td> -->
<%-- 										<form:option value="" label="*** Select Option ***" /> --%>
<%-- 										<form:options items="${payMethod}" /> --%>
<!-- 			</tr> -->
<!-- 			<tr> -->
<!-- 				<td>Delivery method:</td> -->

<!-- 				<td><select name="deliveryMethod"> -->
<%-- 												<form:option value="" label="*** Select Option ***" /> --%>
<%-- 												<form:options items="${deliveryMethod}" /> --%>
<!-- 				</select></td> -->
<!-- 			</tr> -->
<!-- 			<tr> -->
				<%-- 					<td><form:hidden path="id" /> --%>
				<%-- 					<td><form:hidden path="client.id" /> --%>
				<%-- 					<td><form:hidden path="paid" />  --%>
				<%-- 					<td><form:hidden path="deliveryDate" /></td> --%>
				<%-- 					<td><form:hidden path="payMethod" /> --%>
				<%-- 					<td><form:hidden path="creationDate.value" /></td> --%>
				<%-- 					<td><form:hidden path="address" /></td> --%>


			</tr>
			<tr>
				<!-- 					<td><input type="submit" id="cancelOrder" value="Cancel order" -->
				<!-- 						name="action" onclick="return areYouSure();" /></td> -->

				<td><input type="submit" value="Next" name="action"
					class="input"></td>
				<td><a href="<spring:url value="/client/cancelOrder"/>">Cancel Order</a>
				<input type="button" value="Cancel" name="action"
					class="input" onclick="return areYouSure();"></td>
			</tr>
		</table>
		<%-- 	</form:form> --%>
	</form:form>
</div>
<script type="text/javascript">
	function areYouSure() {
		alert("Do you really want to cancel order?");
			}
</script>
<%@ include file="footer.jsp"%>