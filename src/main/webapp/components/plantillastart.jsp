<%@page import="DTO.UsuarioDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
<%@include file="header.jsp"%>
<body id="page-top">

	<%
	UsuarioDTO loggedUserPS = (UsuarioDTO) request.getSession().getAttribute("loggedUser");
	if (loggedUserPS == null) {
		response.sendRedirect("login.jsp");
	}
	%>

	<div id="wrapper">
		<%@include file="sidebar.jsp"%>
		<div id="content-wrapper" class="d-flex flex-column">
			<!-- Main Content -->
			<div id="content">
				<!-- Topbar -->
				<%@include file="topbar.jsp"%>
				<!-- End of Topbar -->
				<!-- Begin Page Content -->
				<div class="container-fluid">
					<div style="padding: 10px">