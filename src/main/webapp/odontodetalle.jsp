
<%@page import="model.Odontologo"%>
<%@page import="model.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="components/editupdatestart.jsp"%>

<div class="text-center">
	<h1 class="h4 text-gray-900 mb-4">Detalle odontólogo</h1>
</div>

<form class="user" action="odontologo" method="POST">
	<div class="form-group row">
		<div class="col-sm-6 mb-3 mb-sm-0">
			<input type="text" class="form-control form-control-user" id="dni"
				placeholder="DNI" name="dni" value="aaaaaaaaaaaaaaaaaaaaaaaa">
		</div>
		<div class="col-sm-6">
			<input type="text" class="form-control form-control-user"
				id="usuario" name="usuario" placeholder="Usuario">
		</div>
	</div>
	<div class="form-group row">
		<div class="col-sm-6 mb-3 mb-sm-0">
			<input type="text" class="form-control form-control-user" id="nombre"
				name="nombre" placeholder="Nombre">
		</div>
		<div class="col-sm-6">
			<input type="text" class="form-control form-control-user"
				id="apellido" name="apellido" placeholder="Apellido">
		</div>

	</div>
	<div class="form-group row">
		<div class="col-sm-6 mb-3 mb-sm-0">
			<input type="text" class="form-control form-control-user"
				id="telefono"name="telefono" placeholder="Teléfono">
		</div>
		<div class="col-sm-6">
			<input type="text" class="form-control form-control-user"
				id="direccion"name="direccion" placeholder="Dirección">
		</div>

	</div>
	<div class="form-group row">
		<div class="col-sm-6 mb-3 mb-sm-0">
			<input type="text" class="form-control form-control-user"
				id="fechanac"name="fechanac" placeholder="Fecha nacimiento">
		</div>
	</div>
	<div class="form-group">
		<input type="email" class="form-control form-control-user"
			id="especialidad"name="especialidad" placeholder="Especialidad">
	</div>
	<div class="form-group">
		<input type="email" class="form-control form-control-user"
			id="horario"name="horario" placeholder="horario">
	</div>

	<button type="submit" class="btn btn-primary btn-user btn-block">
		Cargar odontólogo</button>
</form>

<%@include file="components/editupdateend.jsp"%>
