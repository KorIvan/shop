	<%@ include file="header.jsp"%>
	<div align="center">
	<form action="">
		<table>
			<thead>
				<th>N</th>
				<th></th>
				<th>Client</th>
				<th>Status</th>
				<th>Payment method</th>
				<th>Delivery Method</th>
				<th>Is paid</th>
				<th>Cost</th>
				<th>Creation date</th>

			</thead>
			<tbody>
				<tr>
					<td>${message}</td>
				</tr>
				<c:forEach items="${orders}" var="order" begin="0"
					varStatus="i">

					<tr>
						<td><label>${i.index+1}</label>
						<td><a href="<spring:url value="/manager/orders/${order.id}"/>">Process</a> </td>
						<td><input readonly="true" value="${order.client.email}" /></td>
						<td><input readonly="true" value="${order.status}" /></td>
						<td><input readOnly="true" style="width: 150px;" value="${order.payMethod}" /></td>
						<td><input readonly="true" value="${order.deliveryMethod}" /></td>
						<td><input readonly="true" style="width: 50px;" value="${order.paid}" /></td>
						<td><input readonly="true" style="width: 100px;" value="${order.cost}" /></td>
						<td><input readonly="true" value="${order.creationDate}"/>
					</tr>




				</c:forEach>
			</tbody>
		</table>
		</form>
	</div>
	</body>
</html>