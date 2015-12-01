<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>


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
</body>
</html>