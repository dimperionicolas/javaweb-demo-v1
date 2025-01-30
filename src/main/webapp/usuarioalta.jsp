<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="components/editupdatestart.jsp"%>

<div class="text-center">
	<h1 class="h4 text-gray-900 mb-4">Alta usuario</h1>
</div>
<form class="user" action="usuario" method="POST">
	<div class="form-group row">
		<div class="col-sm-6 mb-3 mb-sm-0">
			<input type="text" class="form-control form-control-user"
				name="nombreusuario" id="nombreusuario" placeholder="Nombre usuario">
		</div>
		<div class="col-sm-6">
			<input type="text" class="form-control form-control-user" name="rol"
				id="rol" placeholder="Rol">
		</div>
	</div>
	<div class="form-group row">
		<div class="col-sm-6 mb-3 mb-sm-0">
			<input type="password" class="form-control form-control-user"
				name="password" id="password" placeholder="Password">
		</div>
		<div class="col-sm-6">
			<input type="password" class="form-control form-control-user"
				name="repeatpassword" id="repeatpassword"
				placeholder="Repetir Password">
		</div>
	</div>
	<button type="submit" class="btn btn-primary btn-user btn-block">
		Crear usuario</button>
</form>

<%@include file="components/editupdateend.jsp"%>
