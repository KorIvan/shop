<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>Index page</title>
<link href="<spring:url value="/css/addNewProd.css"/>" rel="stylesheet">
<style type="text/css">a {	color: yellow;
	text-transform: uppercase;
	font-size: 40px;
}</style>
</head>

<body>
	<h1></h1>
</body>

<div align=center>
	<div>><a href="<spring:url value="/catalog.html"/>">Guest</a></div>
		<div>><a href="<spring:url value="/client"/>">Client</a></div>
		<div>><a href="<spring:url value="/manager"/>">Manager</a></div>
	
</div>
</html>