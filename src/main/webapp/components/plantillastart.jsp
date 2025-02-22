<%@page import="DTO.UsuarioDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
Object loggedUserObj = request.getSession().getAttribute("loggedUser");
if (loggedUserObj == null) {
    response.sendRedirect(request.getContextPath() + "/login.jsp");
    return; 
}
if (!(loggedUserObj instanceof UsuarioDTO)) {
    session.invalidate(); 
    response.sendRedirect(request.getContextPath() + "/login.jsp");
    return;
}
UsuarioDTO loggedUserPS = (UsuarioDTO) loggedUserObj;

%>

<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">

<title>Consultorio</title>

<!-- Font Awesome -->
<link
	href="${pageContext.request.contextPath}/webjars/font-awesome/6.5.1/css/all.min.css"
	rel="stylesheet" type="text/css">
<!-- Bootstrap 4 -->
<link
	href="${pageContext.request.contextPath}/webjars/bootstrap/4.6.0/css/bootstrap.min.css"
	rel="stylesheet">
<!-- Bootstrap Datepicker (para el calendario) -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/webjars/bootstrap-datepicker/1.9.0/css/bootstrap-datepicker.min.css">

<link
	href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
	rel="stylesheet">

<!-- SB Admin 2 CSS -->
<link
	href="${pageContext.request.contextPath}/webjars/startbootstrap-sb-admin-2/4.1.3/css/sb-admin-2.min.css"
	rel="stylesheet">

<!-- DataTables CSS -->
<link
	href="${pageContext.request.contextPath}/webjars/datatables/1.10.24/css/dataTables.bootstrap4.min.css"
	rel="stylesheet">

<!-- Custom CSS -->
<link href="components/css/style.css" rel="stylesheet">



</head>
<body id="page-top">
	<div id="wrapper">
		<%@include file="sidebar.jsp"%>
		<div id="content-wrapper" class="d-flex flex-column">
			<!-- Main Content -->
			<div id="content">
				<!-- Topbar -->
				<nav
					class="navbar navbar-expand navbar-light bg-white topbar mb-2 static-top shadow">
					<ul class="navbar-nav ml-auto mr-2">
						<div class="topbar-divider d-none d-sm-block"></div>
						<li class="nav-item dropdown no-arrow"><a
							class="nav-link dropdown-toggle" href="#" id="userDropdown"
							role="button" data-toggle="dropdown" aria-haspopup="true"
							aria-expanded="false"> <span
								class="mr-2 d-lg-inline text-gray-600 small"> <%=loggedUserPS.getNombre()%>
							</span> <img class="img-profile rounded-circle"
								src="img/undraw_profile.svg">
						</a>
					</ul>
				</nav>
				<!-- End of Topbar -->
				<!-- Begin Page Content -->
				<div class="container-fluid">
					<div style="padding: 10px">