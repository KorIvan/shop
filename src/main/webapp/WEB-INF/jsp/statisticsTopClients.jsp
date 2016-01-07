<%@ include file="header.jsp"%>
<table>
	<thead>
		<th>Client</th>
		<th>Income from client</th>
	</thead>
	<tbody>
		<c:forEach items="${topClients}" var="client">
			<tr>
				<td>${client.value.email}</td>
				<td>${client.key}</td>
			</tr>
		</c:forEach>
	</tbody>
</table>
<%@ include file="footer.jsp"%>