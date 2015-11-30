<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${title}</title>
</head>
<body>
<h1>${title}</h1>
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
<script type="text/javascript"
		src="http://code.jquery.com/jquery-2.1.4.min.js"></script>
	<script type="text/javascript">
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
							var products=data;
							var len = products.length;
							for (var i = 0; i < len; i++) {
								html += '<tr><td><label>' + products[i].name+ '</label></td><td><input type="number" min="0" max="100" value="0"></td></tr>';
							}
							html += '';

							$('#properties').html(html);
						});

			});
		
	</script>

</body>
</html>