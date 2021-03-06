<%@ include file="client.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link href="<spring:url value="/css/menu.css"/>" rel="stylesheet">

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${title}</title>

</head>
<div align="center"><h1>${title}</h1>
<body>
	<form:form commandName="${cart}" method="post">
		<table>
			<%-- <form:input path="cart.itemList[0].amount"/> --%>
			<thead>
				<th>Product</th><th></th>
				<th>Price</th><th></th>
				<th>Amount of product</th>
			</thead>
			<tbody id="cart">
<!-- <a href=<spring:url value="makeOrder.html"/>> Make order</a>			 -->
			
			</tbody>
		</table>
	</form:form>
	</div>
<script type="text/javascript"
		src='<spring:url value="js/jquery-2.1.4.js"/>'></script>
		<script type="text/javascript" >
		var itemList;
	$(document).ready(
			function getCart() {
				$.getJSON('<spring:url value="cartContent.json"/>', {
					ajax : 'true'
				}, function(data) {
					itemList=data.itemList;
					var html = '';
					var len = itemList.length;
					var cost=0;
					for (var i = 0; i < len; i++) {
						html += '<tr><td><label>' + itemList[i].product.name+ '</label><td></td></td><td>'+itemList[i].product.currentPrice+'</td><td></td><td><input id="'+i+'" type="number" min="0" max="100" value="'+itemList[i].amount+'" onchange="changeAmount(this);"></td></tr>';
						console.log(itemList[i]);
						cost+=Number(itemList[i].product.currentPrice)*Number(itemList[i].amount);
						console.log(Number(itemList[i].product.currentPrice)+" "+Number(itemList[i].amount));
					}
					console.log("final cost"+cost);
					html += '<tr><td><b>Total cost</b></td><td></td><td>'+cost+'</td></tr>';

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
	var token = $("meta[name='_csrf']").attr("content");
    var header = $("meta[name='_csrf_header']").attr("content");
    $(document).ajaxSend(function(e, xhr, options) {
        xhr.setRequestHeader(header, token);
    });
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