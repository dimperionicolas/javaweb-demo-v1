<%@page import="model.Odontologo"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="components/editupdatestart.jsp"%>


<div class="text-center">
	<h1 class="h4 text-gray-900 mb-4">Editar odontólogo</h1>
</div>
<%
Odontologo odontoToEdit = (Odontologo) request.getSession().getAttribute("odontoToEdit");
%>

<form class="user" action="odontologo" method="POST">
	<div class="form-group row">
		<div class="col-sm-6 mb-3 mb-sm-0">
			<input type="text" class="form-control form-control-user" id="dni"
				placeholder="DNI" name="dni" value="<%=odontoToEdit.getDni()%>">
		</div>
	</div>
	<div class="form-group row">
		<div class="col-sm-6 mb-3 mb-sm-0">
			<input type="text" class="form-control form-control-user" id="nombre"
				name="nombre" placeholder="Nombre"
				value="<%=odontoToEdit.getNombre()%>">
		</div>
		<div class="col-sm-6">
			<input type="text" class="form-control form-control-user"
				id="apellido" name="apellido" placeholder="Apellido"
				value="<%=odontoToEdit.getApellido()%>">
		</div>
	</div>
	<div class="form-group row">
		<div class="col-sm-6 mb-3 mb-sm-0">
			<input type="text" class="form-control form-control-user"
				id="telefono" name="telefono" placeholder="Teléfono"
				value="<%=odontoToEdit.getTelefono()%>">
		</div>
		<div class="col-sm-6">
			<input type="text" class="form-control form-control-user"
				id="direccion" name="direccion" placeholder="Dirección"
				value="<%=odontoToEdit.getDireccion()%>">
		</div>

	</div>
	<div class="form-group row">
		<div class="col-sm-6 mb-3 mb-sm-0">
			<input type="date" class="form-control form-control-user"
				id="fechanac" name="fechanac" placeholder="Fecha nacimiento"
				value="<%=odontoToEdit.getFecha_nac()%>">
		</div>
	</div>
	<div class="form-group">
		<input type="text" class="form-control form-control-user"
			id="especialidad" name="especialidad" placeholder="Especialidad"
			value="<%=odontoToEdit.getEspecialidad()%>">
	</div>
	<div class="form-group">
		<input type="text" class="form-control form-control-user"
			id="horario" name="hora_inicio"
			placeholder="Inicio del horario laboral"
			value="<%=odontoToEdit.getHorario().getHorario_inicio()%>">
	</div>
	<div class="form-group">
		<input type="text" class="form-control form-control-user"
			id="horario" name="hora_fin" placeholder="Fin del horario labora"
			value="<%=odontoToEdit.getHorario().getHorario_fin()%>">
	</div>


	<input type="hidden" name="_method" value="UPDATE"> <input
		type="hidden" name="id_editar" value="<%=odontoToEdit.getId()%>">


	<button type="submit" class="btn btn-primary btn-user btn-block">
		Editar odontólogo</button>

</form>
<%@include file="components/editupdateend.jsp"%>
