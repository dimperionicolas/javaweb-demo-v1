<%@page import="java.util.List"%>
<%@page import="DTO.OdontoDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="components/plantillastart.jsp"%>

<div class="card o-hidden border-0 shadow-lg">
	<div class="p-5">
		<div class="container-fluid">



			<h1 class="h3 mb-2 text-gray-800">Lista de usuarios</h1>
			<p class="mb-4">A continuación podrá visualizar la lista de
				usuarios registrados.</p>

			<!-- DataTales Example -->
			<div class="card shadow mb-4">
				<div class="card-header py-3">
					<h6 class="m-0 font-weight-bold text-primary">Usuarios</h6>
				</div>
				<div class="card-body">
					<div class="table-responsive">
						<table class="table table-bordered" id="dataTable" width="100%"
							cellspacing="0">
							<thead>
								<tr>
									<th>Id</th>
									<th>Nombre</th>
									<th>Rol</th>
									<th>Accion</th>
								</tr>
							</thead>
							<tfoot>
								<tr>
									<th>Id</th>
									<th>Nombre</th>
									<th>Rol</th>
									<th>Accion</th>
								</tr>
							</tfoot>
							<tbody>
								<%
								List<OdontoDTO> odontoList = (List<OdontoDTO>) request.getSession().getAttribute("TODO");
								for (OdontoDTO odonto : odontoList) {
								%>
								<tr>
									<td><%=odonto.getId()%></td>
									<td><%=odonto.getNombre()%></td>
									<td><%=odonto.getRol()%></td>
									<td style="display: flex;">
										<form name="eliminar" action="usuario" method="POST">
											<button type="submit"
												class="btn btn-primary btn-user btn-block"
												style="background-color: red; margin-right: 5px;">
												<i class="fas fa-trash-alt"></i> Eliminar
											</button>
											<input type="hidden" name="_method" value="DELETE"> <input
												type="hidden" name="id_eliminar" value="<%=odonto.getId()%>">
											<!-- esto es para mandar el codigo al servlet -->
										</form>
										<form name="editar" action="usuario" method="POST">
											<!-- esto es para mandar el codigo al servlet -->
											<button type="submit"
												class="btn btn-primary btn-user btn-block"
												style="margin-left: 5px;">
												<i class="fas fa-pencil-alt"></i> Editar
											</button>
											<input type="hidden" name="_method" value="PATCH"> <input
												type="hidden" name="id_editar" value="<%=odonto.getId()%>">
											<!-- esto es para mandar el codigo al servlet -->
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




			<hr>
		</div>
	</div>
</div>
<%@include file="components/plantillaend.jsp"%>

