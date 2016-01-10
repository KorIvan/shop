<%@ include file="header.jsp"%>
<div align="center" class="w3-padding w3-white notranslate">
	<h2>${product.name}</h2>
	<table class="table" style="width: 50%; font-size: 17px; background: #FFFFCC ;border-radius: 20px;">
		<tbody>
			<tr>
				<td>Price</td>
				<td>${product.currentPrice}</td>
			</tr>
			<tr>
				<td>Category</td>
				<td>${product.category.name}</td>
			</tr>
			<c:forEach items="${product.properties}" var="property">
				<tr>
					<td>${property.attributes.name}</td>
					<td>${property.description}</td>
				</tr>
			</c:forEach>
			<tr>
				<td>Weight</td>
				<td>${product.weight}</td>
			</tr>
			<tr>
				<td>Available amount</td>
				<td>${product.storage.amount}</td>
			</tr>
			<tr>
				<td>About product</td>
				<td>${product.description}</td>
			</tr>
		</tbody>
	</table>

</div>
<%@ include file="footer.jsp"%>