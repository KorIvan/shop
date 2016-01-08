<%@ include file="header.jsp"%>
<form:form commandName="product">
	<%-- <c:forEach items="properties" var="attribute"> --%>
	<%-- ${attribute.name} //// ${attribute.description} --%>
	<%-- </c:forEach> --%>
	<form:label path="name" />
	<form:input path="weight" />
	<form:label path="name">
	</form:label>

</form:form>
<div class="w3-padding w3-white notranslate">
	<table class="table">


		<tbody>
			<tr>
				<td>Name</td>
				<td>${product.name}</td>
			</tr>
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
				<tr>
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