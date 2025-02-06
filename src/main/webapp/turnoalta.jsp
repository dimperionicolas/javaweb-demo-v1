
<%@page import="model.Odontologo"%>
<%@page import="model.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="components/editupdatestart.jsp"%>

<div class="text-center">
	<h1 class="h4 text-gray-900 mb-4">Nuevo turno</h1>
</div>

<form class="user" action="turnos" method="POST">
	<div class="form-group row">
		<div class="col-sm-6 mb-3 mb-sm-0">
			<input type="number" class="form-control form-control-user" id="dni"
				placeholder="DNI" name="dni">
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
				id="telefono" name="telefono" placeholder="Teléfono">
		</div>
		<div class="col-sm-6">
			<input type="text" class="form-control form-control-user"
				id="direccion" name="direccion" placeholder="Dirección">
		</div>
	</div>
	<div class="form-group row">
		<div class="col-sm-6 mb-3 mb-sm-0">
			<input type="text" class="form-control form-control-user"
				id="motivoconsulta" name="motivoconsulta" placeholder="Motivo">
		</div>
	</div>
	<div class="form-group row">
		<div class="col-sm-6 mb-3 mb-sm-0">
			<input type="date" class="form-control form-control-user"
				id="fechanac" name="fechanac" placeholder="Fecha nacimiento">
		</div>
	</div>
	<div class="form-group">
		<label for="tiposangre">Tipo de sangre</label> <select
			class="form-control form-control-user" id="tiposangre"
			name="tiposangre">
			<option value="">Seleccione un tipo de sangre</option>
			<option value="A+">A+</option>
			<option value="A-">A-</option>
			<option value="B+">B+</option>
			<option value="B-">B-</option>
			<option value="AB+">AB+</option>
			<option value="AB-">AB-</option>
			<option value="O+">O+</option>
			<option value="O-">O-</option>
		</select> <label>Tiene obra social</label>
		<div>
			<div class="form-check form-check-inline">
				<input class="form-check-input" type="radio" name="tieneos"
					id="tieneos_si" value="Sí"> <label class="form-check-label"
					for="tieneos_si">Sí</label>
			</div>
			<div class="form-check form-check-inline">
				<input class="form-check-input" type="radio" name="tieneos"
					id="tieneos_no" value="No"> <label class="form-check-label"
					for="tieneos_no">No</label>
			</div>
		</div>
	</div>
	<div class="form-group">
		<input type="text" disabled="disabled"
			class="form-control form-control-user" id="horario" name="horario"
			placeholder="Valor del horario turno"> <input type="text"
			class="form-control form-control-user" id="fecha" name="fecha"
			placeholder="valor fecha turno">
	</div>


	<button type="submit" class="btn btn-primary btn-user btn-block">
		Confirmar turno</button>
</form>

<%@include file="components/editupdateend.jsp"%>
