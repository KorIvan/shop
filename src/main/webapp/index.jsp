<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>Index page</title>
<link type="text/css" rel="stylesheet" href="css/mycss.css">
</head>

<body>
	<h1>Hello ugly world!</h1>
</body>
<table>
	<thead>
	</thead>
	<tbody>
		<tr>
			<a href="<spring:url value="/catalog.html"/>">Catalog</a>
		</tr>
	</tbody>
</table>
</html>