<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${title}</title>
<link href="<spring:url value="/css/addNewProd.css"/>" rel="stylesheet">

</head>
<div align="center"><h1>${title}</h1>
<body>
	<form:form commandName="${cart}" method="post">
		<table>
			<%-- <form:input path="cart.itemList[0].amount"/> --%>
			<thead>
				<th>Product</th>
				<th>Amount</th>
			</thead>
			<tbody id="cart">
<%-- 				<c:forEach var="cartItem" items="${cart.itemList}" begin="0" --%>
<%-- 					varStatus="i"> --%>
<!-- 					<tr> -->
<%-- 						<td>${cartItem.product.name}</td> --%>
<%-- 						<td><input type="number" id="${cartItem.product.id}"value="${cartItem.amount}" min="0" max="100" onchange="changeAmount(this);"/></td> --%>
<!-- 					</tr> -->
<%-- 				</c:forEach>													 --%>
			
			
			</tbody>
		</table>
	</form:form>
	</div>
	<script type="text/javascript"
		src="http://code.jquery.com/jquery-2.1.4.min.js"></script>
		<script type="text/javascript" >
		var itemList;
	$(document).ready(
			function() {
				$.getJSON('<spring:url value="cartContent.json"/>', {
					ajax : 'true'
				}, function(data) {
					itemList=data.itemList;
					var html = '';
					var len = itemList.length;
					for (var i = 0; i < len; i++) {
						html += '<tr><td><label>' + itemList[i].product.name+ '</label></td><td><input id="'+i+'" type="number" min="0" max="100" value="'+itemList[i].amount+'" onchange="changeAmount(this);"></td></tr>';
						console.log(itemList[i]);
					}
					html += '';

					$('#cart').html(html);
				});
			});
		
	</script>
	<script type="text/javascript">
	function changeAmount(input){
		var newAmount=input.value;
		var product=itemList[input.id].product;
		console.log(product);
		console.log(newAmount);
		sendAjax(product, newAmount);
	}
	function sendAjax(product,newAmount) {
		 
		$.ajax({ 
		    url: "<spring:url value="cart.html"/>", 
		    type: 'POST', 
		    dataType: 'json', 
		    data: JSON.stringify({ product: product, amount: parseInt(newAmount)}),
		    contentType: 'application/json',
		    mimeType: 'application/json',
		    success: function(data) { 
		        alert(data.id + " " + data.name);
		    },
//		     error:function(data,status,er) { 
//		         alert("error: "+data.name+" status: "+status+" er:"+er);
//		     }
		});
		}
	</script>
</body>
</html>