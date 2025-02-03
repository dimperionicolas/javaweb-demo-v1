<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!-- Sidebar -->
<ul
	class="navbar-nav bg-gradient-primary sidebar sidebar-dark accordion"
	id="accordionSidebar">

	<!-- Sidebar - Brand -->
	<a
		class="sidebar-brand d-flex align-items-center justify-content-center"
		href="#">
		<div class="sidebar-brand-icon rotate-n-15">
			<i class="fas fa-solid fa-tooth"></i>
		</div>
		<div class="sidebar-brand-text mx-3">Consultorio</div>
	</a>

	<!-- Divider -->
	<hr class="sidebar-divider my-0">

	<!-- Nav Item - Dashboard -->
	<li class="nav-item active"><a class="nav-link" href="#"> <i
			class="fas fa-solid fa-bars"></i> <span> Menú</span></a></li>

	<!-- Divider -->
	<hr class="sidebar-divider">

	<!-- Heading -->
	<div class="sidebar-heading">Gestión</div>


	<li class="nav-item"><a class="nav-link collapsed" href="#"
		data-toggle="collapse" data-target="#collapseOdonto"
		aria-expanded="true" aria-controls="collapseTwo"> <i
			class="fas fa-solid fa-hospital-user"></i> <span>Odontólogos</span>
	</a>
		<div id="collapseOdonto" class="collapse" aria-labelledby="headingTwo"
			data-parent="#accordionSidebar">
			<div class="bg-white py-2 collapse-inner rounded">
				<h6 class="collapse-header">Acciones:</h6>
				<a class="collapse-item" href="odontologo">Ver odontólogos.</a>
				<a class="collapse-item" href="odontoalta.jsp">Alta odontólogos.</a>
			</div>
		</div></li>


	<li class="nav-item"><a class="nav-link collapsed" href="#"
		data-toggle="collapse" data-target="#collapsePaciente"
		aria-expanded="true" aria-controls="collapseUtilities"> <i
			class="fas fa-solid fa-user"></i> <span>Pacientes</span>
	</a>
		<div id="collapsePaciente" class="collapse"
			aria-labelledby="headingUtilities" data-parent="#accordionSidebar">
			<div class="bg-white py-2 collapse-inner rounded">
				<h6 class="collapse-header">Acciones:</h6>
				<a class="collapse-item" href="">Ver pacientes.</a> <a
					class="collapse-item" href="">Alta paciente.</a>
			</div>
		</div></li>





	<li class="nav-item"><a class="nav-link collapsed" href="#"
		data-toggle="collapse" data-target="#collapseUsuario"
		aria-expanded="true" aria-controls="collapseUtilities"> <i
			class="fas fa-solid fa-user"></i> <span>Usuarios</span>
	</a>
		<div id="collapseUsuario" class="collapse"
			aria-labelledby="headingUtilities" data-parent="#accordionSidebar">
			<div class="bg-white py-2 collapse-inner rounded">
				<h6 class="collapse-header">Acciones:</h6>
				<a class="collapse-item" href="usuario">Ver usuarios.</a> <a
					class="collapse-item" href="usuarioalta.jsp">Alta usuario.</a>
			</div>
		</div></li>
		
		

	<li class="nav-item"><a class="nav-link collapsed" href="#"
		data-toggle="collapse" data-target="#collapseTurnos"
		aria-expanded="true" aria-controls="collapseTwo"> <i
			class="fas fa-solid fa-hospital-user"></i> <span>Turnos</span>
	</a>
		<div id="collapseTurnos" class="collapse" aria-labelledby="headingTwo"
			data-parent="#accordionSidebar">
			<div class="bg-white py-2 collapse-inner rounded">
				<h6 class="collapse-header">Acciones:</h6>
				<a class="collapse-item" href="calendar.jsp">Ver turnos</a> <a
					class="collapse-item" href="odontoalta.jsp">Nuevos turnos</a>
			</div>
		</div></li>













	<!-- Divider -->
	<hr class="sidebar-divider">

	<!-- Sidebar Toggler -->
	<div class="text-center d-none d-md-inline">
		<button class="rounded-circle border-0" id="sidebarToggle"></button>
	</div>


</ul>