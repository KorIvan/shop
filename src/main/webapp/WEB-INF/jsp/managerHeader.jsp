<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${title}</title>
</head>
<body>
	<nav class="navbar navbar-default">
	<div class="container-fluid">
		<!-- Brand and toggle get grouped for better mobile display -->
		<div class="navbar-header">
			<button type="button" class="navbar-toggle collapsed"
				data-toggle="collapse" data-target="#bs-example-navbar-collapse-1"
				aria-expanded="false">
				<span class="sr-only">Toggle navigation</span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="<spring:url value="/"/>">Entry
				point </a>
		</div>

		<!-- Collect the nav links, forms, and other content for toggling -->
		<div class="collapse navbar-collapse"
			id="bs-example-navbar-collapse-1">
			<ul class="nav navbar-nav">
				<li><a
					href="<spring:url value="http://localhost:8080/shop/catalog.html"/>">Catalog</a>
				</li>

			</ul>
			<ul class="nav navbar-nav">
					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown" role="button" aria-haspopup="true"
						aria-expanded="false">Product<span class="caret"></span></a>
						<ul class="dropdown-menu">
							<li><a
								href="<spring:url value="http://localhost:8080/shop/manager/category.html"/>">Make
									new</a></li>
							<li role="separator" class="divider"></li>

							<li role="separator" class="divider"></li>
						</ul></li>
					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown" role="button" aria-haspopup="true"
						aria-expanded="false">Category<span class="caret"></span></a>
						<ul class="dropdown-menu">
							<li><a
								href="<spring:url value="http://localhost:8080/shop/manager/addCategory.html"/>">Make
									new</a></li>

						</ul></li>
						<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown" role="button" aria-haspopup="true"
						aria-expanded="false">Orders<span class="caret"></span></a>
						<ul class="dropdown-menu">
							<li><a
								href="<spring:url value="http://localhost:8080/shop/manager/orders.html"/>">Order list</a></li>
						</ul></li>
			</ul>

			<ul class="nav navbar-nav navbar-right">



				<li class="active">
						<a><c:out value="MANAGER" /> <span class="sr-only">(current)</span>
						</a>
					
						<li class="active"><a
							href="http://localhost:8080/shop/manager/logout.html">Log out
								<span class="sr-only">(current)</span>
						</a></li>
				<li><span class="sr-only">(current)</span></li>
			</ul>
		</div>
	</div>
	<!-- /.container-fluid --> </nav>
	<script src="<spring:url value="js/bootstrap.min.js"/>"
		integrity="sha384-0mSbJDEHialfmuBBQP6A4Qrprq5OVfW37PRR3j5ELqxss1yVqOtnepnHVP9aJ7xS"
		crossorigin="anonymous"></script>

