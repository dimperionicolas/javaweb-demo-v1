<%@page import="model.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="components/plantillastart.jsp"%>

<div class="card o-hidden border-0 shadow-lg">
	<div class="p-5">
		<div class="container-fluid">




			<div class="text-center">
				<h1 class="h4 text-gray-900 mb-4">Editar usuario</h1>
			</div>
			<%
			Usuario userToEdit = (Usuario) request.getSession().getAttribute("userToEdit");
			boolean puedeEditar = (boolean) request.getSession().getAttribute("puedeEditar");
			%>

			<form class="user" action="usuario" method="POST">
				<div class="form-group row">
					<div class="col-sm-6 mb-3 mb-sm-0">
						<input type="text" class="form-control form-control-user"
							name="nombreusuario" id="nombreusuario"
							placeholder="Nombre usuario"
							value="<%=userToEdit.getNombre_usuario()%>">
					</div>
					<div class="col-sm-6">
						<input type="text" class="form-control form-control-user"
							name="rol" id="rol" placeholder="Rol" value="<%=userToEdit.getRol()%>">
					</div>

				</div>

				<!-- 				Si es el mismo usuario logueado se puede cambiar -->
				<div class="form-group row">
					<div class="col-sm-6 mb-3 mb-sm-0">
						<input type="password" class="form-control form-control-user"
							name="password" id="password" 
							<%if (puedeEditar) {%>
							value="<%=userToEdit.getContrasenia()%>" <%} else {%>
							disabled="disabled" value="******" <%}%>
							placeholder="Password">
					</div>
					<div class="col-sm-6">
						<input type="password" class="form-control form-control-user"
							name="repeatpassword" id="repeatpassword" 
							<%if (puedeEditar) {%>
							value="<%=userToEdit.getContrasenia()%>" <%} else {%>
							disabled="disabled" value="******" <%}%>
							placeholder="Repetir Password">
					</div>
				</div>
				<input type="hidden" name="_method" value="UPDATE"> <input
					type="hidden" name="id_editar" value="<%=userToEdit.getId_usuario()%>">
				<button type="submit" class="btn btn-primary btn-user btn-block">
					Guardar modificación</button>
			</form>



			<hr>
		</div>
	</div>
</div>
<%@include file="components/plantillaend.jsp"%>
