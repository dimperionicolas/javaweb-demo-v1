<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="components/plantillastart.jsp"%>


<div class="card o-hidden border-0 shadow-lg">
	<div class="p-5">
		<div class="text-center">
			<h1 class="h4 text-gray-900 mb-4">Alta odontólogo</h1>
		</div>

		<form class="user">
			<div class="form-group row">
				<div class="col-sm-6 mb-3 mb-sm-0">
					<input type="text" class="form-control form-control-user"
						id="dni" placeholder="DNI">
				</div>
				<div class="col-sm-6">
					<input type="text" class="form-control form-control-user"
						id="usuario" placeholder="Usuario">
				</div>

			</div>
			<div class="form-group row">
				<div class="col-sm-6 mb-3 mb-sm-0">
					<input type="text" class="form-control form-control-user"
						id="nombre" placeholder="Nombre">
				</div>
				<div class="col-sm-6">
					<input type="text" class="form-control form-control-user"
						id="apellido" placeholder="Apellido">
				</div>

			</div>
			<div class="form-group row">
				<div class="col-sm-6 mb-3 mb-sm-0">
					<input type="text" class="form-control form-control-user"
						id="telefono" placeholder="Teléfono">
				</div>
				<div class="col-sm-6">
					<input type="text" class="form-control form-control-user"
						id="direccion" placeholder="Dirección">
				</div>

			</div>
			<div class="form-group row">
				<div class="col-sm-6 mb-3 mb-sm-0">
					<input type="text" class="form-control form-control-user"
						id="fechanac" placeholder="Fecha nacimiento">
				</div>
			</div>


			<div class="form-group">
				<input type="email" class="form-control form-control-user"
					id="especialidad" placeholder="Especialidad">
			</div>
			<a href="login.html" class="btn btn-primary btn-user btn-block">
				Cargar odontólogo </a>
		</form>
		<hr>
	</div>
</div>
<%@include file="components/plantillaend.jsp"%>
