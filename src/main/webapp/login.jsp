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
<link href="vendor/fontawesome-free/css/all.min.css" rel="stylesheet"
	type="text/css">
<link
	href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
	rel="stylesheet">
<link href="css/sb-admin-2.min.css" rel="stylesheet">
</head>
<body class="bg-gradient-primary">

	<div class="container">
		<div class="row justify-content-center ">
			<div
				class="card o-hidden border-0 shadow-lg my-5 col-xl-8 col-lg-8 col-md-8">
				<div class="col-lg-8 p-5">
					<div class="text-center">
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
	<script src="vendor/jquery/jquery.min.js"></script>
	<script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
	<script src="vendor/jquery-easing/jquery.easing.min.js"></script>
	<script src="js/sb-admin-2.min.js"></script>
</body>
</html>