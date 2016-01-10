<%@ include file="header.jsp"%>
		<div class="message" align="center">${message}</div>

<div align="center">
	<form:form commandName="order" method="POST">
		<table >
			<thead>
			</thead>
			<tbody class="springSelect">
				<tr>
					<td style="width: 140px;">Client</td>
					<td><input readonly="true" value="${order.client.email}" /></td>
				</tr>
				<tr>
					<td>Status</td>
					<td>
						<%-- 					<c:if test="${order.paid}"> --%> <form:select
							path="status">
							<%-- 					<form:option  value="${status}" >${status}</form:option> --%>
							<form:options items="${status}" />
						</form:select> <!-- 					<td> --> <%-- 					<input readonly="true" value="${order.status}" /> --%>
						<!-- 					</td> --> <%-- 					</c:if> --%>
					</td>
				</tr>
				<tr>
					<td>Payment method</td>
					<td><input readOnly="true" value="${order.payMethod}" /></td>
				</tr>
				<tr>
					<td>Delivery method</td>
					<td><input readonly="true" value="${order.deliveryMethod}" /></td>
				</tr>

				<tr>
					<td>Is paid</td>
					<c:if test="${order.payMethod == 'EXCHANGING' }">

						<td><form:select path="paid">
								<form:option value="${order.paid}">${order.paid}</form:option>
								<c:if test="${order.paid}">
									<form:option value="false">false</form:option>
								</c:if>
								<c:if test="${!order.paid}">
									<form:option value="true">true</form:option>
								</c:if>
								<%-- 								<c:if test="${order.paid == ''}"> --%>
								<%-- 									<form:option value="true">true</form:option> --%>
								<%-- 									<form:option value="false">false</form:option> --%>
								<%-- 								</c:if> --%>
							</form:select></td>
					</c:if>
					<c:if test="${order.payMethod == 'PROVISIONING'}">
						<td><form:input readonly="true" path="paid"
								value="${order.paid}" /></td>
					</c:if>
				</tr>
				<tr>
					<td>Cost</td>
					<td><input readonly="true" value="${order.cost}" /></td>
				</tr>
				<tr>
					<td>Creation date</td>
					<td><input readonly="true" value="${order.creationDate}" />
				</tr>
				<c:if test="${order.deliveryMethod != 'SELF_DELIVERY'}">
					<tr>
						<td>Delivery date</td>
						<td><input readonly="true" value="${order.deliveryDate}" />
					</tr>
				</c:if>
			</tbody>

		</table>
		<input type="submit" value="Apply changes" />

	</form:form>
</div>
<script type="text/javascript">
	
</script>
<%@ include file="footer.jsp"%>