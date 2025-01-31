
<%@page import="DTO.OdontoDTO"%>
<%@page import="model.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="components/editupdatestart.jsp"%>
<%
OdontoDTO odontodetalle = (OdontoDTO) request.getSession().getAttribute("odontodetalle");
%>


<div class="text-center">
	<h1 class="h4 text-gray-900 mb-4">Detalle odontólogo</h1>
</div>

<form class="user" action="odontologo" method="POST">
	<div class="form-group row">
		<div class="col-sm-6 mb-3 mb-sm-0">
			<input type="text" class="form-control form-control-user" id="dni"
				placeholder="Odontologo" name="dni"
				value="<%=odontodetalle.getNombre()%> <%=odontodetalle.getApellido()%>">
		</div>
	</div>
	<div class="form-group">
		<input type="email" class="form-control form-control-user"
			id="especialidad" name="especialidad" placeholder="Especialidad">
	</div>


	TURNOS PACIENTES



	<button type="submit" class="btn btn-primary btn-user btn-block">
		Cargar odontólogo</button>
</form>

<%@include file="components/editupdateend.jsp"%>
