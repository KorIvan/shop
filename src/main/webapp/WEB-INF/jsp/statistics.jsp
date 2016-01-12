<%@ include file="header.jsp"%>


<div align="center">
		<h2>Current total income</h2>
		<table class="table"
			style="width: 30%; font-size: 17px; background: #FFFFCC; border-radius: 20px;">
			<thead>
			</thead>
			<tbody>
				<tr>
					<td>${totalIncome}</td>
				</tr>
			</tbody>
		</table>
	</div>
<div align="center">
		<h2>Top clients</h2>
		<table class="table"
			style="width: 30%; font-size: 17px; background: #FFFFCC; border-radius: 20px;">
			<thead>
				<th>Client</th>
				<th>Income</th>
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
	</div>
<div align="center">
		<h2>Top products</h2>
		<table class="table"
			style="width: 500px; font-size: 17px; background: #FFFFCC; border-radius: 20px;">
			<thead>
				<th>Product</th>
				<th>Sold amount</th>
			</thead>
			<tbody>
				<c:forEach items="${topProducts}" var="product">
					<tr>
						<td><a
							href="<spring:url value="/product/${product.key.id}"/>">${product.key.name}</a></td>
						<td>${product.value}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<p></p>
	</div>

	<%@ include file="footer.jsp"%>