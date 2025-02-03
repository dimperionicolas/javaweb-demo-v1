<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="components/editupdatestart.jsp"%>

<h1 class="h3 mb-2 text-gray-800">Lista de usuarios</h1>
<p class="mb-4">A continuación podrá visualizar la lista de usuarios
	registrados.</p>
<div class="card shadow mb-4">
	<div class="card-header py-3">
		<h6 class="m-0 font-weight-bold text-primary">Usuarios</h6>
	</div>
	<div class="card-body">
		<div class="table-responsive">
			<table class="table table-bordered" id="dataTable" >
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
					Object userListObjUT = request.getSession().getAttribute("userList");
					List<UsuarioDTO> userListUT = new ArrayList<>();
					if (userListObjUT instanceof List<?> rawList) {
						for (Object item : rawList) {
							if (item instanceof UsuarioDTO) {
						userListUT.add((UsuarioDTO) item);
							} else {
						throw new IllegalStateException("Elemento no es de tipo OdontoDTO");
							}
						}
					} else {
						System.out.println("Error al recibir la lista");
					}
					for (UsuarioDTO userUT : userListUT) {
					%>
					<tr>
						<td><%=userUT.getId()%></td>
						<td><%=userUT.getNombre()%></td>
						<td><%=userUT.getRol()%></td>
						<td style="display: flex;">
							<form name="editar" action="usuario" method="POST">
								<!-- esto es para mandar el codigo al servlet -->
								<button type="submit" class="btn btn-primary btn-user btn-block"
									style="margin-left: 5px;">
									<i class="fas fa-pencil-alt"></i> Editar
								</button>
								<input type="hidden" name="_method" value="PATCH"> <input
									type="hidden" name="id_editar" value="<%=userUT.getId()%>">
								<!-- esto es para mandar el codigo al servlet -->
							</form>

							<form name="eliminar" action="usuario" method="POST">
								<button type="submit" class="btn btn-primary btn-user btn-block"
									style="background-color: red; margin-right: 5px;">
									<i class="fas fa-trash-alt"></i> Eliminar
								</button>
								<input type="hidden" name="_method" value="DELETE"> <input
									type="hidden" name="id_eliminar" value="<%=userUT.getId()%>">
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


<%@include file="components/editupdateend.jsp"%>
