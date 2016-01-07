<%@ include file="header.jsp"%>
<%-- 	<c:out value="${pageContext.request.userPrincipal}"/> --%>
<c:out value="${sessionScope.cart}"/>

<div align="center">
	<h1>${title}</h1>
	<div class="message">${message}</div>
</div>
<div align="center">
	<form:form commandName="cart.cart" name="products">
		<td><select id="categories"></select></td>
		<table>
			<thead>

			</thead>
			<tbody>
				<tr>

				</tr>
			</tbody>
			<tr>
				<th>Product name</th>
				<th></th>
				<th>Price</th>
				<th></th>
				<th align="right">Put product to cart</th>
			</tr>
			<tbody id="properties">
			</tbody>

		</table>
		<security:authorize access="!hasRole('SALES_MANAGER')">
			<input type="button" value="Send to cart" id="send" name="action" />
		</security:authorize>
	</form:form>
</div>
<script type="text/javascript"
	src='<spring:url value="js/jquery-2.1.4.js"/>'></script>
<script type="text/javascript">
	var product;
	var amount;
	$('#send').click(function() {
		var tempAmount;
		amount = 0;
		product = null;
		for (var i = 0; i < products.length; i++) {
			tempAmount = $('#prod' + products[i].id).val();
			if (tempAmount != 0) {
				amount = tempAmount;
				product = products[i];
				tempAmount = 0;
				$('#prod' + products[i].id).val(0);
				sendAjax();
			}
		}
	});

	function sendAjax() {

		$.ajax({
			url : "<spring:url value="addToCart.html"/>",
			type : 'POST',
			dataType : 'json',
			data : JSON.stringify({
				product : product,
				amount : parseInt(amount)
			}),
			contentType : 'application/json',
			mimeType : 'application/json',
			success : function(data) {
				alert(data.id + " " + data.name);
			},
		//     error:function(data,status,er) { 
		//         alert("error: "+data.name+" status: "+status+" er:"+er);
		//     }
		});
	}
</script>
<script type="text/javascript">
	var products;
	$(document)
			.ready(
					function() {
						$
								.getJSON(
										'<spring:url value="categories.json"/>',
										{
											ajax : 'true'
										},
										function(data) {
											var html = '<option id="selectCategory" value="">--Select category--</option>';
											var len = data.length;
											for (var i = 0; i < len; i++) {
												html += '<option value="'
														+ data[i].id + '">'
														+ data[i].name
														+ '</option>';
											}
											html += '</option>';
											$('#categories').html(html);
										});
					});
	$("#categories")
			.change(
					function() {
						var index = $(this)[0].value;
						$
								.getJSON(
										'<spring:url value="category/'
												+ (index) + '.json"/>',
										{
											ajax : 'true'
										},
										function(data) {
											var html = '';
											products = data;
											var len = products.length;
											for (var i = 0; i < len; i++) {
												html += '<tr><td><label>'
														+ products[i].name
														+ '</label></td><td></td><td><label>'
														+ products[i].currentPrice
														+ '</label></td><td></td><td ><input id="prod'+ products[i].id+'" type="number" min="0" max="100" value=""></td></tr>';
											}
											html += '';
											globalData = data;
											$('#properties').html(html);
										});
					});
</script>
<%@ include file="footer.jsp"%>
