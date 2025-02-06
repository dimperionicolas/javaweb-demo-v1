<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@include file="components/plantillastart.jsp"%>


<h1 class="h3 mb-2 text-gray-800">Lista de días y horarios</h1>
<div class="card shadow mb-4">
	<div class="card-header py-3">
		<div class="form-group row">
			<div class="col-sm-4 mb-3 mb-sm-0">
				<h6 class="m-0 font-weight-bold text-primary">Turnos del
					odontologo:</h6>
			</div>
			<div class="dropright col-sm-4 mb-3 mb-sm-0">
				<button class="btn btn-secondary btn-sm dropdown-toggle"
					type="button" id="odontologos" data-toggle="dropdown"
					aria-haspopup="true" aria-expanded="false">Seleccione un
					odontólogo</button>
				<div class="dropdown-menu" aria-labelledby="dropdownMenuButton"
					id="odontologosList">
					<!-- Los odontólogos se cargarán dinámicamente aquí -->
				</div>
			</div>

		</div>

	</div>
	<div class="card-body">
		<div class="row">
			<div class=" col-sm-4 mb-3 mb-sm-0">
				<div class="card  container-fluid">
					<!-- Calendario (Izquierda) -->
					<div class="col-md-6 h-50 d-flex flex-column">

						<div id="calendar"></div>
					</div>
				</div>
			</div>
			<div class="col-sm-8 mb-3 mb-sm-0">
				<div class="card  container-fluid ">
					<!-- Franjas Horarias (Derecha) -->
					<div class="col-md-18 h-80 d-flex flex-column">
						<div id="timeSlots" class="list-group overflow-auto"
							style="max-height: 70vh;"></div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>


</div>

</div>
<!-- /.container-fluid Page Content -->
</div>
<!-- End of Main Content -->

<!-- Footer -->
<footer class="sticky-footer bg-white">
	<div class="container my-auto">
		<div class="copyright text-center my-auto">
			<span>Copyright &copy; Consultorio</span>
		</div>
	</div>
</footer>
<!-- End of Footer -->
</div>
</div>
<a class="scroll-to-top rounded" href="#page-top"> <i
	class="fas fa-angle-up"></i>
</a>
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

<!-- Chart.js -->
<script
	src="${pageContext.request.contextPath}/webjars/chartjs/2.9.4/chart.umd.js"></script>


<!-- Page level demo JavaScript -->
<script
	src="${pageContext.request.contextPath}/webjars/startbootstrap-sb-admin-2/4.1.3/js/demo/chart-area-demo.js"></script>
<script
	src="${pageContext.request.contextPath}/webjars/startbootstrap-sb-admin-2/4.1.3/js/demo/chart-pie-demo.js"></script>

<!-- DataTables JavaScript -->
<script
	src="${pageContext.request.contextPath}/webjars/datatables/1.10.24/js/jquery.dataTables.min.js"></script>
<script
	src="${pageContext.request.contextPath}/webjars/datatables/1.10.24/js/dataTables.bootstrap4.min.js"></script>

<!-- Page level custom scripts -->
<script
	src="${pageContext.request.contextPath}/webjars/startbootstrap-sb-admin-2/4.1.3/js/demo/datatables-demo.js"></script>

<!-- Scripts -->
<script src="${pageContext.request.contextPath}/webjars/bootstrap-datepicker/1.9.0/js/bootstrap-datepicker.min.js"></script>
<script src="${pageContext.request.contextPath}/webjars/bootstrap-datepicker/1.9.0/locales/bootstrap-datepicker.es.min.js"></script>
<!-- Custom Scripts -->
<script src="components/js/script.js"></script>


</body>
</html>