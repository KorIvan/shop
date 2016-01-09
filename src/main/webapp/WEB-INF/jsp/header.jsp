<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta name="_csrf" content="${_csrf.token}" />
<!-- default header name is X-CSRF-TOKEN -->
<meta name="_csrf_header" content="${_csrf.headerName}" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="http://localhost:8080/shop/css/bootstrap.css"
	rel="stylesheet" />
<link href="http://localhost:8080/shop/css/menu.css" rel="stylesheet">
<link href="/shop/css/bootstrap-datepicker.css" rel="stylesheet">
<title>${title}</title>
<script type="text/javascript"
	src="http://localhost:8080/shop/js/jquery-2.1.4.js"></script>
<script type="text/javascript"
	src="http://localhost:8080/shop/js/bootstrap.min.js"></script>
<script type="text/javascript" src="/shop/js/bootstrap-datepicker.js"></script>
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
				<security:authorize var="loggedIn" access="isAuthenticated()" />
				
				<c:if test="${!loggedIn}">
					<li><a
						href="<spring:url value="http://localhost:8080/shop/registration.html"/>">Registration</a></li>
				</c:if>

				<li><a
					href="<spring:url value="http://localhost:8080/shop/catalog.html"/>">Catalog</a>
				</li>
			</ul>
			<ul class="nav navbar-nav">
				<!-- 				<li><a href="#">Link</a></li> -->\
				<security:authorize access="hasRole('CLIENT')">
					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown" role="button" aria-haspopup="true"
						aria-expanded="false">Addresses<span class="caret"></span></a>
						<ul class="dropdown-menu">
							<li><a href="<spring:url value="http://localhost:8080/shop/client/addAddress"/>">Add
									new</a></li>
							<li><a href="<spring:url value="http://localhost:8080/shop/client/allAddresses"/>">All
									addresses</a></li>

							<li><a href="#">Edit</a></li>

						</ul></li>

					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown" role="button" aria-haspopup="true"
						aria-expanded="false">Orders<span class="caret"></span></a>
						<ul class="dropdown-menu">
							<li><a href="http://localhost:8080/shop/client/makeOrder">Make
									new</a></li>
							<li><a href="http://localhost:8080/shop/client/orderHistory">View
									history</a></li>

						</ul></li>

				</security:authorize>

				<security:authorize access="hasRole('SALES_MANAGER')">
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
								href="<spring:url value="http://localhost:8080/shop/manager/orders.html"/>">Order
									list</a></li>

						</ul></li>
				</security:authorize>
			</ul>

			<ul class="nav navbar-nav navbar-right">
				<security:authorize access="!hasRole('SALES_MANAGER')">
					<li><a href="http://localhost:8080/shop/cart.html">Cart</a></li>
				</security:authorize>

				<!-- 				<li class="dropdown"><a href="#" class="dropdown-toggle" -->
				<!-- 					data-toggle="dropdown" role="button" aria-haspopup="true" -->
				<!-- 					aria-expanded="false">Dropdown <span class="caret"></span></a> -->
				<!-- 					<ul class="dropdown-menu"> -->
				<!-- 						<li><a href="#">Action</a></li> -->
				<!-- 						<li><a href="#">Another action</a></li> -->
				<!-- 						<li><a href="#">Something else here</a></li> -->
				<!-- 						<li role="separator" class="divider"></li> -->
				<!-- 						<li><a href="#">Separated link</a></li> -->
				<!-- 					</ul> -->
				<!-- 					</li> -->
				<li class="active"><c:if test="${loggedIn}">
						<li class="active"><a
							href="http://localhost:8080/shop/logout.html">Log out <span
								class="sr-only">(current)</span></a></li>
					</c:if> <%-- 					<security:authentication var="role" property="principal.authorities"/> --%>
					<c:if test="${!loggedIn}">
						<li class="active"><a> GUEST <span class="sr-only">(current)</span>
						</a></li>
					</c:if> <security:authorize access="hasRole('SALES_MANAGER')">
						<li class="active"><a>Manager<span class="sr-only">(current)</span>
						</a></li>
					</security:authorize> 
					<security:authorize access="hasRole('CLIENT')">
						<li class="active"><a>Client 				
<%-- 						<security:authentication property="principal.displayName" /> --%>
<span class="sr-only">(current)</span>
						</a></li>
					</security:authorize> <c:if test="${!loggedIn }">
						<li class="active"><a href="<spring:url value="login"/>">
								Log in <span class="sr-only">(current)</span>
						</a></li>
					</c:if></li>

				<li><span class="sr-only">(current)</span></li>
			</ul>
		</div>
		<!-- /.navbar-collapse -->
	</div>
	<!-- /.container-fluid --> </nav>