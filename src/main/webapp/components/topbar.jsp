<%@page import="DTO.UsuarioDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!-- Topbar -->
<nav
	class="navbar navbar-expand navbar-light bg-white topbar mb-2 static-top shadow">
	<% 
	UsuarioDTO loggedUserTB = (UsuarioDTO) request.getSession().getAttribute("loggedUser");
	%>
	<ul class="navbar-nav ml-auto mr-2">
		<div class="topbar-divider d-none d-sm-block"></div>
		<li class="nav-item dropdown no-arrow"><a
			class="nav-link dropdown-toggle" href="#" id="userDropdown"
			role="button" data-toggle="dropdown" aria-haspopup="true"
			aria-expanded="false"> <span
				class="mr-2 d-lg-inline text-gray-600 small"> <%=loggedUserTB.getNombre()%>
			</span> <img class="img-profile rounded-circle" src="img/undraw_profile.svg">
		</a> 
	</ul>
</nav>