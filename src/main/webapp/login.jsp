<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">
<title>Consultorio</title>
<!-- Font Awesome -->
<link
	href="${pageContext.request.contextPath}/webjars/font-awesome/6.5.1/css/all.min.css"
	rel="stylesheet" type="text/css">
<link
	href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
	rel="stylesheet">
<!-- SB Admin 2 CSS -->
<link
	href="${pageContext.request.contextPath}/webjars/startbootstrap-sb-admin-2/4.1.3/css/sb-admin-2.min.css"
	rel="stylesheet">
</head>
<body class="bg-gradient-primary">

	<div class="container">
		<div class="row justify-content-center ">
			<div
				class="card o-hidden border-0 shadow-lg my-5 col-xl-6 col-lg-6 col-md-6">
				<div class="text-center col-xl-12 col-lg-12 col-md-12 p-5">
				
					<div class="text-center ">
						<h1 class="h4 text-gray-900 mb-4">Bienvenido</h1>
					</div>
					<form class="user" action="login" method="POST">
						<div class="form-group">
							<input type="text" class="form-control form-control-user"
								id="username" name="username"
								placeholder="Ingrese su nombre de usuario">
						</div>
						<div class="form-group">
							<input type="password" name="password"
								class="form-control form-control-user" id="password"
								placeholder="Contraseña">
						</div>
						<button type="submit" class="btn btn-primary btn-user btn-block">Ingresar</button>
					</form>
					
					
				</div>
			</div>
		</div>
	</div>
<!-- Bootstrap core JavaScript -->
<script
	src="${pageContext.request.contextPath}/webjars/jquery/3.6.0/jquery.min.js"></script>
<script
	src="${pageContext.request.contextPath}/webjars/bootstrap/4.6.0/js/bootstrap.bundle.min.js"></script>

<!-- Core plugin JavaScript -->
<script
	src="${pageContext.request.contextPath}/webjars/jquery-easing/1.4.1/jquery.easing.min.js"></script>
<!-- Custom scripts for all pages -->
<script
	src="${pageContext.request.contextPath}/webjars/startbootstrap-sb-admin-2/4.1.3/js/sb-admin-2.min.js"></script>
</body>
</html>