<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${title}</title>
</head>
<body>
<h1>${title}</h1>
	<script type="text/javascript">
		$("#categories").change(
				function() {
					var index = $(this)[0].value;
					$.getJSON('<spring:url value="category/'+(index-1)+'.json"/>', {
						ajax : 'true'
					}, function(data) {
						var html = '';
						var category = data[index - 1];
						var attributes = category.attributesForCategory;
						var len = attributes.length;
						for (var i = 0; i < len; i++) {
							html += '<label>' + attributes[i].name
									+ '</label><input value=""/>';
						}
						html += '';

						$('#properties').html(html);
					});

				});
	</script>

</body>
</html>