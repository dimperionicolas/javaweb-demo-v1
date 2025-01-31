
<%@page import="model.Odontologo"%>
<%@page import="DTO.OdontoDTO"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@include file="components/editupdatestart.jsp"%>

<h1 class="h3 mb-2 text-gray-800">Lista de odontólogos</h1>
<p class="mb-4">A continuación podrá visualizar la lista de
	odontólogos registrados.</p>
<div class="card shadow mb-4">
	<div class="card-header py-3">
		<h6 class="m-0 font-weight-bold text-primary">Odontólogos</h6>
	</div>
	<div class="card-body">
		<div class="table-responsive">
			<table class="table table-bordered" id="dataTable" width="100%"
				cellspacing="0">
				<thead>
					<tr>
						<th>DNI</th>
						<th>Nombre</th>
						<th>Apellido</th>
						<th>Teléfono</th>
						<th>Especialidad</th>
						<th>Fecha Nac</th>
						<th>Horario</th>
						<th>Accion</th>
					</tr>
				</thead>
				<tfoot>
					<tr>
						<th>DNI</th>
						<th>Nombre</th>
						<th>Apellido</th>
						<th>Teléfono</th>
						<th>Especialidad</th>
						<th>Fecha Nac</th>
						<th>Horario</th>
						<th>Accion</th>
					</tr>
				</tfoot>
				<tbody>
					<%
					List<OdontoDTO> odontoList = (List<OdontoDTO>) request.getSession().getAttribute("odontoList");
					for (OdontoDTO odonto : odontoList) {
					%>
					<tr>
						<td><%=odonto.getDni()%></td>
						<td><%=odonto.getNombre()%></td>
						<td><%=odonto.getApellido()%></td>
						<td><%=odonto.getTelefono()%></td>
						<td><%=odonto.getEspecialidad()%></td>
						<td><%=odonto.getFecha_nac()%></td>
						<td> de 
						<%=odonto.getHorario().getHorario_inicio()%>
						 a 
						<%=odonto.getHorario().getHorario_fin()%>
						
						</td>
						<td style="display: flex;">
							<form name="detalles" action="odontologo" method="POST">
								<button type="submit" class="btn btn-sm btn-info btn-block"
									style="margin: 2px">
									<i class="fas fa-info-circle"></i> Detalles
								</button>
								<input type="hidden" name="_method" value="DETAILS"> <input
									type="hidden" name="id_detalles" value="<%=odonto.getId()%>">
							</form>

							<form name="editar" action="odontologo" method="POST">
								<button type="submit" class="btn btn-sm  btn-primary btn-block "
									style="margin: 2px">
									<i class="fas fa-pencil-alt"></i> Editar
								</button>
								<input type="hidden" name="_method" value="PATCH"> <input
									type="hidden" name="id_editar" value="<%=odonto.getId()%>">
							</form>

							<form name="eliminar" action="odontologo" method="POST">
								<button type="submit"
									class="btn btn-sm  btn-danger btn-user btn-block"
									style="margin: 2px">
									<i class="fas fa-trash-alt"></i> Eliminar
								</button>
								<input type="hidden" name="_method" value="DELETE"> <input
									type="hidden" name="id_eliminar" value="<%=odonto.getId()%>">
							</form>

						</td>
					</tr>
					<%
					}
					%>
				</tbody>
			</table>
		</div>
	</div>
</div>


<%@include file="components/editupdateend.jsp"%>


