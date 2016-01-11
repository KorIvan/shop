<%@ include file="header.jsp"%>

<form method="post">
	<p>
		Date from: <input type="text" id="from">
	</p>
	<p>
		Date to: <input type="text" id="to">
	</p>
	<input type="submit" value="Get income" /> <input type="hidden"
		name="${_csrf.parameterName}" value="${_csrf.token}" />

</form>

<div>
	<h2>Current total income</h2>
	<p>${totalIncome}</p>
</div>
<div>
	<h2>Top clients</h2>
	<table class="table" style="width: 30%; font-size: 17px; background: #FFFFCC ;border-radius: 20px;">
		<thead>
			<th>Product</th>
			<th>Income</th>
		</thead>
		<tbody>
		<c:forEach items="${topClients}" var="client">
		<tr><td>${client.value.email}</td><td>${client.key}</td></tr>
		</c:forEach>
		</tbody>
	</table>
</div>
<div style="float: left;">
	<h2>Top products</h2>
		<table class="table" style="width: 500px; font-size: 17px; background: #FFFFCC ;border-radius: 20px;">
		<thead>
			<th>Client</th>
			<th>Income</th>
		</thead>
		<tbody>
		<c:forEach items="${topProducts}" var="product">
		<tr><td><a href="<spring:url value="/product/${product.key.id}"/>">${product.key.name}</a></td><td>${product.value}</td></tr>
		</c:forEach>
		</tbody>
	</table>
	<p></p>
</div>
<script>
	$(function() {
		$("#from").datepicker();
		$("#to").datepicker();
		
	});
</script>
<%@ include file="footer.jsp"%>