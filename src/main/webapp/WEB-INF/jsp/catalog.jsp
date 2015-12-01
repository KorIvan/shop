<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${title}</title>
<%-- <link href="<spring:url value="/css/addNewProd.css"/>" rel="stylesheet"> --%>
</head>
<body>
<div align="center"><h1>${title}</h1>
<form:form commandName="cart.cart" name="products">

<table>
<thead>

</thead>
<tbody>
<tr>
<td>
<select id="categories"></select>
</td>
</tr>
</tbody>
<tr>
<th>Product name</th>
<th>Put to cart</th>
</tr>
<tbody id="properties">
</tbody>

</table>
<input type="button" value="Send to cart" id="send" name="action"/>
</form:form>
</div>
<script type="text/javascript"
		src="http://code.jquery.com/jquery-2.1.4.min.js"></script>
<script type="text/javascript" >
var product;
var amount;
$('#send').click(function(){
console.log(product[0]);
var tempAmount;	amount=0;product=null;
for (var i=0; i<products.length;i++){
	tempAmount=$('#prod'+products[i].id).val();

	if(tempAmount!=0){
		amount=tempAmount;
		product=products[i];
		tempAmount=0;	
		  sendAjax();
	}
}
});
 
function sendAjax() {
 
$.ajax({ 
    url: "<spring:url value="addToCart.html"/>", 
    type: 'POST', 
    dataType: 'json', 
    data: JSON.stringify({ product: product, amount: parseInt(amount)}),
    contentType: 'application/json',
    mimeType: 'application/json',
    success: function(data) { 
        alert(data.id + " " + data.name);
    },
//     error:function(data,status,er) { 
//         alert("error: "+data.name+" status: "+status+" er:"+er);
//     }
});
}
</script>
		<script type="text/javascript" >
		var products;
	$(document).ready(
			function() {
				$.getJSON('<spring:url value="categories.json"/>', {
					ajax : 'true'
				}, function(data) {
					var html = '<option id="selectCategory" value="">--Select category--</option>';
					var len = data.length;
					for (var i = 0; i < len; i++) {
						html += '<option value="' + data[i].id + '">'
								+ data[i].name + '</option>';
					}
					html += '</option>';

					$('#categories').html(html);
				});
			});
	$("#categories").change(
			function() {
				var index = $(this)[0].value;
				$.getJSON('<spring:url value="category/' + (index) + '.json"/>',
						{
							ajax : 'true'
						}, function(data) {
							var html = '';
							products=data;
							var len = products.length;
							for (var i = 0; i < len; i++) {
								html += '<tr><td><label>' + products[i].name+ '</label></td><td><input id="prod'+products[i].id+'" type="number" min="0" max="100" value=""></td></tr>';
							}
							html += '';
							globalData=data;
							$('#properties').html(html);
						});

			});
		
	</script>

</body>


</html>